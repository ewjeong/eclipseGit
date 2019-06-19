package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pur_Er_ListVO implements Serializable{
	
	private Integer pur_er_no;
	private String req_emp_id;
	private String pur_er_date;
	private Integer pur_er_prod_no;
	private Integer prod_no;
	private String prod_name;
	private Integer prod_cost;
	private String prod_size;
	private String prod_outline;
	private String prod_details;
	private String prod_img;
	private String prod_color;
	private String item_code;
	private String item_name;
	private String cl_no;
	private String cl_sort;
	private String cl_name;
	private String cl_receive;
	private String cl_bank;
	private String cl_bankno;
	private String cl_depository;
	private String cl_tel;
	private String cl_charger;
	private String com_no;
	private String com_name;
	private String com_tel;
	private String com_add1;
	private String com_add2;
	private String emp_name;
	private String pur_erprod_qty;
	
	//여러건을 조회할 수 있도록
	private List<Pur_Er_ProdVO> pur_er_prodList;
}
