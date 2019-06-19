<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<!-- datePicker를 사용하기위한 설정 -->
	<!--  jQuery UI CSS파일 --> 
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
	
	<!-- jQuery 기본 js파일 -->
	<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<!-- 	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>   -->
	
	<!-- jQuery UI 라이브러리 js파일 -->
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
	
	<!-- ckeditor js -->
	<script src="<c:url value='/js/ckeditor/ckeditor.js'/>"></script>
	<script src="https://cdn.ckeditor.com/4.11.4/standard-all/ckeditor.js"></script>
	
	<!-- 19-05-22 이초연 추가 -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
	<script>
	// Cannot read property 'msie' of undefined 에러 나올때
	// jQuery import 바로아래에 넣어 주면 됩니다.
	jQuery.browser = {};
	(function () {
	    jQuery.browser.msie = false;
	    jQuery.browser.version = 0;
	    if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
	        jQuery.browser.msie = true;
	        jQuery.browser.version = RegExp.$1;
	    }
	})();
	</script>

<style type="text/css">
	.invite {
		width: 100%;
		height: 50px;
	}
	
	.modal-content{
		width: 650px;
	}
	
</style>
