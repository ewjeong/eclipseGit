package kr.or.ddit.elecAuthorization.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.elecAuthorization.dao.IPushDAO;
import kr.or.ddit.vo.AlertVO;


/**
 * @author 이초연
 * @since 2019. 6. 04
 * @version 1.0
 * @see
 * <pre>
 * [[개정이력(Modification Information)]] 
 * 수정일        수정자     수정내용
 * ==========   ======    ============== 
 * 2019. 6. 04	이초연		최초 작성
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 * 알림 리스트 조회 컨트롤러
 */
@Controller
public class AlertRetreiveController {
	@Inject
	IPushDAO pushDao;
	
	@ResponseBody
	@RequestMapping(value="/elecAuthorization/alert/getAlertList"
		, method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public List<AlertVO> getList( String loginUserId){
		List<AlertVO> alertList = pushDao.selectAlertListById(loginUserId);
		return alertList;
	}
}





