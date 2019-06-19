package kr.or.ddit.purchasingTeam.estimateManage.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.InsertHint;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.estimateManage.service.IEstimatePManageService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Er_ListVO;
import kr.or.ddit.vo.Pur_EstVO;
import kr.or.ddit.vo.Pur_OrdVO;
import kr.or.ddit.vo.ReleaseVO;
import kr.or.ddit.vo.SelectPurErListVO;

@Controller
@RequestMapping("/purchasingTeam/estimateManage/estimateInsert")
public class EstimatePCreateController {
	
	@Inject
	IEstimatePManageService service;   
	
	//입력양식과 모달창에 띄울 견적요청서 리스트
	@RequestMapping()
	public String erList(
			@ModelAttribute("pagingVO") PagingVO<SelectPurErListVO> pagingVO
			, Model model){
		
		List<SelectPurErListVO> erList = service.retrieveErList(pagingVO);
		pagingVO.setDataList(erList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "purchase/estimate/estimateForm";
		
	}
	
	//견적서 선택한걸 넘겨주는 역할
	@RequestMapping(value="/{inputErNo}", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Pur_Er_ListVO ajaxGetErList(
			@PathVariable String inputErNo
			){
		
		String pur_er_no = inputErNo;
		Pur_Er_ListVO purErList = service.retrievePurEr(pur_er_no);
		
		return purErList;
	}
	
	
	//insert
	@RequestMapping(method=RequestMethod.POST)
	public String post(
			Pur_EstVO purEst,
			Model model,
			RedirectAttributes redirectAttributes
//			@Validated(InsertHint.class) @ModelAttribute("purEstVO") Pur_EstVO purEstVO,
//			Errors errors
			){
		
		
		String payment = purEst.getPayment();
		if("현금".equals(payment)){
			payment = "0";
		}else if("카드".equals(payment)){
			payment = "1";
		}
		purEst.setPayment(payment);
		
		
		String view = null;
		String message = null;
//		if(!errors.hasErrors()){
			ServiceResult result = service.createPurEstimate(purEst);
			
			if(ServiceResult.OK.equals(result)){
				view = "redirect:/purchasingTeam/estimateManage/estimateList";
				message ="OK";
				redirectAttributes.addFlashAttribute("message", message);
			} else {
				view = "/purchasingTeam/estimateManage/estimateInsert";
				message = "서버오류, 다시 시도해주세요";		
			}
//		}else{
//			message="none";
//			view="purchase/estimate/estimateForm";
//		}	
		model.addAttribute("message", message);
		return view;
		
	}
	
}





















