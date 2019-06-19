package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SelectPurOrdVO extends CompanyVO implements Serializable{

	private String elec_comple;
	private String pur_ord_date;
	private String pur_ord_code;
	private String cl_name;
	
	
}
