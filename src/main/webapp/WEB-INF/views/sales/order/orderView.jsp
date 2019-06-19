<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
	<style>
	
	</style>
	
	<h3>주문 상세조회</h3>
	<form action="${pageContext.request.contextPath }/salesTeam/orderManage/orderView">
		<input type="hidden" name="what" value="${param.what }">
	</form>

	<div>
		<table class="table table-bordered">
		<c:set value="${sale_ord_listVO }" var="sale_ord"/>
			<tr>
				<th class="thwidth">주문코드</th>
				<c:set value="${sale_ord.sale_ord_code }" var="ordCode"/>
				<td colspan="4">${sale_ord.sale_ord_code}</td>
			</tr>
			<tr>	
				<th class="thwidth">주문일자</th>
				<td colspan="4">${sale_ord.sale_ord_date }</td>
			</tr>
			<tr>
				<th class="thwidth">주문자</th>
				<td colspan="4">${sale_ord.cl_charger }</td>
			</tr>
			<tr>
				<th class="thwidth">주문자전화번호</th>
				<td colspan="4">${sale_ord.cl_tel }</td>
			</tr>
			<tr>
				<th class="thwidth">수령자</th>
				<td colspan="4">${sale_ord.cl_receive }</td>
			</tr>
			<tr>
				<th class="thwidth">수령자주소</th>
				<td colspan="4">${sale_ord.cl_add1 }</td>
			</tr>
			<tr>
				<th class="thwidth">수령자상세주소</th>
				<td colspan="4">${sale_ord.cl_add2 }</td>
			</tr>
			<tr>
				<th class="thwidth">결제방법</th>
				<td colspan="4">
				<c:if test="${sale_ord.payment eq 0 }">
					현금
				</c:if>
				<c:if test="${sale_ord.payment eq 1 }">
					카드
				</c:if>
				
				</td>
			</tr>
			<tr>
				<th class="thwidth">결제금액</th>
<%-- 				<c:	set value="0" var="total"></c:set> --%>
				<td colspan="4">
<%-- 					<c:forEach items="${sale_ord.sale_oprodList }" var="oprod"> --%>
<%-- 						<c:set value="${total + oprod.sale_oprod_qty * oprod.sale_oprod_cost }" var="total"/> --%>
<%-- 					</c:forEach> --%>
<%-- 					<c:out value="${total }"/> --%>
					${sale_ord.total_cost }
				</td>
			</tr>
			<tr>
				<th class="thwidth">거래처코드</th>
				<td colspan="3">${sale_ord.cl_no }</td>
			<tr>
			<tr>
				<th class="thwidth">거래처명</th>
				<td colspan="3">${sale_ord.cl_name }</td>
			</tr>
			<tr>
				<th class="thwidth">비고</th>
				<c:if test="${not empty sale_ord.sale_ord_note }">
					<td colspan="3">${sale_ord.sale_ord_note }</td>
				
				</c:if>
				<c:if test="${empty sale_ord.sale_ord_note }">
					<td colspan="3">내용이 없습니다.</td>
				</c:if>
			</tr>
			<tr>
				<th class="thwidth">담당사원</th>
				<td colspan="4">${sale_ord.emp_name}</td>
			</tr>
			<tr>
				<th class="thwidth">결재상태</th>
				<td colspan="3">${sale_ord.elec_comple }</td>
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
			<c:forEach items="${sale_ord.sale_oprodList }" var="oprod" >
			 	<tr>
					<td>${oprod.prod_no }</td>
					<td>${oprod.prod_name }</td>
					<td>${oprod.prod_color }</td>
					<td>${oprod.prod_size }</td>
					<td>${oprod.sale_oprod_qty }</td>
					<td>${oprod.sale_oprod_cost }</td>
				</tr>
			</c:forEach>
			</tbody>
		 </table>
	
	
	<!-- -------------------------------------------------------------------- -->
		
		
		<c:if test="${sale_ord.elec_comple eq '미결재' }">
			<button id="goToElecApproval" type="button" class="btn btn-primary" 
	<%-- 				onclick="location.href='<c:url value="/elecAuthorization/sangshin/sangshinInsert"/>';" --%>
				>결재신청</button> 
		</c:if>
		<button type="button" id="${sale_ord.sale_ord_code }" class="modifyBtn btn btn-primary"
				onclick="location.href='<c:url value="/salesTeam/orderManage/orderUpdate"/>';">수정</button> 
		
	
		</div>
	</div>
	
<!-- 주문서 코드를 전자결재로 보내기 위한 폼 -->
<form id="goToElectForm" method="get" action="${pageContext.request.contextPath }/elecAuthorization/sangshin/sangshinInsert">
	<input type="hidden" name="ordCode" value="${ordCode }" >
</form>
	
	
<script type="text/javascript">
	$("#goToElecApproval").on("click", function(){
		$("#goToElectForm").submit();
	});

	$(".modifyBtn").click(function(event){
		 var sale_ord_code = $(this).attr("id");
		 alert(sale_ord_code);
		 location.href= "${pageContext.request.contextPath}/salesTeam/orderManage/orderUpdate?order="+sale_ord_code;
	})
</script>
	
	
	
	
	
	
	
	
	
	
	
	