package kr.or.ddit.myPage.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.UpdateHint;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.myPage.service.IMypageService;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class MypageUpdateController {
	
	@Inject
	IMypageService service;
	
	@RequestMapping("/myPage/myPageUpdate")
	public String mypageForm(
			@RequestParam(name="emp_id",required=false)String emp_id
			,Model model
			){
		EmployeeVO employee= service.retrieveMypage(emp_id);
		model.addAttribute("employee",employee);
		return "mypage/mypageForm";
	}
	
	@RequestMapping(value="/myPage/myPageUpdate",method=RequestMethod.POST)
	public String mypageFormUpdate(
			@RequestParam(name="emp_id",required=false) String emp_id
			,@RequestParam(name="emp_name",required=false) String emp_name
			,@RequestParam(name="emp_bir",required=false) String emp_bir
			,@RequestParam(name="emp_reg1",required=false) String emp_reg1
			,@RequestParam(name="emp_reg2",required=false) String emp_reg2
			,@RequestParam(name="emp_gene",required=false) String emp_gene
			,@Validated(UpdateHint.class) @ModelAttribute(name="employee") EmployeeVO employee, Errors errors
			,RedirectAttributes rttr
			){
		
		String view = null;
		String msg = null;
		employee.setEmp_id(emp_id);
		employee.setEmp_name(emp_name);
		employee.setEmp_bir(emp_bir);
		employee.setEmp_reg1(emp_reg1);
		employee.setEmp_reg2(emp_reg2);
		employee.setEmp_gene(emp_gene);
		if(!errors.hasErrors()){
			
		
			ServiceResult result = service.modifyMypage(employee);
			if(ServiceResult.OK.equals(result)){
				msg = "성공";
				rttr.addFlashAttribute("msg", msg);
				view = "redirect:/myPage/myPage";
			}else{
				msg = "회원정보 수정에 실패하셨습니다.";
				rttr.addFlashAttribute("msg", msg);
				view="/myPage/myPageUpdate";
			}
		}else{
			view="mypage/mypageForm";
			return view;
		}	
		
		return view;
	}
}
