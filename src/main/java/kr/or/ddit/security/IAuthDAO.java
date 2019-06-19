package kr.or.ddit.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.EmployeeVO;

@Repository
public interface IAuthDAO {

	public List<GrantedAuthority> selectAuthoritiesByUsername(String emp_id);
	
	/**
	 * 사원들의 권한을 통해 메뉴접근을 위한 사원들의 권한 리스트조회
	 * @return
	 */
	public EmployeeVO selectAuthorityEmp(String emp_id);
	
	//public List<>
}
