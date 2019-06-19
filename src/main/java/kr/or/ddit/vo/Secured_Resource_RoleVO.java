package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Secured_Resource_RoleVO implements Serializable{
	
	private String role_auth;
	private String res_id;
	
}
