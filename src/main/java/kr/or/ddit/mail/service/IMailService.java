 
package kr.or.ddit.mail.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.TokenVO;

/**
 * @author 작성자명
 * @since 2019. 5. 16.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                수정자                    수정내용
 * --------     --------    ----------------------
 * 2019. 5. 16.   박연욱                   최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */

public interface IMailService {
	
	
	/**
	 *  인증 후 발급받은 access token 과 refresh token을 저장
	 * @param token
	 * @return
	 */
	public ServiceResult  createToken(TokenVO token);
	
	
	/**
	 * access token이 재발급 될 때마다 업데이트 
	 * @param emp_id
	 * @return
	 */
	public ServiceResult modifyToken(TokenVO token);
	
	/**
	 * id를  입력받아 가지고있는 token 조회
	 * @param emp_id
	 * @return
	 */
	public TokenVO retrieveToken(String emp_id);
	

}
