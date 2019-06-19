package kr.or.ddit.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Order_SalesVO {
	private String sale_ord_code;
	private Integer sale_est_no;
	private String sale_ord_emp_id;
	private String sale_ord_date;
	private String sale_ord_note;
	private String payment;
}
