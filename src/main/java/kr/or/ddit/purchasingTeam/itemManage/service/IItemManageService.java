package kr.or.ddit.purchasingTeam.itemManage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;

public interface IItemManageService {
	
	public ServiceResult createItem(ItemVO item);
	
	public List<ItemVO> retrieveItemList(PagingVO<ItemVO> pagingVO);
	
	public long retrieveItemCount(PagingVO<ItemVO> pagingVO);
	
	public ServiceResult removeItem(String item_code);
	
	public List<ItemVO> retrieveTopItemCode();
	
	public ServiceResult createTopItem(ItemVO item);
	
	public List<ProductVO> retrieveProductList(String item_name);
	
	
}
