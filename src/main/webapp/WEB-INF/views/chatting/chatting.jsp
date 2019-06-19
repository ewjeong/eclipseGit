<%@page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<link href="${pageContext.request.contextPath }/css/jquery.mCustomScrollbar.min.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|East+Sea+Dokdo|Nanum+Gothic|Nanum+Gothic+Coding|Noto+Sans+KR|Noto+Serif+KR&display=swap"
	rel="stylesheet">
<script src="${pageContext.request.contextPath }/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sockjs.js"></script>

<script>
	$(document).ready(function() {

						//버튼을 클릭했을 때.
						$("#sendBtn").click(function() {
							console.log('send message...');
							//메세지를 보낸다
							sendMessage();
							$("#message").val("");
							//localStorage.setItem('chatMessage', $("#message").val(""));
						});

						//채팅입력창에 메세지 입력하기 위해 키를 누르면 호출되는 콜백함수
						$("#message").keydown(function(key){
							//send버튼 비활성 풀기
							$("#sendBtn").prop('disabled', false);
							//keyCode가 13은 엔터를 뜻함  
							if (key.keyCode == 13) {
								sendMessage();
								$("#message").val("");
							}
						});

						//웹소켓 통로 열어주기
						var sock = new SockJS(
								"${pageContext.request.contextPath}/echo");
						<security:authentication property="principal" var="user"/>
						//통로 오픈되었을 때(세션접속되었을때)
						sock.onopen = function(e) {
							// 			var data =JSON.parse(event.data);
							
							for(i=0;i<sessionStorage.length+1;i++){
								
								if(sessionStorage.length == 0){
									$("#data").append(" ");
								}else{
									$("#data").append(sessionStorage.getItem(i)+"<br/>");
								}
							}
								sock.send("<span>" + "${user.emp_name}"+ "님이 입장하셨습니다</span><br>");
								$('span:contains("입장")').css("color", "#007bff");
							
							// 		var message = {};
							// 		message.sender = null;
							// 		message.depname = null;
							// 		message.message = message.sender+"("+message.depname+")" + "님이 입장하셨습니다.";
							// 		message.type = "open";
							// 		sock.send(JSON.stringify(message));
						}

						function sendMessage() {
							var msg = $("#message").val();
							console.log(msg);
							if (!msg) {
								$("#sendBtn").prop('disabled', true);
							} else {

								$("#sendBtn").prop('disabled', false);
								sock.send($("#message").val());
								
							}
						}

						sock.onmessage = onMessage;
						sock.onclose = onclose;
						var i = 0;
						var close = 0;
						//onMessage는 메세지를 받았을 때 처리되는 함수
						function onMessage(msg) {
							
							var data = msg.data;
							sessionStorage.setItem(i,data);
							i++;
							$("#data").append(data + "<br/>")
							$('span:contains("입장")').css("color", "#007bff");
							 $("#data").scrollTop(99999999);
							 

						}
						//onClose 는 연결 끊겼을 때
						
		function onclose(event) {
		
			var close = 1;
							//$("#data").append("<span>" + "${user.emp_name}"+ "님이 퇴장하셨습니다</span><br/>");
// 							sock.send("<span>" + "${user.emp_name}"+ "님이 퇴장하셨습니다</span><br/>");
// 							$('span:contains("퇴장")').css("color", "#007bff");

// 							for(i=0;i<sessionStorage.length+1;i++){
// 								sessionStorage.getItem(i);
// 							}
		}
	});
</script>
<style>
/* #chatScreen{ */
	
/*     height: inherit; */
/*     border-width: 0; */
/*     border-radius: .375rem 0 0 .375rem; */
/* position: relative; */
/* } */
.modal-content{
	height: 776px;
	width: 500px;
	float: right;
}
	#data {
		/*border: solid 1px #007bff;*/
		font-family: 'Nanum Gothic', sans-serif;
		overflow: auto;  
		max-height: 600px;
		max-width: 450px;
	}


#message {
	font-family: 'Nanum Gothic', sans-serif;
}
</style>
<!-- <div id="chatScreen" style="height: 400px; width: 100%"> -->
<!-- 	<button type="button" class="close" id="close" aria-label="Close"> -->
<!-- 		<span aria-hidden="true">&times;</span> -->
<!-- 	</button> -->

<!-- 	<div id="data" style="height: 300px; width: 100%" -->
<!-- 		class="rounded text-secondary"></div> -->
<!-- 	<div class="input-group mb-3"> -->
<!-- 		<input type="text" id="message" class="form-control" -->
<!-- 			aria-describedby="basic-addon2"> -->
<!-- 		<div class="input-group-append"> -->
<!-- 			<button class="input-group-text" type="button" id="sendBtn"> -->
<!-- 				<i class="fas fa-arrow-up" style="color: #007bff; size: 7x;"></i> -->
<!-- 			</button> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
<!-- </div> -->

<div class="modal-content" id="chatScreen">
	<div class="modal-header">
		<h6 class="modal-title" id="exampleModalScrollableTitle">Chat</h6>
		<button type="button" class="close" id="close" data-dismiss="modal"
			aria-label="Close">  
			<span aria-hidden="true">&times;</span>
		</button>
	</div>    
	<div class="modal-body scroll-wrapper scrollbar-inner" style="position:relative;">
		<div class="scrollbar-inner scroll-content scroll-scrolly_visible"  data-mcs-theme="minimal-dark" id="scroll" style="height: auto; margin-bottom: 0px; margin-right: 0px; ">
		<div id="data"></div>
		</div>
	</div>
	<div class="modal-footer">
		<div class="input-group mb-3">
			<input type="text" id="message" class="form-control"
				aria-describedby="basic-addon2">
			<div class="input-group-append">
				<button class="input-group-text" type="button" id="sendBtn">
					<i class="fas fa-arrow-up" style="color: #007bff; size: 7x;"></i>
				</button>
			</div>
		</div>
	</div>
</div>