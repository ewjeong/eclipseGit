package kr.or.ddit.superManager.EmplscheduleManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.superManager.EmplscheduleManage.dao.IEmplScheduleManageDAO;
import kr.or.ddit.vo.ActiveVO;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.PagingVO;

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
@Service
public class EmplScheduleManageServiceImpl implements IEmplScheduleManageService {

	@Inject
	IEmplScheduleManageDAO iemplSchduleManageDAO;
	
	@Override
	public List<ClientVO> retrieveClient(String cl_name) {
		return iemplSchduleManageDAO.selectClient(cl_name);
	}

	@Override
	public long retrieveScheduleCount(PagingVO<ActiveVO> pagingVO) {
		return iemplSchduleManageDAO.selectScheduleCount(pagingVO);
	}

	@Override
	public List<ActiveVO> retrieveScheduleList(PagingVO<ActiveVO> pagingVO) {
		return iemplSchduleManageDAO.selectScheduleList(pagingVO);
	}

	@Override
	public List<ActiveVO> retrieveCalendar(ActiveVO activeVO) {
		return iemplSchduleManageDAO.selectCalendar(activeVO);
	}

	

}
