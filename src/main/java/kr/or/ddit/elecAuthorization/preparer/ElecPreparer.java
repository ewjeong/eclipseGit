package kr.or.ddit.elecAuthorization.preparer;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.annotation.Preparer;

@Preparer
public class ElecPreparer implements ViewPreparer {

	@Override
	public void execute(Request arg0, AttributeContext arg1) {
		Map<String, String> menuMap = new LinkedHashMap<>();
		menuMap.put("/elecAuthorization/approval/approList", "결재함");
		menuMap.put("/elecAuthorization/sangshin/sangshinList", "상신함");
		menuMap.put("/elecAuthorization/reference/referenceList", "참조함");
		menuMap.put("/elecAuthorization/line/lineList", "결재선 관리");
		menuMap.put("/elecAuthorization/forms/formsList", "결재양식 관리");
		
		Map<String, Object> requestScope = arg0.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menus", menuMap);
	}
}
