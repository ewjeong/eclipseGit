package kr.or.ddit.purchasingTeam.stockManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.Emp_Pos_Dep_VO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Oprod_ListVO;
import kr.or.ddit.vo.Pur_Ord_ListVO;
import kr.or.ddit.vo.SelectPurOrdListVO;
import kr.or.ddit.vo.WareVO;
import kr.or.ddit.vo.Ware_Ord_ListVO;

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
public interface IWarehousingManageDAO {
	
	public int insertWarehousing(WareVO ware);
	
	public List<Pur_Ord_ListVO> selectWarehousingList(PagingVO<Pur_Ord_ListVO> pagingVO);
	
	public long selectWarehousingCount(PagingVO<Pur_Ord_ListVO> pagingVO);
	
	/**
	 * @param 한 건에 대한 발주서 번호
	 * @return 해당되는 발주서 번호를 통해 조회되는 한 건의 발주서 내용 + 여러개의 상품
	 */
	public Ware_Ord_ListVO selectPurOrd(String pur_ord_code);

	/**
	 * 모달창에 띄워줄 발주서리스트를 가져올때 사용
	 */
	public List<SelectPurOrdListVO> selectOrdList(PagingVO<SelectPurOrdListVO> pagingVO);

	/** 
	 * 엑셀다운용
	 */
	public List<Pur_Ord_ListVO> selectExcelList(PagingVO<Pur_Ord_ListVO> pagingVO);
	
}
