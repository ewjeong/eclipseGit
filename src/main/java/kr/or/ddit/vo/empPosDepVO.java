package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 정다혜
 * @since 2019. 5. 27.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 27.      작성자명       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@NoArgsConstructor
@Data
public class empPosDepVO extends EmployeeVO implements Serializable{
	private String pos_name;
	private String dep_name;
}
