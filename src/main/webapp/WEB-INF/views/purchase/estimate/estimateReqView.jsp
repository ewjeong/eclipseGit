<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<h3>견적요청서 상세조회</h3>
<br>
<br>
<div>
	<table class="table table-bordered">
		<c:set value="${purErList }" var="pur_er" />
		<tr>
			<th class="thwidth">견적요청서번호</th>
			<td colspan="4">${pur_er.pur_er_no }</td>
		</tr>
		<tr>	
			<th class="thwidth">견적요청일자</th>
			<td colspan="4">${pur_er.pur_er_date }</td>
		</tr>
		<tr>
			<th class="thwidth">거래처코드</th>
			<td colspan="4">${pur_er.cl_no }</td>
		</tr>
		<tr>
			<th class="thwidth">거래처명</th>
			<td colspan="4">${pur_er.cl_name }</td>
		</tr>
		<tr>
			<th class="thwidth">거래처전화번호</th>
			<td colspan="4">${pur_er.cl_tel }</td>
		</tr>
		<tr>
			<th class="thwidth">담당자</th>
			<td colspan="4">${pur_er.emp_name }</td>
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
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${pur_er.pur_er_prodList }" var="erprod" >
		 	<tr>
				<td>${erprod.prod_no }</td>
				<td>${erprod.prod_name }</td>
				<td>${erprod.prod_color }</td>
				<td>${erprod.prod_size }</td>
				<td>${erprod.pur_erprod_qty }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>	
	<br>
	<br>
	<a class="btn btn-outline-primary col-xs-4 col-md-2" href='<c:url value="/pdf/EstReqView.do?what=${pur_er.pur_er_no }"/>' >
     PDF출력</a>  
</div>

