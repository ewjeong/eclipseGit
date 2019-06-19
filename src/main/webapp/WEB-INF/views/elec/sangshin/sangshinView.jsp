<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<div>
	<h3>상신함</h3>
	<p>${sangshin.elec_no }번 <span class="titleBold">${sangshin.elec_title }</span> 기안서</p>
	<div class="ap_content" >
		<textarea id="elec_content" cols="70" rows="30" name="elec_content" readonly>
			${sangshin.elec_content }
		</textarea>
		<div class="fileWrap">
			<p>첨부파일</p>
			<c:if test="${not empty sangshin.fileList }">
				<c:forEach items="${sangshin.fileList }" var="attach" varStatus="vs">
					<c:url value="/elecAuthorization/download" var="downloadURL">
						<c:param name="what" value="${attach.attach_no }"></c:param>
					</c:url>
					<a href="${ downloadURL }" title="파일크기:${attach.attach_fancysize }">${attach.attach_orgname }</a>
						&nbsp;&nbsp;
						${not vs.last? "|" : ""}
						&nbsp;&nbsp;			
				</c:forEach>
			</c:if>
			<c:if test="${empty sangshin.fileList }">
				<p class="noneFile">등록된 첨부파일이 없습니다.</p>
			</c:if>
		</div>
	</div>
	
	<div class="ap_btns">
		<button type="button" class="btn btn-outline-primary col-xs-4 col-md-2 offset-md-2"
			onclick="history.go(-1);">확인</button>
		<c:if test="${not empty sangshin.appr_status_name}">
			<button type="button" class="btn btn-outline-primary col-xs-4 col-md-2 offset-md-2">수정</button>
		</c:if>
	</div>
</div>

<script>
	CKEDITOR.replace('elec_content', {
		height : 700
// 		,toolbarGroups: [
// 		       {
// 		         "name": "insert",
// 		         "groups": ["insert"]
// 		       },
// 		     ],
		     // Remove the redundant buttons from toolbar groups defined above.
// 		     removeButtons: 'Underline,Strike,Subscript,Superscript,Anchor,Styles,Specialchar'
	});
	var content = $("#elec_content").val();
	CKEDITOR.instances.elec_content.setData(content);
</script>