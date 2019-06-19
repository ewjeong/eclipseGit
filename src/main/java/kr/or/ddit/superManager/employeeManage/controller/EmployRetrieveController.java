package kr.or.ddit.superManager.employeeManage.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.superManager.employeeManage.service.IEmployeeManageService;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.empPosDepVO;

@Controller
public class EmployRetrieveController {
	
	@Inject
	IEmployeeManageService service;
	
	@RequestMapping("/superManager/employeeManage/employeeList")
	public String getEmployeeForm(){
		return "superManager/memberManage/memberManageList";
	}
	
	//모달창에서 사원명,부서명,직급명에 해당하는 리스트를 검색ajax
	@ResponseBody
	@RequestMapping(value="/superManager/employeeManage/employeeList",produces="application/json;charset=UTF-8")
	public PagingVO<EmployeeVO> findEmployee(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			,@RequestParam(required=false) String searchWord
			,@RequestParam(required=false) String searchType
			,Model model
			,HttpServletResponse resp
			){
		//페이지 리스트 3개씩
		PagingVO<EmployeeVO> pagingVO = new PagingVO<EmployeeVO>(3,5);
		//검색정보 셋팅
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		//현재페이지 셋팅
		pagingVO.setCurrentPage(currentPage);
		//토탈레코드 셋팅
		long totalRecord = service.retrieveEmployeeCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		//사원리스트 셋팅
		List<EmployeeVO> empList = service.retrieveEmployeeList(pagingVO);
		pagingVO.setDataList(empList);
		
		model.addAttribute("pagingVO",pagingVO);
		return pagingVO;
	}
	
	
	//모달창에서 선택한 값중 emp_no를 파라미터로 받아 그에 해당하는 employeeVO를 반환하는 ajax
	@ResponseBody
	@RequestMapping(value="/superManager/employeeManage/empOne",produces="application/json;charset=UTF-8")	
	public EmployeeVO employeeOne(
			@RequestParam("emp_no") String emp_no
//			,Model model 
			){
		
		EmployeeVO employee = service.retrieveEmployee(emp_no);
//		model.addAttribute("employee",employee);
		return employee;
	}
	
	
	
}
