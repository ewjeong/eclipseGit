package kr.or.ddit.purchasingTeam.stockManage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReleaseVO;
import kr.or.ddit.vo.Release_Form_ListVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;
import kr.or.ddit.vo.SelectSaleOrdListVO;

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
public interface IReleaseManageService {
	
	public ServiceResult createRelease(ReleaseVO release);
	
	public List<Sale_Ord_ListVO> retrieveReleaseList(PagingVO<Sale_Ord_ListVO> pagingVO);
	
	public long retrieveReleaseCount(PagingVO<Sale_Ord_ListVO> pagingVO);
	
    /**
     * @param sale_ord_code 영업팀 주문서 번호
     * @return
     */
	public Release_Form_ListVO retrieveSaleOrd(String sale_ord_code);
	
	/**
	 * 모달창에 띄워줄 주문서리스트를 가져올때 사용
	 */
	public List<SelectSaleOrdListVO> retrieveOrdList(PagingVO<SelectSaleOrdListVO> pagingVO);
	
	/** 
	 * 엑셀다운용
	 */
	public List<Sale_Ord_ListVO> retrieveExcelList(PagingVO<Sale_Ord_ListVO> pagingVO);

}









