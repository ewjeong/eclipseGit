package kr.or.ddit.myPage.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.or.ddit.myPage.service.IMypageService;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PositionVO;

@Controller
//세션에 저장된 로그인된 아이디 가져오기
@SessionAttributes("emp_id")
public class MypageRetrieveController {
	
	@Inject
	IMypageService mypageService;
	
	@RequestMapping("/myPage/myPage")
	public String getMypage(
		 Model model
		 ,Authentication auth
			){
		//로그인된 아이디 받아오기
		EmployeeVO employeeVO = (EmployeeVO) auth.getPrincipal();
		String emp_id = employeeVO.getEmp_id();
		
		
		model.addAttribute("employee", mypageService.retrieveMypage(emp_id));
		return "mypage/mypageView";
	}
	
}
