package kr.or.ddit.purchasingTeam.estimateManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.Estimate_PurVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Er_ListVO;
import kr.or.ddit.vo.Pur_EstVO;
import kr.or.ddit.vo.Pur_Est_ListVO;
import kr.or.ddit.vo.Pur_Ord_ListVO;
import kr.or.ddit.vo.SelectPurErListVO;


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
@Repository
public interface IEstimatePManageDAO {
	
	/**
	 * 견적서 등록
	 * @param purEst
	 * @return
	 */
	public int insertPurEstimate(Pur_EstVO purEst);
	
	/**
	 * 견적서 상세조회
	 * @param pur_est_no 견적서번호
	 * @return
	 */
	public Pur_Est_ListVO selectEstOrder(String pur_est_no);
	
	public List<Pur_Est_ListVO> selectPurEstimateList(PagingVO<Pur_Est_ListVO> pagingVO);
	
	public long selectPurEstimateCount(PagingVO<Pur_Est_ListVO> pagingVO);
	
	/**
	 * @param pur_er_no 한건에 대한 견적요청서 번호
	 * @return 해당되는 견적서 번호를 통해 조회되는 한 건의 견적서 내용 + 여러개의 상품
	 */
	public Pur_Er_ListVO selectPurEr(String pur_er_no);
	
	/**
	 * 모달창에 띄워줄 견적서리스트를 가져올때 사용
	 */
	public List<SelectPurErListVO> selectErList(PagingVO<SelectPurErListVO> pagingVO);
	
	
	
	
	
}
