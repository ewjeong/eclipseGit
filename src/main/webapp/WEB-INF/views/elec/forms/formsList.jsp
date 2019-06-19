<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<h3>결재 양식 관리</h3>
	<div>
		<button type="button" class="btn btn-primary col-xs-12 offset-md-10"
				onclick="location.href='${pageContext.request.contextPath }/elecAuthorization/forms/formsInsert'">등록
		</button>
		<table class="table table-sm approTable">
			<thead>
				<tr>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">분류</th>
				</tr>
			</thead>
			<tbody id="listBody">
				<c:set value="${pagingVO.dataList }" var="formList"/>
				<c:if test="${not empty formList }">
					<c:forEach items="${formList }" var="form">
						<tr id="${form.elec_form_code }">
							<td>${form.elec_form_title }</td>
							<td>${form.emp_name }</td> <!-- 결재양식 작성자 이름  --> 
							<td>
								<c:if test="${form.send_code eq 'SEN001'}">
									발주
								</c:if>
								<c:if test="${form.send_code eq 'SEN002'}">
									주문
								</c:if>
								<c:if test="${form.send_code eq 'SEN003'}">
									출장
								</c:if>
								<c:if test="${form.send_code eq 'SEN004'}">
									회의
								</c:if>
								<c:if test="${form.send_code eq 'SEN005'}">
									세미나
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>
<script>
	$("#listBody").on("click", "tr", function(){
		var what = $(this).prop("id");
		location.href = "${pageContext.request.contextPath}/elecAuthorization/forms/formsView?what=" + what;
	}).css({ cursor : "pointer" });
</script>
