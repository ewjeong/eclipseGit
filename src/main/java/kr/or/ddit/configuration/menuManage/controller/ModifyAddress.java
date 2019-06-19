package kr.or.ddit.configuration.menuManage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.configuration.menuManage.service.ISettingService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CompanyVO;

@Controller
public class ModifyAddress {

	@Inject
	ISettingService settingService;
	
	@RequestMapping(value = "/settingUp/modifyAddress", method = RequestMethod.GET)
	public String addressView(Model model){
		CompanyVO company = settingService.retrieveCompany();
		model.addAttribute("company",company);
		return "settingUp/modifyAddress";
				
	}
	
	@RequestMapping(value = "/settingUp/modifyAddress", method = RequestMethod.POST)
	public String modifyAddressView(@RequestParam("com_name")String com_name, @RequestParam("com_tel")String com_tel, 
									@RequestParam("com_add1")String com_add1, @RequestParam("com_add2")String com_add2, RedirectAttributes model){
		
		ServiceResult result = null;
		String msg = null;
		CompanyVO company = settingService.retrieveCompany();
		company.setCom_name(com_name);
		company.setCom_tel(com_tel);
		company.setCom_add1(com_add1);
		company.setCom_add2(com_add2);
		result = settingService.modifyCompany(company);
		if(result == ServiceResult.OK){
			msg = "회사정보가 수정되었습니다";
		}else{
			msg = "수정오류. 잠시 후 다시 시도해주세요";
		}
//		Map<String, Object> map = new HashMap<String,Object>();
//		map.put("message", msg);
//		map.put("company", company);
//		model.addAllAttributes(map);
		model.addFlashAttribute("message",msg);
		model.addFlashAttribute("company",company);
		return "redirect:/settingUp/modifyAddress";
		
	}
	
}
