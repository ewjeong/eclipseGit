package kr.or.ddit.salesTeam.buyerManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.InsertHint;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.salesTeam.buyerManage.service.IBuyerSManageService;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.ItemVO;

@Controller
public class BuyerSCreateController {
	    
	@Inject
	IBuyerSManageService service;
	
	//화면을 받아오는 컨트롤러
	@RequestMapping(value="/salesTeam/buyerManage/buyerInsert", method=RequestMethod.GET)
	public String getBuyerForm(){
		return "sales/buyer/buyerForm";
	}
	
	//정보를 넣어주는 컨트롤러
	@RequestMapping(value="/salesTeam/buyerManage/buyerInsert",method=RequestMethod.POST)
	public String insertBuyerForm(
			Authentication auth
			,@Validated(InsertHint.class) @ModelAttribute("client") ClientVO clientVO,Errors errors 
			,Model model
			,RedirectAttributes rttr
			){
		
		String view = null;
		String msg = null;
		
		//로그인된 아이디 받아오기
		EmployeeVO employeeVO =  (EmployeeVO) auth.getPrincipal();
		String emp_id= employeeVO.getEmp_id();
		//ClientVO에 로그인된 아이디 셋팅하기
		clientVO.setCl_empid(emp_id);
		rttr.addFlashAttribute("emp_id",clientVO.getCl_empid());
		if(!errors.hasErrors()){
			//성공
			ServiceResult result = service.createSalesBuyer(clientVO);
			if(ServiceResult.OK.equals(result)){
				view = "redirect:/salesTeam/buyerManage/buyerList";
			}else{
				msg = "다시입력하세요";
				model.addAttribute("msg",msg);
				//실패
				view= "sales/buyer/buyerForm";
			}
		}else{
			msg="필수데이터 누락. 다시입력하세요.";
			view="sales/buyer/buyerForm";
		}
		model.addAttribute("msg",msg);
		return view;
		
	}
	
	//품목명 검색 ajax
	@ResponseBody
	@RequestMapping(value="/salesTeam/buyerManage/item", produces="application/json;charset=UTF-8")
	public List<ItemVO> ajaxItem(
			@RequestParam("item_name") String item_name
			){
		List<ItemVO> itemList = service.retreiveItem(item_name);
		
		return itemList;
	}
	
	
	
//	//거래처 상세조회
//	@RequestMapping(value="/salesTeam/buyerManage/detailBuyer/{cl_no}")
//	public String detailBuyer(
//			ClientVO clientVO
//			,@PathVariable("cl_no") String cl_no
//			,Model model
//			){
//		
//		clientVO = service.retrieveDetailBuyer(cl_no);
//		model.addAttribute("client",clientVO);
//		return "sales/buyer/buyerForm";
//	}
//	
	
	
}



















