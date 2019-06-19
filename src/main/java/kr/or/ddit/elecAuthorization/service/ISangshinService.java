package kr.or.ddit.elecAuthorization.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
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
 * 2019. 5. 20  이초연	    상신 등록
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 * 상신에 관련된 인터페이스
 * 	- 상신함 리스트 조회
 *  - 결재 등록
 *  
 */
public interface ISangshinService {
	/**
	 * 결재(기안서) 등록
	 * @param approval
	 * @return
	 */
//	public ServiceResult createApproval(Elec_ApprovalVO approval);
	
	/**
	 * 상신(기안서) 개수 조회
	 * @param pagingVO
	 * @return
	 */
	public long retreiveSangShinCount(PagingVO<Elec_ApprovalVO> pagingVO);
	
	/**
	 * 상신(기안서) 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<Elec_ApprovalVO> retreiveSangShinList(PagingVO<Elec_ApprovalVO> pagingVO);
	
	/**
	 * 상신(기안서) 조회
	 * @param approval
	 * @return
	 */
	public Elec_ApprovalVO retreiveSangShin(Elec_ApprovalVO approval);

	/**
	 * 상신 시, 결재선 선택할 수 있도록 하기 위한 결재선 리스트 조회
	 * @return
	 */
	public List<FixLineVO> retreiveFixLineList();

	/**
	 * 상신 시, 결재양식 선택할 수 있도록 하기 위한 결재양식 리스트 조회
	 * @return
	 */
	public List<Elec_FormVO> retreiveFormList();

	/**
	 * 적용버튼 클릭 시, 선택된 결재선을 조회
	 * @param fl_no
	 * @return 없으면 commonException 발생
	 */
	public FixLineVO retreiveFixLine(int fl_no);

	/**
	 * 적용버튼 클릭 시, 선택된 양식을 조회
	 * @param elec_form_code
	 * @return 없으면 commonException 발생
	 */
	public Elec_FormVO retreiveForm(String elec_form_code);

	/**
	 *  드디어 결재 등록
	 *  Elec_approvalVO, Elec_ApprLineVO, ReferenceVO, AttachmentVO 에
	 *  각각 insert 해야 한다. 트랙잭션 관리 필수!!
	 * @param approval
	 * @return OK : 성공, FAILED : 실패
	 */
	public ServiceResult createSangshin(Elec_ApprovalVO approval);
}
