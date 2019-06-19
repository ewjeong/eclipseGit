package kr.or.ddit.salesProfitManage.preparer;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.annotation.Preparer;

@Preparer
public class SalesProfitPreparer implements ViewPreparer{
	@Override
	public void execute(Request arg0, AttributeContext arg1) {
		Map<String, String> menuMap = new LinkedHashMap<>();
		menuMap.put("/salesProfitList", "매출조회");
		
		Map<String, Object> requestScope = arg0.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menus", menuMap);	
	}
}
