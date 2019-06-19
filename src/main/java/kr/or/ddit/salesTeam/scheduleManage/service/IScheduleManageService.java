package kr.or.ddit.salesTeam.scheduleManage.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
@Service
public interface IScheduleManageService {

	public ServiceResult createSchedule(ActiveVO activeVO);

	public List<ClientVO> selectClient(String cl_name);
	
	public ServiceResult updateSchedule(ActiveVO active);
	
	public long retrieveScheduleCount(PagingVO<ActiveVO> pagingVO);
	
	public List<ActiveVO> retrieveScheduleList(PagingVO<ActiveVO> pagingVO);
	
	public ServiceResult modifySchedule(ActiveVO active);
	
	public List<ActiveVO> retriveCalendar(ActiveVO activeVO);
	
	public int deleteSchedule(ActiveVO activeVO);
	
	public ActiveVO retrieveActiveForDel(int ac_no);
	
//	public ServiceResult modifyActive(int ac_no);
	
	
}
