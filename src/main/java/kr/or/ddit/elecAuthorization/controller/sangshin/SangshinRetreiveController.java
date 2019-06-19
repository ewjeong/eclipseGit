package kr.or.ddit.elecAuthorization.controller.sangshin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.elecAuthorization.service.ISangshinService;
import kr.or.ddit.vo.Elec_ApprovalVO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SendVO;

@Controller
public class SangshinRetreiveController {
	@Inject
	ISangshinService sangshinService;
	
	// 동기처리
	@RequestMapping("/elecAuthorization/sangshin/sangshinList")
	public String getApproList(
			@RequestParam(required=false) String searchType
			, @RequestParam(required=false) String searchWord
			, @RequestParam(name="page", required=false , defaultValue="1") long currentPage
			, Model model
			, Authentication user
			){
		PagingVO<Elec_ApprovalVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		EmployeeVO employee = (EmployeeVO) user.getPrincipal();
		String loginUserId = employee.getEmp_id();
		pagingVO.setEmp_id(loginUserId);
		
		long totalRecord = sangshinService.retreiveSangShinCount(pagingVO);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setTotalRecord(totalRecord);
		
		List<Elec_ApprovalVO> sangshinList = sangshinService.retreiveSangShinList(pagingVO);
		pagingVO.setDataList(sangshinList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "elec/sangshin/sangshinList";
	}
	
	// 상신(기안서) 상세 조회
	@RequestMapping("/elecAuthorization/sangshin/sangshinView")
	public String getSangshinView(
			@RequestParam(name="what") String elec_noStr
			, Model model
			, Authentication user
			){
		int elec_no = Integer.parseInt(elec_noStr);
		EmployeeVO employee = (EmployeeVO) user.getPrincipal();
		String loginUserId = employee.getEmp_id();
		
		Elec_ApprovalVO approval = new Elec_ApprovalVO();
		approval.setElec_no(elec_no);
		approval.setElec_writer(loginUserId);
		
		Elec_ApprovalVO sangshin = sangshinService.retreiveSangShin(approval);
		model.addAttribute("sangshin", sangshin);
		
		return "elec/sangshin/sangshinView";
	}
	
}
























