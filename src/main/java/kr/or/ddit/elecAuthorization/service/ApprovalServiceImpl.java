package kr.or.ddit.elecAuthorization.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.elecAuthorization.dao.IApprovalDAO;
import kr.or.ddit.elecAuthorization.dao.IAttachmentDAO;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.AttachmentVO;
import kr.or.ddit.vo.CompleteListVO;
import kr.or.ddit.vo.Elec_ApprlineVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.WaitListVO;

@Service
public class ApprovalServiceImpl implements IApprovalService {
	@Inject
	IApprovalDAO approvalDao;
	@Inject
	IAttachmentDAO attachmentDao;

	@Override
	public WaitListVO retrieveWaitApproval(int elec_no) {
		WaitListVO approval =  approvalDao.selectWaitApproval(elec_no);
		if(approval == null) throw new CommonException(elec_no + "번 문서가 존재하지 않습니다.");
		return approval;
	}

	@Override
	public long retrieveWaitApprovalCount(PagingVO<WaitListVO> pagingVO) {
		return approvalDao.selectWaitApprovalCount(pagingVO);
	}

	@Override
	public List<WaitListVO> retrieveWaitApprovalList(PagingVO<WaitListVO> pagingVO) {
		return approvalDao.selectWaitApprovalList(pagingVO);
	}

	@Override
	public AttachmentVO downloadFile(int attach_no) {
		AttachmentVO attach = attachmentDao.selectAttachment(attach_no);
		if(attach == null){
			throw new RuntimeException(attach_no + "번 파일이 없습니다.");
		}
		return attach;
	}

	//---------------------------------------------------------------------------------------------------------------
	@Override
	public CompleteListVO retrieveCompleteApproval(int elec_no) {
		CompleteListVO approval = approvalDao.selectCompleteApproval(elec_no);
		if(approval == null) throw new CommonException(elec_no + "번 문서가 존재하지 않습니다.");
		return approval;
	}

	@Override
	public long retrieveCompleteApprovalCount(PagingVO<CompleteListVO> pagingVO) {
		return approvalDao.selectCompleteApprovalCount(pagingVO);
	}

	@Override
	public List<CompleteListVO> retrieveCompleteApprovalList(PagingVO<CompleteListVO> pagingVO) {
		return approvalDao.selectCompleteApprovalList(pagingVO);
	}

	@Override
	public List<Elec_ApprlineVO> retrieveLineListByElecNo(int elec_no) {
		return approvalDao.selectLineListByElecNo(elec_no);
	}
	
}













