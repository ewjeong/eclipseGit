<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<h3>결재함</h3>
	<p>${approval.elec_no}번 <span class="titleBold">${approval.elec_title }</span> 완료된 기안서</p>
	<div class="ap_content">
		<textarea id="elec_form_html" cols="70" rows="30" name="elec_form_html" readonly>
			${approval.form.elec_form_html }
		</textarea>
		<div class="fileWrap">
			<p>첨부파일</p>
			<c:forEach items="${approval.fileList }" var="attach" varStatus="vs">
				<c:url value="/elecAuthorization/download" var="downloadURL">
					<c:param name="what" value="${attach.attach_no }"></c:param>
				</c:url>
				<a href="${ downloadURL }" title="파일크기:${attach.attach_fancysize }">${attach.attach_orgname }</a>
				&nbsp;&nbsp;
				${not vs.last? "|" : ""}
				&nbsp;&nbsp;			
			</c:forEach>
		</div>
	</div>
	<div class="ap_btns">
		<button type="button" class="btn btn-outline-primary col-xs-4 col-md-2 offset-md-2" onclick="history.back()">확인</button>
	</div>
</div>

<script>
	CKEDITOR.replace('elec_form_html', {
		height : 700,
		height : 700,
		toolbarGroups: [
	       {
	         "name": "insert",
	         "groups": ["insert"]
	       },
	     ],
	     // Remove the redundant buttons from toolbar groups defined above.
	     removeButtons: 'Underline,Strike,Subscript,Superscript,Anchor,Styles,Specialchar'
	});
</script>