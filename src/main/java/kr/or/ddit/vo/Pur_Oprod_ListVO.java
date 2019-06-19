package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pur_Oprod_ListVO implements Serializable{
	private String pur_ord_code;
	private String item_name;
	private String prod_name;
	private Integer prod_no;
	private String item_code;
	private Integer prod_cost;
	private String prod_size;
	private String prod_outline;
	private String prod_details;
	private String prod_img;
	private String prod_color;
	private Integer prod_qty;
	private Integer pur_oprod_qty;
	private Integer pur_oprod_cost;

}
