package kr.or.ddit.elecAuthorization.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.elecAuthorization.dao.IElecManageDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.Elec_FormVO;
import kr.or.ddit.vo.FixLineVO;
import kr.or.ddit.vo.Fix_ApprovalVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class ElecManageServiceImpl implements IElecManageService {
	@Inject
	IElecManageDAO dao;
	
	@Override
	public Elec_FormVO retreiveForm(String elec_form_code) {
		Elec_FormVO form = dao.selectForm(elec_form_code);
		if(form == null) throw new CommonException(elec_form_code +"코드에 해당하는 폼 양식이 없습니다.");
		return form;
	}

	@Override
	public long retreiveFormCount(PagingVO<Elec_FormVO> pagingVO) {
		return dao.selectFormCount(pagingVO);
	}

	@Override
	public List<Elec_FormVO> retreiveFormList(PagingVO<Elec_FormVO> pagingVO) {
		return dao.selectFormList(pagingVO);
	}

	@Override
	public ServiceResult createForm(Elec_FormVO form) {
		int rowCnt = dao.insertForm(form);
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt > 0) result = ServiceResult.OK;
		return result;
	}

	@Override
	public FixLineVO retreiveFixLine(String fl_no) {
		FixLineVO fixLine = dao.selectFixLine(fl_no);
		if(fixLine == null) throw new CommonException(fl_no + "번 결재선이 없습니다.");
		return fixLine;
	}

	@Override
	public long retreiveFixLineCount(PagingVO<FixLineVO> pagingVO) {
		return dao.selectFixLineCount(pagingVO);
	}

	@Override
	public List<FixLineVO> retreiveFixLineList(PagingVO<FixLineVO> pagingVO) {
		return dao.selectFixLineList(pagingVO);
	}

	@Override
	public ServiceResult createFixLine(FixLineVO fixLine) {
		ServiceResult result = ServiceResult.FAILED;
		int rowCnt = dao.insertFixLine(fixLine);
		if(rowCnt > 0) result = ServiceResult.OK;
		return result;
	}

//	@Override
//	public ServiceResult removeForm(String elec_form_code) {
//		ServiceResult result = ServiceResult.FAILED;
//		int rowCnt = dao.deleteForm(elec_form_code);
//		if(rowCnt > 0) result = ServiceResult.OK;
//		return result;
//	}

//	@Transactional
//	@Override
//	public ServiceResult createFixLineApproval(FixLineVO fixLine, Fix_ApprovalVO fixApprovals) {
//		// 먼저, FIX_LINE 테이블에 insert 해주고, 이에 해당하는 FIX_APPROVAL을 결재자 수만큼 insert 해야 한다!
//		
//		ServiceResult result = ServiceResult.FAILED;
//		String fl_no = dao.insertFixLine(fixLine);
//
//		String send_code = fixApprovals.getSend_code();
//		String[] authorizedIds = fixApprovals.getAuthorizedIds();
//		int[] prioritys = fixApprovals.getPrioritys();
//		
//		int rowCnt = 0;
//		if(StringUtils.isNotBlank(fl_no)) {
//			for(int i=0; i < authorizedIds.length; i++){
//				Fix_ApprovalVO newApproval = new Fix_ApprovalVO(prioritys[i], authorizedIds[i]);
//				newApproval.setSend_code(send_code);
//				
//				rowCnt = dao.insertFixApproval(fl_no, newApproval);
//			}
//		}
//		if(rowCnt == authorizedIds.length) result = ServiceResult.OK;
//	
//		return result;
//	}



}


























