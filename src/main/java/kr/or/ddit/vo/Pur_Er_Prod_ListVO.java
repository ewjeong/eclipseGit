package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pur_Er_Prod_ListVO implements Serializable{
	private String item_name;
	private String prod_name;
	private String prod_size;
	private String prod_color;
	private Integer prod_no;
	private Integer pur_erprod_qty;
	private Integer pur_er_prod_no;
	private Integer prod_cost;
	private String prod_outline;
	private String prod_details;
	private String prod_img;
	private String item_code;
}
