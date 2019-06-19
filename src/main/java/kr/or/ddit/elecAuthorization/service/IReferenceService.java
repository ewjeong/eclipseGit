package kr.or.ddit.elecAuthorization.service;

import java.util.List;

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
public interface IReferenceService {
	/**
	 * 참조된 문서 개수
	 * @param pagingVO
	 * @return
	 */
	public long retreiveReferenceCount(PagingVO<ReferenceListVO> pagingVO);
	
	/**
	 * 참조된 문서 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<ReferenceListVO> retreiveReferenceList(PagingVO<ReferenceListVO> pagingVO);
	
	/**
	 * 참조된 문서 상세 조회
	 * @param referenceList
	 * @return
	 */
	public ReferenceListVO retreiveReference(ReferenceListVO referenceList);
}
