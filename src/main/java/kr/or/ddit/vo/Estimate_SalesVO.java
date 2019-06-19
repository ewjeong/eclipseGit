package kr.or.ddit.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Estimate_SalesVO {
	private Integer sale_est_no;
	private String sale_est_date;
	private String sale_detail;
	private String cl_no;
	private String emp_id;
	
}
