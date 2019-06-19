<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
<c:if test="${ not empty message}">
	<c:if test="${message eq 'OK' }">
		swal("등록 완료", "기안서 등록이 완료되었습니다." , "success");
		console.log("${ message }");
	</c:if>
</c:if>

<c:if test="${ not empty message}">
	<c:if test="${status eq  'ERROR'}">
		alert("${message}");
	</c:if>
</c:if>
</script>
<!-- 상신함 조회 -->
<div class="wrap_appr">
	<h3>상신함</h3>
	<div class="waitList row">
<!-- 		<div class="col-md-10">2건의 결재가 있습니다.</div> -->
		<!-- 검색 -->
		<div class="row offset-md-10">
<!-- 			<!-- select --> 
<!-- 			<select class="custom-select col-xs-2 col-md-4"> -->
<!-- 				<option selected>제목</option> -->
<!-- 				<option value="">작성자</option> -->
<!-- 				<option value="">제목+작성자</option> -->
<!-- 			</select> &nbsp;&nbsp; -->
<!-- 			<input type="text" class="form-control col-xs-6 col-md-5" > --> 
			<button type="button" class="btn btn-primary "
				onclick="location.href='${pageContext.request.contextPath }/elecAuthorization/sangshin/sangshinInsert'">
				등록
			</button>
		</div>
		<table class="table table-sm approTable">
			<thead>
				<tr>
					<th scope="col">제목</th>
					<th scope="col">분류</th>
					<th scope="col">날짜</th>
					<th scope="col">완료여부</th>
					<th scope="col">반려여부</th>
				</tr>
			</thead>
			<tbody id="listBody">
				<c:set value="${pagingVO.dataList }" var="sangshinList"  />
				<c:if test="${not empty sangshinList }">
					<c:forEach items="${sangshinList }" var="sangshin">
						<tr id="${sangshin.elec_no }">
							<td>${sangshin.elec_title }</td>
							<td>
								<c:if test="${sangshin.send_code eq 'SEN001'}">
									발주
								</c:if>
								<c:if test="${sangshin.send_code eq 'SEN002'}">
									주문
								</c:if>
								<c:if test="${sangshin.send_code eq 'SEN003'}">
									출장
								</c:if>
								<c:if test="${sangshin.send_code eq 'SEN004'}">
									회의
								</c:if>
								<c:if test="${sangshin.send_code eq 'SEN005'}">
									세미나
								</c:if>
							</td>
							<td>${sangshin.elec_senddate }</td>
							<td>
								<c:if test="${sangshin.elec_comple eq 'N'}">
									미완료
								</c:if>
								<c:if test="${sangshin.elec_comple eq 'Y'}">
									완료
								</c:if>
							</td>
							<td>
								<c:if test="${not empty sangshin.appr_status_name }">
									${ sangshin.appr_status_name }
								</c:if>
								<c:if test="${empty sangshin.appr_status_name }">
									미반려
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
				<c:if test="${empty sangshinList }">
					<tr>
						<td colspan="5" style="text-align:center; ">기안 문서가 없습니다.</td>
					</tr>
				</c:if>
			</tfoot>
		</table>
		<!-- 페이지네이션 -->
		<div class="pageWrap">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item disabled"><a class="page-link" href="#" tabindex="-1" aria-disabled="true">&lt;</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">&gt;</a></li>
				</ul>
			</nav>
		</div>
	</div>
</div>

<script>
	$("#listBody").on("click", "tr", function(){
		var what = $(this).prop("id");
		location.href = "${pageContext.request.contextPath}/elecAuthorization/sangshin/sangshinView?what=" + what;
	}).css({cursor : "pointer"});	
</script>








