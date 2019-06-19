package kr.or.ddit.mail.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.inject.Inject;

import org.apache.http.client.ClientProtocolException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.services.gmail.Gmail;

import kr.or.ddit.mail.service.IMailService;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.TokenVO;

@Controller
public class GetMailBox {
	
	@Inject
	GmailCredential gmail;  
	
	@Inject
	IMailService mailService;
	
	// mailAPI요청 메서드
//		@RequestMapping("/mail/draft")
//		public String mailDraftRequest(Model model) throws ClientProtocolException, IOException, GeneralSecurityException {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			EmployeeVO emp = (EmployeeVO) auth.getPrincipal();
//			String emp_id = emp.getEmp_id();
//			String emp_mail = emp.getEmp_mail();
//			TokenVO token = mailService.retrieveToken(emp_id);
//			String userId = "me";
//			try {
//				// 서비스받아와서 메일리스트 요청하기
//				//Gmail service = gmail.getService();
//				gmail.getDraft(model, gmail.getService(), emp_mail);
//				
//			} catch (Exception e) {
//				gmail.updateCredential();
//				//gmail.getService();
//				gmail.getDraft(model, gmail.getService(), emp_mail);
//
//			}
//
//			return "mail/draft";
//		}
		
//		@RequestMapping("/mail/starred")
//		public String mailStarredRequest(Model model) throws ClientProtocolException, IOException, GeneralSecurityException {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			EmployeeVO emp = (EmployeeVO) auth.getPrincipal();
//			String emp_id = emp.getEmp_id();
//			String emp_mail = emp.getEmp_mail();
//			TokenVO token = mailService.retrieveToken(emp_id);
//			String userId = "me";
//			try {
//				// 서비스받아와서 메일리스트 요청하기
//				//Gmail service = gmail.getService();
//				gmail.getStarred(model, gmail.getService(), emp_mail);
//				
//			} catch (Exception e) {
//				gmail.updateCredential();
//				//gmail.getService();
//				gmail.getStarred(model, gmail.getService(), emp_mail);
//
//			}
//
//			return "mail/starred";
//		}
//		
//		@RequestMapping("/mail/important")
//		public String mailImportantRequest(Model model) throws ClientProtocolException, IOException, GeneralSecurityException {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			EmployeeVO emp = (EmployeeVO) auth.getPrincipal();
//			String emp_id = emp.getEmp_id();
//			String emp_mail = emp.getEmp_mail();
//			TokenVO token = mailService.retrieveToken(emp_id);
//			String userId = "me";
//			try {
//				// 서비스받아와서 메일리스트 요청하기
//				//Gmail service = gmail.getService();
//				gmail.getImportant(model, gmail.getService(), emp_mail);
//				
//			} catch (Exception e) {
//				gmail.updateCredential();
//				//gmail.getService();
//				gmail.getImportant(model, gmail.getService(), emp_mail);
//
//			}
//
//			return "mail/important";
//		}
		
		@RequestMapping("/mail/sent")
		public String mailSentRequest(Model model) throws ClientProtocolException, IOException, GeneralSecurityException {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			EmployeeVO emp = (EmployeeVO) auth.getPrincipal();
			String emp_id = emp.getEmp_id();
			String emp_mail = emp.getEmp_mail();
			TokenVO token = mailService.retrieveToken(emp_id);
			String userId = "me";
			try {
				// 서비스받아와서 메일리스트 요청하기
				//Gmail service = gmail.getService();
				gmail.getSent(model, gmail.getService(), emp_mail);
				
			} catch (Exception e) {
				gmail.updateCredential();
				//gmail.getService();
				gmail.getSent(model, gmail.getService(), emp_mail);

			}

			return "mail/sent";
		}
		
		@RequestMapping("/mail/spam")
		public String mailSpamRequest(Model model) throws ClientProtocolException, IOException, GeneralSecurityException {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			EmployeeVO emp = (EmployeeVO) auth.getPrincipal();
			String emp_id = emp.getEmp_id();
			String emp_mail = emp.getEmp_mail();
			TokenVO token = mailService.retrieveToken(emp_id);
			String userId = "me";
			try {
				// 서비스받아와서 메일리스트 요청하기
				//Gmail service = gmail.getService();
				gmail.getSpam(model, gmail.getService(), emp_mail);
				
			} catch (Exception e) {
				gmail.updateCredential();
				//gmail.getService();
				gmail.getSpam(model, gmail.getService(), emp_mail);

			}

			return "mail/spam";
		}
		

}
