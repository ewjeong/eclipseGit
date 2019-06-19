package kr.or.ddit.purchasingTeam.orderManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.orderManage.dao.IOrderPManageDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Est_ListVO;
import kr.or.ddit.vo.Pur_OrdVO;
import kr.or.ddit.vo.Pur_Ord_ListVO;
import kr.or.ddit.vo.SelectPurEstListVO;
import kr.or.ddit.vo.SelectPurEstVO;

@Service
public class OrderPManageServiceImple implements IOrderPManageService {
	
	@Inject
	IOrderPManageDAO orderPManageDAO;
	
	/**
	 * 발주서등록
	 */
	@Override
	public ServiceResult createPurOrder(Pur_OrdVO purOrd) {
		int rowCnt = orderPManageDAO.inserPurOrder(purOrd);
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt>0) result = ServiceResult.OK;
		return result;
	}

	/**
	 * 전체리스트조회
	 */
	@Override
	public List<Pur_Ord_ListVO> retrievePurOrderList(PagingVO<Pur_Ord_ListVO> pagingVO) {
		return orderPManageDAO.selectPurOrderList(pagingVO);
	}

	@Override
	public long retrivePurOrderCount(PagingVO<Pur_Ord_ListVO> pagingVO) {
		return orderPManageDAO.selectPurOrderCount(pagingVO);
	}

	/**
	 * 발주서 상세조회
	 */
	@Override
	public Pur_Ord_ListVO retrievePurOrder(String pur_ord_code) {
		return orderPManageDAO.selectPurOrder(pur_ord_code);
	}
	
	/**
	 * 견적서 내용조회
	 */
	@Override
	public Pur_Est_ListVO retrievePurEst(String pur_est_no) {
		return orderPManageDAO.selectPurEst(pur_est_no);
	}

	/**
	 * 모달창에 띄워줄 발주서 리스트
	 */
	@Override
	public List<SelectPurEstListVO> retrieveOrdList(PagingVO<SelectPurEstListVO> pagingVO) {
		return orderPManageDAO.selectEstList(pagingVO);
	}

	@Override
	public List<Pur_Ord_ListVO> retrieveExcelList(PagingVO<Pur_Ord_ListVO> pagingVO) {
		return orderPManageDAO.selectExcelList(pagingVO);
	}

	

	

	

	

}
