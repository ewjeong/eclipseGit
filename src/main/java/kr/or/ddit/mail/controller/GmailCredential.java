package kr.or.ddit.mail.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.api.services.gmail.model.MessagePartBody;
import com.google.api.services.gmail.model.MessagePartHeader;

import edu.emory.mathcs.backport.java.util.Collections;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mail.service.IMailService;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.MailAttachVO;
import kr.or.ddit.vo.MailVO;
import kr.or.ddit.vo.TokenVO;

@Controller
public class GmailCredential {

	
	@Inject
	IMailService mailService;
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_LABELS);

	//Gmail service;
	GoogleCredential credential = null;
	
	Gmail service = null;
	

	// mailAPI요청 메서드
	@RequestMapping("/mail/inbox")
	public String mailAPIRequest(Model model) throws ClientProtocolException, IOException, GeneralSecurityException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		EmployeeVO emp = (EmployeeVO) auth.getPrincipal();
		String emp_id = emp.getEmp_id();
		String emp_mail = emp.getEmp_mail();
		TokenVO token = mailService.retrieveToken(emp_id);
		try {
			// 서비스받아와서 메일리스트 요청하기
			getService();
			getInbox(model, getService(), emp_mail);
			//getLabelList(model);
			//getMailList(model);
		} catch (Exception e) {
			updateCredential();
			getService();
			getInbox(model, getService(), emp_mail);
			//getLabelList(model);
			//getMailList(model);
		}

		return "mail/inbox";
	}

	// access token을 받아오는 메서드
	public void getCredential() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getPrincipal().equals("isAnonymous")) {
			//로그인하라고 창띄워주기
		}
		EmployeeVO emp = (EmployeeVO) auth.getPrincipal();
		String emp_id = emp.getEmp_id();
		String emp_mail = emp.getEmp_mail();
		TokenVO token = mailService.retrieveToken(emp_id);

	}
	
	

	// access token을 갱신하는 메서드
	public void updateCredential() throws ClientProtocolException, IOException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		EmployeeVO emp = (EmployeeVO) auth.getPrincipal();
		String emp_id = emp.getEmp_id();
		String emp_mail = emp.getEmp_mail();
		
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

	// service를 받아오는 메서드
	public Gmail getService() throws GeneralSecurityException, IOException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		EmployeeVO emp = (EmployeeVO) auth.getPrincipal();
		String emp_id = emp.getEmp_id();
		String emp_mail = emp.getEmp_mail();
		TokenVO token = mailService.retrieveToken(emp_id);

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		credential = new GoogleCredential().setAccessToken(token.getAccess_token());
		Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("Gmail").build();
		
		return service;

	}

	//아이디,비번찾기  할 때 사용할 관리자용 서비스 받기
	public Gmail getAdminService() throws GeneralSecurityException, IOException {
		
		
		
		String emp_id = "admin";
		String emp_mail = "lcy7747@gmail.com";
		TokenVO token = mailService.retrieveToken(emp_id);
		
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		credential = new GoogleCredential().setAccessToken(token.getAccess_token());
		Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("Gmail").build();
	
		return service;
				
	}
	
	// mailList 가져오기
	public void getLabelList(Model model) throws GeneralSecurityException, IOException {

		String user = "me";
		Gmail service = getService();
		ListLabelsResponse listResponse = service.users().labels().list(user).execute();
		List<Label> labels = listResponse.getLabels();
		
		List<String> labelList = new ArrayList<String>();
		for (Label label : labels) {
			// System.out.println(label.getId());
			labelList.add(label.getId());

		}
		model.addAttribute("labelList",labelList);

	}

	
	//받은편지함
	//@RequestMapping("/mail/inbox")
	public void getInbox(Model model, Gmail service, String userId ) throws IOException, GeneralSecurityException{
		 
		service = getService();
		userId = "me";
		List<String> labelIds = new ArrayList<String>();
		labelIds.add("INBOX");
		
		ListMessagesResponse response = service.users().messages().list(userId).setLabelIds(labelIds).execute();
		
		List<MailVO> mailHeaderList = new ArrayList<MailVO>();
		
		List<Message> messages = new ArrayList<Message>();
		while(response.getMessages() != null){
			messages.addAll(response.getMessages());
			if(response.getNextPageToken() != null){
				String pageToken = response.getNextPageToken();
				response = service.users().messages().list(userId).setPageToken(pageToken).execute();
			}else{
				break;
			}
		}
		
		for(Message message : messages){
			String id = message.getId();
			Message mes = service.users().messages().get(userId, id).execute();
			
			List<MessagePartHeader> iterMessage = mes.getPayload().getHeaders();
			
			MailVO  mailVO = new MailVO();
			mailVO.setId(id);
			
			for(int i=0; i<iterMessage.size();i++) {
				if(iterMessage.get(i).getName().equals("From")) {
					String from = iterMessage.get(i).getValue();
					mailVO.setFrom(from);
				}
				if(iterMessage.get(i).getName().equals("Date")) {
//					try {
//						String date = iterMessage.get(i).getValue();
//						LocalDateTime dateTime = LocalDateTime.from(
//								Instant.from(
//										DateTimeFormatter.ISO_DATE_TIME.parse(date)
//								).atZone(ZoneId.of("Asia/Seoul"))
//						);
//						int year = dateTime.getYear();
//						int month = dateTime.getMonthValue()+1;
//						int dates = dateTime.getDayOfMonth();
//						mailVO.setDate(year+"년 "+month+"월 "+ dates+"일");
//								
//					}catch(DateTimeParseException e) {
//						String date = iterMessage.get(i).getValue();
//						mailVO.setDate(date);
//					}
					String date = iterMessage.get(i).getValue();
					String rDate = date.substring(0, 16);
					mailVO.setDate(rDate);
					
				}
				if(iterMessage.get(i).getName().equals("Subject")) {
					String subject = iterMessage.get(i).getValue();
					mailVO.setSubject(subject);
				}
			}
			mailHeaderList.add(mailVO);
			//System.out.println(mailHeaderList);
			
		}
		model.addAttribute("messages",messages);
		model.addAttribute("mailHeaderList",mailHeaderList);
		//return "mail/inbox";
	}
	
				
		//보낸편지함
		///@RequestMapping("/mail/sent")
		public void getSent(Model model, Gmail service, String userId ) throws IOException, GeneralSecurityException{
			 
			service = getService();
			userId = "me";
			
			List<MailVO> mailHeaderList = new ArrayList<MailVO>();
			
			List<String> labelIds = new ArrayList<String>();
			labelIds.add("SENT");
			
			ListMessagesResponse response = service.users().messages().list(userId).setLabelIds(labelIds).execute();
			
			List<Message> messages = new ArrayList<Message>();
			while(response.getMessages() != null){
				messages.addAll(response.getMessages());
				if(response.getNextPageToken() != null){
					String pageToken = response.getNextPageToken();
					response = service.users().messages().list(userId).setPageToken(pageToken).execute();
				}else{
					break;
				}
			}
			
			for(Message message : messages){
				String id = message.getId();
				Message mes = service.users().messages().get(userId, id).execute();
				
				List<MessagePartHeader> iterMessage = mes.getPayload().getHeaders();
				
				MailVO  mailVO = new MailVO();
				mailVO.setId(id);
				for(int i=0; i<iterMessage.size();i++) {
					if(iterMessage.get(i).getName().equals("From")) {
						String from = iterMessage.get(i).getValue();
						mailVO.setFrom(from);
					}
					if(iterMessage.get(i).getName().equals("Date")) {
						String date = iterMessage.get(i).getValue();
						String rDate = date.substring(0, 16);
						mailVO.setDate(rDate);
					}
					if(iterMessage.get(i).getName().equals("Subject")) {
						String subject = iterMessage.get(i).getValue();
						mailVO.setSubject(subject);
					}
				}
				mailHeaderList.add(mailVO);
				//System.out.println(mailHeaderList);
				
			}
			model.addAttribute("messages",messages);
			model.addAttribute("mailHeaderList",mailHeaderList);
			//return "mail/sent";
		}
		
		//스팸보관함
		//@RequestMapping("/mail/spam")
		public void getSpam(Model model, Gmail service, String userId ) throws IOException, GeneralSecurityException{
			 
			service = getService();
			userId = "me";
			
			List<MailVO> mailHeaderList = new ArrayList<MailVO>();
			
			List<String> labelIds = new ArrayList<String>();
			labelIds.add("SPAM");
			
			ListMessagesResponse response = service.users().messages().list(userId).setLabelIds(labelIds).execute();
			
			List<Message> messages = new ArrayList<Message>();
			while(response.getMessages() != null){
				messages.addAll(response.getMessages());
				if(response.getNextPageToken() != null){
					String pageToken = response.getNextPageToken();
					response = service.users().messages().list(userId).setPageToken(pageToken).execute();
				}else{
					break;
				}
			}
			
			for(Message message : messages){
				String id = message.getId();
				Message mes = service.users().messages().get(userId, id).execute();
				
				List<MessagePartHeader> iterMessage = mes.getPayload().getHeaders();
				
				MailVO  mailVO = new MailVO();
				mailVO.setId(id);
				for(int i=0; i<iterMessage.size();i++) {
					if(iterMessage.get(i).getName().equals("From")) {
						String from = iterMessage.get(i).getValue();
						mailVO.setFrom(from);
					}
					if(iterMessage.get(i).getName().equals("Date")) {
						String date = iterMessage.get(i).getValue();
						String rDate = date.substring(0, 16);
						mailVO.setDate(rDate);
					}
					if(iterMessage.get(i).getName().equals("Subject")) {
						String subject = iterMessage.get(i).getValue();
						mailVO.setSubject(subject);
					}
				}
				mailHeaderList.add(mailVO);
				
			}
			
			
			model.addAttribute("messages",messages);
			model.addAttribute("mailHeaderList",mailHeaderList);
		}
				
				
		//메일 상세조
		@RequestMapping("/mail/mailView")
		public  String mailView(String userId, @RequestParam(name="what",required=true)String id, Model model) throws GeneralSecurityException, IOException, MessagingException {
			
			Gmail service = getService();
			userId = "me";
			MailVO mail = new MailVO();
			mail.setId(id);
			
			//getMimeMessage
			Message message = service.users().messages().get(userId, id).setFormat("raw").execute();
			
			Base64 base64Url = new Base64(true);
			byte[] emailBytes = base64Url.decodeBase64(message.getRaw());
	
			Properties props = new Properties();
			Session session  = Session.getDefaultInstance(props,null);
			
			
			MimeMessage email = new MimeMessage(session, new ByteArrayInputStream(emailBytes));
			
			System.out.println(email.getContentType());
		
			//email을 model에 담아 넘긴다.
			Object content = email.getContent();
			
			if(content instanceof String){
				mail.setContent(content);
				System.out.println(content);
			}else if(content instanceof Multipart){
				Multipart multipart = (Multipart) content;
				int multiPartCount = multipart.getCount();
				for(int i = 0; i<multiPartCount; i++){
					BodyPart bodyPart = multipart.getBodyPart(i);
					Object o;
					o = bodyPart.getContent();
					if(o instanceof String){
						System.out.println(o);
						mail.setContent(o);
					}if(o instanceof MimeMultipart){
						BodyPart body = ((MimeMultipart) o).getBodyPart(i);
						Object bc = body.getContent();
						if(bc instanceof String){
							System.out.println(bc);
							mail.setContent(bc);
						}
					}
					
				}
			}
			
			Address[] address = email.getFrom();
			InternetAddress addre = (InternetAddress) address[0];
			//InternetAddress로 받아오기
			String realAddress = addre.getAddress();
			String receivedDate = email.getSentDate().toString();
			String date = receivedDate.substring(0, 10);
			System.out.println(receivedDate);
			String subject = email.getSubject();
			mail.setFrom(realAddress);
			mail.setDate(date);
			mail.setSubject(subject);
			System.out.println("id : "+id);
			List<MailAttachVO> mailAttachList	=  getAttachments(service, userId, id);
			mail.setAttachList(mailAttachList);
			
			model.addAttribute("email", mail);
			
			
			return "mail/mailView";
			
		}
		
		public Message getMessage(Gmail service, String userId, String messageId) throws IOException {
			Message message = service.users().messages().get(userId,messageId).execute();
			System.out.println("Message snippet : "+ message.getSnippet());
			
			return message;
					
		}
		
		
		//첨부파일 가져오기
		public List<MailAttachVO> getAttachments(Gmail service, String userId, String messageId) throws IOException{
			
			String path = "D:/mailfolder/"+messageId;
//			String path = "/Users/yeonuk/Desktop/web/savefolder/"+messageId;
			   
			File mailfolder = new File(path);
			if(!mailfolder.exists()){
				mailfolder.mkdir();
			}
		
			List<MailAttachVO> mailAttachList = new ArrayList<MailAttachVO>();
			Message message = service.users().messages().get(userId, messageId).execute();
			
			List<MessagePart> parts = message.getPayload().getParts();

			try{
				
			
			
			for(MessagePart part : parts){
				if(part.getFilename() != null && part.getFilename().length()>0){
					String filename = part.getFilename();
					String attId = part.getBody().getAttachmentId();
					System.out.println("messageId:"+messageId);
					System.out.println("UserID:"+userId);
					System.out.println("attID:"+attId);
					
					MessagePartBody attachPart = service.users().messages().attachments().get(userId, messageId, attId).execute();
					MailAttachVO  attachVO  = new MailAttachVO();
					attachVO.setFilename(filename);
					attachVO.setSavepath(path);
					
					byte[] fileByteArray = Base64.decodeBase64(attachPart.getData());
					File file = new File(mailfolder, filename);
					FileOutputStream fileOutFile =
							new FileOutputStream(file);
					fileOutFile.write(fileByteArray);
					fileOutFile.close();
					mailAttachList.add(attachVO);
				}
			}
			}catch(NullPointerException e){
				mailAttachList = null;
			}

			return mailAttachList;

		}		
}
				
