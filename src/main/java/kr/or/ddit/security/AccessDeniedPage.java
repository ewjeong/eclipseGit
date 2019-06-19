package kr.or.ddit.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessDeniedPage {
	
	@RequestMapping("/main/access_denied_page")
	public String deniedPageView(Model model) {
		
		String msg = "접근권한이 없습니다";
		model.addAttribute("menu", msg);
		return "main/mainpage";
	}

}
