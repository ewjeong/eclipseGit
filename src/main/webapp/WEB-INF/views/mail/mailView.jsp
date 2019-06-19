<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    <button type="button" class="btn btn-link" onclick="javascript:history.back()"><i class="fas fa-arrow-left"></i></button>
    <c:set var="email" value="${email}"></c:set>
   
    <table class="table table-borderless">
    	<thead>
    		<tr>
    			<th colspan="2" class="h2">${email.subject }</th>
    			<hr  color="gray">
   			</tr>
    	</thead>
    	<tbody>
    		<tr>
	    		<td class="h6">${email.from}</td>
	    		<td>${email.date}</td>
    		</tr>
    		<tr>
    			<td colspan="2" >  
    				<hr color="gray"/>
    				<div>${email.content}</div>
    				<hr color="gray"/>
   				</td>
    		</tr>
    	</tbody>  
    		<c:set var="attachList" value="${email.attachList }"></c:set>
   			<c:forEach var="attach" items="${attachList }">
   					<tr>
	   					<td>
	   						<i class="fas fa-paperclip"></i>&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/mail/down?path=${attach.savepath}&filename=${attach.filename}">${attach.filename }</a>
	   					</td>
   					</tr>
   			</c:forEach>
    	<tfoot>
    </table>
    