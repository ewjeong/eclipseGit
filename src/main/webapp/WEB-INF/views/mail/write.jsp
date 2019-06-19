<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
    

<script>
<c:if test="${not empty message}">
swal(
	"오류",
	"${message}",
	"error"
);  
</c:if>

	$(function(){
		
		$("#send").click(function(){
			$("#mailSendForm").submit();
		})
		  
	});
</script>
   <c:set var="mail" value="${mail}"></c:set>
    <form  id="mailSendForm" method="post" enctype="multipart/form-data" accept-charset="UTF-8" action="${pageContext.request.contextPath }/mail/send">
	    	<div  class="form-group">
	    		  <label for="to"><p class="font-weight-normal" >받는사람</p></label>
	    		<input class="rounded form-control" type="text" id="to" name="to"  value="${mail.to }" required="required">
	    	</div>
	    	<div class="form-group">    
	    		<label for="title"><p class="font-weight-normal">제목</p></label>
	    		<input class="rounded form-control" type="text" id="title" value="${mail.subject }" name="title">
	    	</div>
	    	<div class="form-group">      
	    		<textarea class="rounded form-control" rows="20" cols="80" name="message"  id="message">${mail.bodytext }</textarea>
	    	</div>   
	    	<div class="form-group filebox">
	    		<label for="file"><i class="fas fa-paperclip"></i></label>
	    		<input class="rounded" type="file" name="file"  id="file">
	    	</div>
	    	<div class="btnCenterWrap ">
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	<button class="btn btn-primary" type="button" id="send">전송</button>
		    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	<button class="btn btn-secondary" type="reset" onclick="javascript:history.back()">취소</button>
	    	</div>
    </form>