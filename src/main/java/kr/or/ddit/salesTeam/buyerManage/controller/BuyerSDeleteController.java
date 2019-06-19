package kr.or.ddit.salesTeam.buyerManage.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.salesTeam.buyerManage.service.IBuyerSManageService;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class BuyerSDeleteController {
	
	@Inject
	IBuyerSManageService service;
	
//	@RequestMapping("/salesTeam/buyerManage/buyerDelete/{cl_no}")
//	public String buyerForm(
////			ClientVO clientVO
////			,@PathVariable("cl_no") String cl_no
////			,Model model
//			){
//		
////		clientVO = service.retrieveDetailBuyer(cl_no);
////		model.addAttribute("client",clientVO);
//		return "sales/buyer/buyerForm";
//	}
	
	
	@RequestMapping(value="/salesTeam/buyerManage/buyerDelete/{cl_no}/{cl_empid}")
	public String getBuyerForm(
			@PathVariable("cl_no") String cl_no
			,@PathVariable("cl_empid") String cl_empid
			,Authentication auth
			,ClientVO clientVO
			,Model model
			,RedirectAttributes rttr
			){
		
		String view = null;
		String msg = null;
		//아이디가 일치되어야 삭제
		EmployeeVO employeeVO =  (EmployeeVO) auth.getPrincipal();
		String emp_id= employeeVO.getEmp_id();
		clientVO.setCl_empid(cl_empid);
		
		
		if(emp_id.equals(clientVO.getCl_empid())){
			service.removeSaleBuyer(clientVO);
			msg = "삭제성공";
			view = "redirect:/salesTeam/buyerManage/buyerList";
		}else{
			msg = "게시물을 등록한 직원만 삭제가 가능합니다.";
			view = "redirect:/salesTeam/buyerManage/detailBuyer/"+cl_no;
		}
		
		
		rttr.addFlashAttribute("msg",msg);
		return view;
	}
}











