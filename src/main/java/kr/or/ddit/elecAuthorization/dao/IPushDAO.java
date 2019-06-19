package kr.or.ddit.elecAuthorization.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AlertVO;
import kr.or.ddit.vo.Elec_ApprlineVO;

/**
 * @author 이초연
 * @since 2019. 5. 31
 * @version 1.0
 * @see
 * <pre>
 * [[개정이력(Modification Information)]] 
 * 수정일        수정자     수정내용
 * ==========   ======    ============== 
 * 2019. 5. 31	이초연		최초 작성
 * 2019. 6. 04  이초연      알림 테이블에 insert
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 * 알림 기능을 위한 DAO
 * 
 */
@Repository
public interface IPushDAO {
	/**
	 * 중간 결재자가 승인 버튼을 클릭한 경우, 현재 로그인한 결재자의 다음 결재자 조회
	 * @param elec_no 문서번호
	 * @param nextPriority 현재 로그인한 결재자의 결재순위 + 1 
	 * @return 다음 결재자의 결재라인 (결자라인코드와 다음결재자ID가 들어 있다.)
	 */
	Elec_ApprlineVO selectNextAuthorizer(Elec_ApprlineVO line);

	/**
	 * 다음 결재자가 로그아웃 상태인 경우, ALERT 테이블에 INSERT한다.
	 * @param paramMap : ELEC_APPRLINE 테이블의 PK 와 알림 content
	 * @return  성공 : 1, 실패 : 0
	 */
	int insertAlert(Map<String, Object> paramMap);

	/**
	 * 알림 리스트 조회
	 * @param loginUserId   로그인한 유저
	 * @return  List<AlertVO>
	 */
	List<AlertVO> selectAlertListById(String loginUserId);

}
