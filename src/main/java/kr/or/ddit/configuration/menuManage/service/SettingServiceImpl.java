package kr.or.ddit.configuration.menuManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.configuration.menuManage.controller.menuAdviceController;
import kr.or.ddit.configuration.menuManage.dao.ISettingDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.Emp_Pos_Dep_VO;
import kr.or.ddit.vo.MenuVO;
import kr.or.ddit.vo.ResourcesVO;

/**
 * @author 작성자명
 * @since 2019. 5. 17.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                  수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 17.    박연욱     	  	최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class SettingServiceImpl implements ISettingService {

	@Inject
	ISettingDAO settingDAO;
	
	/**
	 * 사원명 입력받아 리스트를 조회
	 */
	@Override
	public List<Emp_Pos_Dep_VO> retrieveEmployeelist(String emp_name) {
		List<Emp_Pos_Dep_VO> empList =  settingDAO.selectEmployeelist(emp_name);
		return empList;
	}
	
	/**
	 * 사원명과 사번을 통해  한 명의 사원 정보를 조회
	 */
	@Override
	public Emp_Pos_Dep_VO retrieveEmployee(String emp_name, String emp_no) {
		Emp_Pos_Dep_VO employee = settingDAO.selectEmployee(emp_name, emp_no);
		
		
		return employee;
	}

	
	/**
	 * 직책코드와 부서코드를 수정
	 */
	@Transactional
	@Override
	public ServiceResult modifyEmpAuth(Emp_Pos_Dep_VO employee) {
		 
		
		ServiceResult result = null;
	//	Emp_Pos_Dep_VO selectEmployee = settingDAO.selectEmployee(employee.getEmp_name(), employee.getPos_name(), employee.getDep_name());

			int row = settingDAO.updateEmpAuth(employee);
				
			if(row>0){
			modifyEmpRole(employee);
			result = ServiceResult.OK;
			
			}else {
				result = ServiceResult.FAILED;
			}
		
		return result;
	}
	
	@Override
	public ServiceResult modifyEmpRole(Emp_Pos_Dep_VO employee) {
		ServiceResult result = null;
		int row = settingDAO.updateEmpRole(employee);
		if(row>0) {
			result =  ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}



	/**
	 * 사원의 퇴사여부를 수정
	 */
	@Transactional
	@Override
	public ServiceResult modifyEmpDel(Emp_Pos_Dep_VO employee) {
		
		ServiceResult result = null;
		//	Emp_Pos_Dep_VO selectEmployee = settingDAO.selectEmployee(employee.getEmp_name(), employee.getPos_name(), employee.getDep_name());

				int row = settingDAO.updateEmpDel(employee);
				if(row>0){
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAILED;
				}
			
			return result;
		}

	/**
	 * 회사의 정보를 수정
	 */
	@Transactional
	@Override
	public ServiceResult modifyCompany(CompanyVO company) {

		ServiceResult result = null;
		int row = settingDAO.updateCompany(company);
		if(row>0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		return result;
		
	}

	@Override
	public CompanyVO retrieveCompany() {
		CompanyVO company = settingDAO.selectCompany();
		return company;
	}

	
	/**
	 * 메뉴관리 부분 중 전체 메뉴 조회
	 */
	@Override
	public List<ResourcesVO> retrieveMenuList() {
		List<ResourcesVO> menuList = settingDAO.selectMenuList();
		return menuList;
	}

	@Override
	public ResourcesVO retrieveMenu(String res_name) {
		ResourcesVO menu = settingDAO.selectMenu(res_name);
		return menu;
	}

	@Override
	public ServiceResult modifyMenu(ResourcesVO menu) {
		ServiceResult result = null;
		int row = settingDAO.updateMenu(menu);
		if(row>0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<Emp_Pos_Dep_VO> selectEmployeelistNoneParam() {
		return settingDAO.selectEmployeelistNoneParam();
	}


	
}
