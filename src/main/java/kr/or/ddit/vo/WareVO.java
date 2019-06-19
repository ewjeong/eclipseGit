package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.common.InsertHint;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WareVO implements Serializable{
	
	private Integer ware_no;
	private String pur_ord_code;
	private String emp_id;
	@NotBlank(groups=InsertHint.class)
	private String ware_date;
	private String ware_delete;
	
}
