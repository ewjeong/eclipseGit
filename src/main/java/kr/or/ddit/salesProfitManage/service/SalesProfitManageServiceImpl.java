package kr.or.ddit.salesProfitManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.salesProfitManage.dao.ISalesProfitManageDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SalesProfitVO;

/**
 * @author 박연욱
 * @since 2019. 5. 23.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 23.    박연욱     		  최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class SalesProfitManageServiceImpl implements ISalesProfitManageService {

	@Inject
	ISalesProfitManageDAO salesProfitDAO;
	
	@Override
	public long retrieveSalesProfitCount(PagingVO<SalesProfitVO> pagingVO) {
		return salesProfitDAO.selectsalesProfitCount(pagingVO);
	}

	@Override
	public List<SalesProfitVO> retrieveSalesProfitList(PagingVO<SalesProfitVO> pagingVO) {
		return salesProfitDAO.selectSalesProfitList(pagingVO);
	}

	@Override
	public List<SalesProfitVO> retrieveSalesProfitExcelList(PagingVO<SalesProfitVO> pagingVO) {
		return salesProfitDAO.selectSalesProfitExcelList(pagingVO);
	}

	@Override
	public List<SalesProfitVO> retrieveEmployeeProfitList() {
		return salesProfitDAO.selectEmployeeProfitList();
	}

	@Override
	public List<SalesProfitVO> retrieveProfitMonth() {
		return salesProfitDAO.selectProfitMonth();
	}

	@Override
	public List<SalesProfitVO> retrieveProfitEmployee() {
		return salesProfitDAO.selectProfitEmployee();
	}

	@Override
	public List<SalesProfitVO> retrievePureProfit() {
		return salesProfitDAO.selectPureProfit();
	}

	@Override
	public List<SalesProfitVO> retrieveMonthProfit() {
		return salesProfitDAO.selectMonthProfit();
	}

	
}
