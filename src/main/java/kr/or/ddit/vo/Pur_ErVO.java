package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.common.InsertHint;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pur_ErVO implements Serializable{
	
	private Integer pur_er_no;
	private String req_emp_id;
	@NotBlank(groups=InsertHint.class)
	private String pur_er_date;
	private String cl_no;
	@NotBlank(groups=InsertHint.class)
	private String pur_erprod_qty;
	private String cl_name;
	private String prod_name;
	private String prod_no;
	
	List<Pur_Er_ProdVO> pur_er_prodList;
}
