package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SalesProfitVO implements Serializable{

	private String rel_date;
	private int rel_no;
	private String rel_delete;
	private int sale_oprod_qty;
//	private int sale_oprod_cost;
	private long sale_oprod_cost;
	private String prod_name;
	private String cl_no;
	private String cl_name;
	private String emp_name;
	private int pur_oprod_qty;
//	private int pur_oprod_cost;
	private long pur_oprod_cost;
	private int prod_no;
	private String pur_ord_code;
	private String ware_date;
	private String ware_delete;
	private String item_code;
	private String item_name;
	
	private long soc;	//sale_oprod_qty * sale_oprod_cost
	private long poc;
	
	
	
	
}
