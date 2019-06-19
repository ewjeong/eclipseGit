package kr.or.ddit.purchasingTeam.orderManage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Est_ListVO;
import kr.or.ddit.vo.Pur_OrdVO;
import kr.or.ddit.vo.Pur_Ord_ListVO;
import kr.or.ddit.vo.SelectPurEstListVO;
import kr.or.ddit.vo.SelectPurEstVO;
import kr.or.ddit.vo.SelectPurOrdListVO;

/**
 * @author 정은우
 * @since 2019. 5. 15.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 15.      정은우       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface IOrderPManageService {
	
	/**
	 * 발주서 등록
	 * @param order_Pur
	 * @return
	 */
	public ServiceResult createPurOrder(Pur_OrdVO purOrd);
	
	/**
	 * 발주서 상세조회
	 * @param pur_ord_code
	 * @return
	 */
	public Pur_Ord_ListVO retrievePurOrder(String pur_ord_code);
	
	public List<Pur_Ord_ListVO> retrievePurOrderList(PagingVO<Pur_Ord_ListVO> pagingVO);
	
	public long retrivePurOrderCount(PagingVO<Pur_Ord_ListVO> pagingVO);
	
	/**
	* 
	* @param pur_est_no 견적서 번호
	* @return
	*/
	public Pur_Est_ListVO retrievePurEst(String pur_est_no);
	
	/**
	 * 모달창에 띄워줄 발주서리스트를 가져올때 사용
	 */
	public List<SelectPurEstListVO> retrieveOrdList(PagingVO<SelectPurEstListVO> pagingVO);

	/**
	 * 엑셀 출력위한 조회 (pagingVO에서 처리하지만 page수는 무시)
	 * @param pagingVO
	 * @return
	 */
	public List<Pur_Ord_ListVO> retrieveExcelList(PagingVO<Pur_Ord_ListVO> pagingVO);
	
}
