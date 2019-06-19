package kr.or.ddit.elecAuthorization.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.Elec_ApprlineVO;


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
@Repository
public interface ISeungBanJeanDaeDAO {
	/**
	 * 승인처리
	 * @param map (elec_no, authorized_id) 를 담고 있음
	 * @return
	 */
	int updateLineToApproval(Map<String, Object> map);

	/**
	 * 해당 문서의 로그인한 결재자의 결재선 가져오기
	 * @param map
	 * @return
	 */
	Elec_ApprlineVO selectLineByNoAndAuthorizer(Map<String, Object> map);

	/**
	 * 해당 문서의 결재선의 최종(마지막) 결재순위 가져오기
	 * @param elec_no 최종(마지막) 결재 순위
	 * @return
	 */
	int selectMaxPriority(int elec_no);

	/**
	 * 전결처리
	 * @param paramMap 문서번호, 로그인한 유저아이디
	 * @return
	 */
	int updateLineToJeonGyeol(Map<String, Object> paramMap);

	/**
	 * 대결처리
	 * @param paramMap 문서번호, 대결자아이디, 로그인한 유저아이디
	 * @return
	 */
	int updateLineToInstead(Map<String, Object> paramMap);

	/**
	 * 반려처리
	 * @param paramMap 문서번호, 로그인한 유저아이디, 반려사유
	 * @return
	 */
	int updateLineToReject(Map<String, Object> paramMap);

}
















