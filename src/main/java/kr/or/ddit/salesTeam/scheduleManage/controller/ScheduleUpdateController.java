package kr.or.ddit.salesTeam.scheduleManage.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.salesTeam.scheduleManage.service.IScheduleManageService;
import kr.or.ddit.vo.ActiveVO;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class ScheduleUpdateController {
	
	@Inject
	IScheduleManageService service;
	
	
	//활동관리 수정(활동관리 등록 폼 뿌려주기)/salesTeam/schedule/scheduleUpdate
		@RequestMapping(value="/salesTeam/schedule/scheduleUpdate/{ac_no}/{emp_id}", method=RequestMethod.GET)
		public String schduleModfy(
				@PathVariable(name="ac_no",required=false) String ac_no
				,@PathVariable(name="emp_id",required=false)String emp_id
				,Authentication auth
				,ActiveVO activeVO
				,Model model 
		){
			//에러메세지
			String msg =null;
			String view = null;
			
			//세션에 저장된 아이디와 활동조회때 등록한 아이디를 비교한다.
			EmployeeVO employeeVO =  (EmployeeVO) auth.getPrincipal();
			
			//같다면
			if(employeeVO.getEmp_id().equals(activeVO.getEmp_id())){
				//전달받은 ac_no와 활동관리 조회에 있는 리스트들을 비교한다.
				if(activeVO.getAc_no()==Integer.parseInt(ac_no)){
					//같다면 파라미터로 받아온 ac_no와 일치하는 활동조회 찾아낸다.
					activeVO= service.retrieveActiveForDel(Integer.parseInt(ac_no));
					//그정보를 model로 넘겨준다.
					model.addAttribute("active",activeVO);
					//form으로 이동
					view= "sales/schedule/scheduleForm";
				}
			}else{
				//에러문자 반환
				msg = "본인계정으로 등록한 글이 아닙니다.";
				model.addAttribute("msg",msg);
				view= "sales/schedule/calendar";
			}
				return view;
		}
		
		//활동관리 수정
		@RequestMapping(value="/salesTeam/schedule/scheduleUpdate", method=RequestMethod.POST)
		public String ScheduleUpdate(
//				@RequestParam("ac_no")String ac_no
				@Valid @ModelAttribute("active") ActiveVO activeVO, Errors errors
				,Model model
				,RedirectAttributes rttr
				){
			
			String msg = null;
			String view = null;
//			activeVO.setAc_no(activeVO.getAc_no());
			if(errors.hasErrors()){
				view = "salesTeam/schedule/scheduleForm";
				return view;
			}
			
			
			
			ServiceResult result = service.modifySchedule(activeVO);
			if(ServiceResult.OK.equals(result)){
				msg="수정 성공";
				rttr.addFlashAttribute("msg",msg);
				view = "redirect:/salesTeam/schedule/scheduleList";
			}else{
				msg="수정 실패";
				rttr.addFlashAttribute("msg",msg);
				view = "salesTeam/schedule/scheduleForm";
			}
			return view;
		}
		
		
		
		
		
		//활동관리 수정
//			@RequestMapping(value="/salesTeam/schedule/scheduleUpdate", method=RequestMethod.POST)
//			public String ScheduleUpdate(
//					@RequestParam("ac_no") String ac_no
//					,Authentication auth
//					,ActiveVO activeVO
//					,Model model 
//					){
//				
//				String msg = null;
//				String view = null;
//				ServiceResult result = null;
//				//세션에 저장되어있는 아이디를 불러온다
//				EmployeeVO employeeVO= (EmployeeVO) auth.getPrincipal();
//				//받아온 파라미터와 일치하는 active의 list들을 검색한다.
//				activeVO = service.retrieveActiveForDel(Integer.parseInt((ac_no)));
//				//받아온 파라미터와 검색한 값이 같으면서
//				if(Integer.parseInt(ac_no)==activeVO.getAc_no()){
//					//아이디가 일치하다면
//					if(employeeVO.getEmp_id().equals(activeVO.getEmp_id())){
//							//같으면 수정성공
//							result = service.updateSchedule(activeVO);
//							view = "redirect:/salesTeam/schedule/scheduleLists";
//					}else{
//						//삭제 실패
//						msg="본인이 등록한 일정만 수정 가능합니다.";
//						model.addAttribute("msg",msg);
//						view = "sales/schedule/scheduleForm";
//					}
//				}
//				
//				return view;
//			}
//		
			
			
			
		
}
