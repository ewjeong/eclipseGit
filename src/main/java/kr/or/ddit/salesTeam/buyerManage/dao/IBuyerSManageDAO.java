package kr.or.ddit.salesTeam.buyerManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ScheduleVO;

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
@Repository
public interface IBuyerSManageDAO {

	/**
	 * 거래처 등록
	 * @param clientVO
	 * @return
	 */
	public int insertSalesBuyer(ClientVO clientVO);
	
	/**
	 * 품목명 검색
	 * @return
	 */
	public List<ItemVO> selectItem(String item_name);
	
	
	public long selectSalesBuyerCount(PagingVO<ClientVO> pagingVO);
	
	public List<ClientVO> selectSalesBuyerList(PagingVO<ClientVO> pagingVO);
	
	/**
	 * 거래처 수정
	 * @param clientVO
	 * @return
	 */
	public int updateSalesBuyer(ClientVO clientVO);
	
	public ClientVO selectDetailBuyer(String cl_no);
	
	public int deleteSalesBuyer(ClientVO clientVO);
	
//	deleteSalesBuyer();
	
}
