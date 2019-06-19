package kr.or.ddit.salesTeam.orderManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.Order_SalesVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Sale_EprodVO;
import kr.or.ddit.vo.Sale_Eprod_ListVO;
import kr.or.ddit.vo.Sale_Est_ListVO;
import kr.or.ddit.vo.Sale_OprodVO;
import kr.or.ddit.vo.Sale_OrdVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;

/**
 * @author jinwan
 *
 */
@Repository
public interface IOrderSManageDAO {
	
	/**
	 * 하나의 주문서를 작성하는 메서드
	 * @param order_Sales
	 * @return 성공 : 0, 실패 : 1
	 */
	public int insertSalesOrder(Sale_OrdVO sale_Ord);
	
//	selectSalesOrder();
	
	/**
	 * 페이징처리를 위해 주문서의 갯수를 세는 메서드
	 * @param pagingVO
	 * @return long 타입의 주문서 갯수
	 */
	public long selectSalesOrderCount(PagingVO<Sale_Ord_ListVO> pagingVO);
	
	/**
	 * 여러 건의 주문을 보여주는 메서드
	 * @param pagingVO
	 * @return 여러 건의 주문 List
	 */
	public List<Sale_Ord_ListVO> selectSalesOrderList(PagingVO<Sale_Ord_ListVO> pagingVO);

	
	/**
	 * 한 건의 주문을 상세조회할 수 있는 메서드
	 * @param sale_ord_code
	 * @return Sale_Ord_ListVO(뷰 네임) 한 건에 대한 상세 정보
	 */
	public Sale_Ord_ListVO selectSalesOrder(String sale_ord_code);

	
	
	
	/**
	 * 한 건의 견적서 번호를 받아서 견적 정보를 조회하는 메서드
	 * @param int 한 건에 대한 견적서 번호
	 * @return 해당되는 견적서 번호를 통해 조회되는 한 건의 견적서 내용
	 */
	public Sale_Est_ListVO selectSalesEst(int sale_est_no);
	
	/**
	 * 모달창에 띄울 견적서 리스트 가져오는 메서드
	 * @param pagingVO
	 * @return 견적서 List
	 */
	public List<Sale_Est_ListVO> selectEstList(PagingVO<Sale_Est_ListVO> pagingVO);
	
	/**
	 * 주문 등록 성공 시 , 견적서 번호를 받아서 해당 견적서에 대한 주문 등록
	 * @param sale_est_no 견적서 번호
	 * @return 수정 성공 : 1 , 수정 실패 : 0
	 */
	public int updateOrdCompl(int sale_est_no);
	
	/**
	 * 한 건의 주문을 수정할 수 있는 메서드 (1)
	 * @param sale_Ord
	 * @return
	 */
	public int updateSalesOrder(Sale_OrdVO sale_Ord);
	
	/**
	 * 주문에 대한 상품 수정 (N) 
	 * @param sale_oprod
	 * @return 수정 성공 : 1, 수정 실패 : 0
	 */
	public int updateSaleOprod(Sale_OprodVO sale_oprod);
	
	
	/**
	 * 주문에 대한 엑셀 리스트 출력
	 * @param pagingVO
	 * @return 엑셀로 출력할 수 있는 주문 List
	 */
	public List<Sale_Ord_ListVO> selectSalesOrdExcelList(PagingVO<Sale_Ord_ListVO> pagingVO);
	
//	deleteSalesOrder();
	
	
	
}
