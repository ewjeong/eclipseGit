package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.common.InsertHint;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pur_OrdVO implements Serializable{
	
	private String pur_ord_code;
	private String ord_emp_id;
	private Integer pur_est_no;
	@NotBlank(groups=InsertHint.class)
	private String pur_ord_date;
	private String payment;
	
	//하나의 발주서에 여러개의 상품을 조회할 수 있도록
	private List<Pur_Oprod_ListVO> pur_oprodList;
	
	private List<Pur_Eprod_ListVO> pur_eprodList;
}
