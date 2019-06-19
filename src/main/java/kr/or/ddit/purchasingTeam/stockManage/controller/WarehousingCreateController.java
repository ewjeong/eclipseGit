package kr.or.ddit.purchasingTeam.stockManage.controller;
 
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
import kr.or.ddit.purchasingTeam.stockManage.service.IWarehousingManageService;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReleaseVO;
import kr.or.ddit.vo.SelectPurOrdListVO;
import kr.or.ddit.vo.WareVO;
import kr.or.ddit.vo.Ware_Ord_ListVO;

@Controller
@RequestMapping("/purchasingTeam/stockManage/warehousingInsert")
public class WarehousingCreateController {
	
	@Inject
	IWarehousingManageService service;
	
	//입력양식과 모달창에 띄울 견적서 리스트
	@RequestMapping()
	public String ordList(
			@ModelAttribute("pagingVO") PagingVO<SelectPurOrdListVO> pagingVO
			, Model model
			){
		
		List<SelectPurOrdListVO> OrdList = service.retrieveOrdList(pagingVO);
		pagingVO.setDataList(OrdList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "purchase/stock/warehousingForm";
		
	}
	
	//발주서 선택한걸 넘겨주는 역할
	@RequestMapping(value="/{inputOrdNo}", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Ware_Ord_ListVO ajaxGetOrdList(
			@PathVariable String inputOrdNo
			){
		
		String pur_ord_code = inputOrdNo;
		Ware_Ord_ListVO purOrdList = service.retrievePurOrd(pur_ord_code);
				
		return purOrdList;
		
	}
	
	//insert
	@RequestMapping(method=RequestMethod.POST)
	public String post(
			@RequestParam String pur_ord_code,
			@RequestParam String ware_date,
			@RequestParam String emp_id,
			Model model,
			RedirectAttributes redirectAttributes,
			@Validated(InsertHint.class) @ModelAttribute("ware") WareVO ware,
			Errors errors
			){
		ware = new WareVO();
		ware.setPur_ord_code(pur_ord_code);
		ware.setWare_date(ware_date);
		ware.setEmp_id(emp_id);
		
		String view = null;
		String message = null;
		if(!errors.hasErrors()){
			ServiceResult result = service.createWarehousing(ware);
			if(ServiceResult.OK.equals(result)){
				view = "redirect:/purchasingTeam/stockManage/warehousingList";
				message ="OK";
				redirectAttributes.addFlashAttribute("message", message);
			} else {
				view = "/purchasingTeam/stockManage/warehousingInsert";
				message = "서버오류, 다시 시도해주세요";		
			}
		}else{
			message="none";
			view="purchase/stock/warehousingForm";
		}	
		model.addAttribute("message", message);
		return view;
	}
	
	
}























