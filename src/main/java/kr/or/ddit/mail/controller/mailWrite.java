package kr.or.ddit.mail.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mail.service.IMailService;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.MailVO;
import kr.or.ddit.vo.TokenVO;

@Controller
public class mailWrite {

	@Inject
	GmailCredential gmail;  
	
	@Inject 
	IMailService mailService;
	
	@RequestMapping("/mail/write")
	public String mailWriteView() {
		return "mail/write";
	}
	
	
	@RequestMapping(value="/mail/send", method = RequestMethod.POST)
	public String mailWrite(@RequestParam("to")String to, @RequestParam("title")String title, @RequestParam("message")String bodytext ,@RequestParam("file") MultipartFile file, String userId, Model model,RedirectAttributes rediModel) throws GeneralSecurityException, IOException, MessagingException {
		
		String emp_id = "admin";
		String view = null;
		String msg= null;
		Gmail service = null;
		userId = "me";
		MailVO mailVO = new MailVO();
		System.out.println(bodytext);
		mailVO.setBodytext(bodytext);
		mailVO.setTo(to);
		mailVO.setSubject(title);
		service = gmail.getService();
		TokenVO token = mailService.retrieveToken(emp_id);
	
		try {
			
			MimeMessage message =createEmailWithAttachment(to, title, bodytext, file);
			Message rawMessage =  createMessageWithEmail(message);
			rawMessage = service.users().messages().send(userId, rawMessage).execute();
			
			msg = "메일을 전송하였습니다";
			rediModel.addFlashAttribute("message", msg);
			view = "redirect:/mail/inbox";
			
		}catch(Exception e) {
			msg = "메일 전송에 실패하였습니다. 다시 시도해주세요";
			model.addAttribute("mail",mailVO);
			model.addAttribute("message", msg);
			view  = "mail/write";
			
		}
		
		return view;
	}
	
	public boolean send(String subject, String text, String to) throws GeneralSecurityException, IOException, MessagingException {
		String userId = "me";
		Gmail service =null;
		try{
			service = gmail.getAdminService();
			String from = "lcy7747@gmail.com";	
			MimeMessage message = createAdminEmail(to, from, subject, text);
			Message rawMessage = createMessageWithEmail(message);
			rawMessage = service.users().messages().send(userId, rawMessage).execute();
		}catch(Exception e){
			updateAdminCredential();
			service = gmail.getAdminService();
			String from = "lcy7747@gmail.com";	
			MimeMessage message = createAdminEmail(to, from, subject, text);
			Message rawMessage = createMessageWithEmail(message);
			rawMessage = service.users().messages().send(userId, rawMessage).execute();
		}
		
		return true;
			
	}
	
	public static MimeMessage createAdminEmail(String to, String from, String title, String bodytext) throws AddressException, MessagingException {
		
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props,null);
		
		MimeMessage email = new MimeMessage(session);
		
		email.setFrom(new InternetAddress(from));
		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
		email.setSubject(title);
		email.setText(bodytext);
		return email;
		
	}
	
	public static Message createMessageWithEmail(MimeMessage email) throws IOException, javax.mail.MessagingException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		email.writeTo(baos);
		String encodedEmail = Base64.encodeBase64URLSafeString(baos.toByteArray());
		Message message = new Message();
		message.setRaw(encodedEmail);
		return message;
	}

	public static MimeMessage createEmailWithAttachment(String to, String title, String message , MultipartFile file) throws AddressException, MessagingException, IOException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		EmployeeVO emp = (EmployeeVO) auth.getPrincipal();
		String emp_id = emp.getEmp_id();
		String emp_mail = emp.getEmp_mail();
		
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props,null);
		
		MimeMessage email = new MimeMessage(session);
		
		email.setFrom(new InternetAddress(emp_mail));
		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
		email.setSubject(title);
	
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(message,"text/plain; charset=UTF-8");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		mimeBodyPart = new MimeBodyPart();
		 
		byte[] byteFile =  file.getBytes();
		String fileType = file.getContentType();
		 //String path = filePath.replaceAll("/", File.separator);
		DataSource source = new ByteArrayDataSource(byteFile, fileType);
		
		mimeBodyPart.setDataHandler(new DataHandler(source));
		mimeBodyPart.setFileName(file.getName());
		
		multipart.addBodyPart(mimeBodyPart);
		email.setContent(multipart);
		
		return email;
		
	}
	
	// access token을 갱신하는 메서드
		public void updateAdminCredential() throws ClientProtocolException, IOException {

//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			EmployeeVO emp = (EmployeeVO) auth.getPrincipal();
			String emp_id = "admin";
			String emp_mail = "lcy7747@gmail.com";
			
			TokenVO token = mailService.retrieveToken(emp_id);

			String refreshToken = token.getRefresh_token();

			HttpClient client = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost("https://www.googleapis.com//oauth2/v4/token");
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("client_id",
					"180055727289-t12f33kacmesrj2ber6sq04bmlvk8mu7.apps.googleusercontent.com"));
			parameters.add(new BasicNameValuePair("client_secret", "AY5Og_fBEezB7As75xlNCTMt"));
			parameters.add(new BasicNameValuePair("refresh_token", refreshToken));
			parameters.add(new BasicNameValuePair("grant_type", "refresh_token"));

			postRequest.setEntity(new UrlEncodedFormEntity(parameters));
			HttpResponse resp = client.execute(postRequest);

			BufferedReader rd = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
			StringWriter out = new StringWriter();
			IOUtils.copy(rd, out);
			String value = out.toString();

			ObjectMapper mapper = new ObjectMapper();
			token = mapper.readValue(value, TokenVO.class);

			token.setEmp_id(emp_id);
			System.out.println(token);
			ServiceResult sr = mailService.createToken(token);
		}

	
}
