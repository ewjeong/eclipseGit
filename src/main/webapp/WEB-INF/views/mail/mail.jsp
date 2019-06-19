<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    


<h1>메일함</h1>
<button class="btn btn-primary" onclick="href.location='${pageContext.request.contextPath}/mail/write'">편지쓰기</button>
<div class="container">
	<div class="row">
		<div class="col">
			<table class="table table-hover">
				<c:set var="messageList" value="${snippetList }"></c:set>
				<c:forEach var="snippet" items="${messageList }">
					<tr>
						<td>${snippet}</td>
					</tr>
				</c:forEach>
			</table>
			
		</div>
	</div>

</div>