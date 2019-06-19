<%-- 
* [[개정이력(Modification Information)]]
* 수정일        수정자      수정내용
* ----------  ---------  -----------------
* 2019.06.06   이초연      최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
<c:if test="${ not empty message}">
	<c:if test="${ message eq 'OK'}">
		swal("등록 완료", "상품 등록이 완료되었습니다." , "success");	
	</c:if>
	<c:if test="${ message eq 'FAILED'}">
		swal("등록 완료", "상품 등록이 완료되었습니다." , "error");
	</c:if>
</c:if>	
</script>

<h3>상품 등록</h3>
<br>
<form method="post" action="${pageContext.request.contextPath}/purchasingTeam/itemManage/productInsert" enctype="multipart/form-data">
	<br>
	<input type="hidden" name="" class="form-control">

	<div class="col-xs-12 col-md-5" > 
		<label>품목</label>
		<select class="form-control" name="item_code">
			<c:if test="${not empty itemList }">
				<c:forEach items="${itemList }" var="item">
					<option value="${item.item_code }">${item.item_name }</option>					
				</c:forEach>
			</c:if>
		</select>
	</div>
	<br>
	<div class="col-xs-12 col-md-5 " > 
		<label>상품명</label> 
		<input type="text" name="prod_name" class="form-control">
	</div>
	<br>
	<div class="col-xs-12 col-md-5 " > 
		<label>상품 이미지</label> 
		<input type="file" name="prod_image" class="form-control" accept="image/*">
	</div>
	<br>
	<div class="col-xs-12 col-md-5 " > 
		<label>상품크기</label> 
		<input type="text" name="prod_size" class="form-control ">
	</div>
	<br>
	<div class="col-xs-12 col-md-5 " > 
		<label>상품단가</label> 
		<input type="text" name="prod_cost" class="form-control ">
	</div>
	<br>
	<div class="col-xs-12 col-md-5 " > 
		<label>상품개요</label> 
		<input type="text" name="prod_outline" class="form-control ">
	</div>
	<br>
	<div class="col-xs-12 col-md-5 " > 
		<label>상품상세설명</label> 
		<input type="text" name="prod_details" class="form-control ">
	</div>
	<br>
	<div class="col-xs-12 col-md-5 " > 
		<label>상품색상</label> 
		<input type="text" name="prod_color" class="form-control ">
	</div>
    <br>
    <br>
	<div class="col-xs-4 col-md-6">
		<button type="submit" class="btn btn-primary col-xs-2 col-md-3">
		등록</button>	
	</div>
</form>