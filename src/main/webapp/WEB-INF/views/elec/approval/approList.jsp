<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
<c:if test="${ not empty message}">
	<c:if test="${message eq 'REJECT' }">
		swal("반려 완료", "반려 처리되었습니다." , "success");
		console.log("${ message }");
	</c:if>
	
	<c:if test="${message eq 'DAEGYEOL' }">
		swal("대결 완료", "대결 처리되었습니다." , "success");
		console.log("${ message }");
	</c:if>
	
	<c:if test="${message eq 'JEONGYEOL' }">
		swal("전결 완료", "전결 처리되었습니다." , "success");
		console.log("${ message }");
	</c:if>
</c:if>
</script>
	
<div class="wrap_appr">
	<h3>결재함</h3>
	<div class="waitList row">
		<div class="col-md-6">결재 대기 문서</div>
		<!-- 검색 -->
<!-- 		<form name="searchForm"> -->
			<div class="row col-xs-12 col-md-6">
				<input type="hidden" name="page">
				<!-- select -->
				<select name="searchType" class="custom-select col-xs-2 col-md-4">
					<option selected value="elec_title" ${pagingVO.searchType eq 'all' ? "selected" : "" }>제목</option>
					<option value="elec_writer" ${pagingVO.searchType eq 'all' ? "selected" : "" }>작성자</option>
					<option value=""></option>
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
<!-- 		</form> -->
		<table class="table table-sm approTable">
			<thead>
				<tr>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">상태</th>
					<th scope="col">날짜</th>
				</tr>
			</thead>
			<tbody id="listBody">
				<c:set value="${pagingVO.dataList }" var="approvalList" />
				<c:if test="${not empty approvalList }">
					<c:forEach var="approval" items="${approvalList }">
						<tr id="${approval.elec_no }">
							<td>${approval.elec_title } <i class="fas fa-paperclip"></i></td>
							<td>${approval.elec_writer }</td>
							<td>
								<c:if test="${ empty approval.appr_status_name }">
									<c:if test="${ not empty approval.instead_id }">
										대결
									</c:if>
									<c:if test="${ empty approval.instead_id }">
										미처리
									</c:if>
								</c:if>
								<c:if test="${not empty approval.appr_status_name }">
									${ approval.appr_status_name }
								</c:if>
							</td>
							<td>${approval.elec_senddate }</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
				<tr>
					<c:if test="${empty approvalList  }">
						<td class="none" colspan="4"> 결재할 문서가 없습니다.</td>
					</c:if>
				</tr>
			</tfoot>
		</table>
		<!-- 페이지네이션 -->
		<div class="pageWrap">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item disabled"><a class="page-link" href="#"
						tabindex="-1" aria-disabled="true">&lt;</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">&gt;</a></li>
				</ul>
			</nav>
		</div>
	</div>
	
	<div class="compleList row">	
		<div class="col-md-6">결재 완료 문서</div>
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
					<th scope="col">상태</th>
					<th scope="col">날짜</th>
				</tr>
			</thead>
			<tbody id="completelistBody">
				<c:set value="${complePagingVO.dataList }" var="completeList" />
				<c:if test="${not empty completeList  }">
					<c:forEach items="${completeList }" var="completeApproval">
						<tr id="${ completeApproval.elec_no}">
							<td>${ completeApproval.elec_title}</td>
							<td>${ completeApproval.elec_writer }</td>
							<td>
								<c:if test="${ empty completeApproval.appr_status_name }">
									전결
								</c:if>
								<c:if test="${not empty completeApproval.appr_status_name }">
									${ completeApproval.appr_status_name }
								</c:if>
							</td>
							<td>${ completeApproval.elec_senddate }</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
				<tr>
					<c:if test="${empty completeList  }">
						<td class="none" colspan="4">완료된 결재 문서가 없습니다.</td>
					</c:if>
				</tr>
			</tfoot>
		</table>
		<!-- 페이지네이션 -->
		<div class="pageWrap">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item disabled"><a class="page-link" href="#"
						tabindex="-1" aria-disabled="true">&lt;</a></li>
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
		location.href = "${pageContext.request.contextPath}/elecAuthorization/approval/approView?what="+what;
	}).css({cursor : "pointer"});
	
	$("#completelistBody").on("click", "tr", function(){
		var what = $(this).prop("id");
		location.href = "${pageContext.request.contextPath}/elecAuthorization/approval/completeView?what="+what;
	}).css({cursor : "pointer"});
</script>





















