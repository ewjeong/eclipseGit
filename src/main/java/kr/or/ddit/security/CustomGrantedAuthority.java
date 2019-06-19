package kr.or.ddit.security;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class CustomGrantedAuthority implements GrantedAuthority, Serializable{

	private String authority;
	private String role_auth;
	
	
	
	@Override
	public String getAuthority() {
		return authority;
	}

}
