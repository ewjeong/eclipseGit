
package kr.or.ddit.salesTeam.scheduleManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.salesTeam.scheduleManage.dao.IScheduleManageDAO;
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
public class ScheduleManageServiceImpl implements IScheduleManageService {
	
	@Inject
	IScheduleManageDAO scheduleManageDAO;

	@Override
	public ServiceResult createSchedule(ActiveVO activeVO) {
		ServiceResult result = null;

		int rowCnt = scheduleManageDAO.insertSchedule(activeVO);
		if(rowCnt>0){
			result=ServiceResult.OK;
		}else{
			result=ServiceResult.FAILED;
		}
		return result; 
	}
	

	@Override
	public long retrieveScheduleCount(PagingVO<ActiveVO> pagingVO) {
		return scheduleManageDAO.selectScheduleCount(pagingVO);
	}

	@Override
	public List<ActiveVO> retrieveScheduleList(PagingVO<ActiveVO> pagingVO) {
		return scheduleManageDAO.selectScheduleList(pagingVO);
	}

	@Override
	public ServiceResult modifySchedule(ActiveVO active) {
		ServiceResult result;
		int rowCnt = scheduleManageDAO.updateSchedule(active);
		if(rowCnt>0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public List<ActiveVO> retriveCalendar(ActiveVO activeVO) {
		return scheduleManageDAO.selectCalendar(activeVO);
	}

	@Override
	public List<ClientVO> selectClient(String cl_name) {
		return scheduleManageDAO.selectClient(cl_name);
	}


	@Override
	public int deleteSchedule(ActiveVO activeVO) {
//		ServiceResult result =null;
//		int rowCnt = scheduleManageDAO.deleteSchedule(ac_no);
//		if(rowCnt>0){
//			result=ServiceResult.OK;
//		}else{
//			result=ServiceResult.FAILED;
//		}
//		
//		return result;
		
		return scheduleManageDAO.deleteSchedule(activeVO);
		
	}


	@Override
	public ActiveVO retrieveActiveForDel(int ac_no) {
		return scheduleManageDAO.selectActiveForDel(ac_no);
	}


	@Override
	public ServiceResult updateSchedule(ActiveVO active) {
		// TODO Auto-generated method stub
		return null;
	}


//	@Override
//	public ServiceResult updateSchedule(ActiveVO active) {
//		ServiceResult result;
//		int rowCnt = scheduleManageDAO.modifySchedule(active);
//		if(rowCnt>0){
//			result = ServiceResult.OK;
//		}else{
//			result = ServiceResult.FAILED;
//		}
//		
//		return result;
//	}


//	@Override
//	public ServiceResult modifyActive(int ac_no) {
//		ServiceResult result;
//		int rowCnt = scheduleManageDAO.updateActive(ac_no);
//		if(rowCnt>0){
//			result=ServiceResult.OK;
//		}else{
//			result=ServiceResult.FAILED;
//		}
//		return result;
//	}
	

}










