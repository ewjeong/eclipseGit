package kr.or.ddit.myPage.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.myPage.dao.IMypageDAO;
import kr.or.ddit.vo.EmployeeVO;

@Service
public class MypageServiceImpl implements IMypageService {

	@Autowired
	private ShaPasswordEncoder shaPasswordEncoder;
	
	@Inject
	IMypageDAO mypageDAO;

	@Override
	public EmployeeVO retrieveMypage(String emp_id) {
		return mypageDAO.selectMypage(emp_id);
	}

	@Override
	public ServiceResult modifyMypage(EmployeeVO employee) {
		
		ServiceResult result = null;
		String encPass= shaPasswordEncoder.encodePassword(employee.getEmp_pass(),null);
		employee.setEmp_pass(encPass);
		int rowCnt=	mypageDAO.updateMypage(employee); 
		if(rowCnt>0){
			//비밀번호 암호화
			result = ServiceResult.OK;
		}else{
			result=ServiceResult.FAILED;
		}
		return result; 
	}

}
