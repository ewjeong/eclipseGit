package kr.or.ddit.salesTeam.buyerManage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
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
public interface IBuyerSManageService {
	
	public ServiceResult createSalesBuyer(ClientVO clientVO);
	
	public List<ItemVO> retreiveItem(String item_name);
	
	
	public long retrieveSalesBuyerCount(PagingVO<ClientVO> pagingVO);
	
	public List<ClientVO> retrieveSalesBuyerList(PagingVO<ClientVO> pagingVO);
	
	public ServiceResult modifySalesBuyer(ClientVO clientVO);
	
	public ClientVO retrieveDetailBuyer(String cl_no);
	
	public ServiceResult removeSaleBuyer(ClientVO clientVO);
	
//	removeSalesBuyer();
	
}
