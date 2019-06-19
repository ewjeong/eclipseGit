<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page session="false"%> --%>
<html>
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
   integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
   crossorigin="anonymous">
<head>
<title>Home</title>
</head>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script>


   $(document).ready(function(){
		<c:if test="${not empty message}">
			swal("${message}");  
		</c:if> 
		      
      $("#findIdBtn").on("click",function(){
    	
    	  
    	  $("#findIdForm").submit();
      });
      
      $("#findPassBtn").on("click",function(){
    	  
         $("#findPassForm").submit();
      });
      <c:if test="${not empty resultMsg}">
         swal("${resultMsg}");  
      </c:if> 
   });
   </script>
<body
   style="background-image: url('${pageContext.request.contextPath}/images/company1.jpg'); min-height: 100%; width: 100% background-position: center; background-size: cover;">

   <form action="${pageContext.request.contextPath }/login/loginCheck.do"
      method="post">
      <div class="container d-flex justify-content-center"  
         style="margin-top: 300px; width: 400px;">  
         <div class="row justify-content-center">
            <div class="col-sm">
               <input type="text" class="col form-control mr-sm-2 " name="emp_id"
                  value="" placeholder="아이디"> <input type="password"
                  name="emp_pass" class="col form-control mr-sm-2 "
                  placeholder="패스워드">
            </div>  
            <div style="width: 100px">
               <input type="hidden" name="${_csrf.parameterName }"
                  value="${_csrf.token }">
               <button type="submit" class="btn btn-outline-light"
                  style="width: 80px; height: 80px;">로그인</button>
            </div>
         </div>  
      </div>  
   </form>  
   <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
      <font color="red">
         <div class="container d-flex justify-content-center">
				<p>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message }</p>
			</div> <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
      </font>
   </c:if>
   <div class="container d-flex justify-content-center">
     <input id = "remember_me" name ="_spring_security_remember_me" type = "checkbox"/>자동로그인
   </div>
   <div class="container d-flex justify-content-center">
      <button type="button" class="btn btn-outline-light"
         style="width: 150px; height: 40px;" data-backdrop="static" data-toggle="modal"
         data-target="#exampleModal">아이디 찾기</button>
      &nbsp;
      <button type="button" class="btn btn-outline-light"
         style="width: 150px; height: 40px;"  data-backdrop="static" data-toggle="modal"
         data-target="#exampleModal2">비밀번호 찾기</button>
   </div>
   <div class="container d-flex justify-content-center"
      style="padding-top: 10px;">
      <button type="button" class="btn btn-outline-light"
         style="width: 300px; height: 40px;"
         onclick="location.href='<c:url value="/mainPage/signUp/signUp"/>';">회원가입하기</button>
   </div>

   <!-- 아이디 찾기 버튼을 눌렀을 때의 모달창 -->
   <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
      aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
         <div class="modal-content">
            <div class="modal-header">
               <h5 class="modal-title" id="exampleModalLabel">아이디 찾기</h5>
               <button type="button" class="close"  data-dismiss="modal"
                  aria-label="Close">
                  <span aria-hidden="true">&times;</span>
               </button>
            </div>
            <div class="modal-body">
               <form id="findIdForm" method="post" action="${pageContext.request.contextPath }/mainPage/searchEmployee/searchId.do">
                  <div class="container">
                     <!-- 성명 -->
                     <div class="form-group row">
                        <label for="inputName" class="col-sm-3 col-form-label">성명</label>
                        <div class="col-sm-9">
                           <input type="text" name="emp_name" class="form-control" name="emp_name"
                              placeholder="성명">
                        </div>
                     </div>
                     
<!--                      핸드폰번호 -->
                     <div class="form-group row">
                        <label for="inputName" class="col-sm-3 col-form-label">전화번호</label>
                        <div class="col-sm-9">
                           <input type="text" name="emp_hp" class="form-control" name="emp_hp"
                              placeholder="전화번호">
                        </div>
                     </div>
   
                     <!-- 이메일 -->
                     <div class="form-group row">
                        <label for="inputEmail" class="col-sm-3 col-form-label">Email</label>
                        <div class="col-sm-9">
                           <input type="email" name="emp_mail" class="form-control" name="emp_mail" 
                              placeholder="이메일 주소">
                        </div>
                     </div>
                     <div class="modal-footer btnCenterWrap">
                        <button type="button" class="btn btn-outline-secondary"
                           data-dismiss="modal">닫기</button>
                        <button type="submit" id="findIdBtn" class="btn btn-outline-primary">이메일
                           주소로 아이디 받기</button>
                     </div>
                  </div>
               </form>
            </div>
         </div>
      </div>
   </div>

   <!-- 비밀번호 찾기를 눌렀을 때의 모달창-->
   <div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog"
      aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
         <div class="modal-content">
            <div class="modal-header">
               <h5 class="modal-title" id="exampleModalLabel">비밀번호 찾기</h5>
               <button type="button" class="close" data-dismiss="modal"
                  aria-label="Close">
                  <span aria-hidden="true">&times;</span>
               </button>
            </div>
            <div class="modal-body">
               <form id="findPassForm" method="post" action="${pageContext.request.contextPath }/mainPage/searchEmployee/searchPass.do" >
                  <div class="container">
                     <!-- 성명 -->
                     <div class="form-group row">
                        <label for="inputName" class="col-sm-3 col-form-label">성명</label>
                        <div class="col-sm-9">
                           <input type="text" class="form-control" name="emp_name"
                              placeholder="성명">
                        </div>
                     </div>

                     <!-- 아이디 -->
                     <div class="form-group row">
                        <label for="inputId" class="col-sm-3 col-form-label">아이디</label>
                        <div class="col-sm-9">
                           <input type="text" class="form-control" name="emp_id"
                              placeholder="아이디">
                        </div>
                     </div>  

                     <!-- 이메일 -->
                     <div class="form-group row">
                        <label for="inputEmail" class="col-sm-3 col-form-label">Email</label>
                        <div class="col-sm-9">
                           <input type="email" class="form-control" name="emp_mail"
                              placeholder="이메일 주소">
                        </div>
                     </div>  
                     <div class="modal-footer btnCenterWrap">
                        <button type="button" class="btn btn-outline-secondary"
                           data-dismiss="modal">닫기</button>
                        <button type="button" id="findPassBtn" class="btn btn-outline-primary">새로운
                           비밀번호 받기</button>
                     </div>
                  </div>
               </form>
            </div>
         </div>
      </div>
   </div>
   

   <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"></script>
   <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
      integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
      crossorigin="anonymous"></script>
   <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
      integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
      crossorigin="anonymous"></script>
</body>
</html>