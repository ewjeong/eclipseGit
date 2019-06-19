package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SelectPurOrdListVO extends Ware_Ord_ListVO implements Serializable{
	
	private String pur_charger;
	private String cl_no;
	private String pur_est_no;
	
	
}
