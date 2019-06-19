package kr.or.ddit.purchasingTeam.buyerManage.controller;

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
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.buyerManage.service.IBuyerPManageService;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class BuyerPUpdateController {
	
	@Inject
	IBuyerPManageService service;
	
	//거래처 상세조회
	@RequestMapping(value="/purchasingTeam/buyerManage/detailBuyer/{cl_no}")
	public String getBuyerView(
			ClientVO clientVO
			,@PathVariable("cl_no") String cl_no
			,Model model
			){
		
		clientVO = service.retrieveDetailBuyer(cl_no);
		model.addAttribute("client",clientVO);
		
		return "purchase/buyer/buyerForm";
	}
	
	//거래처 수정
	@RequestMapping(value="/purchasingTeam/buyerManage/detailBuyer/{cl_no}",method=RequestMethod.POST)
	public String getBuyerForm(
			@Validated(InsertHint.class) @ModelAttribute("client") ClientVO clientVO
			,Errors errors
			,@PathVariable("cl_no") String cl_no
			,Model model
			,Authentication auth
			,RedirectAttributes redirectAttributes
			){
		
		String message = null;
		String view = null;
		
		//clientVO에 입력된 cl_empid 와 employeeVO에 입력된 emp_id 비교
		EmployeeVO employeeVO = (EmployeeVO) auth.getPrincipal();
		String emp_id = employeeVO.getEmp_id();
		
		if(!errors.hasErrors()){
			ServiceResult result = service.modifyPurBuyer(clientVO);
		
			//아이디가 일치하면
			if(ServiceResult.OK.equals(result) && emp_id.equals(clientVO.getCl_empid())){
				//수정
				view = "redirect:/purchasingTeam/buyerManage/detailBuyer/"+cl_no;
				message = "modify";
				redirectAttributes.addFlashAttribute("message", message);
			}else{
				//일치 하지 않으면
				view = "redirect:/purchasingTeam/buyerManage/detailBuyer/"+cl_no;
				message = "NoID";
			}
		}else{
			message="NoModify";
			view="purchase/buyer/buyerForm";
		}
		
		model.addAttribute("message",message);
		return view;
		
	}
	
}



















