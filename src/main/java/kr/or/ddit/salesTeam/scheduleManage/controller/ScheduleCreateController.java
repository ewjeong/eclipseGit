package kr.or.ddit.salesTeam.scheduleManage.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.salesTeam.scheduleManage.service.IScheduleManageService;
import kr.or.ddit.vo.ActiveVO;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class ScheduleCreateController {
	
	@Inject
	IScheduleManageService service;
	
	@RequestMapping(value="/salesTeam/schedule/scheduleInsert", method=RequestMethod.GET)
	public String listForm(){
		return "sales/schedule/scheduleForm";	
	}
	
	@RequestMapping(value="/salesTeam/schedule/scheduleInsert", method=RequestMethod.POST)
	public String getScheduleForm(
			@Valid @ModelAttribute("active") ActiveVO activeVO, Errors errors
			,@RequestParam("cl_no") String cl_no
			,Model model
			,Authentication auth
			,RedirectAttributes rttr
			){
		
		//로그인된 아이디를 가져온다.
 		EmployeeVO employeeVO= (EmployeeVO)auth.getPrincipal();
 		String emp_id = employeeVO.getEmp_id();
 		activeVO.setEmp_id(emp_id);
//		model.addAttribute("emp_id",activeVO.getEmp_id());
		rttr.addFlashAttribute("emp_id",activeVO.getEmp_id());
		//고객코드를 activeVO에 셋팅한다.
		activeVO.setCl_no(cl_no);
		
		String view = null;
		String msg = null;
		if(errors.hasErrors()){
			view = "sales/schedule/scheduleForm";
			return view;
		}
		//인서트 스케쥴
		ServiceResult result = service.createSchedule(activeVO);
		if(ServiceResult.OK.equals(result)){
			
			msg="등록 성공";
			view = "redirect:/salesTeam/schedule/scheduleList";
		}else{
				msg = "필수 데이터 누락";
				view = "sales/schedule/scheduleForm";
		}
		
//		model.addAttribute("msg",msg);
		rttr.addFlashAttribute("msg",msg);
		return view;
	}
	
	
	//ajax로 클라이언트 정보 가져오기
	@ResponseBody
	@RequestMapping(value="/salesTeam/schedule/clList",produces = "application/json;charset=UTF-8")
	public List<ClientVO> clientAjax(
			@RequestParam("cl_name") String cl_name
			){
		List<ClientVO> clList= service.selectClient(cl_name);
		
		return clList; 
	}
	
	
	
	
	
	
	
}
