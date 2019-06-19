package kr.or.ddit.elecAuthorization.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import kr.or.ddit.elecAuthorization.dao.IAttachmentDAO;
import kr.or.ddit.elecAuthorization.dao.IPushDAO;
import kr.or.ddit.elecAuthorization.dao.ISangshinDAO;
import kr.or.ddit.elecAuthorization.dao.ISeungBanJeanDaeDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.AttachmentVO;
import kr.or.ddit.vo.Elec_ApprlineVO;
import kr.or.ddit.vo.Elec_ApprovalVO;
import kr.or.ddit.vo.Elec_FormVO;
import kr.or.ddit.vo.FixLineVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class SangshinServiceImpl implements ISangshinService {
	@Inject
	ISangshinDAO sangshinDao;
	@Inject
	IAttachmentDAO attachmentDao;
	@Inject
	ISeungBanJeanDaeDAO sbjdDao;
	@Inject
	IPushDAO pushDao;
	
	@Resource(name="sessionMap")
	Map<String, WebSocketSession> sessionMap;
	
	@Value("#{appInfo['pdspath']}")
	String attachmentSavePath;
//	@Override
//	public ServiceResult createApproval(Elec_ApprovalVO approval) {
//		int rowCnt = sangshinDao.insertApproval(approval);
//		ServiceResult result = ServiceResult.FAILED;
//		if(rowCnt > 0) {
//			result = ServiceResult.OK;
//		}
//		return result;
//	}

	@Override
	public long retreiveSangShinCount(PagingVO<Elec_ApprovalVO> pagingVO) {
		return sangshinDao.selectSangShinCount(pagingVO);
	}

	@Override
	public List<Elec_ApprovalVO> retreiveSangShinList(PagingVO<Elec_ApprovalVO> pagingVO) {
		return sangshinDao.selectSangShinList(pagingVO);
	}

	@Override
	public Elec_ApprovalVO retreiveSangShin(Elec_ApprovalVO approval) {
		Elec_ApprovalVO savedApproval = sangshinDao.selectSangShin(approval);
		if(savedApproval == null) throw new CommonException(approval.getElec_no() + "번 문서가 존재하지 않습니다.");
		return savedApproval;
	}

	@Override
	public List<FixLineVO> retreiveFixLineList() {
		return sangshinDao.selectFixLineList();
	}

	@Override
	public List<Elec_FormVO> retreiveFormList() {
		return sangshinDao.selectFormList();
	}

	@Override
	public FixLineVO retreiveFixLine(int fl_no) {
		FixLineVO fixLine = sangshinDao.selectFixLine(fl_no);
		if(fixLine == null) throw new CommonException(fl_no +"번 결재선이 없습니다.");
		return fixLine;
	}

	@Override
	public Elec_FormVO retreiveForm(String elec_form_code) {
		Elec_FormVO form = sangshinDao.selectForm(elec_form_code);
		if(form == null) throw new CommonException(elec_form_code+"번 결재양식이 없습니다.");
		return form;
	}
	
	// 첨부파일의 메타 데이터 셋팅
	private void preProcessAttachmentList(Elec_ApprovalVO approval) {
		List<AttachmentVO> fileList = approval.getFileList();
		if(fileList == null) return;
		for(AttachmentVO file : fileList){
			String saveName = UUID.randomUUID().toString();
			file.setAttach_path(attachmentSavePath + "/" + saveName);
		}
	}
	
	private void processAttachment(Elec_ApprovalVO approval) {
		// 메타 데이터 셋팅
		preProcessAttachmentList(approval);
		
		List<AttachmentVO> fileList = approval.getFileList();
		if(fileList != null && fileList.size() > 0) { // null 체킹만하면 attachment.xml의 insertAll 쿼리문에서 foreach문 실행 안 될 수도 있다.
			attachmentDao.insertAllAttachment(approval);
			
			for(AttachmentVO attach : fileList){
				String savePath = attach.getAttach_path();
				File saveFile = new File(savePath); // 저장할 파일
				MultipartFile fileItem = attach.getFileItem();
				
				try(
					InputStream is = fileItem.getInputStream();	
				){
					FileUtils.copyInputStreamToFile(is, saveFile);
				} catch (IOException e) {
					throw new RuntimeException(e);
				} 
			}
		}
		
		// 삭제 파일 처리
		int[] deleteFileNos = approval.getDeleteFileNos();
		if(deleteFileNos != null && deleteFileNos.length > 0) {
			// 삭제 대상 첨부 파일 조회
			List<AttachmentVO> delFileList = attachmentDao.selectAttachmentList(approval);
			
			// 파일의 메타데이터 삭제
			attachmentDao.deleteAttachmentAll(approval);
			
			// 실제 파일 삭제
			deleteRealFile(delFileList);
		}
	}
	
	private void deleteRealFile(List<AttachmentVO> delFileList) {
		if(delFileList != null && delFileList.size() > 0){ // delFileList.size() > 0 조건이 없으면 nullPointException 발생!
			for(AttachmentVO file : delFileList){
				if(file == null ) continue;
				String savePath = file.getAttach_path(); // left outer join 으로 인해 attch_no 가 없더라도 delFileList는 무조건 1개가 생기고 file에 관한 데이터는 다 null이다. 따라서 
				if(file.getAttach_path() != null){ // 첨부파일이 없는 게시글을 삭제할 경우를 고려해야 하니까 이 조건을 반드시 추가해야 한다.
					FileUtils.deleteQuietly(new File(savePath));
				}
			}
		}
	}

	@Transactional
	@Override
	public ServiceResult createSangshin(Elec_ApprovalVO approval) {
		ServiceResult result = ServiceResult.FAILED;
		
		// Elec_approvalVO 와 AttachmentVO 에 insert 
		int rowCnt = sangshinDao.insertApproval(approval);
		if(rowCnt > 0) { // insertApproval 성공 시
			processAttachment(approval); // 첨부파일 처리
			
//			// insertApproval 에서 새롭게 추가된 elec_approval의 elec_no 가져오기
//			int elec_no = sangshinDao.selectApproval();
//			// 새롭게 추가된 elec_no 셋팅하기
//			approval.setElec_no(elec_no);       ==> 이걸 할 필요 없어. selectKey 에서 생성된 elec_no는 approval에 이미 담겨있다.
			
			// 발주서나 주문서 결재 등록 시, elec_comple 을 'N'으로 업뎃하기
			String sendTypeCode = approval.getSend_type_code();
			int orderSheetUpdateCnt = 0;
			if(StringUtils.isNotBlank(sendTypeCode)){
				if(StringUtils.startsWith(sendTypeCode, "SO")){
					// 주문서인 경우
					orderSheetUpdateCnt = sangshinDao.updateSaleOrdSheet(sendTypeCode);
					if(orderSheetUpdateCnt > 0) result = ServiceResult.OK;
				} else if(StringUtils.startsWith(sendTypeCode, "PO")){
					// 발주서인 경우
					orderSheetUpdateCnt = sangshinDao.updatePurOrdSheet(sendTypeCode);
					if(orderSheetUpdateCnt > 0) result = ServiceResult.OK;
				}
			}
			// Elec_ApprLineVO 에 insert ---------------------------
			int lineCnt = sangshinDao.insertApprLine(approval);
		
			// ReferenceVO 에 insert -------------------------------
			int referCnt = sangshinDao.insertRefenrece(approval);
			
			if(lineCnt > 1 && referCnt > 0 ) { // // insertApproval 성공한 후, insertApprLine 와 insertRefenrece 도 성공하면
				result = ServiceResult.OK; // 그제서야 OK
			}
			
			// **알림기능 구현 순서** : 1.그 결재순위 +1 한 결재순위를 갖고 있는 결재자를 찾는다.
			int elec_no = approval.getElec_no();
			// 해당 문서의 로그인한 결재자의 결재선 가져오기
			Elec_ApprlineVO paramLine = new Elec_ApprlineVO();
			paramLine.setElec_no(elec_no);
			paramLine.setElec_priority(1);
			
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
						if(resultAlertCnt <= 0) 
							result = ServiceResult.FAILED;
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				} else if (webSocketSession == null) {
					//2-2. 다음 결재자가 로그아웃 상태인 경우, ALERT에 INSERT한다.
					int resultAlertCnt = pushDao.insertAlert(paramMap);
					if(resultAlertCnt <= 0) 
						result = ServiceResult.FAILED;
				}
			} // line null checking 
			
		}
		return result;
	}

}



















