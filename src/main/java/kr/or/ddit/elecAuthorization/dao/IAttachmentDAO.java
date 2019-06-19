package kr.or.ddit.elecAuthorization.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttachmentVO;
import kr.or.ddit.vo.Elec_ApprovalVO;

@Repository
public interface IAttachmentDAO {
	public int insertAttachment(AttachmentVO attachment);
	
	public int insertAllAttachment(Elec_ApprovalVO approvalVO);
	
	public AttachmentVO selectAttachment(int attach_no);
	
	public int deleteAttachment(int attach_no);
	
	public int deleteAttachmentAll(Elec_ApprovalVO approval);
	
	/**
	 * 실제 파일을 지우기 위한 메서드
	 * @param approval deleteFileNos 프로퍼티가 필요하기 때문에.
	 * @return attach_no, attach_path
	 */
	public List<AttachmentVO> selectAttachmentList(Elec_ApprovalVO approval);
}
