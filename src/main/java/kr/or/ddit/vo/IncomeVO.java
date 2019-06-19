package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class IncomeVO extends ReleaseVO implements Serializable{
	
	private String sale_oprod_qty   ;
	private String sale_oprod_cost  ;
	private String cl_no            ;
	private String cl_name          ;
	private String emp_id           ;
	private String emp_name         ;
	private String emp_no           ;
	private String prod_name;
	private String income;
	private String month;
	private int sales;
}                                   
