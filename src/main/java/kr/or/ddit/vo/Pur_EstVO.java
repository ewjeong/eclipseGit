package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.common.InsertHint;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pur_EstVO implements Serializable{
	
	private Integer pur_est_no;
	private Integer pur_er_no;
	@NotBlank(groups=InsertHint.class)
	private String pur_est_date;
	private String pur_charger;
	private String payment;
	private String est_emp_id;
	@NotBlank(groups=InsertHint.class)
	private String pur_eprod_cost;
	
	//여러건을 조회할 수 있도록
	private List<Pur_Eprod_ListVO> pur_eprodList;
	
	//여러건을 조회할 수 있도록
	private List<Pur_Er_Prod_ListVO> pur_er_prodList;
	
}
