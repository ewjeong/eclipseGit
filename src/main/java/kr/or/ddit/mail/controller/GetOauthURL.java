
package kr.or.ddit.mail.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mail.service.IMailService;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.TokenVO;

@Controller
public class GetOauthURL {
	
	@Inject
	IMailService service;
	
// 뷰를 보여줌.
	@RequestMapping("/mail/test")
	public String get() {
		return "mail/mainpage";
	}
	
	
	// 사용자 권한을 얻을 수 있는 url 요청페이지.구글이 url를 redirect해준다.
	@RequestMapping(value = "/mail/redirect")
	public String post(String url, HttpServletResponse resp, HttpServletRequest req) throws ClientProtocolException, IOException {
					
			String body = null;
			HttpClient client = HttpClientBuilder.create().build();
			
			//url="https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fgmail.readonly&access_type=offline&include_granted_scopes=true&state=state_parameter_passthrough_value&redirect_uri=http%3A%2F%2Flocalhost%2FlastProject%2Fmail%2FgetURL&response_type=code&client_id=180055727289-t12f33kacmesrj2ber6sq04bmlvk8mu7.apps.googleusercontent.com";
			url="https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A%2F%2Fmail.google.com%2F&access_type=offline&include_granted_scopes=true&state=state_parameter_passthrough_value&redirect_uri=http%3A%2F%2Flocalhost%2FlastProject%2Fmail%2FgetURL&response_type=code&client_id=180055727289-t12f33kacmesrj2ber6sq04bmlvk8mu7.apps.googleusercontent.com";
			HttpPost postRequest = new HttpPost(url);
			
			HttpResponse response = client.execute(postRequest);
			response.getStatusLine().getStatusCode();
			String location = response.getHeaders("location")[0].getValue();
			System.out.println("---------");

			//code 	번호대로 처리해준다.
			
		
//		return "mail/geturl";
			return "redirect:"+location;
	}
	

//쿼리스트링 값 code받아서 다시 넘긴다.
	@RequestMapping(value = "/mail/getURL")
	public String getCode(String url, HttpServletRequest req, Model model) throws ClientProtocolException, IOException {
		
		String view = null;
		
		String body=null;
		String code = req.getParameter("code");
		System.out.println(code);
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost("https://www.googleapis.com//oauth2/v4/token");
		
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		//postRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
		parameters.add(new BasicNameValuePair("code", code));
		parameters.add(new BasicNameValuePair("client_id", "180055727289-t12f33kacmesrj2ber6sq04bmlvk8mu7.apps.googleusercontent.com"));
		parameters.add(new BasicNameValuePair("client_secret", "AY5Og_fBEezB7As75xlNCTMt"));
		parameters.add(new BasicNameValuePair("redirect_uri", "http://localhost/lastProject/mail/getURL"));
		parameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
		
		postRequest.setEntity(new UrlEncodedFormEntity(parameters));

		HttpResponse resp = client.execute(postRequest);
		
		System.out.println("response  code : "+ resp.getStatusLine().getStatusCode());
		System.out.println(resp.getStatusLine().getReasonPhrase());
		System.out.println(resp.getEntity());
		System.out.println(resp.getEntity().getContent());
		BufferedReader rd = new  BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
		
		StringWriter out = new StringWriter();
		
		IOUtils.copy(rd, out);
		String value = out.toString();

		ObjectMapper mapper = new ObjectMapper();
		TokenVO token = mapper.readValue(value, TokenVO.class);
		
		Authentication auth = (Authentication)SecurityContextHolder.getContext().getAuthentication();
		EmployeeVO employee = (EmployeeVO) auth.getPrincipal();
		String emp_id = employee.getEmp_id();
		token.setEmp_id(emp_id);
		System.out.println(token);
		ServiceResult sr = service.createToken(token);
		if(ServiceResult.OK.equals(sr)){
			view = "main/mainpage";
		}else{
			String msg = "오류";
			model.addAttribute("msg",msg);
			view = "main/mainpage";
		}

		 
//		if(resp.getStatusLine().getStatusCode() == 200) {
//			ResponseHandler<String> handler = new BasicResponseHandler();
//			body = handler.handleResponse(resp);
//			
//		}else {
//			System.out.println(resp);
//			System.out.println("error:"+resp.getStatusLine().getStatusCode());
//			System.out.println("error:"+resp.getStatusLine().getReasonPhrase());
//		}
		//System.out.println(body);
		return view;
		
	}

	
}
