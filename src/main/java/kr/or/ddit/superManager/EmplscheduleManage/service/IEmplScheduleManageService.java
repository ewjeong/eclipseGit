package kr.or.ddit.superManager.EmplscheduleManage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
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
public interface IEmplScheduleManageService {

	public List<ClientVO> retrieveClient(String cl_name);
	
	public long retrieveScheduleCount(PagingVO<ActiveVO> pagingVO);
	
	public List<ActiveVO> retrieveScheduleList(PagingVO<ActiveVO> pagingVO);
	
	public List<ActiveVO> retrieveCalendar(ActiveVO activeVO);

}