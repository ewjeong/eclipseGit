package kr.or.ddit.purchasingTeam.estimateManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.estimateManage.dao.IEstimatePManageDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Er_ListVO;
import kr.or.ddit.vo.Pur_EstVO;
import kr.or.ddit.vo.Pur_Est_ListVO;
import kr.or.ddit.vo.SelectPurErListVO;


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
public class EstimatePManageServiceImple implements IEstimatePManageService {

	@Inject
	IEstimatePManageDAO estimatePManageDAO;
	
	@Override
	public ServiceResult createPurEstimate(Pur_EstVO purEst) {
		int rowCnt = estimatePManageDAO.insertPurEstimate(purEst);
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt>0) result = ServiceResult.OK;
		return result;
	}

	@Override
	public List<Pur_Est_ListVO> retrievePurEstimateList(PagingVO<Pur_Est_ListVO> pagingVO) {
		return estimatePManageDAO.selectPurEstimateList(pagingVO);
	}

	@Override
	public long selectPurEstimateCount(PagingVO<Pur_Est_ListVO> pagingVO) {
		return estimatePManageDAO.selectPurEstimateCount(pagingVO);
	}

	@Override
	public Pur_Est_ListVO retrieveEstOrder(String pur_est_no) {
		return estimatePManageDAO.selectEstOrder(pur_est_no);
	}

	@Override
	public Pur_Er_ListVO retrievePurEr(String pur_er_no) {
		return estimatePManageDAO.selectPurEr(pur_er_no);
	}

	@Override
	public List<SelectPurErListVO> retrieveErList(PagingVO<SelectPurErListVO> pagingVO) {
		return estimatePManageDAO.selectErList(pagingVO);
	}



	


}
