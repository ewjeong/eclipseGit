package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Emp_Pos_Dep_VO implements Serializable{
	
	private String emp_name;
	private String emp_no;
	private String emp_id;
	private String pos_code;
	private String pos_name;
	private String dep_code;
	private String dep_name;
	private String emp_del;
	private String role_auth;
}
