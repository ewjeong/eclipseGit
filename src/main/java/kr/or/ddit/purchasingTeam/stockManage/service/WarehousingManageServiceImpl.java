package kr.or.ddit.purchasingTeam.stockManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.stockManage.dao.IWarehousingManageDAO;
import kr.or.ddit.vo.PagingVO;
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
@Service
public class WarehousingManageServiceImpl implements IWarehousingManageService{
	
	@Inject
	IWarehousingManageDAO warehousingManageDAO;
	
	@Override
	public ServiceResult createWarehousing(WareVO ware) {
		int rowCnt = warehousingManageDAO.insertWarehousing(ware);
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt>0) result = ServiceResult.OK;
		return result;
	}
	
	@Override
	public long retrieveWarehousingCount(PagingVO<Pur_Ord_ListVO> pagingVO) {
		return warehousingManageDAO.selectWarehousingCount(pagingVO);
	}

	@Override
	public List<Pur_Ord_ListVO> retrieveWarehousingList(PagingVO<Pur_Ord_ListVO> pagingVO) {
		return warehousingManageDAO.selectWarehousingList(pagingVO);
	}
	

	@Override
	public Ware_Ord_ListVO retrievePurOrd(String pur_ord_code) {
		return warehousingManageDAO.selectPurOrd(pur_ord_code);
	}

	/**
	 * 모달창에 띄워줄 발주서 리스트
	 */
	@Override
	public List<SelectPurOrdListVO> retrieveOrdList(PagingVO<SelectPurOrdListVO> pagingVO) {
		return warehousingManageDAO.selectOrdList(pagingVO);
	}

	@Override
	public List<Pur_Ord_ListVO> retrieveExcelList(PagingVO<Pur_Ord_ListVO> pagingVO) {
		return warehousingManageDAO.selectExcelList(pagingVO);
	}


}
