package kr.or.ddit.superManager.employeeManage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.superManager.employeeManage.service.IEmployeeManageService;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class EmployUpdateController {
	
	@Inject
	IEmployeeManageService service;
	
	@RequestMapping("/superManager/employeeManage/Updateemployee/{emp_no}")
	public String getEmployeeForm(
			@PathVariable("emp_no") String emp_no
			,Model model
			,EmployeeVO employeeVO
			){
		
		employeeVO = service.retrieveEmployee(emp_no);
		model.addAttribute("emp",employeeVO);
		
		return "superManager/memberManage/memberUpdate";
	}
	
	//수정 ajax
	@ResponseBody
	@RequestMapping(value="/superManager/employeeManage/Updateemployee/{emp_no}",method=RequestMethod.POST)
	public Map<String,Object> employeeUpdate(
			@PathVariable("emp_no") String emp_no
			,@RequestParam("dep_code") String dep_code
			,@RequestParam("pos_code") String pos_code
			,@RequestParam("emp_del") String emp_del
			,EmployeeVO employeeVO
			){
		
		employeeVO.setEmp_no(emp_no);
		employeeVO.setDep_code(dep_code);
		employeeVO.setPos_code(pos_code);
		employeeVO.setEmp_del(emp_del);
		
		String msg = null;
		ServiceResult result= service.modifyEmployee(employeeVO);
		Map<String,Object> map = new HashMap<>();
		if(result.OK.equals(ServiceResult.OK)){
			msg="수정성공";
			map.put("OK", msg);
		}else if(result.FAILED.equals(ServiceResult.FAILED)){
			msg="수정실패";
			map.put("FAILED",msg);
		}
		
		return map;
	}
	
	
}
