package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReferenceVO implements Serializable{
	
	private Integer elec_no;
	private String emp_id;
	private String ref_view;
	private String ref_view_date;
	
}
