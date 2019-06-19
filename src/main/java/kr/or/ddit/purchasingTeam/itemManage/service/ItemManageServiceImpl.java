package kr.or.ddit.purchasingTeam.itemManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.itemManage.dao.IItemManageDAO;
import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;

@Service
public class ItemManageServiceImpl implements IItemManageService {
	
	@Inject
	private IItemManageDAO itemDAO;
	
	@Override
	public ServiceResult createItem(ItemVO item) {
		int rowCnt = itemDAO.insertItem(item);
		ServiceResult result = null;
		if(rowCnt>0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<ItemVO> retrieveItemList(PagingVO<ItemVO> pagingVO) {
		// TODO Auto-generated method stub
		return itemDAO.selectItemList(pagingVO);
	}

	@Override
	public long retrieveItemCount(PagingVO<ItemVO> pagingVO) {
		return itemDAO.selectItemCount(pagingVO);
	}

	@Override
	public ServiceResult removeItem(String item_code) {
		int rowCnt = itemDAO.deleteItem(item_code);
		ServiceResult result = null;
		if(rowCnt>0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<ItemVO> retrieveTopItemCode() {
		return itemDAO.selectTopItemCode(); 
	}

	@Override
	public ServiceResult createTopItem(ItemVO item) {
		int rowCnt = itemDAO.insertTopItem(item);
		ServiceResult result;
		if(rowCnt>0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<ProductVO> retrieveProductList(String item_name) {
		return itemDAO.selectProductList(item_name);
	}

}
