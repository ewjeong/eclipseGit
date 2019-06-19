
package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.or.ddit.common.UpdateHint;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 박연욱
 * @since 2019. 5. 15.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 15.     박연욱       최초수정
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@NoArgsConstructor
@Data
public class EmployeeVO implements Serializable,UserDetails{
	
	
	public EmployeeVO(String emp_id) {
		this.emp_id = emp_id;
	}
	
	private String emp_id;
	private String dep_code;
	private String pos_code;
	@NotBlank(groups=UpdateHint.class)
	private String emp_pass;
	private String emp_name;
	@NotBlank(groups=UpdateHint.class)
	private String emp_mail;
	@NotBlank(groups=UpdateHint.class)
	private String emp_hp;
	@NotBlank(groups=UpdateHint.class)
	private String emp_add1;
	private String emp_gene;
	
	private String emp_bir;
	private String emp_manage;
	private String emp_reg1;
	private String emp_reg2;
	@NotBlank(groups=UpdateHint.class)
	private String emp_zip;
	@NotBlank(groups=UpdateHint.class)
	private String emp_add2;
	private String emp_del;
	private String emp_no;
	
	
	private List<GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return emp_pass;
	}
	@Override
	public String getUsername() {
		return emp_id;
	}
	@Override
	public boolean isAccountNonExpired() {
		return !"Y".equals(emp_del);
	}
	@Override
	public boolean isAccountNonLocked() {
		return !"Y".equals(emp_del);
	}
	@Override
	public boolean isCredentialsNonExpired() {	//비밀번호 변경시 이력관리
		return !"Y".equals(emp_del);
	}
	@Override
	public boolean isEnabled() {	//탈퇴의사를 밝힌경우
		return !"Y".equals(emp_del);
	}
	
	//동시 세션을 제어하기 위해 UserDetails 내에서는 반드시 hashCode와 equals 메서드를 명확하게 재정의해야한다.
	//그렇지 않은 경우, 동일 아이디로 다른 기기에서 접속시 동일 principal임을 확인하지 못하는 경우가 발생한다.
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emp_pass == null)? 0 : emp_pass.hashCode());
		result = prime * result + ((emp_id == null) ? 0 : emp_id.hashCode());
		result = prime * result + ((emp_name == null) ? 0 : emp_name.hashCode());
		return result;
		
	}
	
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		EmployeeVO other = (EmployeeVO) obj;
		if(emp_pass == null){
			if(other.emp_pass != null)
				return false;
		}else if(!emp_pass.equals(other.emp_pass))
			return false;
		if(emp_id == null){
			if(other.emp_id != null)
				return false;
		}else if(!emp_id.equals(other.emp_id))
			return false;
		if(emp_name == null){
			if(other.emp_id != null)
				return false;
		}else if(!emp_name.equals(other.emp_name))
			return false;
		return true;
	}
	
}











