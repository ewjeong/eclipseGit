package kr.or.ddit.purchasingTeam.estimateManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.purchasingTeam.estimateManage.service.IEstimatePReqManageService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Er_ListVO;


/**
 * @author 정은우
 * @since 2019. 5. 26.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 26.      정은우       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class EstimatePReqRetrieveController {
	
	@Inject
	IEstimatePReqManageService service;
	
	//화면
	@RequestMapping(value="/purchasingTeam/estimateManage/estimateReqList", method=RequestMethod.GET)
	public String getEstList(){
		return "purchase/estimate/estimateReqList";
	}
	
	@ResponseBody
	@RequestMapping(value="/purchasingTeam/estimateManage/estimateReqList", produces="application/json;charset=UTF-8")
	public PagingVO<Pur_Er_ListVO> getEstimateRequestForm(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			, @RequestParam(name="cl_name") String cl_name
			, @RequestParam(name="pur_er_date") String pur_er_date
			, Pur_Er_ListVO searchData
			, @ModelAttribute("pagingVO") PagingVO<Pur_Er_ListVO> pagingVO
			, Model model
			){
		
		pagingVO = new PagingVO<Pur_Er_ListVO>(10,5);
		searchData.setCl_name(cl_name);
		searchData.setPur_er_date(pur_er_date);
		
		pagingVO.setSearchData(searchData);
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = service.selectPurReqEstCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<Pur_Er_ListVO> erList = service.retreivePurReqEstList(pagingVO);
		pagingVO.setDataList(erList);
		
		model.addAttribute("pagingVO", pagingVO);
		return pagingVO;
	}
	
	//발주서상세조회
	@RequestMapping("/purchasingTeam/estimateManage/estimateReqView")
	public String getOrderView(
			@RequestParam(required=true, name="what") String pur_er_no
			, Model model){
		Pur_Er_ListVO purErList = service.retreiveReqEst(pur_er_no);
		model.addAttribute("purErList", purErList);
		return "purchase/estimate/estimateReqView";
	}
	
	
}



















