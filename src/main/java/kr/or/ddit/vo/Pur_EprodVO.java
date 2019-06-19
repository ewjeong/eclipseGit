package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pur_EprodVO implements Serializable{
	
	private Integer pur_eprod_no;
	private Integer prod_no;
	private Integer pur_est_no;
	private Integer pur_eprod_qty;
	private Integer pur_eprod_cost;
	
}
