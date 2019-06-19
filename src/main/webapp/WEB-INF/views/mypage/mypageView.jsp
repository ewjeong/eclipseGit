<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


	<div class="col-xs-6 col-md-9" style="padding: 10px 0px 10px 0px;">
		<div class="row">
				<div class="col-12" style="border: 0.5px solid #E6E6E6; padding-top: 10px;">
				<div class="col">
						<h3>나의 정보</h3>
						<hr>
						<label for="validationServer01">아이디</label> <label>&nbsp;|&nbsp;</label>
						<input type="hidden" name="emp_id" value="${employee.emp_id }" id="emp_id"/>
						<label>${employee.emp_id }</label>
				</div>

				<div class="col">
					<label for="validationServer02">이름</label> <label>&nbsp;|&nbsp;</label>
					<label>${employee.emp_name }</label>
				</div>
				<div class="col">
					<label for="validationServer03">생년월일</label> <label>&nbsp;|&nbsp;</label>
					<label>${employee.emp_bir }</label>
				</div>
				<div class="col">
					<label for="validationServer02">주민등록번호</label> <label>&nbsp;|&nbsp;</label>
					<label>${employee.emp_reg1 }</label> <label>-</label> <label>${employee.emp_reg2 }</label>
				</div>
				<div class="col">
					<label for="validationServerUsername">이메일</label> <label>&nbsp;|&nbsp;</label>
					<label>${employee.emp_mail }</label>
				</div>
				<div class="col">
					<label for="validationServer05">주소</label> <label>&nbsp;|&nbsp;</label>
					<label>${employee.emp_add1 }</label>
				</div>
				<div class="col">
					<label for="validationServer05">상세주소</label> <label>&nbsp;|&nbsp;</label>
					<label>${employee.emp_add2 }</label>
				</div>
				<div class="col">
					<label for="validationServer05">성별</label> <label>&nbsp;|&nbsp;</label>
					<label>${employee.emp_gene eq 'w'?'여자':'남자' }</label>
				</div>
				<div class="col">
					<label for="validationServer05">부서</label> <label>&nbsp;|&nbsp;</label>
					<label>${employee.dep_name }</label>
				</div>
				<div class="col">
					<label for="validationServer05">직급</label> <label>&nbsp;|&nbsp;</label>
					<label>${employee.pos_name}</label>
				</div>
			<div class="col"></div>
			<br>
			</div>
		</div>
		
			<div class="btnCenterWrap">
			<div class="col-9 " style="padding-top: 10px;">
				<button class="btn btn-primary " type="button" id="modifyBtn"
<%-- 					onclick='location.href="<c:url value="/myPage/myPageUpdate" />";' --%>
					style="padding-bottom: 10px; margin: 10px 0px 10px 0px; float: right;">수정</button>
			</div>
			</div>
	
		
</div>



<script>
	$("#modifyBtn").click(function(){
		var emp_id= $("#emp_id").val();
		location.href="${pageContext.request.contextPath}/myPage/myPageUpdate?emp_id="+emp_id;
	})
</script>











