package kr.or.ddit.purchasingTeam.estimateManage.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.InsertHint;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.estimateManage.service.IEstimatePReqManageService;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;
import kr.or.ddit.vo.Pur_ErVO;
import kr.or.ddit.vo.Pur_Er_ProdVO;
import kr.or.ddit.vo.ReleaseVO;

@Controller
public class EstimatePReqCreateController {

	@Inject
	IEstimatePReqManageService service;
	
	@RequestMapping("/purchasingTeam/estimateManage/estimateReqInsert")
	public String getEstimateRequestForm(
			){
		return "purchase/estimate/estimateReqForm";
	}
	
	//ajax로 거래처 페이징 처리 (거래처리스트)
	@RequestMapping(value="/purchasingTeam/estimateManage/estimateClientLists", produces="application/json;charset=UTF-8")
	@ResponseBody //보내는 데이터가 객체일 때
	public PagingVO<ClientVO> ajaxGetClientList(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			,@RequestParam(required=false) String searchWord //검색단어
			,@RequestParam(required=false) String searchType //검색타입
			,Model model
			,HttpServletResponse resp
			){
		
		PagingVO<ClientVO> pagingVO = new PagingVO<ClientVO>(3,5);
		
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		//현재 페이지를 넣어준다.
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = service.retrievePurClientCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<ClientVO> clientList = service.retrievePurClientList(pagingVO);
		pagingVO.setDataList(clientList);
		pagingVO.setFunctionName("pagingBuyer");
		model.addAttribute("pagingVO", pagingVO);
		
		return pagingVO;
	}
	
	//ajax로 거래처에 대한 정보 불러오기
	@RequestMapping(value="/purchasingTeam/estimateManage/estimateClient/{cl_no}", produces="application/json;charset=UTF-8")
	@ResponseBody
	public ClientVO ajaxGetClient(@PathVariable String cl_no ){
		ClientVO client = service.retrievePurClient(cl_no);
		return client;
	}
	
	//ajax로 상품에 대한 정보 불러오기
	@RequestMapping(value="/purchasingTeam/estimateManage/estimateProd/{prod_no}", produces="application/json;charset=UTF-8")
	@ResponseBody
	public ProductVO ajaxGetProd(@PathVariable int prod_no ){
		 
		 ProductVO prod = service.retrievePurProd(prod_no);
		
		return prod;
	}
		
	//ajax로 제품 페이징 처리 (제품리스트)
	@RequestMapping(value="/purchasingTeam/estimateManage/estimateProdLists", produces="application/json;charset=UTF-8")
	@ResponseBody //보내는 데이터가 객체일 때
	public PagingVO<ProductVO> ajaxGetProdList(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			,@RequestParam(required=false) String searchWord //검색어
			,@RequestParam(required=false) String searchType //검색분류
			,Model model
			,HttpServletResponse resp
			){
		
		PagingVO<ProductVO> pagingVO = new PagingVO<>(3,5);
		
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = service.retrievePurProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<ProductVO> prodList = service.retrievePurProdList(pagingVO);
		pagingVO.setDataList(prodList);
		pagingVO.setFunctionName("pagingProd");
		model.addAttribute("pagingVO", pagingVO);
		
		return pagingVO;
	}
		
			
			
	//등록작업 (등록 작업은 동기로 처리한다. --> 견적서 조회 화면으로 넘어간다.)
	@RequestMapping(value = "/purchasingTeam/estimateManage/estimateReqInsert", method=RequestMethod.POST)
	public String insertEst(
				RedirectAttributes redirectAttributes,
				Model model,
				Pur_ErVO purEr
//				@Validated(InsertHint.class) @ModelAttribute("purErVO") Pur_ErVO purErVO,
//				Errors errors
			){
		
		
		String view = null;
		String message = null;
//		if(!errors.hasErrors()){
			ServiceResult result = service.CreatePurReqEstimate(purEr);
			if(ServiceResult.OK.equals(result)){
				view = "redirect:/purchasingTeam/estimateManage/estimateReqList";
				message = "OK";
				redirectAttributes.addFlashAttribute("message", message);
			}else {
				view = "/purchasingTeam/estimateManage/estimateReqInsert";
				message = "서버 오류, 잠시 후 다시 확인해주세요";
			}
//		}else{
//			message="none";
//			view="purchase/estimate/estimateReqForm";
//		}	
		model.addAttribute("message", message);
		return view;				
	}
}
