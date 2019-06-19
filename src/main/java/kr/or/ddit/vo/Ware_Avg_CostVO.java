package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Ware_Avg_CostVO implements Serializable{
	
	private Integer prod_no;
	private Integer prod_cost;
	
}
