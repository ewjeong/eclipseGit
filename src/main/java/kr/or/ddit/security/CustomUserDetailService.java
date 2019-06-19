package kr.or.ddit.security;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.ddit.mainPage.signUp.dao.ISignUpDAO;
import kr.or.ddit.vo.EmployeeVO;

/**
 * @author 박연욱
 * @since 2019. 5. 15.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 15.    박연욱 			      최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */

/**
 * 데이터베이스의 사용자 신원정보와 권한 정보를 조회하기 위한 UserDetailsService 구현체
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Inject
	ISignUpDAO empDAO;
	
	@Inject
	IAuthDAO authDAO;
	
	
	@Override
	public UserDetails loadUserByUsername(String emp_id) throws UsernameNotFoundException {
		EmployeeVO user = empDAO.selectEmployee(emp_id);
		if(user==null) {
			System.out.println(emp_id+"에 해당하는 사원이 없음");
			throw new UsernameNotFoundException(emp_id+"에 해당하는 사원이 없음");
		}
		System.out.println("로그인 성공");
	
	
//		List<GrantedAuthority> authorities = authDAO.selectAuthoritiesByUsername(emp_id);
//		user.setAuthorities(authorities);
		return user;
			
	}
	
	

	
}








