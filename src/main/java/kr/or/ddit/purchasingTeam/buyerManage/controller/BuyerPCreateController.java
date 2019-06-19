package kr.or.ddit.purchasingTeam.buyerManage.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.InsertHint;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.buyerManage.service.IBuyerPManageService;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class BuyerPCreateController {
	
	@Inject
	IBuyerPManageService service;
	
	//화면을 받아오는 컨트롤러
	@RequestMapping(value="/purchasingTeam/buyerManage/buyerInsert", method=RequestMethod.GET)
	public String getPurBuyerForm(){
		return "purchase/buyer/buyerForm";
	}
	
	//정보를 넣어주는 컨트롤러
	@RequestMapping(value="/purchasingTeam/buyerManage/buyerInsert", method=RequestMethod.POST)
	public String insertPurBuyerForm(
			Authentication auth
			, Model model
			, RedirectAttributes redirectAttributes,
			@Validated(InsertHint.class) @ModelAttribute("client") ClientVO clientVO,
			Errors errors
			){
		
		String view = null;
		String message = null;
		
		//로그인된 아이디 받아오기
		EmployeeVO employeeVO =  (EmployeeVO) auth.getPrincipal();
		String emp_id= employeeVO.getEmp_id();
		//ClientVO에 로그인된 아이디 셋팅하기
		clientVO.setCl_empid(emp_id);
		redirectAttributes.addFlashAttribute("emp_id",clientVO.getCl_empid());
		
		if(!errors.hasErrors()){
			//성공
			ServiceResult result = service.createPurBuyer(clientVO);
			if(ServiceResult.OK.equals(result)){
				view = "redirect:/purchasingTeam/buyerManage/buyerList";
				message = "create";
				redirectAttributes.addFlashAttribute("message", message);
			}else{
				//실패
				view= "purchase/buyer/buyerForm";
				message = "서버오류, 다시입력하세요";
			}
		}else{
			message="none";
			view="purchase/buyer/buyerForm";
		}	
		model.addAttribute("message",message);
		return view;
	}
	
	//품목명 검색 ajax
//	@ResponseBody
//	@RequestMapping(value="/purchasingTeam/buyerManage/item", produces="application/json;charset=UTF-8")
//	public List<ItemVO> ajaxItem(
//			@RequestParam("item_name") String item_name
//			){
//		List<ItemVO> itemList = service.retreiveItem(item_name);
//		
//		return itemList;
//	}
	
	
	
	
}

















