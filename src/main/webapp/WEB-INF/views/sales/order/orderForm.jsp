<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>


		<c:choose>
			<c:when test="${empty sale_ord_listVO.sale_ord_code  }">
				<h3>주문서 등록</h3>
			</c:when>
			<c:when test="${not empty sale_ord_listVO.sale_ord_code }">
				<h3>주문서  수정</h3>
			</c:when>
		</c:choose>
		
		<c:choose>
			<c:when test="${empty sale_ord_listVO.sale_ord_code  }">
				<form name="searchForm" class="form-inline">
					<div class="row">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label for="searchPurEst">견적서 검색 : &nbsp;&nbsp;</label>
						<div class="col-xs-12 col-md-12 form-group input-group">
							<input type="text" id="sale_est_no" name="sale_est_no" class="form-control" readonly="readonly" /> 
							<span class="input-group-btn searchWrap" >
								<button id = "searchOrdBtn" type="button" class="btn btn-default btnSearch"
				                        data-toggle="modal" data-target="#exampleModal">
				            	<i class="fa fa-search" ></i>
				            	</button>
							</span>	
						</div>
				   	</div>
				</form>
			</c:when>
			<c:when test="${not empty sale_ord_listVO.sale_ord_code }">
			
			</c:when>
		</c:choose>



<!-- 모달  -->
<form:form name="selectEst" action="${pageContext.request.contextPath}/purchasingTeam/stockManage/warehousingInsert" method="post">
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">견적서 리스트</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      	<div class="modal-body">
	          	<table class="table">
				   <thead>
				     <tr>
				     	<th></th>
						<th>견적일자</th>
						<th>견적서번호</th>
						<th>담당자명</th>
						<th>거래처명</th>
					  </tr>
				    </thead>
				    <tbody id="listBody">
				    <c:choose>
	          		<c:when test="${not empty pagingVO }">
					  <c:set value="${pagingVO.dataList }" var="estList" />
						<c:forEach items="${estList }" var="est">
			   				<tr class="showView" id="${est.sale_est_no }">
			   					<td><input type="checkbox" name="checkbox" value="${est.sale_est_no }"/></td>
			   					<td>${est.sale_est_date }</td>
								<td>${est.sale_est_no }</td>
								<td>${est.emp_name }</td>
								<td>${est.cl_name }</td>
			   				</tr>
				        </c:forEach>
					  </c:when>
					  </c:choose>
					</tbody>
	          	</table>	
          	</div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
	        <button type="button" id="insertEstBtn" class="btn btn-primary">등록</button>
	      </div>
	    </div>
	  </div>
	</div>
</form:form>











<form:form commandName="saleOrd" id="orderForm" method="post">

	<c:set value="${sale_ord_listVO }" var="ord_list"></c:set>
	
	<input type="hidden" name="sale_est_no" value="${param.what }">
	
	<div class="col-xs-12 col-md-10">
		<label>주문자명</label> 
		<c:choose>
			<c:when test="${empty sale_ord_listVO.sale_ord_code  }">
				<input type="text" readonly="readonly" name="cl_charger" class="form-control" value="${sale_est_listVO.cl_charger }"/>
			</c:when>
			<c:when test="${not empty sale_ord_listVO.sale_ord_code }">
				<input type="text"  name="cl_charger" class="form-control" readonly="readonly" value="${ord_list.cl_charger }"/>  
			</c:when>
		</c:choose>
		
		
	</div>
	<div class="col-xs-12 col-md-10">

	<label>주문자 전화번호</label> 
		<c:choose>
			<c:when test="${empty sale_ord_listVO.sale_ord_code  }">
				<input type="text" readonly="readonly" name="cl_tel" class="form-control" value="${sale_est_listVO.cl_tel }">
			</c:when>
			<c:when test="${not empty sale_ord_listVO.sale_ord_code }">
				<input type="text" name="cl_charger" class="form-control" readonly="readonly" value="${ord_list.cl_tel }"/>  
			</c:when>
		</c:choose>
	</div>
	<div class="col-xs-12 col-md-10">
		<label>수령자명</label> 
		<c:choose>
			<c:when test="${empty sale_ord_listVO.sale_ord_code  }">
				<input type="text" readonly="readonly" name="cl_receive" class="form-control" value="${sale_est_listVO.cl_receive }">
			</c:when>
			<c:when test="${not empty sale_ord_listVO.sale_ord_code }">
				<input type="text" name="cl_receive" class="form-control" readonly="readonly" value="${ord_list.cl_receive }"/>  
			</c:when>
		</c:choose>

	</div>
	<br>
	<div class="col-xs-12 col-md-10">
		<label for="validationServer05">주소</label> &nbsp;&nbsp;&nbsp;
		<c:choose>
			<c:when test="${empty sale_ord_listVO.sale_ord_code  }">
				<input type="text" readonly="readonly" name="cl_add1" class="form-control" value="${sale_est_listVO.cl_add1 }">
			</c:when>
			<c:when test="${not empty sale_ord_listVO.sale_ord_code }">
				<input type="text" name="cl_add1" class="form-control" readonly="readonly" value="${ord_list.cl_add1 }"/>  
			</c:when>
		</c:choose>
		
		
	</div>
	<div class="col-xs-12 col-md-10">
		<label for="validationServer05">상세주소</label> &nbsp;&nbsp;&nbsp;
		<c:choose>
			<c:when test="${empty sale_ord_listVO.sale_ord_code  }">
				<input type="text" readonly="readonly" name="cl_add2" class="form-control" value="${sale_est_listVO.cl_add2 }">
			</c:when>
			<c:when test="${not empty sale_ord_listVO.sale_ord_code }">
				<input type="text" name="cl_add2" class="form-control" readonly="readonly" value="${ord_list.cl_add2 }"/>  
			</c:when>
		</c:choose>

	</div>
	<br>
	<div class="col-xs-12 col-md-10">
		<table class="table">
			<thead>
			<tr>
				<th>상품코드</th>
				<th>상품명</th>
				<th>크기</th>
				<th>단가</th>
				<th>수량</th>
			</tr>
			</thead>
			
			<tbody id="eProdTable">
				<c:if test="${not empty sale_ord_listVO.sale_ord_code }">
					<c:forEach var="sale_oprod" items="${sale_ord_listVO.sale_oprodList }" varStatus="idx">
						<th><input type="hidden" name="sale_oprodList[${idx.index }].sale_oprod_no"   readonly="readonly" class="form-control" value="${sale_oprod.sale_oprod_no }"></th>
						<th><input type="text" name="sale_oprodList[${idx.index }].prod_no"   readonly="readonly" class="form-control" value="${sale_oprod.prod_no }"></th>
						<th><input type="text" name="prod_name" readonly="readonly"  class="form-control" value="${sale_oprod.prod_name }"></th>
						<th><input type="text" name="prod_size" readonly="readonly"  class="form-control" value="${sale_oprod.prod_size }"></th>
						<th><input type="number" name="sale_oprodList[${idx.index }].sale_oprod_cost" class="form-control" value="${sale_oprod.sale_oprod_cost }"></th>
						<th><input type="number" name="sale_oprodList[${idx.index }].sale_oprod_qty"  class="form-control" value="${sale_oprod.sale_oprod_qty }"></th>
					</c:forEach>
				</c:if>
	
			</tbody>
		</table>
<!-- 		<div class="row"> -->
<%-- 			<i id="icon" class="fas fa-exclamation-circle"></i>&nbsp;&nbsp;<form:errors id="errors" path="${sale_oprod.sale_ord_cost }" element="span" cssClass="error"/> --%>
<!-- 		</div> -->
<!-- 		<div class="row"> -->
<%-- 			<i id="icon" class="fas fa-exclamation-circle"></i>&nbsp;&nbsp;<form:errors id="errors" path="${sale_oprod.sale_ord_qty }" element="span" cssClass="error"/> --%>
<!-- 		</div> -->
	</div>
	<div class="col-xs-12 col-md-10">
		<c:choose>
			<c:when test="${empty sale_ord_listVO.sale_ord_code  }">

				<select name="payment" class="form-control">
					<option>현금</option>
					<option>카드</option>
				</select>
			</c:when>
			<c:when test="${not empty sale_ord_listVO.sale_ord_code }">
				<c:if test="${sale_ord_listVO.payment eq 1 }">
					<select name="payment" class="form-control">
						<option>현금</option>
						<option selected="selected">카드</option>
					</select>
				</c:if>
				<c:if test="${sale_ord_listVO.payment eq 0 }">
					<select name="payment" class="form-control">
						<option selected="selected">현금</option>
						<option>카드</option>
					</select>
				</c:if>
			</c:when>
		</c:choose>
	</div>
	<div class="col-xs-12 col-md-10">
		<label>거래처명</label> 
		<c:choose>
			<c:when test="${empty sale_ord_listVO.sale_ord_code  }">
				<input name="cl_name" type="text" readonly="readonly"
					class="form-control" value="${sale_est_listVO.cl_name }">
			</c:when>
			<c:when test="${not empty sale_ord_listVO.sale_ord_code }">
				<input type="text" name="cl_name"  readonly="readonly" class="form-control" value="${ord_list.cl_name }"/>  
			</c:when>
		</c:choose>
		
			
	</div>
	<div class="col-xs-12 col-md-10">
		<label>비고</label> 
		<c:choose>
			<c:when test="${empty sale_ord_listVO.sale_ord_code  }">
				<input type="text" name="sale_ord_note" class="form-control">
			</c:when>
			<c:when test="${not empty sale_ord_listVO.sale_ord_code }">
				<input type="text" name="sale_ord_note" class="form-control" value="${ord_list.sale_ord_note }"/>  
			</c:when>
		</c:choose>

	</div>
	<br>
	<div class="col-xs-4 col-md-6">
		<c:choose>
			<c:when test="${empty sale_ord_listVO.sale_ord_code  }">
				<button id="insertOrdBtn" type="submit" class="btn btn-primary col-x0s-2 col-md-3">등록</button>
			</c:when>
			<c:when test="${not empty sale_ord_listVO.sale_ord_code }">
				<button id="modifyOrdBtn" type="submit" class="btn btn-primary col-x0s-2 col-md-3">수정</button>  
			</c:when>
		</c:choose>
	
	
		
	</div>
	
	

</form:form>
<script>
	

	
	
// 	<!-- date picker를 사용하기 위한 script 설정 -->
	  $( "#estimateDate" ).datepicker({
	         changeMonth: true, 
	         dateFormat: 'yy-mm-dd',
	         dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
	         dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
	         monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	         monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	  });
		  
	  
	  $(document).ready(function() {
		    //라디오 요소처럼 동작시킬 체크박스 그룹 셀렉터
		    $('input[type="checkbox"][name="checkbox"]').click(function(){
		        //클릭 이벤트 발생한 요소가 체크 상태인 경우
		        if ($(this).prop('checked')) {
		            //체크박스 그룹의 요소 전체를 체크 해제후 클릭한 요소 체크 상태지정
		            $('input[type="checkbox"][name="checkbox"]').prop('checked', false);
		            $(this).prop('checked', true);
		        }
		    });
	   });
		  
	//비동기처리
	$('#insertEstBtn').click(function(){
		
		
		
		  var check = $("input[type='checkbox']:checked").val();
		  $("#exampleModal").modal('hide');
		  $("#inputOrdNo").val(check);
			
			var inputEstNo = check;

		
			
			$.ajax({
				url: "${pageContext.request.contextPath}/salesTeam/orderManage/orderInsert/"+inputEstNo,
// 				data: jsonData, //
				method: "GET" , //객체를 보낼 땐  -> 바디에 담겨서 가야하므로 post
// 				contentType: "application/json;charset=UTF-8", //보낼 때
				dataType: "json",
				success: function(resp){
// 					resp.sale_est_listVO
// 					resp.sale_eprodList
					var sale_est_no = $("input[name='checkbox']").val();
					
						 //견적서 정보를 보여준다.
						 $("[name='sale_est_no']").val(resp.sale_est_no)
						 $("[name='cl_charger']").val(resp.cl_charger);
						 $("[name='cl_tel']").val(resp.cl_tel);
						 $("[name='cl_receive']").val(resp.cl_receive);
						 $("[name='cl_add1']").val(resp.cl_add1);
						 $("[name='cl_add2']").val(resp.cl_add2);
						 $("[name='cl_name']").val(resp.cl_name);
					 	
						 var trTags = [];
						
						 
						 //has many 관계... sale_oprod_qty , sale_oprod_cost, prod_no는 다른 테이블에 있는 has many 관계임
						 //그래서 name 값을 배열로 줘야한다 (1:N 관계)
						 $(resp.sale_eprodList).each(function(idx, sale_eprod){
							var tr = $("<tr>").prop("id", resp.sale_est_no)
												.append(
													$("<td>").append(
														$("<input>").attr({
															type: "number",
															class: "form-control",
															name: "sale_oprodList["+idx+"].prod_no"
														}).val(sale_eprod.prod_no)
													),	
													$("<td>").text(sale_eprod.prod_name),	
													$("<td>").text(sale_eprod.prod_size),	
													$("<td>").append(
														$("<input>").attr({
															type: "number",
															class: "form-control",
															name: "sale_oprodList["+idx+"].sale_oprod_cost"
														}).val(sale_eprod.sale_eprod_cost)
													).append(
														$("<span>")	
													)
													,	
													$("<td>").append(
														$("<input>").attr({
															type: "number",
															class: "form-control",
															name: "sale_oprodList["+idx+"].sale_oprod_qty"
														}).val(sale_eprod.sale_eprod_qty)
													)	
												);
							trTags.push(tr);
						});
						 $("#eProdTable").html(trTags);
						
				}
			})
			 //db에서 꺼낸 데이터인 sale_est_no와 , 사용자가 입력한 input태그의 내용이 일치하면 그에 해당하는 견적서 정보를 보여줘야한다. 
			
		  });
	
		
	
	
// 	<c:set value="0" var="total"/>
// 		<td colspan="4">
// 			<c:forEach items="${sale_ord.sale_oprodList }" var="oprod">
// 				<c:set value="${total + oprod.sale_oprod_qty * oprod.sale_oprod_cost }" var="total"/>
// 			</c:forEach>
			
// 			<c:out value="${total }"/>
// 		</td>
	
</script>