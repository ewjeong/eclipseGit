<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<h3>견적서 상세조회</h3>
		
		<br><br>
		<div>
			<div>

			
				<form action="${pageContext.request.contextPath }/salesTeam/estimateManage/estimateView">
					<input type="hidden" name="what" value="${param.what }" >
				</form>
					<table class="table table-bordered">
					<c:set value="${sale_est_listVO }" var="sale_est"/>
						<tr>
							<th class="thwidth">견적서코드</th>
							<td td colspan="4">${sale_est.sale_est_no }</td>
						</tr>
						<tr>	
							<th class="thwidth">견적일자</th>
							<td td colspan="4">${sale_est.sale_est_date }</td>
						</tr>
						<tr>
							<th class="thwidth">주문자</th>
							<td td colspan="4">${sale_est.cl_charger }</td>
						</tr>
						<tr>
							<th class="thwidth">담당사원</th>
							<td td colspan="4">${sale_est.emp_name }</td>
						</tr>
						<tr>
							<th class="thwidth">거래처명</th>
							<td colspan="4">${sale_est.cl_name }</td>
						</tr>
						<tr>
							<th class="thwidth">특기사항</th>
							<td colspan="4">${sale_est.sale_detail }</td>
						</tr>
					</table>
					<div class="col-xs-12 col-md-12">
					<table class="table">
						<thead>
						<tr>
							<th>상품코드</th>
							<th>상품명</th>
							<th>색상</th>
							<th>크기</th>
							<th>수량</th>
							<th>6개월치 평균 단가</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${sale_est_listVO.sale_eprodList }" var="sale_eprod">
							 	<tr>
									<td>${sale_eprod.prod_no }</td>
									<td>${sale_eprod.prod_name}</td>
									<td>${sale_eprod.prod_color}</td>
									<td>${sale_eprod.prod_size}</td>
									<td>${sale_eprod.sale_eprod_qty}</td>
									<td>${sale_est_listVO.ware_avg_cost.prod_cost }</td>
								</tr>
							</c:forEach>
							
						</tbody>
					 </table>	
			
			
			
			  
							
			<input type="hidden" class="saleEstNo" id="${sale_est.sale_est_no }"/>
<!-- 				<button type="button" class="btn btn-primary" -->
<%-- 					onclick="location.href='<c:url value="/salesTeam/estimateManage/estimateList"/>';" --%>
<!-- 				>뒤로가기</button> -->
				<button class="pdf btn btn-outline-primary">PDF출력</button>
<%-- 				<a class="btn btn-outline-primary col-xs-4 col-md-2" href="'<c:url value="/salesTeam/salesEstimatePDFView?what=${sale_est.sale_est_no }"/>'"> --%>

			
		</div>
	</div>
	
	<script type="text/javascript">
		$(".saleEstNo").click(function(event){
			var sale_est_no = $(this).attr("id");
			alert(sale_est_no);	
			location.href="${pageContext.request.contextPath}/salesTeam/estimateManage/estimateUpdate?est="+sale_est_no;
		})
		
		$(".pdf").click(function(event){
			var sale_est_no = $(".saleEstNo").attr("id");
			location.href="${pageContext.request.contextPath}/pdf/salesEstimatePDFView?what="+sale_est_no;
		})
		
	</script>
