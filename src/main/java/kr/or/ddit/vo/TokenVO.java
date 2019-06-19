package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
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


@NoArgsConstructor
@Data
public class TokenVO implements Serializable{
	
	private String emp_id;
	private String access_token;
	private String refresh_token;
	private int expires_in;
	private String scope;
	private String token_type;
	private String id_token;

}
