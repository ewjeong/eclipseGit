package kr.or.ddit.mainPage.signUp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.InsertHint;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mainPage.signUp.service.ISignUpService;
import kr.or.ddit.vo.EmployeeVO;

@Controller	
public class SignUpController {
	
	@RequestMapping("/mainPage/signUp/signUp")
	public String signUpForm(){
		return "/main/signUp";
	}
	
	@Inject
	ISignUpService service;
	
	@RequestMapping(value="/mainPage/signUp/signUp", method=RequestMethod.POST)
	public String signUp(@Validated(InsertHint.class) @ModelAttribute("employee")EmployeeVO employee, BindingResult errors, RedirectAttributes model){
		String view = "/main/signUp";
		
		String msg = null;
		
		new EmployeeValidator().validate(employee, errors);
		
		
		//통과
		if(!errors.hasErrors()){
			ServiceResult result = service.createEmployee(employee);
			switch(result){
			case PKDUPLICATED:
				view = "/main/signUp";
				msg = "이미 등록된 사원입니다.";
				model.addAttribute("message",msg);
				break;
			
			case FAILED:
				view = "/main/signUp";
				msg = "가입에 실패하였습니다. 잠시후 다시 시도해주세요.";
				model.addAttribute("message",msg);
				break;
			
			//가입 성공	
			default:
				view = "redirect:/";
				msg = "가입이 완료되었습니다. 다시 로그인 해주세요.";
				model.addFlashAttribute("message",msg);
			}
		}else{
			model.addAttribute("message",msg);
		}
		return view;
	}
	
	
	@RequestMapping(value="mainPage/signUp/idcheck.do", method = RequestMethod.POST)
	@ResponseBody
	public String idcheck(@RequestParam("emp_id") String emp_id){
		
		int rowCnt = service.idCheck(emp_id);
		return String.valueOf(rowCnt);
	}
	
}
