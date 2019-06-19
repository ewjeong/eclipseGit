package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Ware_Ord_ListVO implements Serializable {
	
	private String pur_ord_code;
	private String pur_ord_date;
	private String cl_name;
	private String cl_no;
	private String elec_comple;
	private String emp_id;
	private String ware_date;
	private String prod_no;
	
	//하나의 발주서에 여러개의 상품을 조회할 수 있도록
	private List<Pur_Oprod_ListVO> pur_oprodList;
	
}
