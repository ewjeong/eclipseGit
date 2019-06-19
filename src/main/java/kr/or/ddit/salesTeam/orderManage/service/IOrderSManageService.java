package kr.or.ddit.salesTeam.orderManage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.Order_SalesVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Sale_Eprod_ListVO;
import kr.or.ddit.vo.Sale_Est_ListVO;
import kr.or.ddit.vo.Sale_OprodVO;
import kr.or.ddit.vo.Sale_OrdVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;

public interface IOrderSManageService {
	
	public ServiceResult createSalesOrder(Sale_OrdVO sale_Ord);
	
	public long retrieveSalesOrderCount(PagingVO<Sale_Ord_ListVO> pagingVO);
	
	public List<Sale_Ord_ListVO> retrieveSalesOrderList(PagingVO<Sale_Ord_ListVO> pagingVO);
	
	public Sale_Ord_ListVO retrieveSalesOrder(String sale_ord_code);
	
	public Sale_Est_ListVO retrieveSalesEst(int sale_est_no);	
	
	public List<Sale_Est_ListVO> retrieveEstList(PagingVO<Sale_Est_ListVO> pagingVO);
	
	public int modifyOrdCompl(int sale_est_no);
	
	public ServiceResult modifySalesOrder(Sale_OrdVO sale_Ord);
	
	public ServiceResult modifySaleOprod(Sale_OprodVO sale_oprod);
	
	public List<Sale_Ord_ListVO> retrieveSalesOrdExcelList(PagingVO<Sale_Ord_ListVO> pagingVO);
}
