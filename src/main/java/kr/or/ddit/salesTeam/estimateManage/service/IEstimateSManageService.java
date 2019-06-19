package kr.or.ddit.salesTeam.estimateManage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.Estimate_SalesVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;
import kr.or.ddit.vo.Sale_EprodVO;
import kr.or.ddit.vo.Sale_EstVO;
import kr.or.ddit.vo.Sale_Est_ListVO;

public interface IEstimateSManageService {
	
	public ServiceResult createSalesEstimate(Sale_EstVO sale_Est);
	
	public long retrieveSalesEstimateCount(PagingVO<Sale_Est_ListVO> pagingVO);
	
	public List<Sale_Est_ListVO> retrieveSalesEstimateList(PagingVO<Sale_Est_ListVO> pagingVO);
	
	public ServiceResult modifySalesEstimate(Sale_EstVO sale_Est);
	
	public ServiceResult modifySalesEprod(Sale_EprodVO sale_eprod);
	
	public Sale_Est_ListVO retrieveSalesEstimate(int sale_est_no);

	public List<ClientVO> retrieveSalesClientList(PagingVO<ClientVO> pagingVO);
	
	public ClientVO retrieveSalesClient(String cl_no);
	
	public List<ProductVO> retrieveSalesProdList(PagingVO<ProductVO> pagingVO);
	
	public ProductVO retrieveSalesProd(int prod_no);
	
	public long retrieveClientCount(PagingVO<ClientVO> pagingVO);
	
	public long retrieveProdCount(PagingVO<ProductVO> pagingVO);
	
	public List<Sale_Est_ListVO> retrieveSalesEstExcelList(PagingVO<Sale_Est_ListVO> pagingVO);
}
