package kr.or.ddit.purchasingTeam.buyerManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
@Repository
public interface IBuyerPManageDAO {
	
	/**
	 * 거래처 등록
	 * @param clientVO
	 * @return
	 */
	public int insertPurBuyer(ClientVO clientVO);
	
	/**
	 * 품목명 검색
	 * @return
	 */
	public List<ItemVO> selectItem(String item_name);
	
	public long selectPurBuyerCount(PagingVO<ClientVO> pagingVO);
	
	public List<ClientVO> selectPurBuyerList(PagingVO<ClientVO> pagingVO);
	
	/**
	 * 거래처 수정
	 * @param clientVO
	 * @return
	 */
	public int updatePurBuyer(ClientVO clientVO);
	
	public ClientVO selectDetailBuyer(String cl_no);
	
	public int deletePurBuyer(ClientVO clientVO);
	
}
