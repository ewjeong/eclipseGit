package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Sale_Est_ListVO implements Serializable{
	
	private Integer sale_est_no;
	private String sale_est_date;
	private String sale_detail;
	private Integer sale_eprod_no;
	private String emp_id;
	private String emp_name;
	private String cl_no;
	private String cl_sort;
	private String cl_name;
	private String cl_receive;
	private String cl_bank;
	private String cl_bankno;
	private String cl_depository;
	private String cl_tel;
	private String ord_compl;
	private String cl_add1;
	private String cl_add2;
	private String cl_charger;
	
	
	private Ware_Avg_CostVO ware_avg_cost;
	
	//여러건을 조회할 수 있도록
	private List<Sale_Eprod_ListVO> sale_eprodList;
}
