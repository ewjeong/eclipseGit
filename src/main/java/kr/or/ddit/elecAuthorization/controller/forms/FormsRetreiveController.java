package kr.or.ddit.elecAuthorization.controller.forms;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.elecAuthorization.service.IElecManageService;
import kr.or.ddit.vo.Elec_FormVO;
import kr.or.ddit.vo.PagingVO;

@Controller
public class FormsRetreiveController {
	@Inject
	IElecManageService service;
	
	@RequestMapping("/elecAuthorization/forms/formsList")
	public String getFormList(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			, Model model
			){
		PagingVO<Elec_FormVO> pagingVO = new PagingVO<>();
		long totalRecord = service.retreiveFormCount(pagingVO);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setTotalRecord(totalRecord);
		
		List<Elec_FormVO> formList = service.retreiveFormList(pagingVO);
		pagingVO.setDataList(formList);
		model.addAttribute("pagingVO", pagingVO);
		
		return "elec/forms/formsList";
	}
	
	@RequestMapping("/elecAuthorization/forms/formsView")
	public String formView(
			@RequestParam(name="what") String elec_form_code
			, Model model
			){
		Elec_FormVO form = service.retreiveForm(elec_form_code);
		model.addAttribute("form", form);
		return "elec/forms/formsView";
	}
}
