package kr.or.ddit.vo;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthoritiesVO implements GrantedAuthority{
	
	private String emp_id;
	private String role_auth;
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return role_auth;
	}
	
}
