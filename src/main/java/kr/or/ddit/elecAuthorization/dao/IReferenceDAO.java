package kr.or.ddit.elecAuthorization.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReferenceListVO;

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
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 * 참조함 관련 인터페이스
 *  - 참조함 리스트 조회
 *  - 참조된 완료결재문서 상세 조회
 * 
 */
@Repository
public interface IReferenceDAO {
	/**
	 * 참조된 문서 개수
	 * @param pagingVO
	 * @return
	 */
	public long selectReferenceCount(PagingVO<ReferenceListVO> pagingVO);
	
	/**
	 * 참조된 문서 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<ReferenceListVO> selectReferenceList(PagingVO<ReferenceListVO> pagingVO);
	
	/**
	 * 참조된 문서 상세 조회
	 * @param ReferenceListVO
	 * @return
	 */
	public ReferenceListVO selectReference(ReferenceListVO referenceList);
}

