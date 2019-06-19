package kr.or.ddit.purchasingTeam.orderManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Est_ListVO;
import kr.or.ddit.vo.Pur_OrdVO;
import kr.or.ddit.vo.Pur_Ord_ListVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;
import kr.or.ddit.vo.SelectPurEstListVO;
import kr.or.ddit.vo.SelectPurEstVO;


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
 * 2019. 5. 24.      정은우       발주서등록
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IOrderPManageDAO {

	/**
	 * 발주서 등록
	 * @param order_Pur
	 * @return
	 */
	public int inserPurOrder(Pur_OrdVO purOrd);
	
	/**
	 * 발주서 상세조회
	 * @param pur_ord_code 발주서코드
	 * @return
	 */
	public Pur_Ord_ListVO selectPurOrder(String pur_ord_code);
	
	public List<Pur_Ord_ListVO> selectPurOrderList(PagingVO<Pur_Ord_ListVO> pagingVO);
	
	public long selectPurOrderCount(PagingVO<Pur_Ord_ListVO> pagingVO);
	
	/**
	 * @param 한 건에 대한 견적서 번호
	 * @return 해당되는 견적서 번호를 통해 조회되는 한 건의 견적서 내용 + 여러개의 상품
	 */
	public Pur_Est_ListVO selectPurEst(String pur_est_no);
	
	/**
	 * 모달창에 띄워줄 견적서리스트를 가져올때 사용
	 */
	public List<SelectPurEstListVO> selectEstList(PagingVO<SelectPurEstListVO> pagingVO);
	
	/**
	 * 엑셀 출력위한 조회 (pagingVO에서 처리하지만 page수는 무시)
	 * @param pagingVO
	 * @return
	 */
	public List<Pur_Ord_ListVO> selectExcelList(PagingVO<Pur_Ord_ListVO> pagingVO);
	
	
}
