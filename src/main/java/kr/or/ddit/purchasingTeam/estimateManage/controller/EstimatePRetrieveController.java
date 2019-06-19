package kr.or.ddit.purchasingTeam.estimateManage.controller;

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

import kr.or.ddit.purchasingTeam.estimateManage.service.IEstimatePManageService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Est_ListVO;
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
public class EstimatePRetrieveController {
	
	@Inject
	IEstimatePManageService service;
	
	//화면
	@RequestMapping(value="/purchasingTeam/estimateManage/estimateList", method=RequestMethod.GET)
	public String getEstList(){
		return "purchase/estimate/estimateList";
	}
	
	@ResponseBody
	@RequestMapping(value="/purchasingTeam/estimateManage/estimateList", produces="application/json;charset=UTF-8")
	public PagingVO<Pur_Est_ListVO> getEstimateForm(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			, @RequestParam(name="cl_name") String cl_name
			, @RequestParam(name="pur_est_date") String pur_est_date
			, Pur_Est_ListVO searchData
			, @ModelAttribute("pagingVO") PagingVO<Pur_Est_ListVO> pagingVO
			, Model model
			){
		
		pagingVO = new PagingVO<Pur_Est_ListVO>(10, 5);
		searchData.setCl_name(cl_name);
		searchData.setPur_est_date(pur_est_date);
		
		pagingVO.setSearchData(searchData);
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = service.selectPurEstimateCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<Pur_Est_ListVO> estList = service.retrievePurEstimateList(pagingVO);
		pagingVO.setDataList(estList);
		
		model.addAttribute("pagingVO", pagingVO);
		return pagingVO;
	}
	
	//견적서상세조회
	@RequestMapping("/purchasingTeam/estimateManage/estimateView")
	public String getOrderView(
			@RequestParam(required=true, name="what") String pur_est_no
			, Model model){
		Pur_Est_ListVO purEstList = service.retrieveEstOrder(pur_est_no);
		model.addAttribute("purEstList", purEstList);
		return "purchase/estimate/estimateView";
	}
}
