package kr.or.ddit.purchasingTeam.itemManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.purchasingTeam.itemManage.service.IProductManageService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;
  

/**
 * @author 정은우
 * @since 2019. 6. 5.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 6. 5.      정은우       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class ProductRetrieveController {
	
	@Inject
	IProductManageService service;
	
	//화면
	@RequestMapping(value ="/purchasingTeam/itemManage/productList", method=RequestMethod.GET)
	public String productList(){
		return "purchase/item/productList";
	}
	
	//페이징처리
	@ResponseBody
	@RequestMapping(value="/purchasingTeam/itemManage/productList", produces="application/json;charset=UTF-8")
	public PagingVO<ProductVO> getPaging(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			, @RequestParam(name="prod_name", required=false) String prod_name
			, ProductVO searchData
			, @ModelAttribute("pagingVO") PagingVO<ProductVO> pagingVO
			, Model model
			){
		
		//screensize 10개
		pagingVO = new PagingVO<ProductVO>(10, 5);
		
		searchData.setProd_name(prod_name);
		
		//검색데이터 넣어줌
		pagingVO.setSearchData(searchData);
		//현재페이지 넣어줌
		pagingVO.setCurrentPage(currentPage);
		//토탈페이지의 수
		long totalRecord = service.retrieveProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		//전체 리스트 출력
		List<ProductVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);

		model.addAttribute("pagingVO", pagingVO);
		return pagingVO;
	}
		 
	//영업팀 주문서 상세조회
	@RequestMapping("/purchasingTeam/itemManage/productView")
	public String getOrderView(
			@RequestParam(required=true, name="what") int prod_no
			, Model model){
		ProductVO prodList = service.retrieveProdView(prod_no);
		model.addAttribute("prodList", prodList);
		return "purchase/item/productView";
	}
	

	
	
}



















