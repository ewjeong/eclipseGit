package kr.or.ddit.purchasingTeam.itemManage.preparer;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.annotation.Preparer;

@Preparer
public class ItemPreparer implements ViewPreparer{
	@Override
	public void execute(Request arg0, AttributeContext arg1) {
		Map<String, String> menuMap = new LinkedHashMap<>();
		menuMap.put("/purchasingTeam/itemManage/itemList", "품목조회");
		menuMap.put("/purchasingTeam/itemManage/itemInsert", "품목등록");
		menuMap.put("/purchasingTeam/itemManage/productList", "상품조회");
		menuMap.put("/purchasingTeam/itemManage/productInsert", "상품등록");
		menuMap.put("/purchasingTeam/itemManage/proposeProduct", "상품검출서비스");
		
		Map<String, Object> requestScope = arg0.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menus", menuMap);		
		
	}
}
