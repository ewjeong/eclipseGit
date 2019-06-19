package kr.or.ddit.mainPage.signUp.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.mainPage.signUp.dao.ISignUpDAO;
import kr.or.ddit.vo.AuthoritiesVO;
import kr.or.ddit.vo.DepNameVO;
import kr.or.ddit.vo.EmployeeVO;

@Service
public class SignUpServiceImpl implements ISignUpService {

	@Autowired
	private ShaPasswordEncoder shaPasswordEncoder;
	
	ISignUpDAO signUpDAO;
	
	@Inject
	public void setSignUpDAO(ISignUpDAO signUpDAO) {
		this.signUpDAO = signUpDAO;
	}
	
	@Transactional
	@Override
	public ServiceResult createEmployee(EmployeeVO employee) {
		
		boolean exception = false;
		try{
			EmployeeVO idCheck =  retrieveEmployee(employee.getEmp_id());
			if(idCheck != null){
				exception = false;
			}
		}catch (CommonException e) {
			exception = true;
		}
		
		ServiceResult result = null;
		
		if(exception){
			//salt는 강도
			String encPass = shaPasswordEncoder.encodePassword(employee.getEmp_pass(), null);
			employee.setEmp_pass(encPass);
			int rowCnt = signUpDAO.insertEmployee(employee);
			
			//가입 성공
			if(rowCnt>0){
				signUpDAO.insertEmployeeRole(employee.getEmp_id());
				result = ServiceResult.OK;
				
			}else{   //가입 실패
				result = ServiceResult.FAILED;
			}
		//이미 있는 회원
		}else{
		
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	
	}
	
	@Override
	public ServiceResult createEmployeeRole(String emp_id) {

		ServiceResult sr = null;
		int row = signUpDAO.insertEmployeeRole(emp_id);
		
		if(row>0) {
			sr = ServiceResult.OK;
			
		}else {
			sr = ServiceResult.FAILED;
		}
		return sr;
		
	}

	@Override
	public EmployeeVO retrieveEmployee(String emp_id) {
		EmployeeVO employee = signUpDAO.selectEmployee(emp_id);
		if(employee == null)
			throw new CommonException(emp_id+"에 해당하는 사원이 없습니다.");
		return employee;
	}

	@Override
	public int idCheck(String emp_id) {
		int result = 0;
		result = signUpDAO.idCheck(emp_id);
		
		return result;
	}

	@Override
	public DepNameVO retrieveDepName(String emp_id) {
		DepNameVO depName = signUpDAO.selectDepName(emp_id);
		return depName;
	}

	

	

}
