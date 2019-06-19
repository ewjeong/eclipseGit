package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 재고조회
 * @author 정은우
 * @since 2019. 5. 16.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 16.      정은우       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@NoArgsConstructor
@Data
public class StockVO implements Serializable{
	
	private Integer prod_no;
	private String prod_name;
	private String prod_size;
	private String prod_color;
	private String item_name;
	private Integer avg_cost;
	private String stock;
	
	
}
