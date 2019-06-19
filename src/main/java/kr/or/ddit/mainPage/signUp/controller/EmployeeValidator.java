package kr.or.ddit.mainPage.signUp.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import kr.or.ddit.vo.EmployeeVO;

public class EmployeeValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return EmployeeVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EmployeeVO employee = (EmployeeVO) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emp_id","required", "필수입력 사항입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emp_name","required", "필수입력 사항입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emp_mail","required", "필수입력 사항입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emp_hp","required", "필수입력 사항입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emp_pass","required", "필수입력 사항입니다.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emp_pass_Check","required", "필수입력 사항입니다.");
		
		if(employee.getEmp_gene() == null || employee.getEmp_gene().length() == 0) {
			errors.rejectValue("emp_gene", "select");
		}
		
		if(employee.getEmp_pass().length()<5 || employee.getEmp_pass().length()>12) {
			errors.rejectValue("emp_pass", "lengthsize","비밀번호는 5자이상 12자 이하만 가능합니다.");
		}
	}

}
