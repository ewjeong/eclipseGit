package kr.or.ddit.mail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;


@Controller
public class mailController {
	
//	HttpTransport transport = new NetHttpTransport();
//	try {
//		
//	}
	
//	@RequestMapping(value="/mail/mail", method=RequestMethod.GET)
//	public String mailView(){
//		return "mail/mail";
//	}
	
	@RequestMapping(value="/mail/login", method=RequestMethod.GET)
	public String mailLoginView(){
		return "mail/login";
	}
	
	@RequestMapping(value="/mail/code", method=RequestMethod.GET)
	public String mailCodeView(){
		return "mail/receiveCode";
	}
//	
//	@RequestMapping(value="/mail/token", method=RequestMethod.GET)
//	public String mailTokenView(){
//		return "mail/refreshToken";
//	}
	
	
	
	@RequestMapping(value="/mail/authlogin", method=RequestMethod.GET)
	public String authLoginView(){
		return "mail/oAuthLogin";
	}
}
