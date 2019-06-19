package kr.or.ddit.purchasingTeam.itemManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;

@Repository
public interface IProductManageDAO {
	
	/**
	 * 상품리스트조회
	 * @param pagingVO
	 * @return
	 */
	public List<ProductVO> selectProdList(PagingVO<ProductVO> pagingVO);
	
	/**
	 * 페이징처리
	 * @param pagingVO
	 * @return
	 */
	public long selectProdCount(PagingVO<ProductVO> pagingVO);
	
	/**
	 * 상품 상세조회
	 * @param prod
	 * @return
	 */
	public ProductVO selectProdView(int prod_no);
	
	/**
	 * 상품등록
	 * @param prod
	 * @return
	 */
	public int insertProd(ProductVO prod);

	/**
	 * 상품 등록을 위한 품목 리스트 조회
	 * @return
	 */
	public List<ItemVO> selectItemList();
}
