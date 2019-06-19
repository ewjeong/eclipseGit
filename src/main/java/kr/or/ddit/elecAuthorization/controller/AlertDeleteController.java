package kr.or.ddit.elecAuthorization.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.elecAuthorization.dao.IPushDAO;

/**
 * @author 이초연
 * @since 2019. 6. 10
 * @version 1.0
 * @see
 * <pre>
 * [[개정이력(Modification Information)]] 
 * 수정일        수정자     수정내용
 * ==========   ======    ============== 
 * 2019. 6. 10	이초연		최초 작성
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 * 알림 삭제용 컨트롤러
 */
@Controller
public class AlertDeleteController {
	@Inject
	IPushDAO pushDao;
	
//	@RequestMapping("/elecAuthorization/alert/deleteAlert")
//	public String deleteAlert(
//			@RequestParam("what") String alert_no
//			){
//		String view = null;
//		String msg = "";
//		int rowCnt = pushDao.deleteAlert(alert_no);
//		if(rowCnt> 0){
//			view = "mainPage";
//		} else if(rowCnt <= 0){
//			
//		}
//		return null;
//	}
}
















