package kr.or.ddit.elecAuthorization.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
 * 2019. 5. 16  이초연 		결재 양식 추가    
 * 2019. 5. 17  이초연		결재선 조회
 * 2019. 5. 20  이초연      결재선 추가
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 * 결재선, 결재 양식 관리에 대한 인터페이스
 */
@Repository
public interface IElecManageDAO {
	// 결재 양식 ----------------------------------------------------------
	// public int insertForm(Elec_FormVO form);
	
	public Elec_FormVO selectForm(String elec_form_code);
	
	public long selectFormCount(PagingVO<Elec_FormVO> pagingVO);
	
	public List<Elec_FormVO> selectFormList(PagingVO<Elec_FormVO> pagingVO);
	
	public int insertForm(Elec_FormVO form);
	
	// 결재선 -------------------------------------------------------------
	public FixLineVO selectFixLine(String fl_no);

	public long selectFixLineCount(PagingVO<FixLineVO> pagingVO);
	
	public List<FixLineVO> selectFixLineList(PagingVO<FixLineVO> pagingVO);
	
	/**
	 * 먼저, fix_line 테이블에 insert 한 후
	 * @param fixLine
	 * @return 
	 */
	public int insertFixLine(FixLineVO fixLine);

	/**
	 * 결재 양식 삭제
	 * @param elec_form_code
	 * @return
	 */
//	public int deleteForm(String elec_form_code);

}























