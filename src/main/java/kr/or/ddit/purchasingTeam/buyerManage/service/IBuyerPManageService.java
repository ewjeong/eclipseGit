package kr.or.ddit.purchasingTeam.buyerManage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;


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
public interface IBuyerPManageService {
	
	public ServiceResult createPurBuyer(ClientVO clientVO);
	
	public List<ItemVO> retreiveItem(String item_name);
	
	public long retrievePurBuyerCount(PagingVO<ClientVO> pagingVO);
	
	public List<ClientVO> retrievePurBuyerList(PagingVO<ClientVO> pagingVO);
	
	public ServiceResult modifyPurBuyer(ClientVO clientVO);
	
	public ClientVO retrieveDetailBuyer(String cl_no);
	
	public ServiceResult removePurBuyer(ClientVO clientVO);
	
	
}
