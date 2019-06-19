package kr.or.ddit.purchasingTeam.stockManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.stockManage.dao.IReleaseManageDAO;
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
 * 2019. 5. 16.      정은우       영업팀 주문서 내역 검색 메서드 추가
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class ReleaseManageServiceImpl implements IReleaseManageService{

	@Inject
	IReleaseManageDAO releaseManageDAO;

	@Override
	public ServiceResult createRelease(ReleaseVO release) {
		int rowCnt = releaseManageDAO.insertRelease(release);
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt>0) result = ServiceResult.OK;
		return result;
	}

	@Override
	public List<Sale_Ord_ListVO> retrieveReleaseList(PagingVO<Sale_Ord_ListVO> pagingVO) {
		return releaseManageDAO.selectReleaseList(pagingVO);
	}

	@Override
	public long retrieveReleaseCount(PagingVO<Sale_Ord_ListVO> pagingVO) {
		return releaseManageDAO.selectReleaseCount(pagingVO);
	}

	@Override
	public Release_Form_ListVO retrieveSaleOrd(String sale_ord_code) {
		return releaseManageDAO.selectSaleOrd(sale_ord_code);
	}

	/**
	 * 모달창에 띄워줄 주문서 리스트
	 */
	@Override
	public List<SelectSaleOrdListVO> retrieveOrdList(PagingVO<SelectSaleOrdListVO> pagingVO) {
		return releaseManageDAO.selectOrdList(pagingVO);
	}

	@Override
	public List<Sale_Ord_ListVO> retrieveExcelList(PagingVO<Sale_Ord_ListVO> pagingVO) {
		return releaseManageDAO.selectExcelList(pagingVO);
	}

	

	

	
	
}
