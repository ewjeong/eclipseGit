package kr.or.ddit.mail.preparer;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.annotation.Preparer;

@Preparer
public class MailPreparer implements ViewPreparer{
	
	@Override
	public void execute(Request arg0, AttributeContext arg1) {
		Map<String, String> menuMap = new LinkedHashMap<>();
		menuMap.put("/mail/inbox", "받은편지함");
//		menuMap.put("/mail/starred", "별표편지함");
//		menuMap.put("/mail/important", "중요편지함");
		menuMap.put("/mail/sent", "보낸편지함");
//		menuMap.put("/mail/draft", "임시보관함");
		menuMap.put("/mail/spam", "스팸편지함");
		
		Map<String, Object> requestScope = arg0.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menus", menuMap);		
		
	}

}
