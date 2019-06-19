package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pur_OprodVO implements Serializable{
	
	private Integer pur_oprod_no;
	private String pur_ord_code;
	private Integer pur_oprod_qty;
	private Integer pur_oprod_cost;
	private Integer prod_no;
	
}
