package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Elec_FormVO implements Serializable{
	
	private String elec_form_code;
	private String elec_form_html;
	private String elec_form_writer;
	private String elec_form_title;
	private String send_code;
	private String emp_name;
}
