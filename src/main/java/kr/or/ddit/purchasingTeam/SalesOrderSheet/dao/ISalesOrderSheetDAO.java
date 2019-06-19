package kr.or.ddit.purchasingTeam.SalesOrderSheet.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;
import kr.or.ddit.vo.SalesProfitVO;

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
@Repository
public interface ISalesOrderSheetDAO {
	
	/**
	 * 영업팀 주문서 상세조회
	 * @param sale_ord_code
	 * @return
	 */
	public Sale_Ord_ListVO selectSalesOrderSheet(String sale_ord_code);
	
	public long selectSalesOrderCount(PagingVO<Sale_Ord_ListVO> pagingVO);
	
	/**
	 * 영업팀 주문서 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<Sale_Ord_ListVO> selectSalesOrderSheetList(PagingVO<Sale_Ord_ListVO> pagingVO);
	
	/**
	 * 엑셀 출력위한 조회 (pagingVO에서 처리하지만 page수는 무시)
	 * @param pagingVO
	 * @return
	 */
	public List<Sale_Ord_ListVO> selectExcelList(PagingVO<Sale_Ord_ListVO> pagingVO);
	
}
