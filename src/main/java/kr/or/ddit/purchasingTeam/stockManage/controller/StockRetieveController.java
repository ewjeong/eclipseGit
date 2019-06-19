package kr.or.ddit.purchasingTeam.stockManage.controller;
  
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.purchasingTeam.stockManage.service.IStockManageService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.StockVO;


/**
 * @author 정은우
 * @since 2019. 5. 14.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 14.      정은우       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class StockRetieveController {
	@Inject
	IStockManageService service;
	
	//화면
	@RequestMapping(value="/purchasingTeam/stockManage/stockList", method=RequestMethod.GET)
	public String getStockList(){
		return "purchase/stock/stockList";
	}
	
	//페이징처리
	@ResponseBody
	@RequestMapping(value="/purchasingTeam/stockManage/stockList", produces="application/json;charset=UTF-8")
	public PagingVO<StockVO> getStockForm(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			, @RequestParam(name="prod_name") String prod_name
			, StockVO searchData
			, @ModelAttribute("pagingVO") PagingVO<StockVO> pagingVO
			, Model model
			){
		
		//screensize
		pagingVO = new PagingVO<StockVO>(3, 5);
		searchData.setProd_name(prod_name);
		
		pagingVO.setSearchData(searchData);
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = service.retrieveStockCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<StockVO> stockList = service.retrieveStockList(pagingVO);
		pagingVO.setDataList(stockList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return pagingVO;
	}
}











