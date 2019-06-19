package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RolesVO implements Serializable{
	
	private String role_auth;
	private String role_name;
	private String role_detail;
	private String role_cre_date;
	private String role_modi_date;
	private Integer role_position;
	
}
