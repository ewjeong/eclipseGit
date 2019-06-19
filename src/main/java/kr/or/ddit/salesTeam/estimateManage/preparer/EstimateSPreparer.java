package kr.or.ddit.salesTeam.estimateManage.preparer;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.annotation.Preparer;

@Preparer
public class EstimateSPreparer implements ViewPreparer {
	@Override
	public void execute(Request arg0, AttributeContext arg1) {
		Map<String, String> menuMap = new LinkedHashMap<>();
		menuMap.put("/salesTeam/estimateManage/estimateList", "견적서 조회");
		menuMap.put("/salesTeam/estimateManage/estimateInsert", "견적서 등록");
		
		Map<String, Object> requestScope = arg0.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menus", menuMap);		
		
	}
}
