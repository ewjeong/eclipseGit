package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.common.InsertHint;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Sale_OprodVO implements Serializable{
	
	private Integer sale_oprod_no;
	private String sale_ord_code;
	@NotBlank(groups=InsertHint.class)
	private Integer sale_oprod_qty;
	@NotBlank(groups=InsertHint.class)
	private Integer sale_oprod_cost;
	private Integer prod_no;
	private String prod_name;
	private Integer prod_cost;
	private String prod_size;
	private String prod_outline;
	private String prod_details;
	private String prod_img;
	private String prod_color;
	private Number prod_qty;
	private String item_code;
	private String item_name;
	
}
