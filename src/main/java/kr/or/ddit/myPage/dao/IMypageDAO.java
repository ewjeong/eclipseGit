package kr.or.ddit.myPage.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.EmployeeVO;

@Repository
public interface IMypageDAO {

	/**
	 * @author jdh
	 * @param emp_id
	 * @since 2019.05.12 
	 * @return
	 */
	public EmployeeVO selectMypage(String emp_id);
	
	public int updateMypage(EmployeeVO employee);
	
}
