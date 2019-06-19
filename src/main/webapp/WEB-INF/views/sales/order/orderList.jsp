<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <style>
    	.fa-calendar-alt{
    		line-height: 38px; margin-left: 10px;
    	}
    	.fa-edit{
    		color: white;
    	}
    </style>
    
	<h3>주문리스트 조회</h3>
	<br><br>

		<div class="row">
				<!-- 결제상태 처리시 -->
				<form id="searchForm" name="searchForm" class="form-inline offset-md-8">
						<input type="hidden" name="page" />
					
				<div class="col-xs-12 col-md-9 offset-md-4 input-group">
					<select name="searchType" id="searchType" class="form-control">
						<option value="all" ${pagingVO.searchType eq 'all' ? "selected":"" }>전체</option>
						<option value="sale_ord_code" ${pagingVO.searchType eq 'sale_ord_code' ? "selected":"" }>주문코드</option>
						<option value="cl_charger" ${pagingVO.searchType eq 'cl_charger' ? "selected":"" }>주문자명</option>
						<option value="prod_name" ${pagingVO.searchType eq 'prod_name' ? "selected":"" }>상품명</option>
					</select>
					&nbsp;&nbsp;
					
					<input class="form-control col-md-8" id="searchWord" type="text" name="searchWord" value="${pagingVO.searchWord }"/> 
					<span class="input-group-btn searchWrap">
						<button class="btn btn-default btnSearch" id="searchBtn" type="submit">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
				
				</form>
			
		</div>
		
		
		<div style="padding-top: 10px;">
		
		
		
		
		<table class="table">
			<thead>
				<tr>
					<td>주문코드</td>
					<td>주문일자</td>
					<td>주문자</td>
					<td>수령자</td>
					<td>담당사원</td>
					<td>결재상태</td>
				</tr>
			</thead>
			<tbody id="listBody">
					<c:set var="orderList" value="${pagingVO.dataList }" />
					<c:if test="${not empty pagingVO }">
						<c:forEach items="${orderList }" var="order">
							<tr class="showView" id="${order.sale_ord_code }">
								<td>${order.sale_ord_code }</td>
								<td>${order.sale_ord_date }</td>
								<td>${order.cl_charger }</td>
								<td>${order.cl_receive }</td>
								<td>${order.emp_name }</td>
								<td>${order.elec_comple }</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty pagingVO }">
						<tr>
							<td colspan="5">
								조건에 맞는 주문 내역이 없습니다.
							</td>
						</tr>
					</c:if>
					<tfoot>
						<tr>
							<td id="pagingArea" colspan="5">
									${pagingVO.pagingHTML }
							</td>
						</tr>
					</tfoot>
					
			</tbody>
	
		</table>
		<form action="${pageContext.request.contextPath }/salesOrdListExcel" method="post">
			<input type="hidden" id="hiddenType" name="hiddenType">
			<input type="hidden" id="hiddenWord" name="hiddenWord">
			<button type="submit" class="btn btn-outline-primary col-xs-4 col-md-2" >엑셀 출력</button>
		</form>

		</div>
		
		
		
		<script type="text/javascript">
		 	
		$( "#startDate" ).datepicker({
		         changeMonth: true, 
		         dateFormat: 'yy-mm-dd',
		         dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
		         dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
		         monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		         monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		  	});
			
		 
			function paging(page) {
				$("#searchForm").find("input[name='page']").val(page);
				$("#searchForm").submit();
			}
		
			$("#searchBtn").on("click", function(event) {
//		 		$("#searchForm").find("input[name='page']").val("1"); //페이지를 1로 설정 (검색했을 때 1페이지가 나오게)
//		 		e.preventDefault();
				$("#searchForm").submit();
			});
		 
			$("#searchForm").on("submit", function() {
				var datas = $(this).serialize(); //class가 search 인 (searchType과, searchWord를 serialize())
				console.log(datas);
				$.ajax({
					url : "${pageContext.request.contextPath}/salesTeam/orderManage/orderLists",
					data : datas,
					dataType: "json",	
					success : function(resp) { //컨트롤러로부터 받는 데이터 (resp)
						console.log(resp.dataList);// item
						console.log(resp);
						dataListModify(resp);
					},
					error : function(errorResp) {
						console.log(errorResp.status);
						swal("오류");
					}
				});
				return false;
			});


			function dataListModify(resp) {
				var orderList = resp.dataList;
				var pagingHTML = resp.pagingHTML;
				var trTags = [];
				$(orderList).each(function(idx, order) {
							var tr = $("<tr>").prop("id", order.sale_ord_code).append(
										$("<td>").text(order.sale_ord_code),
										$("<td>").text(order.sale_ord_date),
										$("<td>").text(order.cl_charger),
										$("<td>").text(order.cl_receive),
										$("<td>").text(order.emp_name),
										$("<td>").text(order.elec_comple)
										
							);
							
							trTags.push(tr);
						});
				$("#listBody").html(trTags);
				$("#pagingArea").html(resp.pagingHTML);
			}
			
// 			paging(1);
		 
		$("#listBody").on("click","tr",function(){
			var what = $(this).prop("id");
			location.href="${pageContext.request.contextPath}/salesTeam/orderManage/orderView?what="+what;
		});
		 
		
		 <c:if test="${not empty message }">
			swal("등록완료", "주문서 등록이 완료되었습니다.", "success");
		</c:if>
	
	
		  
	</script>
