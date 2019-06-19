package kr.or.ddit.purchasingTeam.buyerManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.buyerManage.dao.IBuyerPManageDAO;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.PagingVO;


/**
 * @author 정은우
 * @since 2019. 5. 30.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 30.      정은우       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class BuyerPManageServiceImpl implements IBuyerPManageService {

	@Inject
	IBuyerPManageDAO ibuyerPManageDAO;

	@Override
	public ServiceResult createPurBuyer(ClientVO clientVO) {
		ServiceResult result = null;
		int rowCnt = ibuyerPManageDAO.insertPurBuyer(clientVO);
		if(rowCnt>0){
			result = ServiceResult.OK;
		}else{
			result= ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<ItemVO> retreiveItem(String item_name) {
		return ibuyerPManageDAO.selectItem(item_name);
	}

	@Override
	public long retrievePurBuyerCount(PagingVO<ClientVO> pagingVO) {
		return ibuyerPManageDAO.selectPurBuyerCount(pagingVO);
	}

	@Override
	public List<ClientVO> retrievePurBuyerList(PagingVO<ClientVO> pagingVO) {
		return ibuyerPManageDAO.selectPurBuyerList(pagingVO);
	}

	@Override
	public ServiceResult modifyPurBuyer(ClientVO clientVO) {
		ServiceResult result = null;
		int rowCnt = ibuyerPManageDAO.updatePurBuyer(clientVO);
		if(rowCnt>0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ClientVO retrieveDetailBuyer(String cl_no) {
		return ibuyerPManageDAO.selectDetailBuyer(cl_no);
	}

	@Override
	public ServiceResult removePurBuyer(ClientVO clientVO) {
		ServiceResult result = null;
		int rowCnt = ibuyerPManageDAO.deletePurBuyer(clientVO);
		if(rowCnt>0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	

	

	
	
	
	
	

	


}
