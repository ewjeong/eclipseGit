
package kr.or.ddit.mainPage.searchEmployee.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
 * 2019. 5. 16.      박연욱       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface ISearchEmployeeDAO {
   
   /**
    * 아이디 찾기 시 입력한 이름과 전화번호를 가진 사원이 존재하는지 확인
    * @param emp_name, emp_hp 입력받은 사원 아이디와 전화번호를 조회한다.
    * @return 사원정보 객체, 존재하지 않는다면 null을 반환
    */
   public EmployeeVO selectEmployeeNameHp(@Param("emp_name") String emp_name, @Param("emp_hp") String emp_hp);
   
   /**
    * 회원 아이디로 조회
    * @param emp_id
    * @return
    */
   public EmployeeVO selectEmployee(String emp_id);
   
   /**
    * 비밀번호 찾기 시 입력한 이름과 아이디로 사원이 존재하는 지 확인
    * @param emp_id
    * @param emp_name
    * @return 사원정보 객체, 존재하지 않는다면 null을 반환
    */
   public EmployeeVO selectEmployees(@Param("emp_id") String emp_id, @Param("emp_name")String emp_name);
   
   /**
    * 비밀번호 찾기에서 새로운 비밀번호 발급
    * @param employee
    * @return
    */
   public int updateEmployee(EmployeeVO employee);
   
}