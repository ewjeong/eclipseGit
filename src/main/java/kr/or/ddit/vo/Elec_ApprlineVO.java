package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Elec_ApprlineVO implements Serializable{
	
	private Integer elec_apprline_code;
	private Integer elec_no;
	private String appr_status_code;
	private Integer elec_priority;
	private String elec_reject_reason;
	private String elec_appr_date;
	private String authorized_id;
	private String owner_id;
	private String instead_id;
	
	// 결재 문서 상세 조회 화면에서
	// 결재선 정보를 가져오기 위한 프로퍼티 
	private String emp_name;
	private String pos_name;
	
}
