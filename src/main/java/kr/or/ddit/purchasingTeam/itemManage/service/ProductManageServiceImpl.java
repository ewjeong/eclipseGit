package kr.or.ddit.purchasingTeam.itemManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.purchasingTeam.itemManage.dao.IProductManageDAO;
import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;

@Service
public class ProductManageServiceImpl implements IProductManageService {
	
	@Inject
	private IProductManageDAO productManageDAO;

	//상품리스트조회
	@Override
	public List<ProductVO> retrieveProdList(PagingVO<ProductVO> pagingVO) {
		return productManageDAO.selectProdList(pagingVO);
	}

	//페이징처리
	@Override
	public long retrieveProdCount(PagingVO<ProductVO> pagingVO) {
		return productManageDAO.selectProdCount(pagingVO);
	}

	//상품상세조회
	@Override
	public ProductVO retrieveProdView(int prod_no) {
		return productManageDAO.selectProdView(prod_no);
	}
	
	//상품등록
	@Override
	public ServiceResult CreateProd(ProductVO prod) {
		int rowCnt = productManageDAO.insertProd(prod);
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt>0) result = ServiceResult.OK;
		return result;
	}

	@Override
	public List<ItemVO> retrieveItemList() {
		List<ItemVO> itemList = productManageDAO.selectItemList();
		if(itemList == null) throw new CommonException("조회된 품목 리스트가 없습니다.");
		return itemList;
	}

	
	

}
