package kr.or.ddit;

import java.util.List;

import javax.el.PropertyNotFoundException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.salesProfitManage.service.ISalesProfitManageService;
import kr.or.ddit.vo.SalesProfitVO;

@Controller
public class MainPageController {
	@Inject
	ISalesProfitManageService salesProfitService;
	
	@RequestMapping("/mainPage")
	public String mainPage( Model model ){
		
		String view = null;
		
		try {
		List<SalesProfitVO> monthProfitList = salesProfitService.retrieveProfitMonth();
		List<SalesProfitVO> monthInputProfitList = salesProfitService.retrieveMonthProfit();
		List<SalesProfitVO> pureProfitList = salesProfitService.retrievePureProfit();
		
		model.addAttribute("monthProfit",monthProfitList);
		model.addAttribute("monthInputList", monthInputProfitList);
		model.addAttribute("pureProfitList", pureProfitList);
		view = "main/mainpage";
	
		}catch(PropertyNotFoundException e) {
			view = "/";
		}
		return view;
	}
	
}
