package kr.or.ddit.configuration.menuManage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.Emp_Pos_Dep_VO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.MenuVO;
import kr.or.ddit.vo.ResourcesVO;

/**
 * @author 작성자명
 * @since 2019. 5. 17.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 17.   박연욱   		    최초작성
 * 2019. 5. 27    이초연         결재선 사원 리스트 조회 때문에 메서드 하나 추가함
 * 
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface ISettingDAO {

	/**
	 * 관리자가 사원에게 권한을 부여할 때 사원명을 입력받아 사원명,부서명,직책 조회
	 * @param emp_name
	 * @return
	 */
	public List<Emp_Pos_Dep_VO> selectEmployeelist(String emp_name);
	
	/**
	 * @author 이초연
	 * 결재선 등록할 때, 결재자 선택을 위한 사원 리스트 조회
	 * @return
	 */
	public List<Emp_Pos_Dep_VO> selectEmployeelistNoneParam();
	
	
	/**
	 * 사원명, 사번을 입력받아 사원정보를 조회
	 * @param emp_name
	 * @param emp_no
	 * @return
	 */
	public Emp_Pos_Dep_VO selectEmployee(@Param("emp_name") String emp_name, @Param("emp_no") String emp_no);
	
	
	/**
	 *  사원명과 사원번호를 입력받아 그에 맞게 부서코드, 직책코드 수정
	 * @param employee
	 * @return
	 */
	public int updateEmpAuth(Emp_Pos_Dep_VO employee);
	/**
	 * 사원정보 입력받아 그에 맞는 권한role 수정
	 * @param employee
	 * @return
	 */
	public int updateEmpRole(Emp_Pos_Dep_VO employee);
	
	
	/**
	 * 사원명, 사원번호 입력받아 사원의 퇴사여부 업데이트
	 * @param employee
	 * @return
	 */
	public int updateEmpDel(Emp_Pos_Dep_VO employee);
	
	/**
	 * 회사정보를 수정
	 * @param company
	 * @return
	 */
	public int updateCompany(CompanyVO company);
	
	/**
	 * 회사 정보 조회
	 * @return
	 */
	public CompanyVO selectCompany();
	
	/**
	 * 메뉴명을 입력받아 조회
	 * @param res_name
	 * @return
	 */
	public ResourcesVO selectMenu(String res_name);
	
	/**
	 * 메뉴리스트를 관리하기 위한 메뉴조회(header.jsp 메뉴 세팅)
	 * @return
	 */
	public List<ResourcesVO> selectMenuList();
	
	/**
	 * 메뉴 활성,비활성 처리
	 * @param menu
	 * @return
	 */
	public int updateMenu(ResourcesVO menu);
}
