package kr.or.ddit.purchasingTeam.estimateManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.estimateManage.dao.IEstimatePManageDAO;
import kr.or.ddit.purchasingTeam.estimateManage.dao.IEstimatePReqManageDAO;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.Estimate_PurVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;
import kr.or.ddit.vo.Pur_ErVO;
import kr.or.ddit.vo.Pur_Er_ListVO;
import kr.or.ddit.vo.Pur_Est_ListVO;


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
@Service
public class EstimatePReqManageServiceImple implements IEstimatePReqManageService {

	@Inject
	IEstimatePReqManageDAO estPReqManageDAO;

	/**
	 * 견적요청서 등록
	 */
	@Override
	public ServiceResult CreatePurReqEstimate(Pur_ErVO purEr) {
		int rowCnt = estPReqManageDAO.insertPurReqEstimate(purEr);
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt>0) result = ServiceResult.OK;
		return result;
	}

	/**
	 * 견적요청서 상세조회
	 */
	@Override
	public Pur_Er_ListVO retreiveReqEst(String pur_er_no) {
		return estPReqManageDAO.selectReqEst(pur_er_no);
	}

	/**
	 * 견적요청서 리스트 조회
	 */
	@Override
	public List<Pur_Er_ListVO> retreivePurReqEstList(PagingVO<Pur_Er_ListVO> pagingVO) {
		return estPReqManageDAO.selectPurReqEstList(pagingVO);
	}

	/**
	 * 페이징처리
	 */
	@Override
	public long selectPurReqEstCount(PagingVO<Pur_Er_ListVO> pagingVO) {
		return estPReqManageDAO.selectPurReqEstCount(pagingVO);
	}

	/**
	 * 견적요청서 수정
	 */
	@Override
	public ServiceResult modifyPurReqEstimate(Pur_ErVO purEr) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 클라이언트 리스트 조회
	 */
	@Override
	public List<ClientVO> retrievePurClientList(PagingVO<ClientVO> pagingVO) {
		return estPReqManageDAO.selectPurClientList(pagingVO);
	}

	/**
	 * 모달 창에서 선택한 클라이언트 코드를 통해 한 건의 클라이언트를 상세조회
	 */
	@Override
	public ClientVO retrievePurClient(String cl_no) {
		return estPReqManageDAO.selectPurClient(cl_no);
	}

	/**
	 * 상품 리스트 조회
	 */
	@Override
	public List<ProductVO> retrievePurProdList(PagingVO<ProductVO> pagingVO) {
		return estPReqManageDAO.selectPurProdList(pagingVO);
	}

	/**
	 * 모달 창에서 선택한 제품 번호를 통해 한 건의 제품 상세정보 조회
	 */
	@Override
	public ProductVO retrievePurProd(int prod_no) {
		return estPReqManageDAO.selectPurProd(prod_no);
	}

	/**
	 * 클라이언트 페이징처리
	 */
	@Override
	public long retrievePurClientCount(PagingVO<ClientVO> pagingVO) {
		return estPReqManageDAO.selectPurClientCount(pagingVO);
	}

	/**
	 * 상품 페이징처리
	 */
	@Override
	public long retrievePurProdCount(PagingVO<ProductVO> pagingVO) {
		return estPReqManageDAO.selectPurProdCount(pagingVO);
	}

	
	


}
