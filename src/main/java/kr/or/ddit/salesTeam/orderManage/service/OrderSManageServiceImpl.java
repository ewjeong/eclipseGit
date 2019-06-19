package kr.or.ddit.salesTeam.orderManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.salesTeam.orderManage.dao.IOrderSManageDAO;
import kr.or.ddit.vo.Order_SalesVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Sale_Eprod_ListVO;
import kr.or.ddit.vo.Sale_Est_ListVO;
import kr.or.ddit.vo.Sale_OprodVO;
import kr.or.ddit.vo.Sale_OrdVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;

@Service
public class OrderSManageServiceImpl implements IOrderSManageService {

	@Inject
	private IOrderSManageDAO orderDAO;
	
	@Transactional
	@Override
	public ServiceResult createSalesOrder(Sale_OrdVO sale_Ord) {
		int rowCnt = orderDAO.insertSalesOrder(sale_Ord);
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt>0){
			result = ServiceResult.OK;
			modifyOrdCompl(sale_Ord.getSale_est_no());
		}
		
		return result;
		
	}

	@Override
	public long retrieveSalesOrderCount(PagingVO<Sale_Ord_ListVO> pagingVO) {
		return orderDAO.selectSalesOrderCount(pagingVO);
	}

	@Override
	public List<Sale_Ord_ListVO> retrieveSalesOrderList(PagingVO<Sale_Ord_ListVO> pagingVO) {
		return orderDAO.selectSalesOrderList(pagingVO);
	}

	@Override
	public Sale_Ord_ListVO retrieveSalesOrder(String sale_ord_code) {
		Sale_Ord_ListVO sale_ord_listVO = orderDAO.selectSalesOrder(sale_ord_code);
		if(sale_ord_code==null) throw new CommonException("해당하는 주문서가 없습니다.");
		return sale_ord_listVO;
	}

	@Override
	public Sale_Est_ListVO retrieveSalesEst(int sale_est_no) {
		return orderDAO.selectSalesEst(sale_est_no);
	}

	@Override
	public List<Sale_Est_ListVO> retrieveEstList(PagingVO<Sale_Est_ListVO> pagingVO) {
		return orderDAO.selectEstList(pagingVO);
	}

	@Override
	public int modifyOrdCompl(int sale_est_no) {
		return orderDAO.updateOrdCompl(sale_est_no);
	}


	//주문수정(1건)
	@Transactional
	@Override
	public ServiceResult modifySalesOrder(Sale_OrdVO sale_Ord) {
		 int rowCnt = orderDAO.updateSalesOrder(sale_Ord);
		 ServiceResult result = null;
		 if(rowCnt>0){
			 result = ServiceResult.OK;
		 }else{
			 result = ServiceResult.FAILED;
		 }
		 for(Sale_OprodVO sale_oprod : sale_Ord.getSale_oprodList()){
			 result = modifySaleOprod(sale_oprod);
		 }
		 
		 
		return result;
	}
	
	//하나의 주문에 대한 상품수정(N건)
	@Override
	public ServiceResult modifySaleOprod(Sale_OprodVO sale_oprod) {
		int rowCnt = orderDAO.updateSaleOprod(sale_oprod);
		
		ServiceResult result = null;
		if(rowCnt > 0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<Sale_Ord_ListVO> retrieveSalesOrdExcelList(PagingVO<Sale_Ord_ListVO> pagingVO) {
		return orderDAO.selectSalesOrdExcelList(pagingVO);
	}
	

}
