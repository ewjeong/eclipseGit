package kr.or.ddit.myPage.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;

@Service
public interface IMypageService {
	
	public EmployeeVO retrieveMypage(String emp_id);
	
	public ServiceResult modifyMypage(EmployeeVO employee);
}
