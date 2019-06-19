package kr.or.ddit.purchasingTeam.SalesOrderSheet.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.purchasingTeam.SalesOrderSheet.dao.ISalesOrderSheetDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;

/**
 * @author 작성자명
 * @since 2019. 5. 13.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 13.      정은우       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class SalesOrderSheetServiceImpl implements ISalesOrderSheetService{
	@Inject
	ISalesOrderSheetDAO salesOrderSheetDAO;
	
	/**
	 * 영업팀 주문서 상세조회
	 */
	@Override
	public Sale_Ord_ListVO retrieveSalesOrderSheet(String sale_ord_code) {
		Sale_Ord_ListVO saleOrdListVO = salesOrderSheetDAO.selectSalesOrderSheet(sale_ord_code);
		if(saleOrdListVO == null)
			throw new RuntimeException(sale_ord_code + "주문서 코드 없음");
		return saleOrdListVO;
	}
	
	@Override
	public long retrieveSalesOrderCount(PagingVO<Sale_Ord_ListVO> pagingVO) {
		return salesOrderSheetDAO.selectSalesOrderCount(pagingVO);
	}

	/**
	 * 영업팀 주문서 리스트 조회
	 */
	@Override
	public List<Sale_Ord_ListVO> retireveSalesOrderSheetList(PagingVO<Sale_Ord_ListVO> pagingVO) {
		return salesOrderSheetDAO.selectSalesOrderSheetList(pagingVO);
	}

	/**
	 * 엑셀 출력용
	 */
	@Override
	public List<Sale_Ord_ListVO> retireveExcelList(PagingVO<Sale_Ord_ListVO> pagingVO) {
		return salesOrderSheetDAO.selectExcelList(pagingVO);
	}




}
