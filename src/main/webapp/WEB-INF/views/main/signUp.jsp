 <%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자          수정내용
* ----------  ---------  -----------------
* 2019.05.13      박연욱         최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">

	
	 <c:if test="${not empty message}">
		alert("${message}");  
	</c:if> 
	
	$(document).ready(function(){
		var idCk = 0;   // 아이디 중복체크 여부 확인코드
		var idOk = 0;   //아이디 중복인지 아닌지 확인코드
		
		//아이디 중복검사를 ajax를 통해 조회하여 가져온다. 
		$("#idck").on("click",function(){
			var emp_id = $("#emp_id").val();
			
			$.ajax({
				type:'POST',
				url:'idcheck.do',
				data:{
					"emp_id":$("#emp_id").val()
				},
				success:function(data){  //성공한 경우
					if($.trim(data)==0){     //데이터가 0이면 -> 중복체크 걸리지 않은 경우
						$("#idckArea").html('<p style="color:red">사용가능한 아이디입니다 </p>');
						idOk = 0;		//중복체크 합격
						idCk = 1;
						
					}else{
						$("#idckArea").html('<p style="color:red">사용 불가능한 아이디입니다.<p>');
						idOk = 1;  //중복체크 불합격
					}
				
				}
			})    //ajax end
			
		});  //button click end
		
		
		//폼에대한 유효성검사
		$("#register").validate({
			rules:{
				emp_id : {
					required: true,
					minlength:5
				},
				emp_pass: {
					required: true,
					minlength:5
				},
				emp_pass_Check : {
					required: true,
					minlength:5,
					equalTo:"#emp_pass"
				},
				emp_name: {
					required: true
				},
				emp_hp: {
					maxlength : 15,
					minlength : 11,
					required: true,
					phoneno: true
				},
				emp_mail: {
					required: true,
					email:true
				},
				emp_reg1: {
					required: false,
					minlength:6,
					maxlength:6
				},
				emp_reg2: {
					required: false,
					minlength:7,
					maxlength:7
				}
				
			},
			messages:{
				emp_id:{
					required: "아이디를 입력해주세요",
					minlength: "아이디는 5글자 이상입니다"
				},
				emp_pass:{
					required: "비밀번호 입력해주세요",
					minlength: "비밀번호 5글자 이상입니다"
				},
				emp_pass_Check:{
					required: "비밀번호를 다시 한  입력해주세요",
					minlength: "비밀번호는 5글자 이상입니다",
					equalTo : "비밀번호가 일치하지 않습니다"
				},
				emp_name:{
					required: "이름 입력해주세요"
				},
				emp_hp:{
					required: "전화번호를 입력해주세요",
					maxlength: "형식에 맞춰 입력해주세요",
					minlength :"형식에 맞춰 입력해주세요",
					phoneno:"형식에 맞춰 입력해주세요"
				},
				emp_mail:{
					required: "이메일을 입력해주세요",
					email: "이메일 형식에 맞지 않습니다"
				},
				emp_reg1: {
					minlength:"형식에 맞춰 입력해주세요",
					maxlength:"형식에 맞춰 입력해주세요"
				},
				emp_reg2: {
					minlength:"형식에 맞춰 입력해주세요",
					maxlength:"형식에 맞춰 입력해주세요"
				}
			}
		});
		
		$("#join").on("click",function(event){
			if(idCk==0){
				event.preventDefault();
				swal("아이디 중복체크를 해주세요");
				return false;
			}
			
			if(!$("#agree").is(":checked")){
				event.preventDefault();
				swal("약관에 동의해주세요");
				return false;
			}
			
			//swal("회원가입이 완료되었습니다.");
			$('#register').submit();
		})
		
		$.validator.addMethod("phoneno", function(emp_hp, element) {
			  return this.optional(element) || /^\d{3}-\d{3,4}-\d{4}$/.test(emp_hp);
		}, "형식에 맞게 입력해주세요");
			
		
		  
		
	});
	
	function sample4_execDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				var fullRoadAddr = data.roadAddress; //도로명주소 변수
				//swal(fullRoadAddr);
				var extraRoadAddr = ''; //도로명 조합형 주소 변수

				//법정동명이 있을 경우 추가
				//법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraRoadAddr += data.bname;
				}

				//건물명이 있고, 공동주택일 경우 추가
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraRoadAddr += (extraRoadAddr !== '' ? ', '
							+ data.buildingName : data.buildingName);
				} 

				// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraRoadAddr !== '') {
					extraRoadAddr = ' (' + extraRoadAddr + ')';
				}
				// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
				if (fullRoadAddr !== '') {
					fullRoadAddr += extraRoadAddr;
				}

				//우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('zipCode').value = data.zonecode; //다섯자리 새우편번호 사용
				if (data.jibunAddress) {
					document.getElementById('add1').value = data.jibunAddress; //지번
				} else {
					document.getElementById('add1').value = fullRoadAddr; //도로명
				}
			}
		}).open();
		
	}
</script>
</head>
<style>
	label.error{
	  display:block;
	  color:red;
	}
</style>
<body>  
	<form:form method="post" id="register"  cssClass="form-group" action="/lastProject/mainPage/signUp/signUp" commandName="employee">
		<div class="col-xs-11 col-md-11" style="padding: 50px;">
			<div class="row">
				<div class="col"></div>             
				  
				<div class="col-xs-12 col-md-12" style="padding-top: 10px;">
					<h3>회원가입</h3>
					<p style="color: #A4A4A4;">양식에 맞게 기입해주세요</p>
					<p>*는 필수입력항목</p>
					<hr>
					
					<label for="emp_id">아이디(*)</label>
					<div class="input-group mb-3 form-group">
						<input type="text" class="form-control" name="emp_id" id="emp_id" aria-describedby="idck" value="${employee.emp_id }"  placeholder="아이디를 입력하세요" >
 						<div class="input-group-append">
 						<button class="btn btn-outline-primary rounded" id="idck"  type="button">중복체크</button>
 						</div>
 						<span id="idckArea"></span>	
						<form:errors path="emp_id" element="span" cssClass="error" />
					</div>
					<br>    
					<div class="form-group">
						<label for="emp_pass">비밀번호(*)</label> 
						<input type="password" class="form-control" value="${employee.emp_pass }" name="emp_pass" id="emp_pass" maxlength="12"  placeholder="비밀번호" >
						<form:errors path="emp_pass" element="span" cssClass="error" />
					</div>
					<br>
					<div class="form-group">
						<label for="emp_pass_Check">비밀번호 확인(*)</label> 
						<input type="password" class="form-control" name="emp_pass_Check" maxlength="12" id="emp_pass_Check" placeholder="비밀번호 확인" >
						 <span id="checkArea"></span>
					</div>
					<br>
					<div class="form-group">
						<label for="emp_name">이름(*)</label> 
						<input type="text" class="form-control" name="emp_name" id="emp_name" value="${employee.emp_name }" placeholder="이름" >
						<form:errors path="emp_name" element="span" cssClass="error" />
					</div>
					<br>
					<div class="form-group">
						<label for="validationServer02">주민등록번호</label> 
						<input type="text" class="form-control" name="emp_reg1" value="${employee.emp_reg1 }" placeholder="주민등록번호 앞 6자리" >
						 <input type="text" class="form-control" name="emp_reg2"   placeholder="주민등록번호 뒤 7자리">
						 <form:errors path="emp_reg1" element="span" cssClass="error" />
						 <form:errors path="emp_reg2" element="span" cssClass="error" />
					</div>
					<br>
					<div class="form-group">
						<label for="validationServerUsername">전화번호(*)</label> 
						<input  type="text" class="form-control" name="emp_hp" id="emp_hp" value="${employee.emp_hp }" placeholder="010-0000-0000" >
						<form:errors path="emp_hp" element="span" cssClass="error" />
					</div>
					<br> 
					
					<div class="form-group">
						<label for="validationServerUsername">이메일(*)</label>
						<input type="email" class="form-control" name="emp_mail" id="emp_mail" value="${employee.emp_mail}" placeholder="name@example.com" >
						<form:errors path="emp_mail" element="span" cssClass="error" />
					</div>
					
					<br>
					<div class="form-group">
						<label for="validationServer03">생년월일</label> 
						<input type="date" class="form-control" name="emp_bir" placeholder="생년월일" value="${employee.emp_bir }">
						<form:errors path="emp_bir" element="span" cssClass="error" />
					</div>
					<br>
					<label for="zip_code">우편번호</label>
					<div class="form-group input-group mb-3">
						 <input type="text" class="form-control" name="emp_zip" id="zipCode" value="${employee.emp_zip }"  readonly="readonly" placeholder="ex) 12345"> 
						 <div class="input-group-append">
						 <input type="button" class="btn btn-outline-primary" onclick="sample4_execDaumPostcode()" value="검색"/>
						 </div>
						<form:errors path="emp_zip" element="span" cssClass="error" />
					</div>
					<br>
					<div class="form-group">
						<label for="validationServer05">주소</label> 
						<input type="text" class="form-control" name="emp_add1" id="add1"  value="${employee.emp_add1}" readonly="readonly" placeholder="ex) 대전시 중구 대흥동">
						<form:errors path="emp_add1" element="span" cssClass="error" />
					</div>
					<br>
					<div class="form-group">
						<label for="validationServer05">상세주소</label> 
						<input type="text" class="form-control" name="emp_add2" id="add2" value="${employee.emp_add2 }" placeholder="ex) 123-1번지 101호">
						<form:errors path="emp_add2" element="span" cssClass="error" />
					</div>
					<br>
					<div class="form-group">
						<label for="validationServer05">성별(*)</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="emp_gene" value="w" ${employee.emp_gene eq 'w'? "checked":""}  aria-label="Radio button for following text input" required>여성
						&nbsp;&nbsp;&nbsp; 
						<input type="radio" name="emp_gene" value="m"  ${employee.emp_gene eq 'm'? "checked":""} aria-label="Radio button for following text input" required>남성
						<form:errors path="emp_name" element="span" cssClass="error" />
					</div>
					<br>
					<div class="form-group">
						<input type="checkbox" name="agree" id="agree">약관에 동의합니다.

					</div>    
					<br>  
					<div class="col-md-12 offset-md-5">  
						<button class="btn btn-outline-primary"  type="button" id="join"
							style="padding-bottom: 10px; margin: 10px 0px 10px 0px;">확인</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-outline-secondary" onclick="javascript:history.back()" >취소</button>
					</div>  
					
				</div>
				<div class="col"></div>
			</div>
		</div>
	</form:form>
</body>