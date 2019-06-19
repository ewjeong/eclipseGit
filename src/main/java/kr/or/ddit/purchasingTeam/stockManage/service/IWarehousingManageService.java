package kr.or.ddit.purchasingTeam.stockManage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
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
public interface IWarehousingManageService {
	
	public ServiceResult createWarehousing(WareVO ware);
	
	public List<Pur_Ord_ListVO> retrieveWarehousingList(PagingVO<Pur_Ord_ListVO> pagingVO);
	
	public long retrieveWarehousingCount(PagingVO<Pur_Ord_ListVO> pagingVO);

   /**
    * @param pur_ord_code 발주서 번호
    * @return
    */
	public Ware_Ord_ListVO retrievePurOrd(String pur_ord_code);
	
	/**
	 * 모달창에 띄워줄 발주서리스트를 가져올때 사용
	 */
	public List<SelectPurOrdListVO> retrieveOrdList(PagingVO<SelectPurOrdListVO> pagingVO);
	
	/** 
	 * 엑셀다운용
	 */
	public List<Pur_Ord_ListVO> retrieveExcelList(PagingVO<Pur_Ord_ListVO> pagingVO);
}
