package kr.or.ddit.salesTeam.scheduleManage.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.salesTeam.scheduleManage.service.IScheduleManageService;
import kr.or.ddit.vo.ActiveClientEmpVO;
import kr.or.ddit.vo.ActiveVO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author 정다혜
 * @since 2019. 5. 17.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 17.      작성자명       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class ScheduleRetrieveController {
	
	@Inject
	IScheduleManageService service;
	
	//화면
	@RequestMapping(value="/salesTeam/schedule/scheduleList", method=RequestMethod.GET)
	public String getActiveList(){
		return "sales/schedule/calendar";
	}
	
	
	//캘린더 ajax
	@RequestMapping(value="/salesTeam/schedule/activeList", produces="application/json;charset=UTF-8")
	public @ResponseBody List<ActiveVO> activeList(
			ActiveVO activeVO
			,Model model
			){
		List<ActiveVO> activeList = service.retriveCalendar(activeVO);
		model.addAttribute(activeList);
		return activeList;
	}
	
	
	//삭제 ajax
	@ResponseBody
	@RequestMapping(value="/salesTeam/schedule/delete",method=RequestMethod.POST)
	public Map<String,Object> deleteSchedule(
			//ac_no 받아오기
			@RequestParam("ac_no") String ac_no
			,Authentication auth
			,ActiveVO activeVO
			,Model model
			,RedirectAttributes rttr
			){
		
		//로그인된 아이디를 가져온다.
		EmployeeVO employeeVO = (EmployeeVO) auth.getPrincipal();

		String msg = null;
		String view = null;
		int delSchedule = 0;
		//세션에 저장된 아이디와 글을 쓸때 입력된 아이디를 비교
		//ac_no에 일치하는 activeVO.getEmp_id()와 비교해야하는데 어떻게 하지
		activeVO = service.retrieveActiveForDel(Integer.parseInt((ac_no)));
		Map<String,Object> map = new HashMap<>();
		if(Integer.parseInt(ac_no)==activeVO.getAc_no()){
			if(employeeVO.getEmp_id().equals(activeVO.getEmp_id())){
				//같으면 삭제성공
				delSchedule= service.deleteSchedule(activeVO);
				map.put("delSchedule", delSchedule);
				msg="삭제 성공";
				map.put("msg", msg);
			}else{
				//삭제 실패
				msg="본인이 등록한 일정만 삭제 가능합니다.";
				map.put("msg", msg);
			}
		}
		
		return map;
	}
	
	
	
	//페이징처리 ajax
	@ResponseBody
	@RequestMapping(value="/salesTeam/schedule/scheduleLists", produces="application/json;charset=UTF-8")
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
	  
//	  resp.setHeader("Pragma", "no-cache");
//	  resp.setHeader("Cache-Control", "no-cache");
//	  resp.addHeader("Cache-Control", "no-store");
//	  resp.setDateHeader("Expires", 0);
	  
	  model.addAttribute("pagingVO", pagingVO);
      return pagingVO;
   }
	
	
	
	
	
	
   
 
}