package kr.or.ddit.elecAuthorization.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CompleteListVO;
import kr.or.ddit.vo.Elec_ApprlineVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.WaitListVO;

/**
 * @author 이초연
 * @since 2019. 5. 13
 * @version 1.0
 * @see
 * <pre>
 * [[개정이력(Modification Information)]] 
 * 수정일        수정자     수정내용
 * ==========   ======    ============== 
 * 2019. 5. 13	이초연		최초 작성
 * 2019. 5. 15  이초연      대기/완료 문서 리스트 분류
 * 2019. 5. 23  이초연      결재완료처리(elec_comple='Y') 업데이트
 * 2019. 6. 5   이초연      결재 완료 시, 발주서 또는 주문서 테이블에 elec_comple 업데이트
 * 
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 * 결재함 조회 및 결재완료처리에 대한 인터페이스
 * - 결재 대기 문서 및 리스트 조회
 * - 결재 완료 문서 및 리스트 조회
 * 
 */
@Repository
public interface IApprovalDAO {
	/**
	 * 대기 결재 문서  조회
	 * @param elec_no
	 * @return
	 */
	public WaitListVO selectWaitApproval(int elec_no);
	
	/**
	 * 대기 결재 문서 개수
	 * @param pagingVO
	 * @return
	 */
	public long selectWaitApprovalCount(PagingVO<WaitListVO> pagingVO);
	
	/**
	 * 대기 결재 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<WaitListVO> selectWaitApprovalList(PagingVO<WaitListVO> pagingVO);
	
	//-------------------------------------------------------------------------------------------------
	/**
	 * 완료 결재 문서 조회
	 * @param elec_no
	 * @return
	 */
	public CompleteListVO selectCompleteApproval(int elec_no);
	
	/**
	 * 완료 결재 문서 개수
	 * @param pagingVO
	 * @return
	 */
	public long selectCompleteApprovalCount(PagingVO<CompleteListVO> pagingVO);
	
	/**
	 * 완료 결재 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<CompleteListVO> selectCompleteApprovalList(PagingVO<CompleteListVO> pagingVO);

	/**
	 * 마지막 결재자가 '승인'버튼 클릭 시, 해당 문서는 결재 완료 상태가 된다.
	 * ELEC_APPROVAL 의 ELEC_COMPLE 을 'Y'로 업데이트 해야 한다.
	 * @param elec_no
	 * @return
	 */
	public int updateApprovalComplete(int elec_no);

	/**
	 * 해당 문서에 해당하는 결재선 조회
	 * @param elec_no
	 * @return
	 */
	public List<Elec_ApprlineVO> selectLineListByElecNo(int elec_no);

	/**
	 * 결재 완료 시, 주문서 테이블(SALE_ORD)에 elec_comple 업데이트
	 * @param sendTypeCode
	 * @return
	 */
	public int updateSaleOrderComplete(String sendTypeCode);

	/**
	 * 결재 완료 시, 발주서서 테이블(PUR_ORD)에 elec_comple 업데이트
	 * @param sendTypeCode
	 * @return
	 */
	public int updatePurchasOrderComplete(String sendTypeCode);
	
	
	
	
}
