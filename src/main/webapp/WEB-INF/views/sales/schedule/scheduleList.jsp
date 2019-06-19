<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.cal_listWrap {
/* 	margin-left: 5%; */
}
</style>

<link href='${pageContext.request.contextPath}/css/fullcalendar.min.css' rel='stylesheet' />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fullcalendar.min.js"></script>

<script>
	jQuery(document).ready(function() {//비동기로 일정데이터를 받아와서 처리해준다.
	  
	  var activecal={
			  "title" : "<c:out value='${activeVO.ac_content}'/>",
			  "start" : "<c:out value='${activeVO.ac_startdate}'/>",
			  "end" : "<c:out value='${activeVO.ac_enddate}'/>"
  		}
	  
	  $.ajax({
			url : "${pageContext.request.contextPath}/salesTeam/schedule/activeList",//요청주소
			method : "get",
			data : JSON.stringify(activecal),
			dataType : "json", // request header(Accept), response header(Content-Type)
			success : function(resp) {
				jQuery("#calendar").fullCalendar({
//			       defaultDate: '2019-01-12', //달력 시작 기준날짜를 정할 수 있다.
			    editable: false,// 일정수정 가능 여부 true 일 경우
			      					//일정을 드래그해서 옮길 수 있다.
			    eventLimit: true, // allow "more" link when too many events
			    
				});

			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}

		});
});

</script>
<style>

/*   body { */
/*     padding: 0; */
/*     font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif; */
/*     font-size: 14px; */
/*   } */

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }

</style>



<!-- calender id 를 가진 div 요소를 추가해 준다. -->
<div class="row">
	<div>
		<div id='calendar' class="col-md-6"
			style="height: 500px; border: 1px solid red;"></div>
		<i class="fas fa-square" style="color: blue"></i>출장 <i
			class="fas fa-square" style="color: yellow"></i>회의 <i
			class="fas fa-square" style="color: red"></i>세미나
	</div>
	<div class="cal_listWrap col-md-6">
		<div class="row">
			<!-- 				검색조건과 키워드에 대한 처리가 되면 검색 후 페이지를 이동해서 동일한 검색사항들이 계속 유지됨 -->
			<form id='actionForm'
				action="${pageContext.request.contextPath }/salesTeam/schedule/scheduleList"
				method='get'>
				<input type='hidden' name='emp_name'
					value='${pagingVO.searchData.emp_name }' /> <input type='hidden'
					name='ac_startdate' value='${pagingVO.searchData.ac_startdate }' />
				<input type="hidden" name="page" />
			</form>
<!-- 			<div class="col-xs-12 col-md-6 offset-md-8 form-group input-group"> -->
<!-- 				<input type="text" class="form-control" placeholder="사원명" -->
<!-- 					id="nameText" name="emp_name" -->
<%-- 					value="${pagingVO.searchData.emp_name }"> --%>
<%-- 											<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum }'> --%>
<%-- 											<input type='hidden' name='amount' value='${pageMaker.cri.amount }'> --%>
<!-- 				<span class="input-group-btn searchWrap"> -->
<!-- 					<button class="btn btn-default btnSearch" type="button" -->
<!-- 						id="namebtn"> -->
<!-- 						<i class="fa fa-search" style="color: #007bff"></i> -->
<!-- 					</button> -->
<!-- 				</span> -->
<!-- 			</div> -->
		</div>

		<table class="table table-sm">
			<thead>
				<tr>
					<td style="padding-top: 6px"><i
						class="far fa-calendar-alt fa-lg"></i></td>
					<td>
						<div class="row">
							<input type="date" class="col-xs-12 col-md-6 form-control"
								id="testDatepicker" name="ac_startdate"
								value="${pagingVO.searchData.ac_startdate }"> <span
								class="input-group-btn searchWrap">
								<button class="btn btn-default btnSearch" type="button"
									id="datebtn">
									<i class="fa fa-search" style="color: #007bff"></i>
								</button>
							</span>
							
							<div class="col-xs-12 col-md-6 offset-md-8 form-group input-group">
								<input type="text" class="form-control" placeholder="사원명"
									id="nameText" name="emp_name"
									value="${pagingVO.searchData.emp_name }">
								<%-- 							<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum }'> --%>
								<%-- 							<input type='hidden' name='amount' value='${pageMaker.cri.amount }'> --%>
								<span class="input-group-btn searchWrap">
									<button class="btn btn-default btnSearch" type="button"
										id="namebtn">
										<i class="fa fa-search" style="color: #007bff"></i>
									</button>
								</span>
							</div>
							
							
						</div>
					</td>
				</tr>
			</thead>
			<tbody id="listBody">
				<c:choose>
					<c:when test="${not empty pagingVO }">
						<c:set var="activeList" value="${pagingVO.dataList }" />
						<c:forEach var="active" items="${activeList }">
							<tr>
								<td colspan="2">
									<p>시작 일자 : ${active.ac_startdate } &nbsp;&nbsp;&nbsp;종료일자 :
										${active.ac_enddate }</p>
									<p>
										사원명 : ${active.emp_name } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<c:if test="${not empty active.cl_name }">거래처 :${active.cl_name }</c:if>
									</p>
									<p>활동분류 : ${active.ac_sort }
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 장소 : ${active.ac_location }</p>
									<p>내용 : ${active.ac_content }</p>
								</td>
								<td>
									<button class="btn btn-primary" type="button"
										onclick='location.href="<c:url value="/salesTeam/schedule/scheduleUpdate" />";'>
										<i class="far fa-edit"></i>수정
									</button>
									<button class="btn btn-primary" type="button"
										onclick='location.href="<c:url value="/salesTeam/schedule/scheduleDelete" />";'>
										<i class="far fa-edit"></i>삭제
									</button>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="2">일치하는 데이터가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2" id="pagingArea">${pagingVO.pagingHTML }</td>
				</tr>
			</tfoot>
		</table>

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
function paging(page){
	
	//actionForm 태그안에 있는 hidden type들을 submit함
	var actionForm = $("#actionForm");
	
	actionForm.find("input[name='emp_name']").val($("#nameText").val());
	actionForm.find("input[name='ac_startdate']").val($("#testDatepicker").val());
	actionForm.find("input[name='page']").val(page);
	actionForm.submit();
}

  $(document).ready(function(){
	  
	var actionForm = $("#actionForm");
	  
	//사원명 넣는 input태그의 button에 id를 주고 
	//actionForm태그에서 emp_name을 찾은뒤 
	//inputx태그안에 준 id를 의 값을 넘겨준다.
	var namebtn = $("#namebtn");
	  namebtn.on("click",function(e){
		actionForm.find("input[name='emp_name']").val($("#nameText").val());
		actionForm.submit();		  
	  })
	
	  var datebtn = $("#datebtn");
	  datebtn.on("click",function(e){
		  actionForm.find("input[name='ac_startdate']").val($("#testDatepicker").val());
		  actionForm.submit();
	  })
  
		 //검색버튼의 이벤트 처리
		 //검색 버튼을 클릭하면 검색은 1페이지를 하도록 하기
		 //화면에 검색 조건과 키워드가 보이게 처리하는 작업을 진행
// 		 var searchForm = $("#searchForm");
		 
// 		 $("#searchForm").on("click",function(e){
// 			 if(!searchForm.find("input[name='emp_name']").val($("#nameText").val())){
// 				 alert("키워드를 입력하세요");
// 				 return false;
// 			 }
			 
// 			 //브라우저에 검색 버튼을 클릭하면 <form>태그의 전송은 막고, 페이지 번호는 1이 되도록 처리
// 			 searchForm.find("input[name='startRow']").val("1");
// 			 e.preventDefault();
			 
// 			 searchForm.submit();
// 		 });
  
  });
  
  
  
  
</script>








