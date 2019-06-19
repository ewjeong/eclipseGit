<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div>
	<h3>참조함</h3>
	<p>${reference.elec_no}번 <span class="titleBold">${reference.elec_title }</span> 기안서</p>
	<div class="ap_content">
		<textarea id="elec_form_html" cols="70" rows="30" name="elec_form_html" readonly="readonly">
			${approval.form.elec_form_html }
		</textarea>
	</div>
	<div class="ap_btns">
		<button type="button" class="btn btn-outline-primary col-xs-4 col-md-2 offset-md-2">확인</button>
		<button type="button" class="btn btn-outline-primary col-xs-4 col-md-2" >버튼</button>
	</div>
</div>

<script>
	CKEDITOR.replace('elec_form_html', {
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

