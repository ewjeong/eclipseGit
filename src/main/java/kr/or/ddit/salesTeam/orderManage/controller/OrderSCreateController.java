package kr.or.ddit.salesTeam.orderManage.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Insert;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.InsertHint;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.salesTeam.orderManage.service.IOrderSManageService;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Sale_Eprod_ListVO;
import kr.or.ddit.vo.Sale_EstVO;
import kr.or.ddit.vo.Sale_Est_ListVO;
import kr.or.ddit.vo.Sale_OprodVO;
import kr.or.ddit.vo.Sale_OrdVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;

@Controller
public class OrderSCreateController {

	@Inject
	IOrderSManageService service;

	// 입력 양식 가져오기
/*	@RequestMapping("/salesTeam/orderManage/orderInsert")
	public ModelAndView getOrderForm(
			@RequestParam(name = "sale_est_no") String sale_est_noStr, 
			ModelAndView model
	) {
		
		int sale_est_no = 0;
		if (StringUtils.isNumeric(sale_est_noStr)) {
			sale_est_no = Integer.parseInt(sale_est_noStr);
		}
		//한건의 견적 VO 받아서 보내주기(jsp쪽으로)
		Sale_Est_ListVO sale_est_listVO = service.retrieveSalesEst(sale_est_no);
		model.addObject("sale_est_listVO", sale_est_listVO);
		
		//여러 건의 견적상품 리스트 받아서 보내주기 (jsp쪽으로)
		List<Sale_Eprod_ListVO> sale_est_list = service.retrieveSalesEstEprodList(sale_est_no);
		model.addObject("sale_est_list", sale_est_list);
		
		model.setViewName("sales/order/orderForm");
	
		return model;

	}*/

	//입력 양식과 모달 창에 리스트 띄우기
	@RequestMapping("/salesTeam/orderManage/orderInsert")
	public String estList(
		@ModelAttribute("pagingVO") PagingVO<Sale_Est_ListVO> pagingVO
		, Model model
	){
		
		List<Sale_Est_ListVO> sale_est_list = service.retrieveEstList(pagingVO);
		
		pagingVO.setDataList(sale_est_list);
		model.addAttribute("pagingVO", pagingVO);
		return "sales/order/orderForm";
	}
	
	
	
	

	@RequestMapping(value="/salesTeam/orderManage/orderInsert", method=RequestMethod.POST)
	public String post(// 파라미터로 전달해 줄 내용
			Authentication user, //사원코드
			RedirectAttributes redirectAttributes,
			@Validated(InsertHint.class) @ModelAttribute("saleOrd") Sale_OrdVO saleOrd, //validation을 위한 설정 1
			
			Errors errors //validation을 위한 설정 2
			
	){
//		int sale_est_no = 0;
//		if (StringUtils.isNumeric(sale_est_noStr)) {
//			sale_est_no = Integer.parseInt(sale_est_noStr);
//		}
//		saleOrd.setSale_est_no(sale_est_no);
//		saleOrd.setPayment(payment);
		
		List<Sale_OprodVO> oprodList = saleOrd.getSale_oprodList();
		int total_cost = 0;
		for(Sale_OprodVO sale_oprod : oprodList){
			int oprod_cost= sale_oprod.getSale_oprod_cost();
			int oprod_qty = sale_oprod.getSale_oprod_qty();
			total_cost += oprod_cost*oprod_qty;
		}
		saleOrd.setTotal_cost(total_cost);
		EmployeeVO employee = (EmployeeVO) user.getPrincipal();
		String sale_ord_emp_id = employee.getEmp_id();
		saleOrd.setSale_ord_emp_id(sale_ord_emp_id); //로그인한 아이디
		
		
		ServiceResult result = null;
		String msg = null;

		String payment = saleOrd.getPayment();
		if("현금".equals(payment)){
			payment = "0";
		}else if("카드".equals(payment)){
			payment = "1";
		}
		saleOrd.setPayment(payment);
		
		String view = null;
		
		if(!errors.hasErrors()){
			result = service.createSalesOrder(saleOrd);
			service.modifyOrdCompl(saleOrd.getSale_est_no());
			if(ServiceResult.FAILED.equals(result)){
				msg = "서버 오류, 잠시 후 다시 확인해주세요";
				view = "/salesTeam/orderManage/orderInsert";
			}else if(ServiceResult.OK.equals(result)){
				msg = "OK";
				view = "redirect:/salesTeam/orderManage/orderList";
			}
		}else{
			msg = "none";
		}
		
		
		redirectAttributes.addFlashAttribute("message", msg);
		
		
		return view;
	}
	
	

	// 비동기처리라면 이렇게한다.
	//**여기서는 jsp쪽에서 객체를 꺼내는 것이 아니라 문자열 하나를 꺼내는 것이므로 @PathVariable을 사용할것이다.(GET 방식) 
	//버튼을 누르면 비동기로 견적서에 해당하는 내용들이 나타난다. 비동기처리면 VO를 리턴하고, 동기면 String을 리턴한다
	 
	 @RequestMapping(value="/salesTeam/orderManage/orderInsert/{inputEstNo}", produces="application/json;charset=UTF-8")
	 @ResponseBody 
	 public Sale_Est_ListVO ajaxGetOrdEst ( // @RequestBody String inputEstNo //jsp에 객체를 꺼낼 때
			 @PathVariable String inputEstNo //여기서는 객체가 아니므로 이렇게 꺼낸다 
	 ){ 
		 int sale_est_no = 0; 
		 if(StringUtils.isNumeric(inputEstNo)){ 
			 sale_est_no = Integer.parseInt(inputEstNo); 
		 }		
		 
		 Sale_Est_ListVO sale_est_listVO = service.retrieveSalesEst(sale_est_no); 
		 
		 return sale_est_listVO; 
	 }
	 
	 
	
}