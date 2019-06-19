package kr.or.ddit.salesTeam.estimateManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.salesTeam.estimateManage.dao.IEstimateSManageDAO;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.Estimate_SalesVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;
import kr.or.ddit.vo.Sale_EprodVO;
import kr.or.ddit.vo.Sale_EstVO;
import kr.or.ddit.vo.Sale_Est_ListVO;

@Service
public class EstimateSManageServiceImpl implements IEstimateSManageService{

	@Inject
	IEstimateSManageDAO estDAO;
	
	@Override
	public ServiceResult createSalesEstimate(Sale_EstVO sale_Est) {
		int rowCnt = estDAO.insertSalesEstimate(sale_Est);
		ServiceResult result = null;
		if(rowCnt>0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public long retrieveSalesEstimateCount(PagingVO<Sale_Est_ListVO> pagingVO) {
		return estDAO.selectSalesEstimateCount(pagingVO);
	}

	@Override
	public List<Sale_Est_ListVO> retrieveSalesEstimateList(PagingVO<Sale_Est_ListVO> pagingVO) {
		return estDAO.selectSalesEstimateList(pagingVO);
	}

	@Transactional
	@Override
	public ServiceResult modifySalesEstimate(Sale_EstVO sale_Est) {
		int rowCnt = estDAO.updateSalesEstimate(sale_Est);
		ServiceResult result = null;
		if(rowCnt>0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		for(Sale_EprodVO sale_eprod : sale_Est.getSale_eprodList()){
			result = modifySalesEprod(sale_eprod);
		}
		
		return null;
	}

	@Override
	public Sale_Est_ListVO retrieveSalesEstimate(int sale_est_no) {
		return estDAO.selectSalesEstimate(sale_est_no);
	}

	@Override
	public List<ClientVO> retrieveSalesClientList(PagingVO<ClientVO> pagingVO) {
		return estDAO.selectSalesClientList(pagingVO);
	}

	@Override
	public List<ProductVO> retrieveSalesProdList(PagingVO<ProductVO> pagingVO) {
		return estDAO.selectSalesProdList(pagingVO);
	}

	@Override
	public ClientVO retrieveSalesClient(String cl_no) {
		return estDAO.selectSalesClient(cl_no);
	}

	@Override
	public ProductVO retrieveSalesProd(int prod_no) {
		return estDAO.selectSalesProd(prod_no);
	}

	@Override
	public long retrieveClientCount(PagingVO<ClientVO> pagingVO) {
		return estDAO.selectClientCount(pagingVO);
	}

	@Override
	public long retrieveProdCount(PagingVO<ProductVO> pagingVO) {
		return estDAO.selectProdCount(pagingVO);
	}

	@Override
	public List<Sale_Est_ListVO> retrieveSalesEstExcelList(PagingVO<Sale_Est_ListVO> pagingVO) {
		return estDAO.selectSalesEstExcelList(pagingVO);
	}

//수정	
	@Override
	public ServiceResult modifySalesEprod(Sale_EprodVO sale_eprod) {
		int rowCnt = estDAO.updateSalesEprod(sale_eprod);
		ServiceResult result = null;
		if(rowCnt>0){
			result= ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	

}
