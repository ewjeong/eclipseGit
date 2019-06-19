package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResourcesVO implements Serializable{
	
	private String res_id;
	private String res_name;
	private String res_pattern;
	private String res_type;
	private int res_flag;
	
}
