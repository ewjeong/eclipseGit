package kr.or.ddit.elecAuthorization.controller.line;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.configuration.menuManage.service.ISettingService;
import kr.or.ddit.elecAuthorization.service.IElecManageService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.Emp_Pos_Dep_VO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.FixLineVO;

@Controller
public class LineCreateController {
	@Inject
	IElecManageService service;
	@Inject
	ISettingService settingService;
	
	@RequestMapping(value="/elecAuthorization/line/lineInsert", method=RequestMethod.POST)
	public String createLine(
			FixLineVO fixLine
//			, Errors errors
			, RedirectAttributes redirectAttributes
			, Authentication user
			){
//		String view = null;
		String msg = null;
//		if(!errors.hasErrors()){
			ServiceResult result = null;
			
			EmployeeVO employee = (EmployeeVO) user.getPrincipal();
			fixLine.setOwner_id(employee.getEmp_id()); 
			result = service.createFixLine(fixLine);
			
			if(ServiceResult.FAILED.equals(result)){
				msg = "서버 오류, 잠시 후 다시 확인해 주세요.";
			} else if ( ServiceResult.OK.equals(result)) {
				msg = "OK";
			}
//		} else { // 검증 불통
//			msg = "필수 파라미터 누락";
//		}
		redirectAttributes.addFlashAttribute("message", msg);
		return "redirect:/elecAuthorization/line/lineList";
	}

	@ResponseBody
	@RequestMapping(value="/elecAuthorization/line/getEmpList"
		, method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public List<Emp_Pos_Dep_VO> getEmployeeList(){
		List<Emp_Pos_Dep_VO> empList = settingService.selectEmployeelistNoneParam();
		return empList;
	}
}













