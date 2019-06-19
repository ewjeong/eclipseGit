package kr.or.ddit.elecAuthorization.service;

import java.util.List;

import kr.or.ddit.vo.AttachmentVO;
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
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 * 결재함(승인, 반려, 대결, 전결)에 대한 인터페이스
 * 
 */
public interface IApprovalService {
	// 대기 결재 문서 조회 ----------------------------------------------------------------------
	/**
	 * 대기 결재 문서 조회
	 * @param elec_no
	 * @return 존재하지 않으면,CommonException 발생
	 */
	public WaitListVO retrieveWaitApproval(int elec_no);
	
	/**
	 * 대기 결재 문서 개수
	 * @param pagingVO
	 * @return
	 */
	public long retrieveWaitApprovalCount(PagingVO<WaitListVO> pagingVO);
	
	/**
	 * 대기 결재 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<WaitListVO> retrieveWaitApprovalList(PagingVO<WaitListVO> pagingVO);
	
	// 완료 결재 문서 조회 ---------------------------------------------------------------------------------------------
	/**
	 * 완료 결재 문서 조회
	 * @param elec_no
	 * @return 존재하지 않으면,CommonException 발생
	 */
	public CompleteListVO retrieveCompleteApproval(int elec_no);
	
	/**
	 * 완료 결재 문서 개수
	 * @param pagingVO
	 * @return
	 */
	public long retrieveCompleteApprovalCount(PagingVO<CompleteListVO> pagingVO);
	
	/**
	 * 완료 결재 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<CompleteListVO> retrieveCompleteApprovalList(PagingVO<CompleteListVO> pagingVO);
	
	
	/**
	 * 첨부파일 다운로드
	 * @param attach_no
	 * @return 파일이 없는 경우, CommonException 발생
	 */
	public AttachmentVO downloadFile(int attach_no); // 다운로드용

	/**
	 * 결재선 조회
	 * @param elec_no  해당 문서 번호
	 * @return
	 */
	public List<Elec_ApprlineVO> retrieveLineListByElecNo(int elec_no);
	
	
	
}
