package kr.or.ddit.elecAuthorization.controller.line;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.elecAuthorization.service.IElecManageService;
import kr.or.ddit.vo.FixLineVO;
import kr.or.ddit.vo.PagingVO;

@Controller
public class LineRetreiveController {
	@Inject
	IElecManageService service;
	
	@RequestMapping("/elecAuthorization/line/lineList")
	public String getApproList(
			@RequestParam(name="page", required=false, defaultValue="1" ) long currentPage
			, Model model
			){
		PagingVO<FixLineVO> pagingVO = new PagingVO<>();
		long totalRecord = service.retreiveFixLineCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<FixLineVO> fixLineList = service.retreiveFixLineList(pagingVO);
		pagingVO.setDataList(fixLineList);
		model.addAttribute("pagingVO", pagingVO);
		
		return "elec/line/lineList";
	}
}
