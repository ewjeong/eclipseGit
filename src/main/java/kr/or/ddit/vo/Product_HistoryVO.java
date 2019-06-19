package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Product_HistoryVO implements Serializable{
	
	private Integer prod_no;
	private String prod_his_startdate;
	private String prod_his_enddate;
	private Integer prod_his_cost;
	
}
