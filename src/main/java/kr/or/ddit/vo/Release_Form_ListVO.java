package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Release_Form_ListVO implements Serializable{
	private String sale_ord_code;
	private String sale_ord_date;
	private String cl_no;
	private String cl_name;
	private Integer sale_oprod_cost;
	private Integer sale_oprod_qty;
	private String item_name;
	private Integer prod_no;
	private String prod_name;
	private String prod_size;
	private String prod_color;
	
	//하나의 발주서에 여러개의 상품을 조회할 수 있도록
	private List<Sale_OprodVO> sale_oprodList;
	
}
