package kr.or.ddit.purchasingTeam.orderManage.controller;

import java.util.List;

import javax.inject.Inject;

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
import kr.or.ddit.purchasingTeam.orderManage.service.IOrderPManageService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Est_ListVO;
import kr.or.ddit.vo.Pur_OrdVO;
import kr.or.ddit.vo.SelectPurEstListVO;
import kr.or.ddit.vo.WareVO;

@Controller
@RequestMapping("/purchasingTeam/orderManage/orderInsert")
public class OrderPCreateController {
	
	@Inject
	IOrderPManageService service;
	
	//입력양식과 모달창에 띄울 견적서 리스트
	@RequestMapping()
	public String estList(
			@ModelAttribute("pagingVO") PagingVO<SelectPurEstListVO> pagingVO
			, Model model){
		
		List<SelectPurEstListVO> estList = service.retrieveOrdList(pagingVO);
		pagingVO.setDataList(estList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "purchase/order/orderForm";
		
	}
	
	//견적서 선택한걸 넘겨주는 역할
	@RequestMapping(value="/{inputEstNo}", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Pur_Est_ListVO ajaxGetEstList(
			@PathVariable String inputEstNo
			){
		
		String pur_est_no = inputEstNo;
		Pur_Est_ListVO purEstList = service.retrievePurEst(pur_est_no);
		
		return purEstList;
	}
	
	
	//insert
	@RequestMapping(method=RequestMethod.POST)
	public String post(
			Pur_OrdVO purOrd,
			Model model,
			RedirectAttributes redirectAttributes,
			@Validated(InsertHint.class) @ModelAttribute("pur_ord") Pur_OrdVO pur_ord,
			Errors errors
			){
		
		
		String payment = purOrd.getPayment();
		if("현금".equals(payment)){
			payment = "0";
		}else if("카드".equals(payment)){
			payment = "1";
		}
		purOrd.setPayment(payment);
		
		
		String view = null;
		String message = null;
		if(!errors.hasErrors()){
			ServiceResult result = service.createPurOrder(purOrd);
			if(ServiceResult.OK.equals(result)){
				view = "redirect:/purchasingTeam/orderManage/orderList";
				message ="OK";
				redirectAttributes.addFlashAttribute("message", message);
			} else {
				view = "/purchasingTeam/orderManage/orderInsert";
				message = "서버오류, 다시 시도해주세요";		
			}
		}else{
			message="none";
			view="purchase/order/orderForm";
		}	
		model.addAttribute("message", message);
		return view;
		
	}
	
	
}























