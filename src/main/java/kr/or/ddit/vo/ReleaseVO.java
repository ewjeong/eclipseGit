package kr.or.ddit.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.common.InsertHint;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 정은우
 * @since 2019. 5. 07.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 07.      정은우       최초작성
 * 2019. 6. 01.      정은우       Validation 추가
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */

@NoArgsConstructor
@Data
public class ReleaseVO implements Serializable{
	
	private Integer rel_no;
	private String sale_ord_code;
	private String emp_id;
	@NotBlank(groups=InsertHint.class)
	private String rel_date;
	private String rel_delete;
	
}








