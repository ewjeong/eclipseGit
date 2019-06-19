package kr.or.ddit.superManager.employeeManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PerformanceVO;

@Repository
public interface IEmployeeManageDAO {
	
	/**
	 * 사원명과 부서명으로 검색해서 리스트를 띄움
	 * @param emPaging
	 * @return
	 */
	public List<EmployeeVO> selectEmployeeList(PagingVO<EmployeeVO> pagingVO);
	
	public long selectEmployeeCount(PagingVO<EmployeeVO> pagingVO);
	
	public int updateEmployee(EmployeeVO employeeVO);
	
	
//	/**
//	 * 사원명과 부서명으로 검색해서 리스트를 띄움
//	 * @param employeeVO
//	 * @return
//	 */
//	public List<EmployeeVO> selectEmployeeFind(EmployeeVO employeeVO);
	
	/**
	 * 모달창에서 emp_no를 넘긴걸 받아와서 emp_no에 해당하는 열을 employeeVO에서 찾아냄
	 * @param employeeVO
	 * @return
	 */
	public EmployeeVO selectEmployee(String emp_no);
}
