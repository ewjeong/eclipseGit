<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>	
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
<c:if test="${not empty message}">
	swal("완료","${message}","success");
</c:if>
	
	function Postcode(){
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
				//document.getElementById('zipCode').value = data.zonecode; //다섯자리 새우편번호 사용
				if (data.jibunAddress) {
					document.getElementById('com_add1').value = data.jibunAddress; //지번
				} else {
					document.getElementById('com_add1').value = fullRoadAddr; //도로명
				}
			}
		}).open();
		
	}
	

</script>

<h3>회사 정보 수정</h3>
<br><br><br>
<div class="form-group row"></div>  
<form:form method="post" id="modifyCompany" commandName="company"
	action="${pageContext.request.contextPath }/settingUp/modifyAddress">
	<div class="form-group row">
		<label for="colFormLabel" class="col-sm-2 col-form-label">회사명</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="com_name" name="com_name" value="${company.com_name }" 
				placeholder="회사명">
		</div>
	</div>
	<br><br>
	<div class="form-group row">
		<label for="colFormLabel" class="col-sm-2 col-form-label">전화번호</label>
		<div class="col-sm-10">
			<input type="tel" class="form-control" id="com_tel" name="com_tel" value="${company.com_tel }" 
				placeholder="전화번호">
		</div>
	</div>
	<br><br>
	<div class="form-group row">
		<label for="colFormLabel" class="col-sm-2 col-form-label">주소</label>
		<div class="col-sm-10">
			<input type="tel" class="form-control" id="com_add1" readonly="readonly" value="${company.com_add1 }" name="com_add1"
				placeholder="주소">
			<input type="button" class="btn btn-primary" onclick="Postcode()" value="검색"/>
		</div>
		<label for="colFormLabel" class="col-sm-2 col-form-label">상세주소</label>
		<div class="col-sm-10">
			<input type="tel" class="form-control" id="com_add2" value="${company.com_add2 }" name="com_add2"
				placeholder="상세주소">
		</div>
		<br><br><br><br><br><br><br><br><br>
		<div class="btnCenterWrap">
			<button type="reset" class="btn btn-outline-primary">취소</button>
			<button type="submit" class="btn btn-primary ">수정</button>
		</div>
	</div>
</form:form>