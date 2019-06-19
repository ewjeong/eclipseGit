package kr.or.ddit.mainPage.searchEmployee.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.mainPage.searchEmployee.dao.ISearchEmployeeDAO;
import kr.or.ddit.vo.EmployeeVO;

@Service
public class SearchEmployeeServiceImpl implements ISearchEmployeeService{

   @Autowired
   private ShaPasswordEncoder shaPasswordEncoder;
   
   ISearchEmployeeDAO searchDAO;
   
   @Inject
   public void setSearchDAO(ISearchEmployeeDAO searchDAO) {
      this.searchDAO = searchDAO;
   }

   @Override
   public EmployeeVO retrieveEmployee(String emp_id) {
      EmployeeVO employee = searchDAO.selectEmployee(emp_id);
      
      return employee;
   }
   

   @Transactional
   @Override
   public ServiceResult modifyEmployee(EmployeeVO employee) {
      
      ServiceResult result = null;
      
      //사원유무
      EmployeeVO savedEmployee = retrieveEmployee(employee.getEmp_id());
      
      //입력한 아이디와 이름이 사원의 아이디와 이름과 일치할때
      if(savedEmployee.getEmp_id().equals(employee.getEmp_id()) && savedEmployee.getEmp_name().equals(employee.getEmp_name()) ){
         String encPass = shaPasswordEncoder.encodePassword(employee.getEmp_pass(), null);
         employee.setEmp_pass(encPass);
         int row = searchDAO.updateEmployee(employee);
         if(row>0){
            result = ServiceResult.OK;
         }else{
            result = ServiceResult.FAILED;
         }
         //수정실패
         //아이디 또는 이름 일치X
      }else{
         result = ServiceResult.INVALIDID;
      }
      return result;
   }

   @Override
   public EmployeeVO retrieveEmployees(String emp_id, String emp_name) {
      
      EmployeeVO employee = searchDAO.selectEmployees(emp_id,emp_name);
//      if(employee == null)
//         throw new CommonException(emp_id+" 또는 "+emp_name+"에 해당하는 사원이 없습니다.");
      return employee;
   }

   @Override
   public EmployeeVO retrieveEmployeeNameHp(String emp_name, String emp_hp) {
      EmployeeVO employee = searchDAO.selectEmployeeNameHp(emp_name, emp_hp);
      return employee;
   }
   
   





   

}