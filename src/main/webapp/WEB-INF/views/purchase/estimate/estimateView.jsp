<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<h3>견적서 상세조회</h3>
<br>
<br>
<div>
	<table class="table table-bordered">
		<c:set value="${purEstList }" var="pur_est" />
		<tr>
			<th class="thwidth">견적서번호</th>
			<td colspan="4">${pur_est.pur_est_no }</td>
		</tr>
		<tr>	
			<th class="thwidth">견적일자</th>
			<td colspan="4">${pur_est.pur_est_date }</td>
		</tr>
		<tr>
			<th class="thwidth">거래처코드</th>
			<td colspan="4">${pur_est.cl_no }</td>
		</tr>
		<tr>
			<th class="thwidth">거래처명</th>
			<td colspan="4">${pur_est.cl_name }</td>
		</tr>
		<tr>
			<th class="thwidth">거래처전화번호</th>
			<td colspan="4">${pur_est.cl_tel }</td>
		</tr>
		<tr>
			<th class="thwidth">결제방법</th>
			<td colspan="4">${pur_est.payment }</td>
		</tr>
		<tr>
			<th class="thwidth">결제금액</th>
			<td colspan="4">${pur_est.est_cost }</td>
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
		<c:forEach items="${pur_est.pur_eprodList }" var="eprod" >
		 	<tr>
				<td>${eprod.prod_no }</td>
				<td>${eprod.prod_name }</td>
				<td>${eprod.prod_color }</td>
				<td>${eprod.prod_size }</td>
				<td>${eprod.pur_eprod_qty }</td>
				<td>${eprod.pur_eprod_cost }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>	
