package kr.or.ddit.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductHistoryVO {
	private Integer prod_no;
	private String prod_his_startdate;
	private String prod_his_enddate;
	private Integer prod_his_cost;
}
