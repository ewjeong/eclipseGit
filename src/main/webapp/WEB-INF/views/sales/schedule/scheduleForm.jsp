<%@page import="kr.or.ddit.vo.EmployeeVO"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link href="${pageContext.request.contextPath }/css/datetimepicker.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/js/datetimepicker.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.0/moment-with-locales.min.js"></script>
<style>
    #errors{color: red;}
    #icon{size: 10x; color: red; padding-top: 5px;}
</style>



	<div>
		<div style="border: 0.5px solid #E6E6E6; padding: 20px 0;">
		
		<div class="col-xs-12 col-md-10">
			<c:choose>  
				<c:when test="${empty active.ac_no }">
					<h3>활동 등록</h3>				
				</c:when>
				<c:otherwise>
					<h3>활동 수정</h3>	         
				</c:otherwise>
			</c:choose>										
		</div>
		<hr>
<!-- 		<br>							 -->
		<div class="col-xs-12 col-md-5">
			<label>활동분류</label>
		</div>
		<form:form commandName="active" method="post" id="activeForm">
			<c:choose>
				<c:when test="${not empty active.ac_no }">
					<input type="hidden" name="ac_no" id="ac_noID" value="${active.ac_no }"/>
				</c:when>
			</c:choose>
			<div class="col-xs-12 col-md-5">
				<select name="ac_sort" id="ac_sort" class="form-control">
					<option value="">활동분류</option>	
					<option value="출장" ${active.ac_sort eq '출장'? "selected": "" }>출장</option>							
					<option value="세미나" ${active.ac_sort eq '세미나'? "selected": "" }>세미나</option>							
					<option value="회의" ${active.ac_sort eq '회의'? "selected": "" }>회의</option>							
				</select>
				<form:errors id="errors" path="ac_sort" element="span" cssClass="errors"/>
			</div>
		   <div class="col-xs-12 col-md-5">
		      <input type="hidden" name="emp_id" id="emp_id" value="${emp_id }" class="form-control">
		   </div>
			<div class="col-xs-12 col-md-5">
		      <label>시작 날짜</label> 
		      <div id="pickerStart"> </div>
			  	  <input type="hidden" name="ac_startdate" id="ac_startdate" value="${active.ac_startdate }" class="form-control">
		  	 	  <form:errors id="errors" path="ac_startdate" element="span" cssClass="errors"/>
		  	 </div>
		   <div class="col-xs-12 col-md-5">
		      <label>종료 날짜</label> 
		      <div id="pickerEnd"> </div>
			      <input type="hidden" name="ac_enddate" id="ac_enddate" value="${active.ac_enddate }" class="form-control">
		 	  	  <form:errors id="errors" path="ac_enddate" element="span" cssClass="errors"/>
		 	  </div>
			<div class="col-xs-12 col-md-5">
		      <label>장소</label> 
		      <input type="text" name="ac_location" id="ac_location" value="${active.ac_location }" class="form-control">
		   </div>
		   <div class="col-xs-12 col-md-5">
		      <label>거래처</label> 
		   <div class="row">
		   	<div style="padding-left: 15px;">
		   	      <input type="text" name="cl_no" id="cl_no" value="${active.cl_no }" placeholder="거래처가 있을시에만 입력하세요" class="form-control">
		   	</div>
		   	<div style="padding-left: 20px;">
		       	  <button type="button" class="btn btn-primary"
	                           data-toggle="modal" data-target="#myModal">
	                                 검색</button>
		   	</div>
		   </div>
		   </div>
		   <div class="col-xs-12 col-md-5">
		      <label>내용</label> 
		      <input type="text" name="ac_content" id="ac_content" value="${active.ac_content }" class="form-control">
		   </div>
		   <br>
		   <c:choose>
		   	<c:when test="${empty active.ac_no }">
			   <div class="col-xs-4 col-md-6">
					<button type="button" id="insertBtn" class="btn btn-primary col-xs-2 col-md-2">
						등록</button>
				</div>
		   	</c:when>
		   	<c:otherwise>
		   		<div class="col-xs-4 col-md-6">
					<button type="button" id="modiBtn" class="btn btn-primary col-xs-2 col-md-2">
						<i class="far fa-edit"></i>수정</button>
				</div>
		   	</c:otherwise>
		   </c:choose>
		</form:form>
   	  </div>
   </div>
   
   <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">거래처검색</h4>
        </div>
        <div class="modal-body">
			<form action="${pageContext.request.contextPath }/salesTeam/schedule/scheduleInsert" method="post">
                     <div class="container">
                        <div class="row">
                           <div class="col">
                              <input class="form-control" id="cl_name" name="cl_name" type="text" placeholder="거래처명을 입력하세요">
                           </div>
                           <div class="col">
                              <button class="btn btn-outline-primary" id="findClient" type="button">검색하기</button>
                           </div>
                        </div>
                        <div class="row">
                        	<div class="col">
                           	<table class="table table-borderless">
                           		<thead id="listhead">
                           		</thead>
                           		<tbody id="listbody">
                           		</tbody>
                           	</table>
                           </div>
                        </div>
                     </div>
            </form>           
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default btn btn-outline-secondary" data-dismiss="modal">닫기</button>
          <button type="button" id="selectBtn" class="btn btn-outline-primary" data-dismiss="modal">선택하기</button>
        </div>
      </div>
                  
    </div>
  </div>
  
   
   
  <script>
  
  
  
  	//수정버튼을 눌렀을때
  	$("#modiBtn").click(function(){
  		$("#activeForm").attr("action","${pageContext.request.contextPath }/salesTeam/schedule/scheduleUpdate");
  		$("#activeForm").submit();
  	})
  	
  	//등록버튼을 눌렀을때
  	$("#insertBtn").click(function(){
  		$("#activeForm").attr("action", "${pageContext.request.contextPath }/salesTeam/schedule/scheduleInsert");
  		$("#activeForm").submit();
  	})
  
  	$(function(){
  		var ac_startdate = $("#ac_startdate").val();
  		//ac_startdate 의 값이 없을때는 현재시간을 출력하라
  		if(!ac_startdate){
  			ac_startdate="now";
  		}
  		//ac_enddate 의 값이 없을때는 현재시간을 출력하라
  		var ac_enddate = $("#ac_enddate").val();
  		if(!ac_enddate){
  			ac_enddate="now";
  		}
  		
  		$('#pickerStart').dateTimePicker({
  			 dateFormat: "YYYY-MM-DD HH:mm"
  			, locale: 'en'
  			,showTime: true
  			,selectData: ac_startdate
  			,positionShift: { top: 20, left: 0}
  			,title: "Select Date and Time"
  			,buttonTitle: "Select"
  		});

  		
  		$('#pickerEnd').dateTimePicker({
  			 dateFormat: "YYYY-MM-DD HH:mm"
  			, locale: 'en'
  			,showTime: true
  			,selectData: ac_enddate
  			,positionShift: { top: 20, left: 0}
  			,title: "Select Date and Time"
  			,buttonTitle: "Select"
  		});
  		
  		function clFindList(resp){
  			
  			var clList = resp;
  			
  			var tr1 = $("<tr>").append(
				$("<td>").text("거래처 코드")
				,$("<td>").text("거래처 명")
				,$("<td>").text("")
			)
			var trTag = [];
  			var tr2;
  			if(clList !=null && clList.length>0){
			$(clList).each(function(idx,client){
				tr2 = $("<tr>").append(
					$("<td class='no'>").text(client.cl_no)
					,$("<td class='name'>").text(client.cl_name)
					,$("<td><input type='checkbox' class='checkAll' name='checkAll'/></td>")
				)
				trTag.push(tr2);
			});
  			}else{
				tr2 = $("<tr>").append(
						$("<td>").text("검색어에 해당하는 활동이 없습니다.")
						.attr("colspan", "2")
						.css("text-align", "center")
			   		);
				trTag.push(tr2);	  
			}
  			$("#listhead").html(tr1);
  			$("#listbody").html(trTag);
  			
  		
  		}
  	
  		$("#findClient").click(function(event){
  			var cl_name = $("#cl_name").val();
  			$.ajax({
  				url:"<c:url value='/salesTeam/schedule/clList'/>"
  				,method:"post"
  				,data:{
  					cl_name:cl_name
  				}
  				,success:function(resp){
  					clFindList(resp);
  				}
  				,error:function(error){
  					alert("에러");
  				}
  			});//ajax end
  			return false;
  		});//findClient end
  		
  	
  	//거래처리스트 검색후 리스트바디에 출력 
  	//옆에 체크박스를 선택하면 true로
  	$("#listbody").on("click","tr",function(){
  		$('input[type="checkbox"][name="checkAll"]').click(function(){
  			if($(this).prop('checked')){
  				$('input[type="checkbox"][name="checkAll"]').prop('checked',false);
  				$(this).prop('checked',true);
  			}
  		});
  	
  	
  	//모달자식창에서 부모창에 값 전달하기
  	//선택하기 버튼을 누르면 화면에 띄어지게한다
  	$("#selectBtn").click(function(){
  		$(".checkAll").each(function(idx){
  			var tr = $(this).prop('checked');
//   			var inputt = $("input[name='checkAll']").prop('checked');
  			if(tr){
  				cl_no = $(this).parent().parent().find(".no").text();
  				console.log(cl_no);
  			}
  		})
	  	$("#cl_no").val(cl_no);
	  	})
  	});	
  	
  	});//function end
  	
  	
  	<c:if test="${not empty msg}">
		swal("${msg}");
		<c:remove var="msg" scope="session"/>
	</c:if>  
  	
  	
  </script> 
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   