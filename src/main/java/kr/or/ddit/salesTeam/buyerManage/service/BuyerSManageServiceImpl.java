package kr.or.ddit.salesTeam.buyerManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.salesTeam.buyerManage.dao.IBuyerSManageDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author 정다혜
 * @since 2019. 5. 23.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 23.      작성자명       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class BuyerSManageServiceImpl implements IBuyerSManageService {

	@Inject
	IBuyerSManageDAO iBuyerSManageDAO;
	
	@Override
	public ServiceResult createSalesBuyer(ClientVO clientVO) {
		ServiceResult result = null;
		int rowCnt = iBuyerSManageDAO.insertSalesBuyer(clientVO);
		if(rowCnt>0){
			result = ServiceResult.OK;
		}else{
			result= ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public long retrieveSalesBuyerCount(PagingVO<ClientVO> pagingVO) {
		return iBuyerSManageDAO.selectSalesBuyerCount(pagingVO);
	}

	@Override
	public List<ClientVO> retrieveSalesBuyerList(PagingVO<ClientVO> pagingVO) {
		return iBuyerSManageDAO.selectSalesBuyerList(pagingVO);
	}

	@Override
	public ServiceResult modifySalesBuyer(ClientVO clientVO) {
		ServiceResult result = null;
		int rowCnt = iBuyerSManageDAO.updateSalesBuyer(clientVO);
		if(rowCnt>0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public List<ItemVO> retreiveItem(String item_name) {
		return iBuyerSManageDAO.selectItem(item_name);
	}

	@Override
	public ClientVO retrieveDetailBuyer(String cl_no) {
		return iBuyerSManageDAO.selectDetailBuyer(cl_no);
	}

	@Override
	public ServiceResult removeSaleBuyer(ClientVO clientVO) {
		
		ServiceResult result = null;
		int rowCnt = iBuyerSManageDAO.deleteSalesBuyer(clientVO);
		if(rowCnt>0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

}












