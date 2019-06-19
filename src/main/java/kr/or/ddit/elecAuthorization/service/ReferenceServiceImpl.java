package kr.or.ddit.elecAuthorization.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.elecAuthorization.dao.IReferenceDAO;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReferenceListVO;
import kr.or.ddit.vo.ReferenceVO;

@Service
public class ReferenceServiceImpl implements IReferenceService {
	@Inject
	IReferenceDAO referenceDao;
	
	@Override
	public long retreiveReferenceCount(PagingVO<ReferenceListVO> pagingVO) {
		return referenceDao.selectReferenceCount(pagingVO);
	}

	@Override
	public List<ReferenceListVO> retreiveReferenceList(PagingVO<ReferenceListVO> pagingVO) {
		return referenceDao.selectReferenceList(pagingVO);
	}

	@Override
	public ReferenceListVO retreiveReference(ReferenceListVO referenceList) {
		ReferenceListVO reference = referenceDao.selectReference(referenceList);
		if(reference == null) throw new CommonException(referenceList.getElec_no() + "번 문서가 존재하지 않습니다.");
		return reference;
	}

}
