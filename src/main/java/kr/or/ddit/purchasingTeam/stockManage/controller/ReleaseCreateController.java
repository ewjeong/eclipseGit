package kr.or.ddit.purchasingTeam.stockManage.controller;

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
import kr.or.ddit.purchasingTeam.stockManage.service.IReleaseManageService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReleaseVO;
import kr.or.ddit.vo.Release_Form_ListVO;
import kr.or.ddit.vo.SelectSaleOrdListVO;

@Controller
@RequestMapping("/purchasingTeam/stockManage/releaseInsert")
public class ReleaseCreateController {
	
	@Inject
	IReleaseManageService service;

	//입력양식과 모달창에 띄울 주문서 리스트
	@RequestMapping()
	public String ordList(
			@ModelAttribute("pagingVO") PagingVO<SelectSaleOrdListVO> pagingVO
			, Model model
			){
		List<SelectSaleOrdListVO> ordList = service.retrieveOrdList(pagingVO);
		pagingVO.setDataList(ordList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "purchase/stock/releaseForm";
	}
	
	//주문서 선택한걸 넘겨주는 역할
	@RequestMapping(value="/{inputOrdNo}", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Release_Form_ListVO ajaxGetOrdList(
			@PathVariable String inputOrdNo
			){
		
		String sale_ord_code = inputOrdNo;
		Release_Form_ListVO saleOrdList = service.retrieveSaleOrd(sale_ord_code);
		
		return saleOrdList;
	}
	
	
	//insert
	@RequestMapping(method=RequestMethod.POST)
	public String post(
			@RequestParam String sale_ord_code,
			@RequestParam String rel_date,
			@RequestParam String emp_id,
			Model model,
			RedirectAttributes redirectAttributes,
			@Validated(InsertHint.class) @ModelAttribute("release") ReleaseVO release,
			Errors errors
			){
		release = new ReleaseVO();
		release.setSale_ord_code(sale_ord_code);
		release.setRel_date(rel_date);
		release.setEmp_id(emp_id);
		
		String view = null;
		String message = null;
		if(!errors.hasErrors()){
			ServiceResult result = service.createRelease(release);
			if(ServiceResult.OK.equals(result)){
				view="redirect:/purchasingTeam/stockManage/releaseList";
				message ="OK";
				redirectAttributes.addFlashAttribute("message", message);
			}else {
				view = "/purchasingTeam/stockManage/releaseForm";
				message = "서버오류, 다시 시도해주세요";	
			}
		}else{
			message="none";
			view="purchase/stock/releaseForm";
		}
		model.addAttribute("message", message);
		return view;
	}
	
	
	
}














