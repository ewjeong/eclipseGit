<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 참조함 조회 -->
<div class="wrap_appr">
	<h3>참조함</h3>
	<div class="waitList row">
		<div class="col-md-6">2건의 결재가 있습니다.</div>
		<!-- 검색 -->
		<div class="row col-md-6">
			<!-- select -->
			<select class="custom-select col-xs-2 col-md-4">
				<option selected>제목</option>
				<option value="">작성자</option>
				<option value="">제목+작성자</option>
			</select> 
			<!-- 검색용 input 태그와 버튼 -->
			<div class="col-xs-12 col-md-6 form-group input-group">
             <input type="text" class="form-control">
             <span class="input-group-btn searchWrap">
                 <button class="btn btn-default btnSearch" type="button">
                 	<i class="fa fa-search"></i>
                 </button>
             </span>
         </div>
		</div>
		<table class="table table-sm approTable">
			<thead>
				<tr>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">분류</th>
					<th scope="col">날짜</th>
				</tr>
			</thead>
			<tbody id="listBody">
				<c:set value="${pagingVO.dataList }" var="referenceList" />
				<c:if test="${not empty referenceList }">
					<c:forEach items="${referenceList }" var="reference">
						<tr id="${ reference.elec_no}">
							<td>${ reference.elec_title }<i class="fas fa-paperclip"></i></td>
							<td>${ reference.elec_writer}</td>
							<td>${ reference.send.send_name }</td>
							<td>${ reference.elec_senddate }</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
				<c:if test="${empty referenceList }">
					<tr>
						<td colspan="4" style="text-align:center; ">참조된 문서가 없습니다.</td>
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
		location.href = "${pageContext.request.contextPath}/elecAuthorization/reference/referenceView?what=" + what;
	}).css({ cursor : "pointer" });
</script>