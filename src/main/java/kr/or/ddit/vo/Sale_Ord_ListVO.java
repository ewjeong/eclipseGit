package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Sale_Ord_ListVO implements Serializable{
	
	private String sale_ord_code;
	private String sale_ord_date;
	private String sale_ord_note;
	private Integer sale_oprod_no;
	private Integer sale_oprod_cost;
	private Integer sale_oprod_qty;
	private String cl_no;
	private String cl_sort;
	private String cl_name;
	private String cl_receive;
	private String cl_bank;
	private String cl_bankno;
	private String cl_depository;
	private String cl_charger;
	private String cl_tel;
	private String cl_add1;
	private String cl_add2;
	private String sale_ord_complete;
	private String emp_id;
	private String emp_name;
	private Integer rel_no;
	private String rel_date;
	private String payment;
	private String stock_qty;
	private Number pur_oprod_qty;
	private String ord_cost;
	private String prod_name;
	private String prod_no;
	private String prod_size;
	private String prod_color;
	private String item_name;
	private String elec_comple;
	private Integer total_cost;
	
	private Ware_Avg_CostVO ware_avg_cost;
	
	//하나의 견적서 당 여러개의 상품을 조회할 수 있도록
	private List<Sale_Eprod_ListVO> sale_eprodList;
	
	//하나의 주문서 당 여러개의 상품을 조회할 수 있도록
	private List<Sale_OprodVO> sale_oprodList;
	
	
	
}
