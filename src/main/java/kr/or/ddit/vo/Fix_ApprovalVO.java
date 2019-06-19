package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Fix_ApprovalVO implements Serializable {
	
	public Fix_ApprovalVO(Integer fix_priority, String authorized_id) {
		super();
		this.fix_priority = fix_priority;
		this.authorized_id = authorized_id;
	}
	private Integer fix_apprline_no;
	private Integer fix_priority;
	private String fix_delete;
	private String authorized_id;
	
	// 상신등록 시 적용버튼을 클릭했을 때,
	// 선택된 결재선 정보를 가져오기 위한 프로퍼티
	private String emp_name;
	private String pos_name;
	
}
