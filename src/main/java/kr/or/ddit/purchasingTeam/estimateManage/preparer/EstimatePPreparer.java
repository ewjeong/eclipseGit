package kr.or.ddit.purchasingTeam.estimateManage.preparer;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.annotation.Preparer;

@Preparer
public class EstimatePPreparer implements ViewPreparer{
	@Override
	public void execute(Request arg0, AttributeContext arg1) {
		Map<String, String> menuMap = new LinkedHashMap<>(); 
		menuMap.put("/purchasingTeam/estimateManage/estimateReqList", "견적요청서조회");
		menuMap.put("/purchasingTeam/estimateManage/estimateReqInsert", "견적요청서작성");
		menuMap.put("/purchasingTeam/estimateManage/estimateList", "견적서조회");
		menuMap.put("/purchasingTeam/estimateManage/estimateInsert", "견적서등록");
		
		Map<String, Object> requestScope = arg0.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menus", menuMap);		
		
	}
}
