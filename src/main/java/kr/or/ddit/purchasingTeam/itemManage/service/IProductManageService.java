package kr.or.ddit.purchasingTeam.itemManage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;


/**
 * @since 2019. 5. 13
 * @version 1.0
 * @see
 * <pre>
 * [[개정이력(Modification Information)]] 
 * 수정일        수정자     수정내용
 * ==========   ======    ============== 
 * 2019. 6. 5	정은우		상품 조회
 * 2019. 6. 7   이초연      상품 등록
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 * 상품관리를 위한 service
 * 
 */
public interface IProductManageService {
	/**
	 * 상품리스트조회
	 * @param pagingVO
	 * @return
	 */
	public List<ProductVO> retrieveProdList(PagingVO<ProductVO> pagingVO);
	
	/**
	 * 페이징처리
	 * @param pagingVO
	 * @return
	 */
	public long retrieveProdCount(PagingVO<ProductVO> pagingVO);
	
	/**
	 * 상품 상세조회
	 * @param prod
	 * @return
	 */
	public ProductVO retrieveProdView(int prod_no);
	
	/**
	 * 상품등록
	 * @param prod
	 * @return
	 */
	public ServiceResult CreateProd(ProductVO prod);

	/**
	 * 상품 등록을 위한 품목 조회
	 * @return  null 인 경우 commonexception 발생
	 */
	public List<ItemVO> retrieveItemList();
}
