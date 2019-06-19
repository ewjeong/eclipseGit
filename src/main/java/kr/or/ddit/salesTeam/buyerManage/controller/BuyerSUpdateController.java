package kr.or.ddit.salesTeam.buyerManage.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import kr.or.ddit.common.InsertHint;
import kr.or.ddit.salesTeam.buyerManage.service.IBuyerSManageService;
import kr.or.ddit.vo.ClientEmpVO;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class BuyerSUpdateController {
	
	@Inject
	IBuyerSManageService service;
	
//	@RequestMapping(value="/salesTeam/buyerManage/buyerUpdate",method=RequestMethod.GET)
//	public String buyerForm(
//			ClientVO clientVO
//			,@RequestParam("cl_no") String cl_no
//			,Model model
//			){
//		clientVO = service.retrieveDetailBuyer(cl_no);
//		model.addAttribute("client",clientVO);
//		return "sales/buyer/buyerForm";
//	}
//	
	 
	//거래처 상세조회
	@RequestMapping(value="/salesTeam/buyerManage/detailBuyer/{cl_no}")
	public String detailBuyer(
			ClientVO clientVO
			,@PathVariable("cl_no") String cl_no
			,Model model
			){
		
		clientVO = service.retrieveDetailBuyer(cl_no);
		model.addAttribute("client",clientVO);
		return "sales/buyer/buyerForm";
	}
	
	
	
	//거래처 수정
	@RequestMapping(value="/salesTeam/buyerManage/detailBuyer/{cl_no}",method=RequestMethod.POST)
	public String getBuyerForm(
			@Validated(InsertHint.class) @ModelAttribute("client") ClientEmpVO clientVO, Errors errors
			,@PathVariable(name="cl_no",required=false) String cl_no
//			,@RequestParam(name="cl_empid",required=false)String cl_empid
			,Model model
			,Authentication auth
			,RedirectAttributes rttr
			){
		String msg = null;
		String view = null;
		
		//clientVO에 입력된 cl_empid 와 employeeVO에 입력된 emp_id 비교
		EmployeeVO employeeVO = (EmployeeVO) auth.getPrincipal();
		String emp_id = employeeVO.getEmp_id();
		
		//이부분없애기
//		clientVO.setCl_empid(cl_empid);
		
		
		
		if(!errors.hasErrors()){
			//아이디가 일치하면
			if(emp_id.equals(clientVO.getCl_empid())){
				//수정
				service.modifySalesBuyer(clientVO);
				msg = "수정성공";
	//			model.addAttribute("msg",msg);
				rttr.addFlashAttribute("msg",msg);
				view = "redirect:/salesTeam/buyerManage/detailBuyer/"+cl_no;
						
			}else{
				//일치 하지 않으면
				msg = "등록한 아이디와 일치하지 않아 수정이 불가능 합니다.";
				rttr.addFlashAttribute("msg",msg);
				view = "redirect:/salesTeam/buyerManage/detailBuyer/"+cl_no;
			}
		}else{
			msg="필수데이터 누락. 다시입력하세요.";
//				rttr.addFlashAttribute("msg",msg);
			model.addAttribute("msg",msg);
			view="sales/buyer/buyerForm";
			return view;
		}
		return view;
	}
}
