<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<h1>중요편지함</h1>
 <button class="btn btn-primary"  onclick="location.href='${pageContext.request.contextPath}/mail/write'">편지쓰기</button>
<div class="container">
<!-- 	<div class="row"> -->
<!-- 		<div class="col"> -->
			<table class="table table-hover">
				<tr>
					<th>보낸사람</th>
					<th>제목</th>
					<th>받은날짜</th>
				</tr>
				<c:set var="messageList" value="${mailHeaderList}"></c:set>
				<c:forEach var="mail" items="${messageList }">
					<tr>
						<td>${mail.from}</td>
						<td>${mail.subject}</td>
						<td>${mail.date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>