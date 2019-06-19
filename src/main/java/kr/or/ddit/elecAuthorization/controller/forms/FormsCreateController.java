package kr.or.ddit.elecAuthorization.controller.forms;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.elecAuthorization.service.IElecManageService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.Elec_FormVO;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class FormsCreateController {
	@Inject
	IElecManageService service;
	
	@RequestMapping(value="/elecAuthorization/forms/formsInsert", method=RequestMethod.GET)
	public String getForm(){
		return "elec/forms/formsForm";
	}
	
	@RequestMapping(value="/elecAuthorization/forms/formsInsert", method=RequestMethod.POST)
	public String createForm(
			 @ModelAttribute(name="form") Elec_FormVO form
			 , Errors errors
			 , Model model
			 , Authentication user
			){
		String view = "";
		String msg = null;
		// 로그인한 유저 셋팅
		EmployeeVO loginUser = (EmployeeVO) user.getPrincipal();
		form.setElec_form_writer(loginUser.getEmp_id());
		
		if(!errors.hasErrors()){
			ServiceResult result = service.createForm(form);
			
			if(ServiceResult.OK.equals(result)){
				view = "redirect:/elecAuthorization/forms/formsList";
			} else if (ServiceResult.FAILED.equals(result)) {
				view = "elec/forms/formsForm";
				msg = "서버 오류, 잠시 뒤 다시 확인해 주세요.";
			}
		} else { // 검증 불통
			view = "elec/forms/formsForm";
			msg = "필수 파라미터 누락";
		}
		model.addAttribute("message", msg);
		return view;
	}
}
