package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReferenceListVO implements Serializable {
	private Integer elec_no;
	private String emp_id;
	private String ref_view;
	private String ref_view_date;
	private String send_code;
	private String elec_writer;
	private String elec_form_code;
	private String elec_title;
	private String elec_content;
	private String elec_senddate;
	private String elec_deadline;
	private String elec_comple;
	private String send_type_code;
	
	private SendVO send;
}
