<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style>
    #errors{color: red;}
    #icon{size: 10x; color: red; padding-top: 5px;}
</style>


<form:form commandName="employee" id="modifyForm" action="${pageContext.request.contextPath }/myPage/myPageUpdate" method="post">
<div class="col-xs-6 col-md-9" style="padding: 10px 0px 10px 0px;">
	<div class="row">

		<div class="col-12"
			style="border: 0.5px solid #E6E6E6; padding-top: 10px;">
			<div class="col">
				<h3>정보 수정</h3>
				<p style="color: #A4A4A4;">양식에 맞게 기입해주세요</p>
				<hr>
				<label>아이디</label> <label>&nbsp;|&nbsp;</label>
				<input type="hidden" name="emp_id" value="${employee.emp_id }"/>
				<label>${employee.emp_id }</label>
			</div>
			<div class="col">
				<label>비밀번호</label> 
				<input type="password" class="form-control" name="emp_pass" id="emp_pass" placeholder="비밀번호" required>
				<div class="valid-feedback">조건에 맞는 비밀번호 입니다</div>
				<form:errors id="errors" path="emp_pass" element="span" cssClass="errors" />
			</div>
			<div class="col">
				<label>비밀번호 확인</label> 
				<input type="password" class="form-control" id="emp_passCheck" placeholder="비밀번호 확인" required>
				<div class="invalid-feedback">비밀번호를 다시 확인해주십시오</div>
				<div id="pwsame" style="color:red;"></div>
			</div>
			<br>
			<div class="col">
				<label>이름</label> <label>&nbsp;|&nbsp;</label>
				<input type="hidden" name="emp_name" value="${employee.emp_name }"/>
				<label>${employee.emp_name }</label>
			</div>
			<br>
			<div class="col">
				<label>생년월일</label> <label>&nbsp;|&nbsp;</label>
				<input type="hidden" name="emp_bir" value="${employee.emp_bir }"/>
				<label>${employee.emp_bir }</label>
			</div>
			<br>
			<div class="col">
				<label>주민등록번호</label> <label>&nbsp;|&nbsp;</label>
				<input type="hidden" name="emp_reg1" value="${employee.emp_reg1 }"/>
				<input type="hidden" name="emp_reg2" value="${employee.emp_reg2 }"/>
				<label>${employee.emp_reg1 }</label> <label>-</label> <label>${employee.emp_reg2 }</label>
			</div>
			<br>
			<div class="col">
				<label>이메일</label> 
				<input type="email" class="form-control" name="emp_mail" placeholder="name@example.com">
				<div class="invalid-feedback">이메일 입력</div>
				<form:errors id="errors" path="emp_mail" element="span" cssClass="errors" />
			</div>
			<br>
			<div class="col">
				<label>전화번호</label> 
				<input type="text" class="form-control" name="emp_hp"  placeholder="010-0000-0000">
				<div class="invalid-feedback">전화번호 입력</div>
				<form:errors id="errors" path="emp_hp" element="span" cssClass="errors" />
			</div>
			
			
			<br>
			<div class="row" style="padding-left: 15px";>
				<div  class="col-xs-12 col-md-10">
					<label>우편번호</label>
					 <input type="text" class="form-control" name="emp_zip" id="zipCode" readonly="readonly" placeholder="ex) 12345"> 
					<form:errors id="errors" path="emp_zip" element="span" cssClass="errors" />
				</div>
				 <div style="padding-top:40px";>
						 <input type="button" class="btn btn-outline-primary" onclick="sample4_execDaumPostcode()" value="검색"/>
				</div>
			</div>
			<br>
			<div  class="col-xs-12 col-md-10">
				<label>주소</label> 
				<input type="text" class="form-control" name="emp_add1" id="add1"  readonly="readonly" placeholder="ex) 대전시 중구 대흥동">
				<form:errors id="errors" path="emp_add1" element="span" cssClass="errors" />
			</div>
			<br>
			<div  class="col-xs-12 col-md-10">
				<label>상세주소</label> 
				<input type="text" class="form-control" name="emp_add2" id="add2" placeholder="ex) 123-1번지 101호">
				<form:errors id="errors" path="emp_add2" element="span" cssClass="errors" />
			</div>
			
			  
			<br>
			<div class="col">
				<label>성별</label> <label>&nbsp;|&nbsp;</label>
				<input type="hidden" name="emp_gene" value="${employee.emp_gene }"/>
				<label>${employee.emp_gene eq 'w'?'여성':'남성' }</label>
			</div>
		</div>

		<div class="btnCenterWrap">
			<div class="col-12 " style="padding-top: 10px;">
				<button class="btn btn-primary" type="button" id="modifyBtn"
<%-- 					onclick='location.href="<c:url value="/myPage/myPage" />";' --%>
					style="padding-bottom: 10px; margin: 10px 0px 10px 0px; float: right;">확인</button>
			</div>
		</div>
	</div>
</div>
</form:form>


<script>
	//주소찾기
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
	
	$("#modifyBtn").click(function(){
		
		var emp_pass=$("#emp_pass").val();
		var emp_passCheck= $("#emp_passCheck").val();
		
		if(emp_pass!=emp_passCheck){
// 			document.getElementById('pwsame').innerHTML='비밀번호가 일치하지 않습니다. 다시 입력하세요';
			$("#pwsame").html('비밀번호가 일치하지 않습니다. 다시 입력하세요.');
			return false;
		}
		
		$("#modifyForm").submit();
	})
	
</script>





