package kr.or.ddit.elecAuthorization.controller.reference;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.elecAuthorization.service.IReferenceService;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReferenceListVO;

/**
 * @author 이초연
 * @since 2019. 5. 15
 * @version 1.0
 * @see
 * <pre>
 * [[개정이력(Modification Information)]] 
 * 수정일        수정자     수정내용
 * ==========   ======    ============== 
 * 2019. 5. 15	이초연		최초 작성
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 *  참조함 관련 컨트롤러
 *  
 */
@Controller
public class ReferenceRetreiveController {
	@Inject
	IReferenceService service;
	
	// 상세 조회
	@RequestMapping("/elecAuthorization/reference/referenceView")
	public String getReferenceView(
			@RequestParam(name="what") String elec_noStr
			, Model model
			, Authentication user
			){
		EmployeeVO employee = (EmployeeVO) user.getPrincipal();
		String emp_id = employee.getEmp_id();
		int elec_no = Integer.parseInt(elec_noStr);
		
		ReferenceListVO referece = new ReferenceListVO();
		referece.setElec_no(elec_no);
		referece.setEmp_id(emp_id);
		
		ReferenceListVO dbReference = service.retreiveReference(referece);
		model.addAttribute("reference", dbReference);
		return "elec/reference/referView";
	}
	
	// 동기 처리
	@RequestMapping("/elecAuthorization/reference/referenceList")
	public String getReferenceList(
			@RequestParam(required=false) String searchType
			, @RequestParam(required=false) String searchWord
			, @RequestParam(name="page", required=false, defaultValue="1") long currentPage
			, Model model
			, Authentication user
			){
		EmployeeVO employee = (EmployeeVO) user.getPrincipal();
		String emp_id = employee.getEmp_id();
		// dataList 의 ReferenceListVO 들에 emp_id 셋팅
		PagingVO<ReferenceListVO> pagingVO = new PagingVO<>();
		pagingVO.setEmp_id(emp_id);
		
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		long totalRecord = service.retreiveReferenceCount(pagingVO);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setTotalRecord(totalRecord);
		
		List<ReferenceListVO> referenceList = service.retreiveReferenceList(pagingVO);
		pagingVO.setDataList(referenceList);
		model.addAttribute("pagingVO", pagingVO);
		
		return "elec/reference/referList";
	}
}























