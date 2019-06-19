package kr.or.ddit.superManager.EmplscheduleManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.superManager.EmplscheduleManage.dao.ISalesManageDAO;
import kr.or.ddit.vo.IncomeVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReleaseVO;

@Service
public class SalesManageServiceImpl implements ISalesManageService{

	@Inject
	ISalesManageDAO iSalesManageDAO;

	@Override
	public List<IncomeVO> retrieveEmpIncomeList(PagingVO<IncomeVO> pagingVO) {
		return iSalesManageDAO.selectEmpIncomeList(pagingVO);
	}

	@Override
	public long retrieveEmpIncomeCount(PagingVO<IncomeVO> pagingVO) {
		return iSalesManageDAO.selectEmpIncomeCount(pagingVO);
	}

	@Override
	public List<ReleaseVO> retrieveClient(String emp_name) {
		return iSalesManageDAO.selectClient(emp_name);
	}

	@Override
	public List<IncomeVO> retrieveEmpExcel(PagingVO<IncomeVO> pagingVO) {
		return iSalesManageDAO.selectEmpExcel(pagingVO);
	}

	@Override
	public List<IncomeVO> retrieveEmpSales(PagingVO<IncomeVO> pagingVO) {
		return iSalesManageDAO.selectEmpSales(pagingVO);
	}
	
	
}
