package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Sale_OrdVO implements Serializable{
	
	private String sale_ord_code;
	private Integer sale_est_no;
	private String sale_ord_emp_id;
	private String sale_ord_date;
	private String sale_ord_note;
	private String payment;
	private String elec_comple;
	private Integer total_cost;
	
	List<Sale_OprodVO> sale_oprodList;
}
