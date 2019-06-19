package kr.or.ddit.purchasingTeam.buyerManage.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.buyerManage.service.IBuyerPManageService;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class BuyerPDeleteController {

	@Inject
	IBuyerPManageService service;

	@RequestMapping("/purchasingTeam/buyerManage/buyerDelete/{cl_no}/{cl_empid}")
	public String getBuyerForm(
			@PathVariable("cl_no") String cl_no
			,@PathVariable("cl_empid") String cl_empid
			,Authentication auth
			,ClientVO clientVO
			,Model model
			,Errors errors
			, RedirectAttributes redirectAttributes
			){
		
		String view = null;
		String message = null;
		
		//아이디가 일치되어야 삭제
		EmployeeVO employeeVO =  (EmployeeVO) auth.getPrincipal();
		String emp_id= employeeVO.getEmp_id();
		clientVO.setCl_empid(cl_empid);
		
		if(!errors.hasErrors()){
			ServiceResult result = service.removePurBuyer(clientVO);
			if(emp_id.equals(clientVO.getCl_empid())){
				view = "redirect:/purchasingTeam/buyerManage/buyerList";
				message = "delete";
				redirectAttributes.addFlashAttribute("message", message);
			}else{
				view = "/purchasingTeam/buyerManage/detailBuyer/"+cl_no;
				message = "게시물을 등록한 직원만 삭제가 가능합니다.";
				model.addAttribute("message",message);
			}
		}else{
			message="none";
			view="purchase/buyer/buyerForm";
		}	
		model.addAttribute("message",message);
		return view;
		
	}
}
