package kr.or.ddit.superManager.EmplscheduleManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ActiveVO;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PerformanceVO;
import kr.or.ddit.vo.ScheduleVO;

/**
 * @author 정다혜
 * @since 2019. 5. 28.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 28.      작성자명       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IEmplScheduleManageDAO {
	
	
	/**
	 * 거래처를 검색할때
	 * 클라이언트 이름으로 검색할수있다.
	 * @param cl_name
	 * @return
	 */
	public List<ClientVO> selectClient(String cl_name);
	
	
	/**
	 * 페이징 처리를 위해서 토탈리스트의 수를 셀때
	 * @param pagingVO
	 * @return
	 */
	public long selectScheduleCount(PagingVO<ActiveVO> pagingVO);
	
	/**
	 * 리스트출력
	 * @param pagingVO
	 * @return
	 */
	public List<ActiveVO> selectScheduleList(PagingVO<ActiveVO> pagingVO);
	
	/**
	 * 캘린더를 띄울때 리스트
	 * @param activeVO
	 * @return
	 */
	public List<ActiveVO> selectCalendar(ActiveVO activeVO);
	
	
}
