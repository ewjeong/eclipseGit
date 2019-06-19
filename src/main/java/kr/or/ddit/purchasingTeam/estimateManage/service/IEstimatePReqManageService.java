package kr.or.ddit.purchasingTeam.estimateManage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProductVO;
import kr.or.ddit.vo.Pur_ErVO;
import kr.or.ddit.vo.Pur_Er_ListVO;


/**
 * @author 정은우
 * @since 2019. 5. 15.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 15.      정은우       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface IEstimatePReqManageService {
	
	
	/**
	 * 견적요청서 등록
	 * @param purEr
	 * @return
	 */
	public ServiceResult CreatePurReqEstimate(Pur_ErVO purEr);
	
	/**
	 * 견적요청서 상세조회
	 * @param pur_est_no 견적요청서번호
	 * @return
	 */
	public Pur_Er_ListVO retreiveReqEst(String pur_er_no);
	
	/**
	 * 견적요청서 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<Pur_Er_ListVO> retreivePurReqEstList(PagingVO<Pur_Er_ListVO> pagingVO);
	
	/**
	 * 페이징처리
	 * @param pagingVO
	 * @return
	 */
	public long selectPurReqEstCount(PagingVO<Pur_Er_ListVO> pagingVO);
	
	/**
	 * 견적서요청서 수정
	 * @param purEr 
	 * @return 성공 : 1 , 실패 : 0
	 */
	public ServiceResult modifyPurReqEstimate(Pur_ErVO purEr);
	
	/**
	 * 모달창을 통해 클라이언트 리스트 조회
	 * @return
	 */
	public List<ClientVO> retrievePurClientList(PagingVO<ClientVO> pagingVO);
	
	/**
	 * 모달 창에서 선택한 클라이언트 코드를 통해 한 건의 클라이언트를 상세조회
	 * @param cl_no 클라이언트 코드
	 * @return 한 건의 클라이언트
	 */
	public ClientVO retrievePurClient(String cl_no);
	
	
	/**
	 * 모달창을 통해 제품리스트 조회하기
	 * @return
	 */
	public List<ProductVO> retrievePurProdList(PagingVO<ProductVO> pagingVO);
	
	/**
	 * 모달 창에서 선택한 제품 번호를 통해 한 건의 제품 상세정보 조회
	 * @param prod_no
	 * @return
	 */
	public ProductVO retrievePurProd(int prod_no);
	
	/**
	 * 페이징 처리를 위해 전체 클라이언트 레코드 카운트
	 * @param pagingVO
	 * @return
	 */
	public long retrievePurClientCount(PagingVO<ClientVO> pagingVO);
	
	/**
	 * 페이징 처리를 위해 전체 제품 레코드 카운트
	 * @param pagingVO
	 * @return
	 */
	public long retrievePurProdCount(PagingVO<ProductVO> pagingVO);
	
}
