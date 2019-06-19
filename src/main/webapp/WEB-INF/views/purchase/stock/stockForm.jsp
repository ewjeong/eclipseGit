<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h3>재고 수정</h3>
<br>
	<div class="col-xs-12 col-md-5">
		<label>상품코드</label> 
		<input type="text" class="form-control">
	</div>
	<div class="col-xs-12 col-md-5">
		<label>품목분류명</label> 
		<input type="text" class="form-control">
	</div>
	<div class="col-xs-12 col-md-5">
		<label>상품명</label> 
		<input type="text" class="form-control">
	</div>
	<div class="col-xs-12 col-md-5">
		<label>상품크기</label> 
		<input type="text" class="form-control">
	</div>
	<div class="col-xs-12 col-md-5">
		<label>상품색상</label> 
		<input type="text" class="form-control">
	</div>
	<div class="col-xs-12 col-md-5">
		<label>재고수량</label> 
		<input type="text" class="form-control">
	</div>
	<div class="col-xs-12 col-md-5">
		<label>단가</label> 
		<input type="text" class="form-control">
	</div>
<br>	
<div class="row">
	<div class="col-xs-4 col-md-6">
		<button type="button" class="btn btn-primary col-xs-2 col-md-3">등록</button>
	</div>
</div>

<script>
	<!-- date picker를 사용하기 위한 script 설정 -->
	  $( "#estimateDate" ).datepicker({
	         changeMonth: true, 
	         dateFormat: 'yy-mm-dd',
	         dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
	         dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
	         monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	         monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	  });
</script>