<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.cal_listWrap {
/* 	margin-left: 5%; */
}
</style>

<link href='${pageContext.request.contextPath}/css/fullcalendar.min.css'
	rel='stylesheet' />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/moment.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/fullcalendar.min.js"></script>


<style>
body {
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>
<form id="calendarListForm">
	<div>
		<input type="hidden" id="title" value="${activeList.ac_content }" /> <input
			type="hidden" id="start" value="${activeList.ac_startdate }" /> <input
			type="hidden" id="end" value="${activeList.ac_enddate }" />
	</div>
</form>

<div class="row">
	<div>
		<div id='calendar' class="col-md-6" style="height: 500px;"></div>
		<i class="fas fa-square" style="color: #A9F5BC"></i>출장 
		<i class="fas fa-square" style="color: #F5A9E1"></i>회의 
		<i class="fas fa-square" style="color: #D8CEF6"></i>세미나
	</div>

	<div class="col-md-6  cal_listWrap">
		<!-- 		<div class="row"> -->
		<!-- 			<!-- 				검색조건과 키워드에 대한 처리가 되면 검색 후 페이지를 이동해서 동일한 검색사항들이 계속 유지됨 -->
	
		<%-- 			<form id='actionForm' action="${pageContext.request.contextPath }/salesTeam/schedule/scheduleList" --%>
		<!-- 				method='get'> -->
		<%-- 				<input type='hidden' name='emp_name' value='${pagingVO.searchData.emp_name }' />  --%>
		<%-- 				<input type='hidden' name='ac_startdate' value='${pagingVO.searchData.ac_startdate }' /> --%>
		<!-- 				<input type="hidden" name="page" /> -->
		<!-- 			</form> -->
		<!-- 		</div> -->

		<table class="table table-sm">
			<thead>
				<tr>
<!-- 					<td style="padding-top: 6px"><i -->
<!-- 						class="far fa-calendar-alt fa-lg"></i></td> -->
					<td>
						<!-- ajax로 검색과 페이징처리를 한번에 하는 폼  -->
						<form id="searchForm">
							<div class="row">
								<!-- 날짜검색폼 -->
								<div class="col-xs-12 col-md-6 form-group input-group">
								<i style="padding-top: 15px;" class="far fa-calendar-alt fa-lg"></i>
								<input type="date" class="col-xs-12 col-md-8 form-control"
									id="testDatepicker" name="start"
									value="${pagingVO.searchData.ac_startdate }"> 
									<span class="input-group-btn searchWrap">
									<button class="btn btn-default btnSearch" type="submit"
										id="datebtn">
										<i class="fa fa-search" style="color: #007bff"></i>
									</button>
								</span>
								</div>
								<!-- 사원명검색폼 -->
								<div class="col-xs-12 col-md-6 form-group input-group">
									<input type="text" class="form-control" placeholder="사원명"
										id="nameText" name="emp_name"
										value="${pagingVO.searchData.emp_name }"> 
										<span class="input-group-btn searchWrap"> 
										<!--	페이징처리 하기위한 폼 -->
										<input type="hidden" name="page" />
										<button class="btn btn-default btnSearch" type="button"
											id="namebtn">
											<i class="fa fa-search" style="color: #007bff"></i>
										</button>
									</span>
								</div>
							</div>
						</form>

					</td>
				</tr>
			</thead>
			<tbody id="listBody">
				<!-- 						비동기 -->
				<%-- 				<c:choose> --%>
				<%-- 					<c:when test="${not empty pagingVO }"> --%>
				<%-- 						<c:set var="activeList" value="${pagingVO.dataList }" /> --%>
				<%-- 						<c:forEach var="active" items="${activeList }"> --%>

				<!-- 							<tr> -->
				<!-- 								<td colspan="2"> -->
				<%-- 									<input type="hidden" name="active.ac_no" value="${active.ac_no }"> --%>
				<%-- 									<p>시작 일자 : ${active.ac_startdate }  --%>
				<%-- 				    					&nbsp;&nbsp;&nbsp;종료일자 : ${active.ac_enddate }</p> --%>
				<!-- 									<p> -->
				<%-- 										사원명 : ${active.emp_name } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --%>
				<%-- 										<c:if test="${not empty active.cl_name }">거래처 :${active.cl_name }</c:if> --%>
				<!-- 									</p> -->
				<%-- 									<p>활동분류 : ${active.ac_sort } --%>
				<%-- 										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 장소 : ${active.ac_location }</p> --%>
				<%-- 									<p>내용 : ${active.ac_content }</p> --%>
				<!-- 								</td> -->
				<!-- 								<td> -->
				<!-- 									<button class="btn btn-primary" type="button" -->
				<%-- 										onclick='location.href="<c:url value="/salesTeam/schedule/scheduleUpdate" />";'> --%>
				<!-- 										<i class="far fa-edit"></i>수정 -->
				<!-- 									</button> -->
				<!-- 									<button class="btn btn-primary" type="button"> -->
				<!-- 										<i class="far fa-edit"></i>삭제 -->
				<!-- 									</button> -->
				<!-- 								</td> -->
				<!-- 							</tr> -->

				<%-- 						</c:forEach> --%>
				<%-- 					</c:when> --%>
				<%-- 					<c:otherwise> --%>
				<!-- 						<tr> -->
				<!-- 							<td colspan="2">일치하는 데이터가 없습니다.</td> -->
				<!-- 						</tr> -->
				<%-- 					</c:otherwise> --%>
				<%-- 				</c:choose> --%>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2" id="pagingArea">${pagingVO.pagingHTML }</td>
				</tr>
			</tfoot>
		</table>
		<!-- 페이지네이션 -->
		<!-- 			<nav aria-label="Page navigation example"> -->
		<!-- 				<div class='pull-right'> -->
		<!-- 					<ul class="pagination justify-content-center"> -->
		<%-- 						<c:if test="${pageMaker.prev }"> --%>
		<!-- 							<li class="page-item"> -->
		<%-- 							<a class="page-link" href="${pageMaker.startPage-1 }" tabindex="-1" aria-disabled="true">&lt;</a></li> --%>
		<%-- 						</c:if> --%>

		<%-- 						<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }"> --%>
		<%-- 							<li class="page-item ${pageMaker.cri.pageNum==num ? "active" : "" }"> --%>
		<%-- 								<a class="page-link" href="${num }">${num }</a></li> --%>
		<%-- 						</c:forEach> --%>

		<%-- 						<c:if test="${pageMaker.next }"> --%>
		<%-- 							<li class="page-item"><a class="page-link" href="${pageMaker.endPage }">&gt;</a></li> --%>
		<%-- 						</c:if> --%>
		<!-- 					</ul> -->
		<!-- 				</div> -->
		<!-- 			</nav> -->
	</div>
</div>


<script>
//페이징 처리하는 함수
//페이지 변수를 받아와서
function paging(page){
	console.log(page);//1잘찍힘
	$("#searchForm").find("input[name='emp_name']").val($("#nameText").val());
	$("#searchForm").find("input[name='start']").val($("#testDatepicker").val());
	$("#searchForm").find("input[name='page']").val(page);
	//submit이벤트 강제발생
	$("#searchForm").submit();
}

//리스트 AJAX에 넣을 내용들
function scheduleModify(resp){
	var scheduleList = resp.dataList;
	console.log(scheduleList);
	var pagingHTML = resp.pagingHTML;
	//배열 형태로 만들어서 push를 해줘야 원하는 screensize만큼 출력된다.
	var trTag = [];
	
	
	if(scheduleList !=null && scheduleList.length>0){
		$(scheduleList).each(function(idx,active){
			console.log(active);
			var pattern = "<p>시작 일자 : %SD &nbsp;&nbsp;&nbsp;종료일자 : %ED</p>"+
							"<p>사원명 : %N &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; 
							if(resp.cl_no!=null)
								pattern += "거래처:%C"
							+"</p>";
							pattern+="<p>활동분류 : %S &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 장소 : %L</p>";
							pattern+="<p>내용 : %C</p>";
			
				var startdate = pattern.replace("%SD" , active.start)
										.replace("%ED" , active.end)
										.replace("%N" , active.emp_name)
										.replace("%S" , active.ac_sort)
										.replace("%L" , active.ac_location)
										.replace("%C" , active.title);	
			
			var tr = 
						$("<tr>").append(
						$("<td style='width:100%'>").append(
							startdate
						)
					);
			trTag.push(tr);
		})//$(scheduleList).each(function,idx,active) end
	}else{
		var tr = $("<tr>").append(
				$("<td>").text("검색어에 해당하는 활동이 없습니다.")
				.attr("colspan", "2")
				.css("text-align", "center")
	   		);
		trTag.push(tr);	  
	}
		//listBody에 trTags를 넣어준다.
		$("#listBody").html(trTag);
		$('#pagingArea').html(resp.pagingHTML);
}//function scheduleModify(resp) end


		//사원명 넣는 input태그의 button에 id를 주고 
		//actionForm태그에서 emp_name을 찾은뒤 
		//inputx태그안에 준 id를 의 값을 넘겨준다.
		var namebtn = $("#namebtn");
		  namebtn.on("click",function(e){
			  paging(1);
			$(this).find("input[name='emp_name']").val($("#nameText").val());
			$(this).submit();		  
		  })
		
		  var datebtn = $("#datebtn");
		  datebtn.on("click",function(e){
			  paging(1);
			  $(this).find("input[name='ac_startdate']").val($("#testDatepicker").val());
			  $(this).submit();
		  })

jQuery(document).ready(function() {
	
	//페이징처리와 검색처리를 함께하는 폼이 submit을 눌렀을때 발생하는 함수
	$("#searchForm").on("submit", function(){
		  
	  
		  
// 			 검색버튼의 이벤트 처리
// 			 검색 버튼을 클릭하면 검색은 1페이지를 하도록 하기
// 			 화면에 검색 조건과 키워드가 보이게 처리하는 작업을 진행
//	 		 $("#searchForm").on("click",function(e){
//	 			 if(!searchForm.find("input[name='emp_name']").val($("#nameText").val())){
//	 				 alert("키워드를 입력하세요");
//	 				 return false;
//	 			 }
			 
//	 			 //브라우저에 검색 버튼을 클릭하면 <form>태그의 전송은 막고, 페이지 번호는 1이 되도록 처리
//	 			 searchForm.find("input[name='startRow']").val("1");
//	 			 e.preventDefault();
			 
//	 			 searchForm.submit();
//	 		 });
		
		
		var queryString = $(this).serialize();
		console.log(queryString);
		$.ajax({
    		url:'${pageContext.request.contextPath}/superManager/emplScheduleManage/superScheduleLists'
    		, data : queryString
    		, dataType:"json"
    		, success:function(resp){
    			console.log(resp);
    			scheduleModify(resp);
    		}
    	});
		 
		//동기요청취소
		return false;
	})
	
	
//캘린더 ajax	
var calendarListForm = $("#calendarListForm");
var calendarForm = calendarListForm.serialize();//폼을 대상으로 쿼리스트링이 만들어짐

 jQuery('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,listMonth'
        },
        defaultDate: moment().format('YYYY-MM-DD'),
        locale: "ko",
        editable: true,
        navLinks: true,
        eventLimit: true,
        events: function(start, end, timezone, callback) {
            $.ajax({
                url: '${pageContext.request.contextPath}/superManager/emplScheduleManage/superActiveList',
                type : 'get',
//	                data : calendarForm,
                dataType: 'json',//json형태로 내보내주고
                success: function(resp) {//응답데이터로 일정데이터를 받아옴
                	var events = [];//일정들을 받아와서
                    $(resp).each(function(index,event) {//서버사이드에서 이번달의 이벤트들을 보내준다.(몇년도 몇월달)
                        events.push(event);//이벤트를 보냄

                    });
                    callback(events);
                }
            });
            
 
        },
        loading: function(bool) {
            $('#loading').toggle(bool);
        }
    });


 paging(1);

});
</script>














