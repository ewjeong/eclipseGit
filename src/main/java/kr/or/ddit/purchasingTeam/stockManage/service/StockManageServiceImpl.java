package kr.or.ddit.purchasingTeam.stockManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.stockManage.dao.IStockManageDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.StockVO;

  
/**
 * @author 작성자명
 * @since 2019. 5. 14.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 14.      정은우       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class StockManageServiceImpl implements IStockManageService{

	@Inject
	IStockManageDAO stockManageDAO;

	@Override
	public List<StockVO> retrieveStockList(PagingVO<StockVO> pagingVO) {
		return stockManageDAO.selectStockList(pagingVO);
	}

	@Override
	public long retrieveStockCount(PagingVO<StockVO> pagingVO) {
		return stockManageDAO.selectStockCount(pagingVO);
	}

	@Override
	public List<StockVO> retrieveExcelList(PagingVO<StockVO> pagingVO) {
		return stockManageDAO.selectExcelList(pagingVO);
	}
}
