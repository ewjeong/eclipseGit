package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FixLineVO implements Serializable{
	private Integer fl_no;
	private String fl_title;
	private String send_code;
	private String owner_id;
	private String emp_name;
	
	private String first;
	private String second;
	private String last;
	
	private List<Fix_ApprovalVO> fix_approvalList;
	
}
