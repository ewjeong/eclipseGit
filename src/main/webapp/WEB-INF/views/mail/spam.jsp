<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<h1>스팸편지함</h1>
  <div class="row">
  <div class="col">
    <div class="col-xs-12 col-md-3 offset-md-11 form-group input-group">
	 <button class="btn btn-link btn-lg"  onclick="location.href='${pageContext.request.contextPath}/mail/write'"><i class="far fa-edit"></i></button>
	</div>
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
				<c:choose>
					<c:when test="${empty messageList}">  
						<tr>
							<td colspan="3" style="text-align: center;">스팸메일이 없습니다</td>
						</tr>  
					</c:when>    
					<c:otherwise> 
						<c:forEach var="mail" items="${messageList }">  
							<tr>
								<td>${mail.from}</td>
								<td><a href="${pageContext.request.contextPath }/mail/mailView?what=${mail.id}">${mail.subject}</a></td>
								<td>${mail.date}</td>
							</tr>  
						</c:forEach> 
					</c:otherwise>   
				</c:choose>
			</table>  
		</div>
