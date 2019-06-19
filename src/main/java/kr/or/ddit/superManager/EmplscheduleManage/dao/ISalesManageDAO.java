package kr.or.ddit.superManager.EmplscheduleManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.IncomeVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReleaseVO;

/**
 * @author 정다혜
 * @since 2019. 5. 30.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 30.      작성자명       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface ISalesManageDAO {
	
	/**
	 * 페이징처리
	 * @param pagingVO
	 * @return
	 */
	public List<IncomeVO> selectEmpIncomeList(PagingVO<IncomeVO> pagingVO);
	
	/**
	 * 페이징 처리
	 * @param pagingVO
	 * @return
	 */
	public long selectEmpIncomeCount(PagingVO<IncomeVO> pagingVO);
	
	/**
	 * 사원명으로 검색하기
	 * @param emp_name
	 * @return
	 */
	public List<ReleaseVO> selectClient(String emp_name);
	
	
	
	/**
	 * 엑셀 리스트 출력
	 * @param pagingVO
	 * @return
	 */
	public List<IncomeVO> selectEmpExcel(PagingVO<IncomeVO> pagingVO);
	
	/**
	 * 월별 매출
	 * @param pagingVO
	 * @return
	 */
	public List<IncomeVO> selectEmpSales(PagingVO<IncomeVO> pagingVO);
	
	
}
