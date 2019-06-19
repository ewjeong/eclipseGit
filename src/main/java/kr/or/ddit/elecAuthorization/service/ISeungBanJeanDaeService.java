package kr.or.ddit.elecAuthorization.service;

import java.util.Map;

import kr.or.ddit.enumpkg.ServiceResult;

/**
 * @author 이초연
 * @since 2019. 5. 23
 * @version 1.0
 * @see
 * <pre>
 * [[개정이력(Modification Information)]] 
 * 수정일        수정자              수정내용
 * ==========   ========    ============================= 
 * 2019. 5. 23	 이초연		 최초 작성 - 승인 처리중
 * 2019. 5. 28  이초연      대결 처리
 * 2019. 5. 29  이초연      반려 처리
 * 
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 * 
 * 승인, 반려, 전결, 대결 처리 
 * 
 */
public interface ISeungBanJeanDaeService {

	/**
	 * 승인처리 
	 * @param elec_no
	 * @param authorized_id
	 * @return OK, FAILED, null 인 경우 NOTEXIST
	 */
	ServiceResult modifyLineToApproval(int elec_no, String authorized_id);

	/**
	 * 전결처리
	 * @param elec_no
	 * @param loginUserId
	 * @return  OK, FAILED
	 */
	ServiceResult modifyLineToJeonGyeol(int elec_no, String loginUserId);

	/**
	 * 대결처리
	 * @param paramMap
	 * @return OK, FAILED
	 */
	ServiceResult modifyLineToApproval(Map<String, Object> paramMap);

	/**
	 * 반려처리
	 * @param elec_no 
	 * @param loginUserId
	 * @param rejectMsg  반려 사유
	 * @return
	 */
	ServiceResult modifyLineToReject(int elec_no, String loginUserId, String rejectMsg);


}
