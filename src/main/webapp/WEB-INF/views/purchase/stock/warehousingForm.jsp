<%-- 
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019.05.16   정은우      최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<style>
 	#errors{color: red;}
 	#icon{display:none; size: 10x; color: red; padding-top: 5px;}
</style>

<h3>입고 등록</h3>
<br>
<br>
<form name="searchForm" class="form-inline">
		<label for="searchPurEst">발주서 검색 </label>
	<div class="row">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="col-xs-12 col-md-12 form-group  input-group">
			<input type="text" id="inputOrdNo" name="inputOrdNo" class="form-control" readonly="readonly" value="${ware.pur_ord_code }"/> 
			<span class="input-group-btn searchWrap" >
				<button id = "searchOrdBtn" type="button" class="btn btn-default btnSearch"
                        data-toggle="modal" data-target="#exampleModal">
            	<i class="fa fa-search" ></i>
            	</button>
			</span>	
		</div>
   	</div>
</form>
	
<!-- 발주서 모달 -->
<form name="selectEst" action="${pageContext.request.contextPath}/purchasingTeam/stockManage/warehousingInsert" method="post">
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">발주서 리스트</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      	<div class="modal-body">
	          	<table class="table">
				   <thead>
				     <tr>
				     	<th></th>
						<th>발주요청일자</th>
						<th>발주코드</th>
						<th>거래처명</th>
					  </tr>
				    </thead>
				    <tbody id="listBody">
				    <c:choose>
	          		<c:when test="${not empty pagingVO }">
					  <c:set value="${pagingVO.dataList }" var="ordList" />
						<c:forEach items="${ordList }" var="order">
			   				<tr class="showView" id="${order.pur_ord_code }">
			   					<td><input type="radio" name="checkbox" value="${order.pur_ord_code }"/></td>
			   					<td>${order.pur_ord_date }</td>
								<td>${order.pur_ord_code }</td>
								<td>${order.cl_name }</td>
			   				</tr>
				        </c:forEach>
					  </c:when>
					  </c:choose>
					</tbody>
	          	</table>	
          	</div>
	      <div class="modal-footer">
	        <button type="button" id="insertBtn" class="btn btn-outline-primary">등록</button>
	        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">취소</button>
	      </div>
	    </div>
	  </div>
	</div>
</form>
<form method="post" action="${pageContext.request.contextPath}/purchasingTeam/stockManage/warehousingInsert">
	<br>
	<input type="hidden" name="pur_ord_code" class="form-control">

	<div class="col-xs-12 col-md-5" > 
		<label>거래처코드</label>
		<input type="text" name="cl_no" class="form-control " readonly="readonly">
	</div>
	<br>
	<div class="col-xs-12 col-md-5 " > 
		<label>거래처명</label> 
		<input type="text" name="cl_name" class="form-control " readonly="readonly" >
	</div>
	<br>
	<div class="col-xs-12 col-md-10">
		<table class="table">
			<thead>
			<tr>
				<th>품목분류명</th>
				<th>상품코드</th>
				<th>상품명</th>
				<th>상품크기</th>
				<th>상품색상</th>
				<th>입고수량</th>
				<th>단가</th>
			</tr>
			</thead>
			<tbody id="oprodTable">

			</tbody>
		</table>
	</div>
	<br>
	<div class="col-xs-12 col-md-5">
		<label>입고날짜</label>
		<input type="date" name="ware_date" id="estimateDate" class="form-control">
		<div class="row">
		&nbsp;&nbsp;&nbsp;&nbsp;<i id="icon" class="fas fa-exclamation-circle"></i>&nbsp;&nbsp;<form:errors id="errors" path="ware.ware_date" element="span" cssClass="error"/>
		</div>
	</div>
	<br>
	<div class="col-xs-12 col-md-5">
		<label>입고담당자</label> 
		<security:authentication property="principal" var="authEmp"/>
		<security:authorize access="isAuthenticated()">
			<input type="text" name="emp_name" value="${authEmp.emp_name}" class="form-control" readonly="readonly" />
			<input type="hidden" name="emp_id" value="${authEmp.emp_id}" class="form-control" />
		</security:authorize>
	</div>
    <br>
    <br>
	<div class="col-xs-4 col-md-7">
		<button type="submit" class="btn btn-primary col-xs-2 col-md-3">
		등록</button>	
	</div>
</form>

<script type="text/javascript">

// 	  //체크박스 하나만 선택
// 	  $(document).ready(function() {
// 		    //라디오 요소처럼 동작시킬 체크박스 그룹 셀렉터
// 		    $('input[type="checkbox"][name="checkbox"]').click(function(){
// 		        //클릭 이벤트 발생한 요소가 체크 상태인 경우
// 		        if ($(this).prop('checked')) {
// 		            //체크박스 그룹의 요소 전체를 체크 해제후 클릭한 요소 체크 상태지정
// 		            $('input[type="checkbox"][name="checkbox"]').prop('checked', false);
// 		            $(this).prop('checked', true);
// 		        }
// 		    });
// 	   });
	  
	  //체크박스 선택
	  $('#insertBtn').click(function(){
		  var check = $("input[type='radio']:checked").val();
		  $("#exampleModal").modal('hide');
		  $("#inputOrdNo").val(check);
	  
	  	  var inputOrdNo = check;

			$.ajax({
				url: "${pageContext.request.contextPath}/purchasingTeam/stockManage/warehousingInsert/"+inputOrdNo,
				method: "GET", 
				dataType: "json",
				success: function(resp){//resp는 purOrdList
					 var pur_ord_code = $("input[name='checkbox']").val();
					 //발주서 정보를 보여줌.
					 $("[name='pur_ord_code']").val(resp.pur_ord_code);
					 $("[name='prod_no']").val(resp.prod_no);
					 $("[name='item_name']").val(resp.item_name);
					 $("[name='prod_name']").val(resp.prod_name);
					 $("[name='prod_size']").val(resp.prod_size);
					 $("[name='prod_color']").val(resp.prod_color);
					 $("[name='cl_no']").val(resp.cl_no);
					 $("[name='cl_name']").val(resp.cl_name);
					 $("[name='pur_oprod_qty']").val(resp.pur_oprod_qty);
					 $("[name='pur_oprod_cost']").val(resp.pur_oprod_cost);
			    
					 var trTags = [];
					 
					 $(resp.pur_oprodList).each(function(idx, pur_oprod){
						 var tr = $("<tr>").prop("id", resp.pur_ord_code)
							.append(
								$("<td>").text(pur_oprod.item_name),	
								$("<td>").text(pur_oprod.prod_no),	
								$("<td>").text(pur_oprod.prod_name),
								$("<td>").text(pur_oprod.prod_size),
								$("<td>").text(pur_oprod.prod_color),
								$("<td>").text(pur_oprod.pur_oprod_qty),	
								$("<td>").text(pur_oprod.pur_oprod_cost)	
							);
		
						trTags.push(tr);
					 });
					 $("#oprodTable").html(trTags);
				}
			})
			 //db에서 꺼낸 데이터인 pur_ord_code와 , 사용자가 입력한 input태그의 내용이 일치하면 그에 해당하는 견적서 정보를 보여줘야한다. 
	  });
	  
	  
	 
	 <c:if test="${not empty message }">
		<c:if test="${message eq 'none' }">
			swal("입고 등록 실패", "입고 등록이 실패했습니다." , "warning");
			//에러메세지가 있으면 #icon을 show한다
				$("#icon").show();
				//다시 발주서 정보 셋팅해준다 
				//#inputOrdNo에 value="${ware.pur_ord_code } 셋팅해준걸 가져와서 다시 ajax처리
				var inputOrdNo = $("#inputOrdNo").val();

					$.ajax({
						url: "${pageContext.request.contextPath}/purchasingTeam/stockManage/warehousingInsert/"+inputOrdNo,
						method: "GET", 
						dataType: "json",
						success: function(resp){//resp는 purOrdList
							 var pur_ord_code = $("input[name='checkbox']").val();
							 //발주서 정보를 보여줌.
							 $("[name='pur_ord_code']").val(resp.pur_ord_code);
							 $("[name='prod_no']").val(resp.prod_no);
							 $("[name='item_name']").val(resp.item_name);
							 $("[name='prod_name']").val(resp.prod_name);
							 $("[name='prod_size']").val(resp.prod_size);
							 $("[name='prod_color']").val(resp.prod_color);
							 $("[name='cl_no']").val(resp.cl_no);
							 $("[name='cl_name']").val(resp.cl_name);
							 $("[name='pur_oprod_qty']").val(resp.pur_oprod_qty);
							 $("[name='pur_oprod_cost']").val(resp.pur_oprod_cost);
					    
							 var trTags = [];
							 
							 $(resp.pur_oprodList).each(function(idx, pur_oprod){
								 var tr = $("<tr>").prop("id", resp.pur_ord_code)
									.append(
										$("<td>").text(pur_oprod.item_name),	
										$("<td>").text(pur_oprod.prod_no),	
										$("<td>").text(pur_oprod.prod_name),
										$("<td>").text(pur_oprod.prod_size),
										$("<td>").text(pur_oprod.prod_color),
										$("<td>").text(pur_oprod.pur_oprod_qty),	
										$("<td>").text(pur_oprod.pur_oprod_cost)	
									);
				
								trTags.push(tr);
							 });
							 $("#oprodTable").html(trTags);
						}
					})
				
		</c:if>
	 </c:if>	  
		  

	 
</script>
