package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Sale_EstVO implements Serializable{
	
	private Integer sale_est_no;
	private String sale_est_date;
	private String sale_detail;
	private String cl_no;
	private String emp_id;
	
	List<Sale_EprodVO> sale_eprodList;
	
}
