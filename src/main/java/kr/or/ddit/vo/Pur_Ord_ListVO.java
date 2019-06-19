package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pur_Ord_ListVO implements Serializable{
	
	private String pur_ord_code;
	private String pur_ord_date;
	private String payment;
	private String ord_emp_id;
	private Integer pur_oprod_no;
	private Integer pur_oprod_cost;
	private Integer pur_oprod_qty;
	private String cl_no;
	private String cl_charger;
	private String cl_sort;
	private String cl_name;
	private String cl_receive;
	private String cl_bank;
	private String cl_bankno;
	private String cl_depository;
	private String pur_ord_complete;
	private String emp_id;
	private String emp_name;
	private Integer ware_no;
	private String ware_date;
	private String item_name;
	private String com_no;
	private String com_name;
	private String com_add1;
	private String com_add2;
	private String com_tel;
	private String ord_cost;
	private String prod_no;
	private String prod_name;
	private String prod_color;
	private String prod_size;
	private String elec_comple;
	

	//하나의 발주서에 여러개의 상품을 조회할 수 있도록
	private List<Pur_Oprod_ListVO> pur_oprodList;
	
}
