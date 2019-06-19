package kr.or.ddit.superManager.employeeManage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PerformanceVO;

public interface IEmployeeManageService {
	public List<EmployeeVO> retrieveEmployeeList(PagingVO<EmployeeVO> pagingVO);
	
	public long retrieveEmployeeCount(PagingVO<EmployeeVO> pagingVO);
	
	public ServiceResult modifyEmployee(EmployeeVO employeeVO);
	
//	public List<EmployeeVO> retrieveEmployeeFind(EmployeeVO employeeVO);
	
	public EmployeeVO retrieveEmployee(String emp_no);
	
}
