<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="http://example.com/ckeditor/plugins/colorbutton"></script>

<div>
	<h3>결재 양식 관리</h3>
	<p>${form.elec_form_code}번 <span class="titleBold">${form.elec_form_title }</span> 양식</p>
	<div class="form_metadata">
		<div class="row ap_mdWrap">
			<label class=" col-xs-2 col-md-2">제목</label>
			<input type="text" name="elec_form_title" class="form-control col-xs-4 col-md-6"
				value="${form.elec_form_title }" readonly="readonly">
		</div>
		<div class="row">		
			<label class=" col-xs-2 col-md-2">작성자</label>
			<input type="text" name="elec_form_writer" class="form-control col-xs-4 col-md-6"
				value="${form.elec_form_writer }" readonly="readonly">
		</div>
	</div>
	<div class="ap_content">
		<textarea id="elec_form_html" cols="70" rows="30" name="elec_form_html" readonly="readonly">
			${form.elec_form_html }		
		</textarea>
	</div>
	<div class="ap_btns">
		<button type="button" class="btn btn-outline-primary col-xs-4 col-md-2 offset-md-2"
			onclick="history.go(-1);"
		>확인</button>
<!-- 		<button type="button" class="btn btn-outline-primary col-xs-4 col-md-2" -->
<%-- 			onclick="location.href='${pageContext.request.contextPath}/elecAuthorization/forms/formsDelete?what=${form.elec_form_code }'" --%>
<!-- 		>삭제</button> -->
	</div>
</div>

<script>
	CKEDITOR.replace('elec_form_html', {
		height : 700
	});
	
// 	CKEDITOR.instances.elec_form_html.setData( '' );
</script>