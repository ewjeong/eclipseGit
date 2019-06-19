package kr.or.ddit.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.common.InsertHint;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pur_Er_ProdVO implements Serializable{
	
	private String item_name;
	private Integer pur_er_prod_no;
	private Integer pur_er_no;
	private Integer prod_no;
	private String prod_name;
	private String prod_color;
	private String prod_size;
	private String pur_erprod_qty;
	
	
}
