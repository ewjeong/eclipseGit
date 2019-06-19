package kr.or.ddit.salesProfitManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SalesProfitVO;

/**
 * @author 박연욱
 * @since 2019. 5. 23.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자  		         수정내용
 * --------     --------    ----------------------
 * 2019. 5. 23.   박연욱       			최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface ISalesProfitManageDAO {

	/**
	 * 페이징 처리를 위해서 토탈리스트의 수를 셀때
	 * @param pagingVO
	 * @return
	 */
	public long selectsalesProfitCount(PagingVO<SalesProfitVO> pagingVO);
	
	/**
	 * 매출 리스트출력
	 * @param pagingVO
	 * @return
	 */
	public List<SalesProfitVO> selectSalesProfitList(PagingVO<SalesProfitVO> pagingVO);
	
	
	/**
	 * 엑셀 출력위한 조회 (pagingVO에서 처리하지만 page수는 무시)
	 * @param pagingVO
	 * @return
	 */
	public List<SalesProfitVO> selectSalesProfitExcelList(PagingVO<SalesProfitVO> pagingVO);
	
	/**
	 * 월별 사원 매출 조회
	 */
	public List<SalesProfitVO> selectEmployeeProfitList();
	
	/**
	 * 실적이 있는 월 조회
	 */
	public List<SalesProfitVO> selectProfitMonth();
	
	/**
	 * 매출실적이 있는 사원 조회
	 * @return
	 */
	public List<SalesProfitVO> selectProfitEmployee();
	
	/**
	 * 월별 순이익 조회
	 * @return
	 */
	public List<SalesProfitVO> selectPureProfit();
	
	/**
	 * 월별 매출 조회
	 * @return
	 */
	public List<SalesProfitVO> selectMonthProfit();
	
 }
