package kr.or.ddit.myPage.preparer;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.annotation.Preparer;

@Preparer
public class MypagePreparer implements ViewPreparer{

	@Override
	public void execute(Request arg0, AttributeContext arg1) {
		Map<String, String> menuMap = new LinkedHashMap<>();
		menuMap.put("/myPage/myPage", "나의 정보 조회");
		
		Map<String, Object> requestsScope = arg0.getContext(Request.REQUEST_SCOPE);
		requestsScope.put("menus", menuMap);
	}
	
}
