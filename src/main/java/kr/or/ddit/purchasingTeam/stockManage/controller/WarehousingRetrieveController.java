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

import kr.or.ddit.purchasingTeam.stockManage.service.IWarehousingManageService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Ord_ListVO;


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
public class WarehousingRetrieveController {
	@Inject
	IWarehousingManageService service;
	
	//화면
	@RequestMapping(value="/purchasingTeam/stockManage/warehousingList", method=RequestMethod.GET)
	public String getWareList(){
		return "purchase/stock/warehousingList";
	}
	
	@ResponseBody
	@RequestMapping(value="/purchasingTeam/stockManage/warehousingList", produces="application/json;charset=UTF-8" )
	public PagingVO<Pur_Ord_ListVO> getWareForm(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			, @RequestParam(name="prod_name") String prod_name
			, @RequestParam(name="ware_date") String ware_date
			, Pur_Ord_ListVO searchData
			, @ModelAttribute("pagingVO") PagingVO<Pur_Ord_ListVO> pagingVO
			, Model model
			){
		
		pagingVO = new PagingVO<Pur_Ord_ListVO>(10,5);
		searchData.setWare_date(ware_date);
		searchData.setProd_name(prod_name);
		
		pagingVO.setSearchData(searchData);
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = service.retrieveWarehousingCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<Pur_Ord_ListVO> purOrdList = service.retrieveWarehousingList(pagingVO);
		pagingVO.setDataList(purOrdList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return pagingVO;
		
	}
}


















