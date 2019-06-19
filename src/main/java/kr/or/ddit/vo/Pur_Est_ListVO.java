package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pur_Est_ListVO implements Serializable{
	
	private String pur_ord_code;
	private Integer pur_est_no;
	private String pur_est_date;
	private String payment;
	private String cl_no;
	private String cl_sort;
	private String cl_name;
	private String cl_receive;
	private String cl_bank;
	private String cl_bankno;
	private String cl_depository;
	private String cl_tel;
	private String com_no;
	private String com_name;
	private String com_tel;
	private String com_add1;
	private String com_add2;
	private String total_cost;
	private String prod_name;
	private String prod_size;
	private String pur_eprod_cost;
	private String pur_eprod_qty;
	private String est_cost;
	
	
	//여러건을 조회할 수 있도록
	private List<Pur_Eprod_ListVO> pur_eprodList;
	
}
