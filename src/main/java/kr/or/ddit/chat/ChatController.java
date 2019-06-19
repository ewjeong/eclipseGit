package kr.or.ddit.chat;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatController {

//	@Inject
//	private HashMap<String, WebSocketSession> chatSessionMap;
	
	
	@RequestMapping(value="/chatting/chat", method=RequestMethod.GET)
	public String view(Authentication auth){
		//mv.setViewName("chatting/chatting");
//		EmployeeVO employee = (EmployeeVO) auth.getPrincipal();
//		String name = employee.getEmp_name();
//		System.out.println("사용자 : "+name);
//		System.out.println("normal chat page");
		
		//return mv;
		return "chatting/chatting";
	}
}
