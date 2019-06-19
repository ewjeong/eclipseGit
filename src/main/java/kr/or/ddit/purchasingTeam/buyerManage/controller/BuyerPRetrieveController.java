package kr.or.ddit.purchasingTeam.buyerManage.controller;

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

import kr.or.ddit.purchasingTeam.buyerManage.service.IBuyerPManageService;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.PagingVO;


/**
 * @author 정은우
 * @since 2019. 5. 30.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 30.      정은우       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class BuyerPRetrieveController {
	
	@Inject
	IBuyerPManageService service;
	
	//화면을 띄워주는 컨트롤러
	@RequestMapping(value="/purchasingTeam/buyerManage/buyerList", method=RequestMethod.GET)
	public String getPurBuyerList(){
		return "purchase/buyer/buyerList";
	}
	
	//list AJAX
	@ResponseBody
	@RequestMapping(value="/purchasingTeam/buyerManage/buyerList", produces="application/json;charset=UTF-8")
	public PagingVO<ClientVO> ajaxPurBuyerList(
			@ModelAttribute("pagingVO") PagingVO<ClientVO> pagingVO
			,@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			,@RequestParam(name="local", required=false) String cl_add1
			,@RequestParam(name="cl_name",required=false) String cl_name
			,ClientVO searchData
			,Model model
			,HttpServletResponse resp
			){
		
		 //screensize 3개
		   pagingVO= new PagingVO<ClientVO>(5, 5);
		
		searchData.setCl_add1(cl_add1);
		searchData.setCl_name(cl_name);
		
		//검색데이터 넣어줌
		pagingVO.setSearchData(searchData);
		//현재페이지 넣어줌
		pagingVO.setCurrentPage(currentPage);
		//토탈페이지 수
		long totalRecord= service.retrievePurBuyerCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		//전체리스트출력
		List<ClientVO> buyerList = service.retrievePurBuyerList(pagingVO);
		pagingVO.setDataList(buyerList);
		
		model.addAttribute("pagingVO",pagingVO);
		
		return pagingVO;
	}
	

	
}
