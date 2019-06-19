package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SelectSaleOrdListVO extends Release_Ord_ListVO implements Serializable{

//	private String pur_charger;
	private String cl_no;
	private String sale_est_no;
	
}
