package kr.or.ddit.superManager.EmplscheduleManage.service;

import java.util.List;

import kr.or.ddit.vo.IncomeVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReleaseVO;

public interface ISalesManageService {
	
	public List<IncomeVO> retrieveEmpIncomeList(PagingVO<IncomeVO> pagingVO);
	
	public long retrieveEmpIncomeCount(PagingVO<IncomeVO> pagingVO);
	
	public List<ReleaseVO> retrieveClient(String emp_name);

	public List<IncomeVO> retrieveEmpExcel(PagingVO<IncomeVO> pagingVO);
	
	public List<IncomeVO> retrieveEmpSales(PagingVO<IncomeVO> pagingVO);
}
