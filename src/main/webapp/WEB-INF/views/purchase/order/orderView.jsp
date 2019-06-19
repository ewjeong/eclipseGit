<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
	
<h3>발주서 상세조회</h3>
<br>
<br>
<div>
	<table class="table table-bordered">
		<c:set value="${purOrdList }" var="pur_ord" />
		<tr>
			<th class="thwidth">발주코드</th>
			<c:set value="${ pur_ord.pur_ord_code}" var="ordCode" />
			<td colspan="4" id="ordCodeTd">${pur_ord.pur_ord_code }</td>
		</tr>
		<tr>	
			<th class="thwidth">발주일자</th>
			<td colspan="4">${pur_ord.pur_ord_date }</td>
		</tr>
		<tr>
			<th class="thwidth">담당자ID</th>
			<td colspan="4">${pur_ord.ord_emp_id }</td>
		</tr>
		<tr>
			<th class="thwidth">담당자이름</th>
			<td colspan="4">${pur_ord.emp_name }</td>
		</tr>
		<tr>
			<th class="thwidth">주문자</th>
			<td colspan="4">${pur_ord.com_name }</td>
		</tr>
		<tr>
			<th class="thwidth">주문자전화번호</th>
			<td colspan="4">${pur_ord.com_tel }</td>
		</tr>
		<tr>
			<th class="thwidth">주문자주소</th>
			<td colspan="4">${pur_ord.com_add1 }</td>
		</tr>
		<tr>
			<th class="thwidth">주문자상세주소</th>
			<td colspan="4">${pur_ord.com_add2 }</td>
		</tr>
		<tr>
			<th class="thwidth">결제방법</th>
			<td colspan="4">${pur_ord.payment }</td>
		</tr>
		<tr>
			<th class="thwidth">결제금액</th>
			<td colspan="4">${pur_ord.ord_cost }</td>
		</tr>
		<tr>
			<th class="thwidth">거래처코드</th>
			<td colspan="3">${pur_ord.cl_no }</td>
		<tr>
		<tr>
			<th class="thwidth">거래처명</th>
			<td colspan="3">${pur_ord.cl_name }</td>
		</tr>
		<tr>
			<th class="thwidth">결재상태</th>
			<td colspan="3">${pur_ord.elec_comple }</td>
		</tr>
	</table>
	<div class="col-xs-12 col-md-10">
	<table class="table">
		<thead>
		<tr>
			<th>상품코드</th>
			<th>상품명</th>
			<th>색상</th>
			<th>크기</th>
			<th>수량</th>
			<th>단가</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${pur_ord.pur_oprodList }" var="oprod" >
		 	<tr>
				<td>${oprod.prod_no }</td>
				<td>${oprod.prod_name }</td>
				<td>${oprod.prod_color }</td>
				<td>${oprod.prod_size }</td>
				<td>${oprod.pur_oprod_qty }</td>
				<td>${oprod.pur_oprod_cost }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>	
	<br>
	<c:if test="${pur_ord.elec_comple eq '미결재' }">
		<button id="goToElecApproval" type="button" class="btn btn-outline-primary col-xs-4 col-md-2"
		>결재신청</button> 
	</c:if>
	<a class="btn btn-outline-primary col-xs-4 col-md-2" href='<c:url value="/pdf/purOrderView.do?what=${pur_ord.pur_ord_code }"/>' >
      PDF출력</a> 
</div>


<!-- 발주서 코드를 전자결재로 보내기 위한 폼 -->
<form id="goToElectForm" method="get" action="${pageContext.request.contextPath }/elecAuthorization/sangshin/sangshinInsert">
	<input type="hidden" name="ordCode" value="${ordCode }" >
</form>
	
<script>
	$("#goToElecApproval").on("click", function(){
		$("#goToElectForm").submit();
	});
</script>













