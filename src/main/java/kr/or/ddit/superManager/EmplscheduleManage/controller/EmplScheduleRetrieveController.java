package kr.or.ddit.superManager.EmplscheduleManage.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.superManager.EmplscheduleManage.service.IEmplScheduleManageService;
import kr.or.ddit.vo.ActiveClientEmpVO;
import kr.or.ddit.vo.ActiveVO;
import kr.or.ddit.vo.PagingVO;


/**
 * @author 정다혜
 * @since 2019. 5. 28.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 28.      작성자명       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class EmplScheduleRetrieveController {
	
	@Inject
	IEmplScheduleManageService service;
	
	//화면
	@RequestMapping("/superManager/emplScheduleManage/emplScheduleList")
	public String getemplScheForm(){
		return "superManager/salesSchedule/scheduleList";
	}
	
	
	//캘린더 ajax
	@RequestMapping(value="/superManager/emplScheduleManage/superActiveList", produces="application/json;charset=UTF-8")
	public @ResponseBody List<ActiveVO> activeList(
			ActiveVO activeVO
			,Model model
			){
		List<ActiveVO> activeList = service.retrieveCalendar(activeVO);
		model.addAttribute(activeList);
		return activeList;
	}
	
	
	//페이징처리 ajax
		@ResponseBody
		@RequestMapping(value="/superManager/emplScheduleManage/superScheduleLists", produces="application/json;charset=UTF-8")
	   public PagingVO<ActiveVO> getScheduleList(
			   @RequestParam(name="page", required=false, defaultValue="1") long currentPage
			   //삭제를 할때 타는 컨트롤러라서 파라미터 필요없는 존재는 required = false를 반드시 해줘야한다.
			   ,@RequestParam(name="start",required=false) String start
			   ,@RequestParam(name="emp_name",required=false) String emp_name
			   ,ActiveClientEmpVO searchData
			   ,@ModelAttribute("pagingVO") PagingVO<ActiveVO> pagingVO
			   , Model model
			   , HttpServletResponse resp
			   ){
		   
		   //screensize 3개
		   pagingVO= new PagingVO<ActiveVO>(3, 5);
		   
		   searchData.setAc_startdate(start);
		   searchData.setEmp_name(emp_name);
		   
		   //검색데이터 넣어줌
		   pagingVO.setSearchData(searchData);
		   //현재페이지 넣어줌
		   pagingVO.setCurrentPage(currentPage);
		   //토탈페이지의 수
		   long totalRecord = service.retrieveScheduleCount(pagingVO);
		   pagingVO.setTotalRecord(totalRecord);
		   
		   //전체 리스트 출력
		  List<ActiveVO> scheduleList = service.retrieveScheduleList(pagingVO);
		  pagingVO.setDataList(scheduleList);
		  
//		  resp.setHeader("Pragma", "no-cache");
//		  resp.setHeader("Cache-Control", "no-cache");
//		  resp.addHeader("Cache-Control", "no-store");
//		  resp.setDateHeader("Expires", 0);
		  
		  model.addAttribute("pagingVO", pagingVO);
	      return pagingVO;
	   }
		
	
}
