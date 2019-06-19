package kr.or.ddit.purchasingTeam.stockManage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Ord_ListVO;
import kr.or.ddit.vo.ReleaseVO;
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
public interface IStockManageService {
	
	public List<StockVO> retrieveStockList(PagingVO<StockVO> pagingVO);
	
	public long retrieveStockCount(PagingVO<StockVO> pagingVO);
	
	/**
	 * 엑셀 출력위한 조회 (pagingVO에서 처리하지만 page수는 무시)
	 * @param pagingVO
	 * @return
	 */
	public List<StockVO> retrieveExcelList(PagingVO<StockVO> pagingVO);
	
	
}
