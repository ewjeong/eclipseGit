package kr.or.ddit.vo;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Sale_Eprod_ListVO implements Serializable {
	private String prod_name;
	private String prod_size;
	private Integer prod_no;
	private Integer sale_eprod_cost;
	private Integer sale_eprod_qty;
	private Integer prod_cost;
	private String prod_outline;
	private String prod_details;
	private String prod_img;
	private String prod_color;
	private String item_name;
}
