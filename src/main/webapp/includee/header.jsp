
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2018.05.15     박연욱       최초수정(채팅 추가)
* 2018.06.03     이초연       알림 추가
* 2018.06.05     이초연       메인페이지 구현
* Copyright (c) ${year} by DDIT All right reserved
*
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="${pageContext.request.contextPath }/css/jquery.mCustomScrollbar.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sockjs.js"></script>

<security:authorize access="isAuthenticated()">
<security:authentication property="principal" var="user" />
	<!-- 상단 메뉴 화면  -->
	<div id="socketAlert" class="alert alert-success" style="background-color: #20c55f; color:#f4f4f4;" role="alert"></div>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<a class="navbar-brand" href="${pageContext.request.contextPath }/mainPage" style="font-weight: bold;" >ISMS</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarText" aria-controls="navbarText"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav mr-auto">
				<c:choose>
					<c:when test="${not empty menuList }">
						<c:forEach items="${menuList }" var="menu">
							<c:if test="${fn:startsWith(user.dep_code,'p')}">
								<c:if test="${menu.res_flag eq 1 }">
									<c:if test="${fn:startsWith(menu.res_pattern,'purchasingTeam') }">
										<li class="nav-item"><a class="nav-link top"
											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li>
										</c:if>	
									<c:if test="${fn:startsWith(menu.res_pattern,'elecAuthorization') }">
										<li class="nav-item"><a class="nav-link top"
											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li>
										</c:if>	
									<c:if test="${fn:startsWith(menu.res_pattern,'salesProfitList') }">
										<li class="nav-item"><a class="nav-link top"
											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li>
									</c:if>	
<%-- 									<c:if test="${fn:startsWith(menu.res_pattern,'myPage') }"> --%>
<!-- 										<li class="nav-item"><a class="nav-link top" -->
<%-- 											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li> --%>
<%-- 										</c:if>	 --%>
									
								</c:if>
							</c:if>
							<c:if test="${fn:startsWith(user.dep_code,'s')}">
								<c:if test="${menu.res_flag eq 1 }">
									<c:if test="${fn:startsWith(menu.res_pattern,'salesTeam') }">
										<li class="nav-item"><a class="nav-link top"
											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li>
									</c:if>	
									<c:if test="${fn:startsWith(menu.res_pattern,'purchasingTeam/itemManage') }">
										<li class="nav-item"><a class="nav-link top"
											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li>
									</c:if>	
									<c:if test="${fn:startsWith(menu.res_pattern,'elecAuthorization') }">
										<li class="nav-item"><a class="nav-link top"
											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li>
									</c:if>	
									<c:if test="${fn:startsWith(menu.res_pattern,'salesProfitList') }">
										<li class="nav-item"><a class="nav-link top"
											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li>
									</c:if>	
<%-- 									<c:if test="${fn:startsWith(menu.res_pattern,'myPage') }"> --%>
<!-- 										<li class="nav-item"><a class="nav-link top" -->
<%-- 											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li> --%>
<%-- 										</c:if>	 --%>
									
								</c:if>
							</c:if>
							<c:if test="${fn:startsWith(user.pos_code,'super')}">
								<c:if test="${menu.res_flag eq 1 }">
									<c:if test="${fn:startsWith(menu.res_pattern,'superManager')}">
										<li class="nav-item"><a class="nav-link top"
											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li>
									</c:if>
									<c:if test="${fn:startsWith(menu.res_pattern,'elecAuthorization') }">
										<li class="nav-item"><a class="nav-link top"
											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li>
									</c:if>	
								</c:if>
							</c:if>
							<c:if test="${fn:startsWith(user.dep_code,'a')}">
								<c:if test="${menu.res_flag eq 1 }">
<%-- 									<c:if test="${fn:startsWith(menu.res_pattern,'superManager')}"> --%>
<!-- 										<li class="nav-item"><a class="nav-link top" -->
<%-- 											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li> --%>
<%-- 									</c:if> --%>
<%-- 									<c:if test="${fn:startsWith(menu.res_pattern,'salesTeam')}"> --%>
<!-- 										<li class="nav-item"><a class="nav-link top" -->
<%-- 											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li> --%>
<%-- 									</c:if> --%>
<%-- 									<c:if test="${fn:startsWith(menu.res_pattern,'purchasingTeam')}"> --%>
<!-- 										<li class="nav-item"><a class="nav-link top" -->
<%-- 											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li> --%>
<%-- 									</c:if> --%>
<%-- 									<c:if test="${fn:startsWith(menu.res_pattern,'elecAuthorization')}"> --%>
<!-- 										<li class="nav-item"><a class="nav-link top" -->
<%-- 											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li> --%>
<%-- 									</c:if> --%>
<%-- 									<c:if test="${fn:startsWith(menu.res_pattern,'purchasingTeam')}"> --%>
<!-- 										<li class="nav-item"><a class="nav-link top" -->
<%-- 											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li> --%>
<%-- 									</c:if> --%>
<%-- 									<c:if test="${fn:startsWith(menu.res_pattern,'setting')}">   --%>
										<li class="nav-item"><a class="nav-link top"
											href="${pageContext.request.contextPath }/${menu.res_pattern}">${menu.res_name }</a></li>
<%-- 									</c:if>    --%>
								</c:if>
							</c:if>
							
						</c:forEach>
					</c:when>
				</c:choose>
			</ul>
			<!-- 알림 -->
			<button type="button" class="btn btn-primary" id="push"
				 data-toggle="modal" data-target="#alertModal">
  				<i class="fas fa-bell"></i>
  				<span class="alertCnt"></span>
			</button>
			<!-- 메일 -->
			<form action="${pageContext.request.contextPath }/mail/redirect">
				<button type="submit" class="btn btn-primary" id="mail"  >
  					<i class="fas fa-check"></i>
				</button>
			</form>
			<!-- 메일 -->
			<button type="button" class="btn btn-primary" id="mail" onclick="location.href='${pageContext.request.contextPath }/mail/inbox';" >
  				<i class="fas fa-envelope"></i>
			</button>
			<!-- 채팅 -->
			<button type="button" class="btn btn-primary" id="chat" 
				data-toggle="modal" data-target="#exampleModalScrollable">
  				<i class="fas fa-comments"></i>
			</button>
	
			<c:url value="/common/logOut.do" var="logout" />
			<security:authorize access="isRememberMe()">
				<a href="${pageContext.request.contextPath }/myPage/myPage">${user.emp_name }님</a> &nbsp;<a href="${logout }">로그아웃</a>
				<%-- 				<security:authentication property="details.remoteAddress"/> --%>
			</security:authorize>  
			<security:authorize access="isFullyAuthenticated()">
				<span class="navbar-text"><a href="${pageContext.request.contextPath }/myPage/myPage">${user.emp_name }님</a>&nbsp;
				<a href="${logout }">로그아웃</a></span>
				<%-- 				<security:authentication property="details.remoteAddress"/> --%>
			</security:authorize>
		</div>
	</nav>
</security:authorize>
<!-- modal-dialog-scrollable -->
<!-- Modal -->
<!-- <body class="ready modal-open"> -->

<!-- 알림용 모달창-->
	<div class="modal fade" id="alertModal" tabindex="-1" role="dialog"
	   aria-labelledby="exampleModalLabel" aria-hidden="true">
	   <div class="modal-dialog" role="document">
	      <div class="modal-content">
	         <div class="modal-header">
	            <h5 class="modal-title" id="alertModalLabel">알림 리스트</h5>
	            <button type="button" class="close" data-dismiss="modal"
	               aria-label="Close">
	               <span aria-hidden="true">&times;</span>
	            </button>
	         </div>
	         <div class="modal-body">
		         <form action="/setting/settingPage" method="post">
		            <div class="container">
		               <div class="row">
		               	<div class="col">
		                  	<table class="table table-borderless">
		                  		<thead id="alertListhead">
		                  		
		                  		</thead>
		                  		<tbody id="alertListbody">
		                  		</tbody>
		                  	</table>
		                  </div>
		               </div>
		            </div>
	            </form> 
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	         </div>
	      </div>
	   </div>
	</div>

<!-- 채팅용 모달창 -->
<div class="modal fade fixed-right show" id="exampleModalScrollable"  tabindex="-1" role="dialog" aria-labelledby="exampleModalScrollableTitle" aria-hidden="true">
  <div class="modal-dialog  .modal-vertical" id="chatting" role="document">
    </div>
</div>
<!-- </body> -->

<!-- <div id="chatting" class="row" > -->
<!-- 	    <div id="talk" class="col-xs-1 col-md-2"> -->
<!--            <div  class="col-xs-3"> -->
<!--                <a title="채팅하기" ><i id="chat" class="fa fa-comments fa-5x"></i></a> -->
<!--            </div> -->
<!-- 	    </div> -->
<!-- 	</div> -->
<script>
//메뉴접근권한 창띄우기
<c:if test="${not empty menu}">
swal(
	"오류",
	"${menu}",
	"warning"
);  
</c:if>




	<security:authentication property="principal" var="user"/>
	var socket = null;
	
	$(document).ready(function(){
		var loginUserId = "${ user.emp_id }";
		
		$.ajax({
			url : "<c:url value='/elecAuthorization/alert/getAlertList' />"
			, method : "get"
			, data : { "loginUserId" :  loginUserId } // 로그인한 유저 아이디
			, dataType : "json"
			, success : function(resp) {
				var alertListCnt = resp.length;
				$(".alertCnt").text(alertListCnt); // 알림 아이콘 옆에 알림리스트의 개수 셋팅해주기
			}
			, error : function(errorResp){
				console.log(errorResp.status);
				swal(errorResp);
			}
		});
		
		/* 알림 모달창 로직 시작 ---------------------------------------------------------------------- */
		$("#push").on("click", function(){
			// 알림 리스트 가져오기
			$.ajax({
				url : "<c:url value='/elecAuthorization/alert/getAlertList' />"
				, method : "get"
				, data : { "loginUserId" :  loginUserId } // 로그인한 유저 아이디
				, dataType : "json"
				, success : function(resp) {
					alertList(resp);
				}
				, error : function(errorResp){
					console.log(errorResp.status);
					swal("오류");
				}
			});
		});
		
		function alertList(resp){
			var alertList = resp;
			
			var tr1 = $("<tr>").append(
					$("<th>").text("번호").css("width" , "58px")
					,$("<th>").text("내용").css("width", "260px")
					,$("<th colspan='2'>").text("보낸시간")
			).css("text-align", "center");
			var tr2 = null;
			if(alertList.length == 0){
// 				tr2 = "<tr><td colspan='3' style='text-align:center'>알림이 없습니다.</td></tr>";
			} else if (alertList != null) {
				$(alertList).each(function(idx, alert){
	//					console.log(alert.emp_name);
					tr2 += "<tr id='alertTr' class='clicktr' data-alert_no='"+alert.alert_no+ "' data-alert_content='"+alert.alert_content+"' data-alert_time='"+alert.alert_time+ "'>";
					tr2 += "<td>" + alert.alert_no +"</td><td>"+alert.alert_content+"</td><td>"+ alert.alert_time +"</td><tr>";
// 					tr2 += "<td><a class='alertDelBtn' href='${pageContext.request.contextPath}/elecAuthorization/alert/deleteAlert?what="+alert.alert_no+"'>";
// 					tr2 += "<i class='fas fa-minus-square fa-lg'></i></a></td></tr>";
				});
			}
			$("#alertListhead").html(tr1)
			$("#alertListbody").html(tr2);
		}
		/* 알림 모달창 로직 끝 ---------------------------------------------------------------------- */

		/* 알림 socket */
		socket = new SockJS("${pageContext.request.contextPath}/push");
		
		//통로 오픈되었을 때(세션접속되었을때)
		socket.onopen = function(e) {
			console.log("Info: connection opend.");
		}
		socket.onmessage = function(event){
			console.log("ReceiveMessage:" +event.data+"\n");
			var socketAlert = $('#socketAlert');
			socketAlert.html(event.data);
			socketAlert.css('display', 'block');
			
			setTimeout(function(){
				socketAlert.css('display', 'none');
			}, 5000);
		}
		socket.onclose = function(event){
			console.log("Info: connection closed.");
		}
		
		/* 채팅 */
		$('#chatting').modal('handleUpdate');
		
		$("#chat").click(function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/chatting/chat",
				dataType:"html",
				success : function(resp){
				//	$("#talk").hide();
					console.log(resp);
					$("#chatting").append(resp);
				}
			})
			
			$(document).on("click","#close",function(){
				$("#chatScreen").remove();
				//$("#talk").show();
			});
			$("#data").mCustomScrollbar({ 
		        theme:"minimal-dark",
		        setTop:"495px"
			});
			
		});
		
	});
</script>


