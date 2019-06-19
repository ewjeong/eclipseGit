<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- <script src="http://example.com/ckeditor/plugins/colorbutton"></script> -->
<%-- <div>${not empty message } </div> --%>
<div>
	<h3>결재 양식 등록</h3>
	<form method="post" name="CreateForm">
<!-- 		<input type="text" name="elec_form_writer"> 로그인한 user의 id 로 셋팅 -->
		<div class="form_metadata">
			<div class="row ap_mdWrap">
				<label class=" col-xs-2 col-md-2">제목</label>
				<input type="text" name="elec_form_title" class="form-control col-xs-6 col-md-6" required>
			</div>
			<div class="row ap_mdWrap">
				<label class=" col-xs-2 col-md-2">분류</label>
				<select name="send_code" class="custom-select col-xs-2 col-md-2" required>
					<option selected value="SEN001">발주서</option>
					<option value="SEN002">주문서</option>
					<option value="SEN003">출장</option>
					<option value="SEN004">회의</option>
					<option value="SEN005">세미나</option>
				</select> 
			</div>
		</div>
		<div class="ap_content" required>
			<div class="">
				<div class="ap_main">
					<textarea id="elec_form_html" cols="70" rows="30" name="elec_form_html"></textarea>
				</div>
<!-- 				<div class="fileWrap"> -->
<!-- 					파일 선택하기 -->
<!-- 					<div class="input-group mb-3"> -->
<!-- 						<div class="form-group filebox"> -->
<!-- 				             <label for="file"><i class="fas fa-paperclip"></i></label> -->
<!-- 				             <input class="rounded" type="file" name="file"  id="file"> -->
<!-- 				          </div> -->
<!-- 	<!-- 					<div class="input-group-append"> --> 
<!-- 	<!-- 						<span class="input-group-text" id="inputGroupFileAddon02">등록</span> -->
<!-- 	<!-- 					</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="ap_ref"> -->
					
<!-- 				</div>	 -->
			</div>
			<div class="ap_btns">
				<button type="button" class="btn btn-outline-primary col-xs-4 col-md-2 offset-md-4">PDF 출력</button>
				<button type="submit" class="btn btn btn-primary col-xs-4 col-md-2" >완료</button>				
			</div>
		</div>
	</form>
</div>
<!-- <script src="http://example.com/ckeditor/plugins/colorbutton"></script> -->
<script>
	CKEDITOR.replace('elec_form_html', {
		height : 700
// 		, extraPlugins: 'colorbutton,colordialog'
	});
// 	config.colorButton_foreStyle = {
// 	    element: 'font',
// 	    attributes: { 'color': '#(color)' }
// 	};

// 	CKEDITOR.editorConfig = function( config ) {
// 	    config.extraPlugins = 'colorbutton';
// 	};
			
	var data = CKEDITOR.instances.elec_form_html.getData();
	console.log(data);
// 	$("#elec_form_html").value(data);
</script>





















