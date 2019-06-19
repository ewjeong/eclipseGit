package kr.or.ddit.superManager.employeeManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.superManager.employeeManage.dao.IEmployeeManageDAO;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class EmployeeManageServiceImpl implements IEmployeeManageService {

	@Inject
	IEmployeeManageDAO iEmployeeManageDAO;
	
	@Override
	public List<EmployeeVO> retrieveEmployeeList(PagingVO<EmployeeVO> pagingVO) {
		return iEmployeeManageDAO.selectEmployeeList(pagingVO);
	}

	@Override
	public long retrieveEmployeeCount(PagingVO<EmployeeVO> pagingVO) {
		return iEmployeeManageDAO.selectEmployeeCount(pagingVO);
	}

	@Override
	public ServiceResult modifyEmployee(EmployeeVO employeeVO) {
		ServiceResult result = null;
		int rowCnt= iEmployeeManageDAO.updateEmployee(employeeVO);
		if(rowCnt>0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public EmployeeVO retrieveEmployee(String emp_no) {
		return iEmployeeManageDAO.selectEmployee(emp_no);
	}

//	@Override
//	public List<EmployeeVO> retrieveEmployeeFind(EmployeeVO employeeVO) {
//		return iEmployeeManageDAO.selectEmployeeFind(employeeVO);
//	}



}
