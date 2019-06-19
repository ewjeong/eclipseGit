<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<script>
<c:if test="${not empty message}">
swal(
	"완료",
	"${message}",
	"success"
);  
</c:if>
</script>
<h1>받은편지함</h1>
 <div class="row">
    <div class="col-xs-12 col-md-3 offset-md-11 form-group input-group">
	 <button class="btn btn-link btn-lg"  onclick="location.href='${pageContext.request.contextPath}/mail/write'"><i class="far fa-edit"></i></button>
	</div>
  </div>

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
						<td >${mail.from}</td>
						<td><a href="${pageContext.request.contextPath }/mail/mailView?what=${mail.id}">${mail.subject}</a></td>
						<td>${mail.date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>

