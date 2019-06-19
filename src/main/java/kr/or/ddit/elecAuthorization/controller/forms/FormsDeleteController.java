package kr.or.ddit.elecAuthorization.controller.forms;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.elecAuthorization.service.IElecManageService;
import kr.or.ddit.enumpkg.ServiceResult;

@Controller
public class FormsDeleteController {
	@Inject
	IElecManageService service;
	
	//@RequestMapping("/elecAuthorization/forms/formsDelete")
//	public String deleteForm(
//			@RequestParam(name="what") String elec_form_code
//			, RedirectAttributes redirectAttributes
//			){
//		String view = null;
//		String msg = "";
//		
//		ServiceResult result = service.removeForm(elec_form_code);
//		if(ServiceResult.FAILED.equals(result)){
//			view = "elec/forms/formsView";
//			msg = "FAILED";
//		} else if(ServiceResult.OK.equals(result)){
//			view = "redirect:/elecAuthorization/forms/formsList";
//			msg = "OK";
//		}
//		redirectAttributes.addFlashAttribute("message", msg);
//		return view;
//	}
}
