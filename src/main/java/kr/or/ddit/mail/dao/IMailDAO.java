package kr.or.ddit.mail.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.TokenVO;

/**
 * @author 박연욱
 * @since 2019. 06. 04
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 6. 04.      박연욱       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IMailDAO {

	/**
	 *  인증 후 발급받은 access token 과 refresh token을 저장
	 * @param token
	 * @return
	 */
	public int insertToken(TokenVO token);
	
	
	/**
	 * access token이 재발급 될 때마다 업데이트 
	 * @param emp_id
	 * @return
	 */
	public int updateToken(TokenVO token);
	
	/**
	 * id를  입력받아 가지고있는 token 조회
	 * @param emp_id
	 * @return
	 */
	public TokenVO selectToken(String emp_id);
	
	

}
