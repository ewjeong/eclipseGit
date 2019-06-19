package kr.or.ddit.elecAuthorization.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import kr.or.ddit.elecAuthorization.dao.IApprovalDAO;
import kr.or.ddit.elecAuthorization.dao.IPushDAO;
import kr.or.ddit.elecAuthorization.dao.ISeungBanJeanDaeDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.handler.PushHandler;
import kr.or.ddit.vo.Elec_ApprlineVO;
import kr.or.ddit.vo.WaitListVO;

/**
 * @author 이초연
 * @since 2019. 5. 23
 * @version 1.0
 * @see
 * <pre>
 * [[개정이력(Modification Information)]] 
 * 수정일        수정자     수정내용
 * ==========   ======    ============== 
 * 2019. 5. 23	 이초연		최초 작성 - 승인, 전결 처리
 * 2019. 5. 28   이초연     대결 처리
 * 2019. 5. 29   이초연     반려 처리
 * 2019.5.31~6.4  이초연    알림
 * 2019. 6. 5    이초연     결재 완료 시, 발주서 또는 주문서 테이블에 elec_comple 업데이트
 * 2019. 6. 11   이초연     단위테스트 수정사항 반영
 * 
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 * 1. 승인, 전결, 반려, 대결, 결재완료 처리를 하는 컨트롤러
 * 2. 알림 기능 포함
 * 
 */
@Service
public class SeungBanJeanDaeServiceImpl implements ISeungBanJeanDaeService {
	@Inject
	ISeungBanJeanDaeDAO sbjdDao;
	@Inject
	IApprovalDAO approvalDao;
	@Inject
	IPushDAO pushDao;
	
	@Resource(name="sessionMap")
	Map<String, WebSocketSession> sessionMap;
	
	@Transactional
	@Override  	// 승인처리하기
	public ServiceResult modifyLineToApproval(int elec_no, String authorized_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("elec_no", elec_no);
		map.put("authorized_id", authorized_id);
		
		// **알람기능 설명 ** 승인 처리 시, 다음 결재자에게 알림을 보내야 하는데,
		// 마지막 결재자면 다음 결재자가 없으니까 알림을 보낼 필요가 없고,
		// 처음 또는 중간 결재자라면 다음 결재자에게 알림을 보내야 한다.
		// 그럼 일단, 다음 결재자를 알기 위해선, 현재 로그인한 결재자의 결재순위를 먼저 알아야 하고,
		// 그 결재순위 + 1한 결재순위를 갖고 있는 결재자를 찾는다.
//		WebSocketSession webSocketSession = sessionMap.get("");
		
		ServiceResult result = ServiceResult.FAILED;

		// 해당 문서의 로그인한 결재자의 결재선 가져오기
		Elec_ApprlineVO apprLine = sbjdDao.selectLineByNoAndAuthorizer(map); 
		int authorizerPriority = apprLine.getElec_priority(); // 로그인한 결재자의 결재순위
		// 해당 문서의 마지막 결재순위 가져오기
		int maxPriority = sbjdDao.selectMaxPriority(elec_no); 
		
		int resultLastAuthorAppro = 0;
		int resultApproComplete = 0;
		int resultMidleAuthorAppro = 0;
		
		// 해당 결재선의 결재자의 결재순위가 max(elec_priority) 와 같은 경우
		if(authorizerPriority == maxPriority) {
			// 결재 완료될 경우 프로세스 ------------------------------------------------
			int resultOrderComplete = 0;
			// ***** 19-06-05 추가 ***** sale_ord 또는 pur_ord 테이블의 elec_comple 컬럼 'Y'로 update 해야 한다. 
			WaitListVO approSheet = approvalDao.selectWaitApproval(elec_no);
			if(approSheet != null){
				String sendTypeCode = approSheet.getSend_type_code();
				if(StringUtils.isNotBlank(sendTypeCode)){
					if(StringUtils.startsWith(sendTypeCode, "SO")){
						// 영업팀의 주문서인 경우
						resultOrderComplete = approvalDao.updateSaleOrderComplete(sendTypeCode);
					} else if (StringUtils.startsWith(sendTypeCode, "PO")) {
						// 구매팀의 발주서인 경우
						resultOrderComplete = approvalDao.updatePurchasOrderComplete(sendTypeCode);
					}
				}
			}
			// 마지막 결재자가 '승인'버튼 클릭 시, 해당 문서는 결재 완료 상태가 된다.
			resultLastAuthorAppro = sbjdDao.updateLineToApproval(map);
			// ELEC_APPROVAL 의 ELEC_COMPLE 을 'Y'로 업데이트 해야 한다.
			resultApproComplete = approvalDao.updateApprovalComplete(elec_no);
			
			if(resultLastAuthorAppro > 0 && resultApproComplete > 0
					&& resultOrderComplete > 0 ) result = ServiceResult.OK;
			
			// **알람기능** 기안자에게 결재가 완료되었다는 알림을 보낸다. -----------------------------------------
			String ownerId = apprLine.getOwner_id(); 
			WebSocketSession ownerSession = sessionMap.get(ownerId); // 로그인한 결재자의 결재선의 기안자 가져오기
			
			String sendMsg = elec_no+"번 결재문서가 완료되었습니다.";
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("alert_content", sendMsg );
			paramMap.put("emp_id", ownerId);
			
			if(ownerSession != null){
				// 기안자가 로그인한 상태인 경우, 바로 '결재 완료' 알림을 보낸다.
				TextMessage ownerMsg = new TextMessage(sendMsg);
				try {
					ownerSession.sendMessage(ownerMsg);
					int rowCnt = pushDao.insertAlert(paramMap);
					if(rowCnt <= 0) 
						result = ServiceResult.FAILED;
				} catch (IOException e) {
					result = ServiceResult.FAILED;
					throw new RuntimeException(e);
				}
			} else if (ownerSession == null ) {
				// 기안자가 로그아웃 상태인 경우, 알림을 바로 보내지 않고, ALERT 에 INSERT 한다.
				int rowCnt = pushDao.insertAlert(paramMap);
				if(rowCnt <= 0) 
					result = ServiceResult.FAILED;
			}
		} else if (authorizerPriority < maxPriority) {
			// 증간 결재자(첫번째 또는 두번째 결재자)가 승인 버튼을 클릭한 경우
			resultMidleAuthorAppro = sbjdDao.updateLineToApproval(map);
			if(resultMidleAuthorAppro > 0 ) {
				// **알림기능 구현 순서** : 1.그 결재순위 +1 한 결재순위를 갖고 있는 결재자를 찾는다.
				int nextPriority = authorizerPriority + 1;
				Elec_ApprlineVO paramLine = new Elec_ApprlineVO();
				paramLine.setElec_no(elec_no);
				paramLine.setElec_priority(nextPriority);
				
				Elec_ApprlineVO nextLine = pushDao.selectNextAuthorizer(paramLine);
				if(nextLine != null){
					String nextAuthorizerId = nextLine.getAuthorized_id(); // 다음 결재자 아이디
					WebSocketSession webSocketSession = sessionMap.get(nextAuthorizerId);
					
					String sendMsg = elec_no+"번 결재문서가 도착했습니다!";
					Map<String, Object> paramMap = new HashMap<>();
					paramMap.put("alert_content", sendMsg);
					paramMap.put("emp_id", nextAuthorizerId);
					
					if(webSocketSession != null){
						//2-1. 다음 결재자가 로그인한 상태인 경우, 바로 알림을 보낸다.
						TextMessage textMessage = new TextMessage(sendMsg);
						try {
							webSocketSession.sendMessage(textMessage);
							int resultAlertCnt = pushDao.insertAlert(paramMap);
							if(resultAlertCnt > 0) 
								result = ServiceResult.OK;
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					} else if (webSocketSession == null) {
						//2-2. 다음 결재자가 로그아웃 상태인 경우, ALERT에 INSERT한다.
						int resultAlertCnt = pushDao.insertAlert(paramMap);
						if(resultAlertCnt > 0) 
							result = ServiceResult.OK;
					}
				} // line null checking 
			} // end of if(resultMidleAuthorAppro > 0)
		}
		return result;
	}

	@Transactional
	@Override    // 전결처리
	public ServiceResult modifyLineToJeonGyeol(int elec_no, String loginUserId) {
		ServiceResult result = ServiceResult.FAILED;
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("elec_no", elec_no);
		paramMap.put("authorized_id", loginUserId);
		
		// ELEC_APPRLINE 의 상태코드를 AS004(전결)로 UPDATE
		int rowCnt = sbjdDao.updateLineToJeonGyeol(paramMap);
		if(rowCnt > 0 ) {
			// 전결 시, elec_approval 의 elec_comple 을 'Y'로 업데이트 해줘야 한다.
			int resultApproComplete = approvalDao.updateApprovalComplete(elec_no);
			if(resultApproComplete > 0) result = ServiceResult.OK; 
		}
		// **알람기능** 기안자에게 결재가 완료되었다는 알림을 보낸다. -----------------------------------------
		Elec_ApprlineVO apprLine = sbjdDao.selectLineByNoAndAuthorizer(paramMap);
		String ownerId = apprLine.getOwner_id(); 
		WebSocketSession ownerSession = sessionMap.get(ownerId); // 로그인한 결재자의 결재선의 기안자 가져오기
		
		String sendMsg = elec_no+"번 결재문서가 완료되었습니다.";
		
		Map<String, Object> alertParamMap = new HashMap<>();
		alertParamMap.put("alert_content", sendMsg );
		alertParamMap.put("emp_id", ownerId);
		
		if(ownerSession != null){
			// 기안자가 로그인한 상태인 경우, 바로 '결재 완료' 알림을 보낸다.
			TextMessage ownerMsg = new TextMessage(sendMsg);
			try {
				ownerSession.sendMessage(ownerMsg);
				int rowCntalert = pushDao.insertAlert(alertParamMap);
				if(rowCntalert <= 0) 
					result = ServiceResult.FAILED;
			} catch (IOException e) {
				result = ServiceResult.FAILED;
				throw new RuntimeException(e);
			}
		} else if (ownerSession == null ) {
			// 기안자가 로그아웃 상태인 경우, 알림을 바로 보내지 않고, ALERT 에 INSERT 한다.
			int rowCntalert = pushDao.insertAlert(alertParamMap);
			if(rowCntalert <= 0) 
				result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override   // 대결처리
	public ServiceResult modifyLineToApproval(Map<String, Object> paramMap) {
		ServiceResult result = ServiceResult.FAILED;
		
		int rowCnt = sbjdDao.updateLineToInstead(paramMap);
		if(rowCnt > 0) result = ServiceResult.OK;
		return result;
	}

	@Override	// 반려처리
	public ServiceResult modifyLineToReject(int elec_no, String loginUserId, String rejectMsg) {
		ServiceResult result = ServiceResult.FAILED;
		
		Map<String, Object> rejectMap = new HashMap<>();
		rejectMap.put("elec_no", elec_no);
		rejectMap.put("authorized_id", loginUserId);
		rejectMap.put("elec_reject_reason", rejectMsg);
		
		int rowCnt = sbjdDao.updateLineToReject(rejectMap);
		if(rowCnt > 0) result = ServiceResult.OK;
		
		// 기안자에게 반려사유 알림 보내기
		Elec_ApprlineVO line = sbjdDao.selectLineByNoAndAuthorizer(rejectMap);
		String senderId = line.getOwner_id();
		
		String msg = elec_no+"번 문서 반려 사유 : " + rejectMsg;
		Map<String, Object> alertMap = new HashMap<>();
		alertMap.put("alert_content", msg);
		alertMap.put("emp_id", senderId);
		
		WebSocketSession senderSession = sessionMap.get(senderId);
		if(senderSession != null) {
			TextMessage textMessage = new TextMessage(msg);
			int alertCnt = pushDao.insertAlert(alertMap);
			if(alertCnt <= 0) 
				result = ServiceResult.FAILED;
			try {
				senderSession.sendMessage(textMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if ( senderSession == null ) {
			int alertCnt = pushDao.insertAlert(alertMap);
			if(alertCnt <= 0) 
				result = ServiceResult.FAILED;
		}
		return result;
	}
	
}


















