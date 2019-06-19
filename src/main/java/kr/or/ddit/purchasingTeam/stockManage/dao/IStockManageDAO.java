package kr.or.ddit.purchasingTeam.stockManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Ord_ListVO;
import kr.or.ddit.vo.ReleaseVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;
import kr.or.ddit.vo.StockVO;
  
/**
 * @author 정은우
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
@Repository
public interface IStockManageDAO {

	public List<StockVO> selectStockList(PagingVO<StockVO> pagingVO);
	
	public long selectStockCount(PagingVO<StockVO> pagingVO);
	
	/**
	 * 엑셀 출력위한 조회 (pagingVO에서 처리하지만 page수는 무시)
	 * @param pagingVO
	 * @return
	 */
	public List<StockVO> selectExcelList(PagingVO<StockVO> pagingVO);
	
	
	
}
