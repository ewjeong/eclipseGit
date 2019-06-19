package kr.or.ddit.purchasingTeam.stockManage.preparer;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.annotation.Preparer;

@Preparer
public class StockPreparer implements ViewPreparer{
	@Override
	public void execute(Request arg0, AttributeContext arg1) {
		Map<String, String> menuMap = new LinkedHashMap<>();
		menuMap.put("/purchasingTeam/stockManage/stockList", "재고관리");
		menuMap.put("/purchasingTeam/stockManage/warehousingInsert", "입고등록");
		menuMap.put("/purchasingTeam/stockManage/releaseInsert", "출고등록");
		menuMap.put("/purchasingTeam/stockManage/warehousingList", "입고내역");
		menuMap.put("/purchasingTeam/stockManage/releaseList", "출고내역");
		
		Map<String, Object> requestScope = arg0.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menus", menuMap);		
		
	}
}
