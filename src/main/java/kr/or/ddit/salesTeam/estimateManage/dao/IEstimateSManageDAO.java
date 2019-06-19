package kr.or.ddit.salesTeam.estimateManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.Estimate_SalesVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;
import kr.or.ddit.vo.Sale_EprodVO;
import kr.or.ddit.vo.Sale_Eprod_ListVO;
import kr.or.ddit.vo.Sale_EstVO;
import kr.or.ddit.vo.Sale_Est_ListVO;

/**
 * @author jinwan
 *
 */
@Repository
public interface IEstimateSManageDAO {

	/**
	 * 견적서 등록
	 * @param sale_Est
	 * @return
	 */
	public int insertSalesEstimate(Sale_EstVO sale_Est);
	
//	selectSalesEstimate
	
	/**
	 * 페이징 처리를 위해 견적서 개수 조회
	 * @param pagingVO
	 * @return
	 */
	public long selectSalesEstimateCount(PagingVO<Sale_Est_ListVO> pagingVO);
	
	/**
	 * 견적서 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<Sale_Est_ListVO> selectSalesEstimateList(PagingVO<Sale_Est_ListVO> pagingVO);
	
	/**
	 * 견적서 수정(1)
	 * @param sale_Est 
	 * @return 성공 : 1 , 실패 : 0
	 */
	public int updateSalesEstimate(Sale_EstVO sale_Est);
	
	
	/**
	 * 견적에 대한 상품 수정(N)
	 * @param sale_eprod
	 * @return 수정 성공 : 1, 수정 실패 : 0
	 */
	public int updateSalesEprod(Sale_EprodVO sale_eprod);
	
	/**
	 * 하나의 견적서 번호를 받아서 해당 견적서 상세 조회
	 * @param sale_est_no
	 * @return 견적서 상세 조회할 정보를 담고있는 VO
	 */
	public Sale_Est_ListVO selectSalesEstimate(int sale_est_no); 
	
	
	/**
	 * 모달창을 통해 클라이언트 리스트 조회
	 * @return
	 */
	public List<ClientVO> selectSalesClientList(PagingVO<ClientVO> pagingVO);
	
	/**
	 * 모달 창에서 선택한 클라이언트 코드를 통해 한 건의 클라이언트를 상세조회
	 * @param cl_no 클라이언트 코드
	 * @return 한 건의 클라이언트
	 */
	public ClientVO selectSalesClient(String cl_no);
	
	
	/**
	 * 모달창을 통해 제품리스트 조회하기
	 * @return
	 */
	public List<ProductVO> selectSalesProdList(PagingVO<ProductVO> pagingVO);
	
	/**
	 * 모달 창에서 선택한 제품 번호를 통해 한 건의 제품 상세정보 조회
	 * @param prod_no
	 * @return
	 */
	public ProductVO selectSalesProd(int prod_no);
	
	/**
	 * 페이징 처리를 위해 전체 클라이언트 레코드 카운트
	 * @param pagingVO
	 * @return
	 */
	public long selectClientCount(PagingVO<ClientVO> pagingVO);
	
	/**
	 * 페이징 처리를 위해 전체 제품 레코드 카운트
	 * @param pagingVO
	 * @return
	 */
	public long selectProdCount(PagingVO<ProductVO> pagingVO);
	
	public List<Sale_Est_ListVO> selectSalesEstExcelList(PagingVO<Sale_Est_ListVO> pagingVO);
	
	
	/**
	 * 제품 목록 삭제
	 * @param eprodList
	 * @return
	 */
	public int deleteProd(int sale_est_no);
//	deleteSalesEstimate
	
	
	
}
