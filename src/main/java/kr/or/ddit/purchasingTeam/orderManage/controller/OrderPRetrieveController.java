package kr.or.ddit.purchasingTeam.orderManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.purchasingTeam.orderManage.service.IOrderPManageService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Ord_ListVO;


/**
 * @author 정은우
 * @since 2019. 5. 15.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 15.      정은우       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class OrderPRetrieveController {
	@Inject
	IOrderPManageService service;
	
	//화면
	@RequestMapping(value="/purchasingTeam/orderManage/orderList", method=RequestMethod.GET)
	public String getOrderList(){
		return "purchase/order/orderList";
	}
	
	@ResponseBody
	@RequestMapping(value="/purchasingTeam/orderManage/orderList", produces="application/json;charset=UTF-8")
	public PagingVO<Pur_Ord_ListVO> getOrderForm(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			, @RequestParam(name="cl_name") String cl_name
			, @RequestParam(name="pur_ord_date") String pur_ord_date
			, Pur_Ord_ListVO searchData
			, @ModelAttribute("pagingVO") PagingVO<Pur_Ord_ListVO> pagingVO
			, Model model
			){
		pagingVO = new PagingVO<Pur_Ord_ListVO>(10, 5);
		searchData.setCl_name(cl_name);
		searchData.setPur_ord_date(pur_ord_date);
		
		pagingVO.setSearchData(searchData);
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = service.retrivePurOrderCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<Pur_Ord_ListVO> purOrdList = service.retrievePurOrderList(pagingVO);
		pagingVO.setDataList(purOrdList);
		
		model.addAttribute("pagingVO", pagingVO);
		return pagingVO;
	}
	
	//발주서상세조회
	@RequestMapping("/purchasingTeam/orderManage/orderView")
	public String getOrderView(
			@RequestParam(required=true, name="what") String pur_ord_code
			, Model model){
		Pur_Ord_ListVO purOrdList = service.retrievePurOrder(pur_ord_code);
		model.addAttribute("purOrdList", purOrdList);
		return "purchase/order/orderView";
	}
	
}
