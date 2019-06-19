package kr.or.ddit.purchasingTeam.itemManage.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.purchasingTeam.itemManage.service.IItemManageService;
import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;
import kr.or.ddit.vo.SalesProfitVO;

@Controller
public class ItemRetrieveController {
	
	//서비스를 주입받는다
	@Inject
	IItemManageService service;
	
	//동기로 리스트 조회하기
	@RequestMapping(value ="/purchasingTeam/itemManage/itemList", method=RequestMethod.GET)
	public String itemList(
		//page
		@RequestParam(name="page", required=false, defaultValue="1" ) long currentPage
		,@RequestParam(name="searchType", required=false) String searchType
		,@RequestParam(name="searchWord", required=false) String searchWord
		,Model model
			){
		
		PagingVO<ItemVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = service.retrieveItemCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
	
		List<ItemVO> itemList = service.retrieveItemList(pagingVO);
		pagingVO.setDataList(itemList);
		
//		resp.setHeader("Pragma", "no-cache");
//		resp.setHeader("Cache-Control", "no-cache");
//		//파이어폭스
//		resp.addHeader("Cache-Control", "no-store");
//		resp.setDateHeader("Expires", 0);
		
		model.addAttribute("pagingVO", pagingVO);
		return "purchase/item/itemList";
	}
	
//	ajax (비동기처리) 페이징
	@RequestMapping(value="/purchasingTeam/itemManage/itemLists", produces="application/json;charset=UTF-8")
	@ResponseBody //비동기처리할 때 
	public PagingVO<ItemVO> ajaxList(
		@RequestParam(name="page", required=false, defaultValue="1" )long currentPage
		,@ModelAttribute("pagingVO") PagingVO<ItemVO> pagingVO
		, @RequestParam(name="searchType",required=false) String searchType
		, @RequestParam(name="searchWord",required=false) String searchWord
		,Model model
			){
		pagingVO = new PagingVO<>(10,5);
		
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = service.retrieveItemCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<ItemVO> itemList= service.retrieveItemList(pagingVO);
		pagingVO.setDataList(itemList);
		model.addAttribute("paging", pagingVO);
		
		return pagingVO;
	}

	
	
}
