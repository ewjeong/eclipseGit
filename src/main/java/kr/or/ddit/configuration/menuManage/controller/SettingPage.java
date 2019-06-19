package kr.or.ddit.configuration.menuManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.configuration.menuManage.service.ISettingService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.Emp_Pos_Dep_VO;
import kr.or.ddit.vo.MenuVO;
import kr.or.ddit.vo.ResourcesVO;
 
@Controller
public class SettingPage {

	@Inject
	ISettingService settingService;

	@RequestMapping(value = "/setting/settingPage", method = RequestMethod.GET)
	public String settingView() {
		return "settingUp/settingPage";
	}

	@ResponseBody // ajax data로 내보내기
	@RequestMapping(value = "/setting/settingPage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public List<Emp_Pos_Dep_VO> authorizationSetting(@RequestParam("emp_name") String emp_name) {

		// 사원명 부서명 직책
		List<Emp_Pos_Dep_VO> authList = settingService.retrieveEmployeelist(emp_name);
		return authList;
	}
	

	@RequestMapping(value = "/setting/updateAuth.do", method = RequestMethod.POST)
	public String updateEmpAuth(@RequestParam("inputemp_name") String emp_name, @RequestParam("inputemp_no") String emp_no, @RequestParam("inputpos_name") String pos_name, 
														@RequestParam("inputdep_name") String dep_name, RedirectAttributes model) {
		String msg = null;
		String view = "settingUp/settingPage";
		String pos_code = null;
		String dep_code = null;
		Emp_Pos_Dep_VO emp = settingService.retrieveEmployee(emp_name, emp_no);
		switch(pos_name) {
			case "사원":
				pos_code = "e001";
				break;
			case "대리":
				pos_code = "e004";
				break;
			case "과장":
				pos_code = "e002";
				break;
			case "팀장":
				pos_code = "e003";
				break;
			case "상무":
				pos_code = "super";
				break;
			case "-":
				pos_code = "no_pos";
				break;
			
		}
		
		switch(dep_name) {
		case "구매1팀":
			dep_code = "p001";
			break;
		case "구매2팀":
			dep_code = "p002";
			break;
		case "구매3팀":
			dep_code = "p003";
			break;
		case "구매4팀":
			dep_code = "p004";
			break;
		case "구매5팀":
			dep_code = "p005";
			break;
		case "영업1팀":
			dep_code = "s001";
			break;
		case "영업2팀":
			dep_code = "s002";
			break;
		case "영업3팀":
			dep_code = "s003";
			break;
		case "영업4팀":
			dep_code = "s004";
			break;
		case "영업5팀":
			dep_code = "s005";
			break;
		case "-":
			dep_code = "no_dep";
			break;
		}
		
		emp.setDep_code(dep_code);
		emp.setPos_code(pos_code);

		ServiceResult result = settingService.modifyEmpAuth(emp);
		if(result == ServiceResult.OK) {
			msg = "권한부여가 완료되었습니다.";
			view = "redirect:/setting/settingPage";
		}else {
			msg = "권한부여에 실패하였습니다. 잠시 후 다시 시도해주세요.";
		}
		model.addFlashAttribute("message",msg);
		return view;
		
	}

	@RequestMapping(value="/setting/updateDel.do", method=RequestMethod.POST)
	public String updateEmpDel(@RequestParam("inputemp_name") String emp_name,@RequestParam("inputemp_no") String emp_no, @RequestParam("inputpos_name") String pos_name, 
														@RequestParam("inputdep_name") String del_name, RedirectAttributes model){
		
		String msg = null;
		String view = "settingUp/settingPage";
		//Emp_Pos_Dep_VO updateEmp = new Emp_Pos_Dep_VO();
		//사원명, 부서명, 직책명에 해당되는 사원 조회
		Emp_Pos_Dep_VO savedEmp = settingService.retrieveEmployee(emp_name, emp_no);
		//그 사원 퇴사처리
		ServiceResult result = settingService.modifyEmpDel(savedEmp);
		if(result == ServiceResult.OK){
			msg = "퇴사처리 완료";
			view = "redirect:/setting/settingPage";
		}else{
			msg = "오류, 잠시 후 다시 시도해주세요";
		}
		model.addFlashAttribute("message",msg);
		return view;
		
	}
	
	@RequestMapping(value="/settingUp/menuManage.do", method=RequestMethod.POST)
	public String menuManage(@RequestParam("activity")String activity, @RequestParam("menu_name")String menu_name, RedirectAttributes model){
		
		String msg = null;
		String view = "settingUp/settingPage";
		int menu_flag;
		//변경하고 싶은 메뉴를 조회한다.
		ResourcesVO menu = settingService.retrieveMenu(menu_name);
		
		if(activity.equals("활성")){
			menu_flag = 1;	
		}else{
			menu_flag = 0;
		}
		
		menu.setRes_flag(menu_flag);
		//그 메뉴의 상태를 업데이트 한다.
		ServiceResult result = settingService.modifyMenu(menu);
		
		if(result == ServiceResult.OK){
			msg = "수정을 완료하였습니다";
			view = "redirect:/setting/settingPage";
		}else{
			msg = "오류, 잠시 후 다시 시도해주세요";
			view = "settingUp/settingPage";
		}
		model.addFlashAttribute("message", msg);
		return view;
		
	}
	
	
	
}
