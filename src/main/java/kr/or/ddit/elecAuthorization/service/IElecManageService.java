package kr.or.ddit.elecAuthorization.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.Elec_FormVO;
import kr.or.ddit.vo.FixLineVO;
import kr.or.ddit.vo.Fix_ApprovalVO;
import kr.or.ddit.vo.PagingVO;

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
 * 2019. 5. 16  이초연 		결재 양식 등록
 * 2019. 5. 25  이초연      결재 양식 삭제
 * 
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 * 결재선, 결재 양식 관리에 대한 인터페이스
 */
public interface IElecManageService {
	// 결재 양식 ----------------------------------------------------------
	// public ServiceResult createForm(Elec_FormVO form);
	
	public Elec_FormVO retreiveForm(String elec_form_code);
	
	public long retreiveFormCount(PagingVO<Elec_FormVO> pagingVO);
	
	public List<Elec_FormVO> retreiveFormList(PagingVO<Elec_FormVO> pagingVO);

	/**
	 * 폼 양식 추가
	 * @param form
	 * @return
	 */
	public ServiceResult createForm(Elec_FormVO form);
	
	// 결재선 -------------------------------------------------------------
	/**
	 * @param fl_no
	 * @return 존재하지 않으면 CommonException 발생
	 */
	public FixLineVO retreiveFixLine(String fl_no);
	
	public long retreiveFixLineCount(PagingVO<FixLineVO> pagingVO);
	
	public List<FixLineVO> retreiveFixLineList(PagingVO<FixLineVO> pagingVO);
	
//	/**
//	 * 먼저, fix_line 테이블에 insert 한 후
//	 * 이에 해당하는 fix_approval 테이블에 insert
//	 * 트랜잭션 관리를 해주어야 한다.
//	 * @param fixLine, fixApproval[]
//	 * @return 성공 시 OK , 실패 시 FAILED
//	 */
//	public ServiceResult createFixLineApproval(FixLineVO fixLine, Fix_ApprovalVO fixApproval);
	
	public ServiceResult createFixLine(FixLineVO fixLine);

//	/**
//	 * 결재양식 삭제
//	 * @param elec_form_code
//	 * @return
//	 */
//	public ServiceResult removeForm(String elec_form_code);
	
//	/**
//	 * insertFixLine의 fl_no 에 해당하는 fix_approval 테이블에 insert
//	 * @param fl_no	
//	 * @param fixApproval
//	 * @return 실패 시, commonException
//	 */
//	public ServiceResult createFixApproval(String fl_no, Fix_ApprovalVO fixApproval);
	
}







