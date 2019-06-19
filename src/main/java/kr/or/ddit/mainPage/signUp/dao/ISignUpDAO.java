package kr.or.ddit.mainPage.signUp.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AuthoritiesVO;
import kr.or.ddit.vo.DepNameVO;
import kr.or.ddit.vo.EmployeeVO;


/**
 * @author 박연욱
 * @since 2019. 5. 13.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 13.      박연욱       최초작성
 * 2019. 5.  15     박연욱		최초수정
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface ISignUpDAO {
	
	/**
	 * 사원 신규 등록
	 * @param 신규 등록할 사원의 정보를 가진 VO
	 * @return row count > 0 : 성공 => 성공한 컬럼의 수
	 */
	public int insertEmployee(EmployeeVO employee);
	
	/**
	 * 사원 신규 등록시 사원의 권한을 부여하는 작업
	 * @param id 사원의 아이디
	 * @return  row count > 0 : 성공 => 성공한 컬럼의 수
	 */
	public int insertEmployeeRole(String emp_id);

	
	/**
	 * 회원가입시 중복인지 아닌지 확인하기 위한 회원 조회
	 * @param emp_id 입력받은 사원 아이디를 조회한다.
	 * @return 사원정보 객체, 존재하지 않는다면 null을 반환
	 */
	public EmployeeVO selectEmployee(String emp_id);
	
	

	/**
	 * 아이디 중복조회 사용
	 * @param emp_id
	 * @return
	 */
	public int idCheck(String emp_id);
	
	/**
	 * 아이디를 입력받아 부서명 조회
	 * @param emp_id
	 * @return
	 */
	public DepNameVO selectDepName(String emp_id); 

	
	
}
