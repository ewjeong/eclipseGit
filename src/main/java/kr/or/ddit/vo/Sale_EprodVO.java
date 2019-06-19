package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Sale_EprodVO implements Serializable{
	
	private Integer sale_eprod_no;
	private Integer sale_est_no;
	private Integer prod_no;
	private Integer sale_eprod_qty;
	private Integer sale_eprod_cost;
	
}
