package kr.or.ddit.elecAuthorization.controller.approval;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.socket.WebSocketSession;

import kr.or.ddit.elecAuthorization.service.ISeungBanJeanDaeService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;


/**
 * @author 이초연
 * @since 2019. 5. 23
 * @version 1.0
 * @see
 * <pre>
 * [[개정이력(Modification Information)]] 
 * 수정일        수정자     수정내용
 * ==========   ======    ============== 
 * 2019. 5. 23	 이초연		최초 작성 - 승인, 전결 처리
 * 2019. 5. 28     이초연      		대결 처리
 * 2019. 5. 29     이초연      		반려 처리
 * 
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 * 승인, 전결, 반려, 대결, 결재완료 처리를 하는 컨트롤러
 */
@Controller
public class ApprovalUpdateController {
//	@Inject
//	IApprovalService approService;
	
	@Inject
	ISeungBanJeanDaeService sbjdService;
	
	// 승인 처리 ------------------------------------------------------------------------------
	@RequestMapping("/elecAuthorization/approval")
	public String approval(
			@RequestParam(name="what") String elec_noStr
			, Authentication user
			, Model model
			, RedirectAttributes redirectAttributes
			){
		String view = null;
		String msg = null;
		
		EmployeeVO employee = (EmployeeVO) user.getPrincipal();
		String authorized_id = employee.getEmp_id();
		
		int elec_no = Integer.parseInt(elec_noStr);
		
		// 문서번호와 로그인한 결재자에 해당하는 결재선 승인처리로 udpate 하기
		ServiceResult result = sbjdService.modifyLineToApproval(elec_no, authorized_id);
		if(ServiceResult.FAILED.equals(result)){
			view = "elec/approval/approView?what="+elec_no;
			msg = "승인에 실패했습니다.";
		} else if (ServiceResult.NOTEXIST.equals(result)) {
			view = "elec/approval/approView?what="+elec_no;
			msg = "존재하지 않는 문서 및 결재선 입니다.";
		} else if (ServiceResult.OK.equals(result)) {
			view = "redirect:/elecAuthorization/approval/approList";
			msg = "APPROVAL";
			redirectAttributes.addFlashAttribute("message", msg);
		}
		
		model.addAttribute("message", msg);
		return view;
	}
	
	// 전결 처리 ---------------------------------------------------------------------------------
	@RequestMapping("/elecAuthorization/jeonGyeol")
	public String jeonGyeol(
			@RequestParam(name="what") String elec_noStr
			, Authentication user
			, RedirectAttributes redirectAttributes
			, Model model
			){
		String view = null;
		String msg = null;
		
		int elec_no = Integer.parseInt(elec_noStr);
		EmployeeVO employee = (EmployeeVO) user.getPrincipal();
		String loginUserId = employee.getEmp_id();
		
 		ServiceResult result = sbjdService.modifyLineToJeonGyeol(elec_no, loginUserId);
 		if(ServiceResult.OK.equals(result)){
 			view = "redirect:/elecAuthorization/approval/approList";
 			msg = "JEONGYEOL";
 			redirectAttributes.addFlashAttribute("message", msg);
 		} else if( ServiceResult.FAILED.equals(result)) {
 			view = "elec/approval/approView?what="+elec_no;
 			msg = "전결실패";
 			model.addAttribute("message", msg);
 		}
 		
		return view;
	}
	
	// 대결 처리 ---------------------------------------------------------------------------------------
	// 대결자를 지정해주면 그 다음 프로세스는? 
	// 성공하면 approList로 넘어간다. 해당 문서는 대기문서리스트에 없어야 한다.
	@RequestMapping("/elecAuthorization/daeGyeol")
	public String insteadApproval(
			String instead_id
			, int elec_no
			, Model model
			, RedirectAttributes redirectAttributes
			, Authentication user
			){
		String view = null;
		String msg = null;
		EmployeeVO employee = (EmployeeVO) user.getPrincipal();
		String loginUserId = employee.getEmp_id();
		
		// 문서번호와 로그인한 유저아이디, 대결자로 지정할 유저아이디를 저장할 Map
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("elec_no", elec_no);
		paramMap.put("authorized_id", loginUserId);
		paramMap.put("instead_id", instead_id);
		
		ServiceResult result = sbjdService.modifyLineToApproval(paramMap);
		
		if(ServiceResult.FAILED.equals(result)){
			view = "elec/approval/approView?what="+elec_no;
			msg = "대결실패";
			model.addAttribute("message", msg);
		} else if(ServiceResult.OK.equals(result)){
			view = "redirect:/elecAuthorization/approval/approList";
			msg = "DAEGYEOL";
			redirectAttributes.addFlashAttribute("message", msg);
		}
		return view;
	}
	
	// 반려 처리 --------------------------------------------------------------------------------------
	// 반려는 과장, 팀장만 가능하다.
	// 첫번째나 두번째 결재자가 반려 버튼을 클릭한 경우, 결재는 즉시 종료되어 기안자에게 통보된다.
	// 대기문서나 완료 문서 리스트에서 반려처리된 문서 걸러져야 함.
	// 반려된 문서는 최초 기안자의 상신함으로 전달되고, 상태 컬럼이 '반려' 라고 출력되어야 한다.
	
	// 예를 들어, 반려처리된 발주서는 기안자의 상신함에 들어간다. 
	// 기안자는 이 발주서를 수정해서 다시 기안할 수 있다.
	@RequestMapping("/elecAuthorization/reject")
	public String rejectApproval(
			int elec_no
			, String rejectMsg
			, Authentication user
			, Model model
			, RedirectAttributes redirectAttributes
			){
		String view = null;
		String msg = null;
		
		EmployeeVO employee = (EmployeeVO) user.getPrincipal();
		String loginUserId = employee.getEmp_id();
		
		ServiceResult result = sbjdService.modifyLineToReject(elec_no,  loginUserId, rejectMsg);
		if( ServiceResult.FAILED.equals(result) ){
			view = "elec/approval/approView?what="+elec_no;
			msg = "반려실패";
			model.addAttribute("message", msg);
			
		} else if( ServiceResult.OK.equals(result) ){
			view = "redirect:/elecAuthorization/approval/approList";
			msg = "REJECT";
			redirectAttributes.addFlashAttribute("message", msg);
		}
		return view;
	}
}













