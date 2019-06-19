package kr.or.ddit.salesTeam.estimateManage.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.salesTeam.estimateManage.service.IEstimateSManageService;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;
import kr.or.ddit.vo.Sale_EstVO;
import kr.or.ddit.vo.Sale_Est_ListVO;

@Controller
public class EstimateSCreateController {

	@Autowired
	IEstimateSManageService service;

	@RequestMapping("/salesTeam/estimateManage/estimateInsert")
	public String getEstimateForm(){
	
		return "sales/estimate/estimateForm";
	}
	
	//ajax로 거래처 페이징 처리 (거래처리스트)
	@RequestMapping(value="/salesTeam/estimateManage/estimateClientLists", produces="application/json;charset=UTF-8")
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
		
		long totalRecord = service.retrieveClientCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<ClientVO> clientList = service.retrieveSalesClientList(pagingVO);
		pagingVO.setDataList(clientList);
		pagingVO.setFunctionName("pagingBuyer");
		model.addAttribute("pagingVO", pagingVO);
		
		return pagingVO;
	}
	
	//ajax로 거래처에 대한 정보 불러오기
		@RequestMapping(value="/salesTeam/estimateManage/estimateClient/{cl_no}", produces="application/json;charset=UTF-8")
		@ResponseBody
		public ClientVO ajaxGetClient(@PathVariable String cl_no ){
			ClientVO client = service.retrieveSalesClient(cl_no);
			return client;
		}
	
	
	
	
	
	
	//ajax로 상품에 대한 정보 불러오기
	@RequestMapping(value="/salesTeam/estimateManage/estimateProd/{prod_no}", produces="application/json;charset=UTF-8")
	@ResponseBody
	public ProductVO ajaxGetProd(@PathVariable int prod_no ){
		 
		 ProductVO prod = service.retrieveSalesProd(prod_no);
		
		return prod;
	}
	
		//ajax로 제품 페이징 처리 (제품리스트)
		@RequestMapping(value="/salesTeam/estimateManage/estimateProdLists", produces="application/json;charset=UTF-8")
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
			
			long totalRecord = service.retrieveProdCount(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			
			List<ProductVO> prodList = service.retrieveSalesProdList(pagingVO);
			pagingVO.setDataList(prodList);
			pagingVO.setFunctionName("pagingProd");
			model.addAttribute("pagingVO", pagingVO);
			
			return pagingVO;
		}
	
		
		
		//등록작업 (등록 작업은 동기로 처리한다. --> 견적서 조회 화면으로 넘어간다.)
		@RequestMapping(value = "/salesTeam/estimateManage/estimateInsert", method=RequestMethod.POST)
		public String insertEst(
					RedirectAttributes redirectAttributes,
					Sale_EstVO sale_est
				){
			ServiceResult result = null;
			String msg = null;
			result = service.createSalesEstimate(sale_est); //sale_est를 받아서 
			
			if(ServiceResult.FAILED.equals(result)){
				msg = "서버 오류, 잠시 후 다시 확인해주세요";
			}else if(ServiceResult.OK.equals(result)){
				msg = "OK";
			}
			redirectAttributes.addFlashAttribute("message", msg);
			return "redirect:/salesTeam/estimateManage/estimateList";
					
		}
		
		
}
