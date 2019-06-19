package kr.or.ddit.mainPage.signUp.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AuthoritiesVO;
import kr.or.ddit.vo.DepNameVO;
import kr.or.ddit.vo.EmployeeVO;

/**
 * 
 * @author 박연욱
 * @since 2019. 5. 13.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 13.    박연욱       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface ISignUpService {
	
	/**
	 * 사원 신규 등록
	 * @param employee
	 * @return OK: 성공, FAILED: 실패, PKDUPLICATED: 아이디 중복
	 */
	public ServiceResult createEmployee(EmployeeVO employee);

	/**
	 * 사원 신규 등록시 신규사원  권한 부여
	 *  
	 * @param id
	 * @return  OK: 성공, FAILED: 실패
	 */
	public ServiceResult createEmployeeRole(String emp_id);
	

	
	/**
	 * 사원 유무 조회
	 * @param emp_id
	 * @return 존재하지 않는 경우, CommonException발생
	 */
	public EmployeeVO retrieveEmployee(String emp_id);
	
	
	/**
	 * 아이디중복체크 작업
	 * @param emp_id
	 * @return
	 */
	public int idCheck(String emp_id);
	
	/**
	 * 아이디를 입력받아 부서명 조회
	 * @param emp_id
	 * @return
	 */
	public DepNameVO retrieveDepName(String emp_id);
	
}
