package kr.or.ddit.elecAuthorization.controller.approval;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.elecAuthorization.service.IApprovalService;
import kr.or.ddit.elecAuthorization.service.IElecManageService;
import kr.or.ddit.vo.CompleteListVO;
import kr.or.ddit.vo.Elec_ApprlineVO;
import kr.or.ddit.vo.Elec_ApprovalVO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.FixLineVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.WaitListVO;

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
 * 결재함의 결재 대기 문서 리스트, 결재 완료 문서 리스트 조회
 * 결재함의 결재 대기 상세 문서, 결재 완료 상세 문서 조회
 */
@Controller
public class ApprovalRetreiveController {
	@Inject
	IApprovalService approvalService;
	@Inject
	IElecManageService elecManageService;
	
	// 동기처리
	@RequestMapping("/elecAuthorization/approval/approList")
	public String getApproList(
			@RequestParam(name="page", required=false, defaultValue="1") long waitCurrentPage
			, @RequestParam(name="page", required=false, defaultValue="1") long CompleCurrentPage
			, @RequestParam(required=false) String waitSearchType
			, @RequestParam(required=false) String waitSearchWord
			, @RequestParam(required=false) String compleSearchType
			, @RequestParam(required=false) String compleSearchWord
			, Model model
			, HttpServletResponse resp
			, Authentication user
			){
		// 로그인한 유저
		EmployeeVO employee = (EmployeeVO) user.getPrincipal();
		String loginUserId = employee.getEmp_id();
		
		// 결재 대기 문서 ------------------------------------------------------------------------------
			// 페이징 처리
		PagingVO<WaitListVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchType(waitSearchType);
		pagingVO.setSearchWord(waitSearchWord);
			// 로그힌한 유저 id 셋팅
		pagingVO.setEmp_id(loginUserId);
		
		long totalRecord = approvalService.retrieveWaitApprovalCount(pagingVO);
		pagingVO.setCurrentPage(waitCurrentPage);
		pagingVO.setTotalRecord(totalRecord);
		
		List<WaitListVO> approvalList = approvalService.retrieveWaitApprovalList(pagingVO);
		pagingVO.setDataList(approvalList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		// 결재 완료 문서 ------------------------------------------------------------------------------
		PagingVO<CompleteListVO> complePagingVO = new PagingVO<>();
		complePagingVO.setSearchType(compleSearchType);
		complePagingVO.setSearchWord(compleSearchWord);
		// 로그힌한 유저 id 셋팅
		complePagingVO.setEmp_id(loginUserId);
		
		long comTotalRecord = approvalService.retrieveCompleteApprovalCount(complePagingVO);
		complePagingVO.setCurrentPage(CompleCurrentPage);
		complePagingVO.setTotalRecord(comTotalRecord);
		
		List<CompleteListVO> completeList = approvalService.retrieveCompleteApprovalList(complePagingVO);
		complePagingVO.setDataList(completeList);
		
		model.addAttribute("complePagingVO", complePagingVO);
		
		
		// 캐시에 남겨진 json 데이터가 보여지는 걸 방지하기 위한 설정
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Cache-Control", "no-store");
		resp.setDateHeader("Expires", 0);
		
		return "elec/approval/approList";
	}
	
	//비동기처리
	@RequestMapping(value="/elecAuthorization/approval/approList", produces="application/json;charset=UTF-8")
	public PagingVO<Elec_ApprovalVO> ajaxList(){
		return null;
	}
	
	// 결재 대기 문서 상세 조회
	@RequestMapping("/elecAuthorization/approval/approView")
	public String getApproView(
			@RequestParam(name="what") String elec_noStr
			, Model model
			){
		int elec_no = Integer.parseInt(elec_noStr);
		
		// 결재선 가져오기
		List<Elec_ApprlineVO> lineList = approvalService.retrieveLineListByElecNo(elec_no);
		// 해당 문서 가져오기
		WaitListVO approval = approvalService.retrieveWaitApproval(elec_no);
		model.addAttribute("approval", approval);
		model.addAttribute("lineList", lineList);
		
		return "elec/approval/approView";
	}
	
	// 결재 완료 문서 상세 조회
	@RequestMapping("/elecAuthorization/approval/completeView")
	public String getCompleteView(
			@RequestParam(name="what") String elec_noStr
			, Model model
			){
		int elec_no = Integer.parseInt(elec_noStr);
		
		CompleteListVO approval = approvalService.retrieveCompleteApproval(elec_no);
		model.addAttribute("approval", approval);
		
		return "elec/approval/completeView";
	}
	
}
