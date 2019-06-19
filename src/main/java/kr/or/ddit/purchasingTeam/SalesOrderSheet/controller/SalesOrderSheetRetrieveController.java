package kr.or.ddit.purchasingTeam.SalesOrderSheet.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.purchasingTeam.SalesOrderSheet.service.ISalesOrderSheetService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;

/**
 * @author 정은우
 * @since 2019. 5. 13.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 13.      정은우       최초작성
 * 2019. 5. 18.      정은우       검색, 페이징처리
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class SalesOrderSheetRetrieveController {
	@Inject
	ISalesOrderSheetService service;
	
	//화면
	@RequestMapping(value="/purchasingTeam/salesOrderSheet/orderSheetList", method=RequestMethod.GET)
	public String getOrderSheetList(){
		return "purchase/salesOrderSheet";
	}
	
	//페이징처리
	@ResponseBody
	@RequestMapping(value="/purchasingTeam/salesOrderSheet/orderSheetList", produces="application/json;charset=UTF-8")
	public PagingVO<Sale_Ord_ListVO> getPaging(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			, @RequestParam(name="sale_ord_date", required=false) String sale_ord_date
			, @RequestParam(name="cl_name", required=false) String cl_name
			, Sale_Ord_ListVO searchData
			, @ModelAttribute("pagingVO") PagingVO<Sale_Ord_ListVO> pagingVO
			, Model model
			){
		
		//screensize 10개
		pagingVO = new PagingVO<Sale_Ord_ListVO>(10, 5);
		
		searchData.setSale_ord_date(sale_ord_date);
		searchData.setCl_name(cl_name);
		
		//검색데이터 넣어줌
		pagingVO.setSearchData(searchData);
		//현재페이지 넣어줌
		pagingVO.setCurrentPage(currentPage);
		//토탈페이지의 수
		long totalRecord = service.retrieveSalesOrderCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		//전체 리스트 출력
		List<Sale_Ord_ListVO> saleOrdList = service.retireveSalesOrderSheetList(pagingVO);
		pagingVO.setDataList(saleOrdList);

		model.addAttribute("pagingVO", pagingVO);
		return pagingVO;
	}
	 
	//영업팀 주문서 상세조회
	@RequestMapping("/purchasingTeam/salesOrderSheet/orderSheetView")
	public String getOrderView(
			@RequestParam(required=true, name="what") String sale_ord_code
			, Model model){
		Sale_Ord_ListVO saleOrdList = service.retrieveSalesOrderSheet(sale_ord_code);
		model.addAttribute("saleOrdList", saleOrdList);
		return "purchase/salesOrderView";
	}

	
}
