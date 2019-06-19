package kr.or.ddit.superManager.EmplscheduleManage.preparer;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.annotation.Preparer;

@Preparer
public class EmplSchePreparer implements ViewPreparer{
	@Override
	public void execute(Request arg0, AttributeContext arg1) {
		Map<String, String> menuMap = new LinkedHashMap<>();
		menuMap.put("/superManager/emplScheduleManage/emplScheduleList", "영업팀활동조회");
		
		Map<String, Object> requestScope = arg0.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menus", menuMap);		
		
	}
}
