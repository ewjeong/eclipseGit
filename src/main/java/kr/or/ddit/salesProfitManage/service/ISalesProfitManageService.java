package kr.or.ddit.salesProfitManage.service;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SalesProfitVO;
import kr.or.ddit.vo.SalesVO;
 /**
 * @author 박연욱
 * @since 2019. 5. 23.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 23.   박연욱 		      최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface ISalesProfitManageService {
//	retrieveSalesProfit
	
	/**
	 * 페이징 처리를 위해서 토탈리스트의 수를 셀때
	 * @param pagingVO
	 * @return
	 */
	public long retrieveSalesProfitCount(PagingVO<SalesProfitVO> pagingVO);
	
	/**
	 * 매출 리스트출력
	 * @param pagingVO
	 * @return
	 */
	public List<SalesProfitVO> retrieveSalesProfitList(PagingVO<SalesProfitVO> pagingVO);
	
	/**
	 * 엑셀 출력위한 조회 (pagingVO에서 처리하지만 page수는 무시)
	 * @param pagingVO
	 * @return
	 */
	public List<SalesProfitVO> retrieveSalesProfitExcelList(PagingVO<SalesProfitVO> pagingVO);
	
	/**
	 * 매출 조회
	 */
	public List<SalesProfitVO> retrieveEmployeeProfitList();
	
	/**
	 * 실적이 있는 월 조회
	 */
	public List<SalesProfitVO> retrieveProfitMonth();

	/**
	 * 매출실적이 있는 사원 조회
	 * @return
	 */
	public List<SalesProfitVO> retrieveProfitEmployee();
	
	/**
	 * 월별 순이익 조회
	 * @return
	 */
	public List<SalesProfitVO> retrievePureProfit();
	
	/**
	 * 월별 매출 조회
	 * @return
	 */
	public List<SalesProfitVO> retrieveMonthProfit();
	
	
	
}
