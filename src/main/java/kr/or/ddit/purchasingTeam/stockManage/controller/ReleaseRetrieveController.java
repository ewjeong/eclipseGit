package kr.or.ddit.purchasingTeam.stockManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.purchasingTeam.stockManage.service.IReleaseManageService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;

@Controller
public class ReleaseRetrieveController {
	
	@Inject
	IReleaseManageService service;

	//화면
	@RequestMapping(value="/purchasingTeam/stockManage/releaseList", method=RequestMethod.GET)
	public String getRelList(){
		return "purchase/stock/releaseList";
	}
	
	@ResponseBody
	@RequestMapping(value="/purchasingTeam/stockManage/releaseList", produces="application/json;charset=UTF-8" )
	public PagingVO<Sale_Ord_ListVO> getRelForm(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			, @RequestParam(name="prod_name") String prod_name
			, @RequestParam(name="rel_date") String rel_date
			, Sale_Ord_ListVO searchData
			, @ModelAttribute("pagingVO") PagingVO<Sale_Ord_ListVO> pagingVO
			, Model model
			){
		pagingVO = new PagingVO<Sale_Ord_ListVO>(10, 5);
		searchData.setProd_name(prod_name);
		searchData.setRel_date(rel_date);
		
		pagingVO.setSearchData(searchData);
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = service.retrieveReleaseCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<Sale_Ord_ListVO> releaseList = service.retrieveReleaseList(pagingVO);
		pagingVO.setDataList(releaseList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return pagingVO;
	}
}

