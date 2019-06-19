package kr.or.ddit.mail.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.http.GenericUrl;

public class OauthAuthorizationRequest extends AbstractAuthorizationCodeServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//do stuff
	}
	
	@Override
	protected AuthorizationCodeFlow initializeFlow() throws ServletException, IOException {
		//return new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), "180055727289-t12f33kacmesrj2ber6sq04bmlvk8mu7.apps.googleusercontent.com","AY5Og_fBEezB7As75xlNCTMt", Collections.singleton("https://mail.google.com/")).setDataStoreFactory(DATA_STORE_FACTORY).setAccess
		return null;
	}

	@Override
	protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
		GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		url.setRawPath("/oauth2callback");
		return url.build();
	}

	@Override
	protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	
}
