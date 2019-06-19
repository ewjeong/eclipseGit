package kr.or.ddit.mail.service;


import javax.inject.Inject;
import javax.jdo.annotations.Transactional;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mail.dao.IMailDAO;
import kr.or.ddit.vo.TokenVO;

@Service
public class MailServiceImpl implements IMailService{
	
	@Inject
	IMailDAO mailDAO;


	@Transactional
	@Override
	public ServiceResult createToken(TokenVO token) {
		ServiceResult result = null;
		int row = mailDAO.insertToken(token);
		System.out.println(row);
		if(row >0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ServiceResult modifyToken(TokenVO token) {
		ServiceResult result = null;
		int row = mailDAO.updateToken(token);
		if(row>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public TokenVO retrieveToken(String emp_id) {
		TokenVO token = mailDAO.selectToken(emp_id);
		return token;
	}

}
