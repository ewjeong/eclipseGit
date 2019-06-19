package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 구매팀 발주서 작성용 - 견적서내용을 불러와야함
 * @author 정은우
 * @since 2019. 5. 20.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 20.      정은우       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@NoArgsConstructor
@Data
public class SelectPurEstVO extends Pur_Est_ListVO implements Serializable{
	
	private String pur_ord_date;
	private String com_name;
	private String com_tel;
	private String com_add1;
	private String com_add2;
	private String ord_emp_id;
	
}
