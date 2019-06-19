package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AlertVO implements Serializable{
	
	private Integer alert_no;
	private Integer elec_apprline_code;
	private String alert_time;
	private String alert_checkdate;
	private String alert_title;
	private String alert_content;
	private String alert_url;
	
}
