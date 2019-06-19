package kr.or.ddit.mainPage.searchEmployee.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;

/**
 * @author 작성자명
 * @since 2019. 5. 16.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 16.    박연욱                 최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface ISearchEmployeeService {

   /**
    * 입력한 아이디 사원이 존재하는 지 확인
    * @param emp_name, emp_hp
    * @return 사원정보 객체, 존재하지 않는다면 null을 반환
    */
   public EmployeeVO retrieveEmployee(String emp_id);
   
   /**
    * 아이디 찾기 시 입력한 이름과 전화번호로 사원이 존재하는 지 확인
    * @param emp_name, emp_hp
    * @return 사원정보 객체, 존재하지 않는다면 null을 반환
    */
   public EmployeeVO retrieveEmployeeNameHp(String emp_name, String emp_hp);
   
   public ServiceResult modifyEmployee(EmployeeVO employee);
   
   /**
    * 비밀번호 찾기 시 입력한 이름과 아이디로 사원이 존재하는 지 확인
    * @param emp_id
    * @param emp_name
    * @return 사원정보 객체, 존재하지 않는다면 null을 반환
    */
   public EmployeeVO retrieveEmployees(String emp_id, String emp_name);
}