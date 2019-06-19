package kr.or.ddit.salesTeam.scheduleManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ActiveVO;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.CriteriaVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author 정다혜
 * @since 2019. 5. 16.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 16.      작성자명       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IScheduleManageDAO {
	
	/**
	 * 활동관리 리스트에 활동을 추가할때
	 * @param activeVO
	 * @return
	 */
	public int insertSchedule(ActiveVO activeVO);
	
	
	/**
	 * 거래처를 검색할때
	 * 클라이언트 이름으로 검색할수있다.
	 * @param cl_name
	 * @return
	 */
	public List<ClientVO> selectClient(String cl_name);
	
	
	
//	selectSchedule();
	
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
	 * 스케쥴 수정하는 기능
	 * @param active
	 * @return
	 */
	public int updateSchedule(ActiveVO active);
	
	
	/**
	 * 캘린더를 띄울때 리스트
	 * @param activeVO
	 * @return
	 */
	public List<ActiveVO> selectCalendar(ActiveVO activeVO);
	
	/**
	 * 스케쥴 삭제하는것
	 * 이름은 delete지만 update로 처리할것
	 * @param ac_no
	 * @return
	 */
	public int deleteSchedule(ActiveVO activeVO);
	
	
	/**
	 * 삭제할때 ac_no랑 일치하는 활동을 조회함
	 * @return
	 */
	public ActiveVO selectActiveForDel(int ac_no);
	
	/**
	 * 활동관리 수정
	 * @param ac_no
	 * @return
	 */
//	public int updateActive(int ac_no);
	
	
	
	
	
	
	
}
