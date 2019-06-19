package kr.or.ddit.purchasingTeam.itemManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;
import kr.or.ddit.vo.StockVO;

@Repository
public interface IItemManageDAO {
	
	public int insertItem(ItemVO item);
	
	public List<ItemVO> selectItemList(PagingVO<ItemVO> pagingVO);
	
	public long selectItemCount(PagingVO<ItemVO> pagingVO);
	
	public int deleteItem(String item_code);
	
	public List<ItemVO> selectTopItemCode();
	
	//품목 코드와 품목명을 입력받는다 (상위품목)
	public int insertTopItem(ItemVO item);
	
	/**
	 * 품목에 해당하는 상품리스트 조회 
	 */
	public List<ProductVO> selectProductList(String item_name);
}
