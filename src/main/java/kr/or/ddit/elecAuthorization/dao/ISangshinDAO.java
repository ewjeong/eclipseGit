package kr.or.ddit.elecAuthorization.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.Elec_ApprovalVO;
import kr.or.ddit.vo.Elec_FormVO;
import kr.or.ddit.vo.FixLineVO;
import kr.or.ddit.vo.PagingVO;


/**
 * @author 이초연
 * @since 2019. 5. 15
 * @version 1.0
 * @see
 * <pre>
 * [[개정이력(Modification Information)]] 
 * 수정일        수정자     수정내용
 * ==========   ======    ============== 
 * 2019. 5. 15	이초연		최초 작성
 * 2019. 5. 21  이초연      결재(기안서) 등록
 * 2019. 6. 07  이초연      발주서나 주문서 결재완료여부 'N'으로 업뎃
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 *	상신 관련 인터페이스
 *  - 상신함 리스트 조회
 *  - 결재 등록
 *  
 */
@Repository
public interface ISangshinDAO {
	/**
	 * 결재(기안서) 등록
	 * @param approval
	 * @return
	 */
//	public int insertApproval(Elec_ApprovalVO approval);
	
	/**
	 * 상신(기안서) 개수 조회
	 * @param pagingVO
	 * @return
	 */
	public long selectSangShinCount(PagingVO<Elec_ApprovalVO> pagingVO);
	
	/**
	 * 상신(기안서) 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<Elec_ApprovalVO> selectSangShinList(PagingVO<Elec_ApprovalVO> pagingVO);
	
	/**
	 * 상신(기안서) 조회
	 * @param approval - elec_no 와 elec_writer(loginUserId) 를 담고 있다.
	 * @return
	 */
	public Elec_ApprovalVO selectSangShin(Elec_ApprovalVO approval);
	
	/**
	 * 상신 시, 결재선 선택할 수 있도록 하기 위한 결재선 리스트 조회
	 * @return
	 */
	public List<FixLineVO> selectFixLineList();

	/**
	 * 상신 시, 결재양식 선택할 수 있도록 하기 위한 결재양식 리스트 조회
	 * @return
	 */
	public List<Elec_FormVO> selectFormList();
	
	/**
	 * 적용버튼 클릭 시, 선택된 결재선을 조회
	 * @param fl_no
	 * @return
	 */
	public FixLineVO selectFixLine(int fl_no);

	/**
	 * 적용버튼 클릭 시, 선택된 양식을 조회
	 * @param elec_form_code
	 * @return
	 */
	public Elec_FormVO selectForm(String elec_form_code);

	/**
	 * 결재등록(상신) 시, 먼저 Elec_approvalVO 와 AttachmentVO 에 등록
	 * @param approval
	 * @return
	 */
	public int insertApproval(Elec_ApprovalVO approval);
	
	/**
	 * 결재등록(상신) 시, 결재선 등록
	 * @param approval
	 * @return
	 */
	public int insertApprLine(Elec_ApprovalVO approval);
	
	/**
	 * 결재등록(상신) 시, 참조자들 등록
	 * @param approval
	 * @return
	 */
	public int insertRefenrece(Elec_ApprovalVO approval);

	/**
	 * 주문서 결재 등록 시, ELEC_COMPLE 을 'N'으로 업데이트
	 * 
	 * @param sendTypeCode
	 * @return
	 */
	public int updateSaleOrdSheet(String sendTypeCode);

	/**
	 * 발주서서 결재 등록 시, ELEC_COMPLE 을 'N'으로 업데이트
	 * @param sendTypeCode
	 * @return
	 */
	public int updatePurOrdSheet(String sendTypeCode);

	/**
	 * insertApproval 이 성공 시, 생성되는 elec_no가 필요한데
	 * max(elec_no) 로 조회할 것이다.
	 * @return  새롭게(가장 마지막에) 추가된 int elec_no 값
	 */
//	public int selectApproval();
	
}






















