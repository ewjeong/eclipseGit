<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019.05.31   정은우      최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form" %>

<style>
 	#errors{color: red;}
 	#icon{display:none; size: 10x; color: red; padding-top: 5px;}
 	th{text-align: center;}
 	td{text-align: center;}
 	#errorRow{text-align: center;}
</style>

<h3>견적서 등록</h3>
<br>
<br>
<form name="searchForm" class="form-inline">
	<div class="row">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
		<label for="searchPurEr">견적요청서 검색 : &nbsp;&nbsp;</label>
		<div class="col-xs-12 col-md-12 form-group input-group">
			<input type="text" id="inputErNo" name="inputErNo" class="form-control" readonly="readonly" value="${purEstVO.pur_er_no }"> 
  			<span class="input-group-btn searchWrap" >
				<button id = "searchErBtn" type="button" class="btn btn-default btnSearch"
                        data-toggle="modal" data-target="#exampleModal">
            	<i class="fa fa-search" ></i>
            	</button>
			</span>	
		</div>
   	</div>
</form>	
<!-- 모달  -->
<form name="selectEr" action="${pageContext.request.contextPath}/purchasingTeam/estimateManage/estimateInsert" method="post">
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">견적요청서 리스트</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      	<div class="modal-body">
	          	<table class="table">
				   <thead>
				     <tr>
				     	<th></th>
						<th>견적요청일자</th>
						<th>견적요청서번호</th>
						<th>거래처명</th>
					  </tr>
				    </thead>
				    <tbody id="listBody">
				    <c:choose>
	          		<c:when test="${not empty pagingVO }">
					  <c:set value="${pagingVO.dataList }" var="erList" />
						<c:forEach items="${erList }" var="er">
			   				<tr class="showView" id="${er.pur_er_no }">
			   					<td><input type="checkbox" name="checkbox" value="${er.pur_er_no }"/></td>
			   					<td>${er.pur_er_date }</td>
								<td>${er.pur_er_no }</td>
								<td>${er.cl_name }</td>
			   				</tr>
				        </c:forEach>
					  </c:when>
					  </c:choose>
					</tbody>
	          	</table>	
          	</div>
	      <div class="modal-footer">
	        <button type="button" id="insertErBtn" class="btn btn-outline-primary">등록</button>
	        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">취소</button>
	      </div>
	    </div>
	  </div>
	</div>
</form>

<form method="post" action="${pageContext.request.contextPath}/purchasingTeam/estimateManage/estimateInsert">
	<br>
	<input type="hidden" name="pur_est_no" class="form-control"/>

	<div class="col-xs-12 col-md-5" > 
		<label>견적요청서번호</label> 
		<input type="text" name="pur_er_no" class="form-control " readonly="readonly">
	</div>
	<br>
	<div class="col-xs-12 col-md-5" > 
		<label>주문자</label> 
		<input type="text" name="com_name" class="form-control " readonly="readonly">
	</div>
	<br>
	<div class="col-xs-12 col-md-5" > 
		<label>주문자 전화번호</label> 
		<input type="text" name="com_tel" class="form-control " readonly="readonly">
	</div>
	<br>
	<div class="col-xs-12 col-md-5 " > 
		<label>주문자주소</label> 
		<input type="text" name="com_add1" class="form-control " readonly="readonly">
	</div>
	<br>
	<div class="col-xs-12 col-md-5 " > 
		<label>주문자상세주소</label> 
		<input type="text" name="com_add2" class="form-control " readonly="readonly">
	</div>
	<br>
	<div class="col-xs-12 col-md-5 " > 
		<label>거래처코드</label> 
		<input type="text" name="cl_no" class="form-control " readonly="readonly">
	</div>
	<br>
	<div class="col-xs-12 col-md-5 " > 
		<label>거래처명</label> 
		<input type="text" name="cl_name" class="form-control " readonly="readonly">
	</div>
	<br>
	<div class="col-xs-12 col-md-5 " > 
		<label>거래처전화번호</label> 
		<input type="text" name="cl_tel" class="form-control " readonly="readonly">
	</div>
	<br>
	<div class="col-xs-12 col-md-12">
		<table class="table">
			<thead>
			<tr>
				<th>
				품목<br>
				분류명
				</th>
				<th>
				상품<br>
				코드
				</th>
				<th>
				상품명<br>
				<br>
				</th>
				<th>
				상품<br>
				크기
				</th>
				<th>
				상품<br>
				색상
				</th>
				<th>
				수량<br>
				<br>
				</th>
				<th>
				단가
				<div id="errorRow" class="offset-md-1">
					<form:errors id="errors" path="purEstVO.pur_eprod_cost" element="span" cssClass="error" />
				</div>
				</th>
			</tr>
			</thead>
			<tbody id="eprodTable">
				
			</tbody>
		</table>
	</div>
	<br>
	<div class="col-xs-12 col-md-5">
		<label>견적서등록일자</label> 
		<input type="date" name="pur_est_date" id="estimateDate" class="form-control">
		<div class="row">
		&nbsp;&nbsp;&nbsp;&nbsp;<i id="icon" class="fas fa-exclamation-circle"></i>
		&nbsp;&nbsp;<form:errors id="errors" path="purEstVO.pur_est_date" element="span" cssClass="error" />
		</div>
	</div>
	<br>
	<div class="col-xs-12 col-md-5">
	<label>주문처리</label> 
	<select name="payment" class="form-control">
		<option>현금</option>
		<option>카드</option>
	</select>
	</div>
	<br>
	<div class="col-xs-12 col-md-5">
		<label>발주담당자</label>
		<security:authentication property="principal" var="authEmp"/>
		<security:authorize access="isAuthenticated()">
			<input type="text" name="emp_name" value="${authEmp.emp_name}" class="form-control" readonly="readonly" />
			<input type="hidden" name="est_emp_id" value="${authEmp.emp_id}" class="form-control" />
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
	
	  //체크박스 하나만 선택
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
	  
	  //체크박스 선택
	  $('#insertErBtn').click(function(){
		  var check = $("input[type='checkbox']:checked").val();
		  $("#exampleModal").modal('hide');
		  $("#inputErNo").val(check);
	  
	  	  var inputErNo = check;

			$.ajax({
				url: "${pageContext.request.contextPath}/purchasingTeam/estimateManage/estimateInsert/"+inputErNo,
				method: "GET", 
				dataType: "json",
				success: function(resp){//resp는 purErList
					 var pur_er_no = $("input[name='checkbox']").val();
					 //견적요청서 정보를 보여줌.
						 $("[name='pur_est_no']").val(resp.pur_est_no);
						 $("[name='pur_er_no']").val(resp.pur_er_no);
						 $("[name='com_name']").val(resp.com_name);
						 $("[name='com_tel']").val(resp.com_tel);
						 $("[name='com_add1']").val(resp.com_add1);
						 $("[name='com_add2']").val(resp.com_add2);
						 $("[name='cl_no']").val(resp.cl_no);
						 $("[name='cl_name']").val(resp.cl_name);
						 $("[name='cl_tel']").val(resp.cl_tel);
						 $("[name='item_code']").val(resp.item_code);
						 $("[name='prod_no']").val(resp.prod_no);
						 $("[name='prod_name']").val(resp.prod_name);
						 $("[name='prod_size']").val(resp.prod_size);
						 $("[name='prod_color']").val(resp.prod_color);
						 $("[name='pur_erprod_qty']").val(resp.pur_erprod_qty);
			    
					 var trTags = [];
					 
					 $(resp.pur_er_prodList).each(function(idx, pur_erprod){
						 var tr = $("<tr>").prop("id", resp.pur_er_prod_no)
							.append(
								$("<td>").text(pur_erprod.item_name),	
								$("<td>").append(
												$("<input>").attr({
													type: "text",
													class: "form-control",
													readonly:"readonly",
													name: "pur_eprodList["+idx+"].prod_no"
												}).val(pur_erprod.prod_no)
											),
								$("<td>").text(pur_erprod.prod_name),
								$("<td>").text(pur_erprod.prod_size),
								$("<td>").text(pur_erprod.prod_color),
								$("<td>").append(
												$("<input>").attr({
													type: "text",
													class: "form-control",
													readonly:"readonly",
													name: "pur_eprodList["+idx+"].pur_eprod_qty"
												}).val(pur_erprod.pur_erprod_qty)
											),	
								$("<td>").append(
												$("<input>").attr({
													type: "text",
													class: "form-control",
													name: "pur_eprodList["+idx+"].pur_eprod_cost"
												})
											)	
							);
		
						trTags.push(tr);
					 });
					 $("#eprodTable").html(trTags);
				}
			});
	  });
	  
	  <c:if test="${not empty message }">
		<c:if test="${message eq 'none' }">
			swal("견적서 등록 실패", "견적서 등록이 실패했습니다." , "warning");
			//에러메세지가 있으면 #icon을 show한다
		    $("#icon").show();
			
		    var inputErNo = $("#inputErNo").val();

			$.ajax({
				url: "${pageContext.request.contextPath}/purchasingTeam/estimateManage/estimateInsert/"+inputErNo,
				method: "GET", 
				dataType: "json",
				success: function(resp){//resp는 purErList
					 var pur_er_no = $("input[name='checkbox']").val();
					 //견적요청서 정보를 보여줌.
						 $("[name='pur_est_no']").val(resp.pur_est_no);
						 $("[name='pur_er_no']").val(resp.pur_er_no);
						 $("[name='com_name']").val(resp.com_name);
						 $("[name='com_tel']").val(resp.com_tel);
						 $("[name='com_add1']").val(resp.com_add1);
						 $("[name='com_add2']").val(resp.com_add2);
						 $("[name='cl_no']").val(resp.cl_no);
						 $("[name='cl_name']").val(resp.cl_name);
						 $("[name='cl_tel']").val(resp.cl_tel);
						 $("[name='item_code']").val(resp.item_code);
						 $("[name='prod_no']").val(resp.prod_no);
						 $("[name='prod_name']").val(resp.prod_name);
						 $("[name='prod_size']").val(resp.prod_size);
						 $("[name='prod_color']").val(resp.prod_color);
						 $("[name='pur_erprod_qty']").val(resp.pur_erprod_qty);
			    
					 var trTags = [];
					 
					 $(resp.pur_er_prodList).each(function(idx, pur_erprod){
						 var tr = $("<tr>").prop("id", resp.pur_er_prod_no)
							.append(
								$("<td>").text(pur_erprod.item_name),	
								$("<td>").append(
												$("<input>").attr({
													type: "text",
													class: "form-control",
													readonly:"readonly",
													name: "pur_eprodList["+idx+"].prod_no"
												}).val(pur_erprod.prod_no)
											),
								$("<td>").text(pur_erprod.prod_name),
								$("<td>").text(pur_erprod.prod_size),
								$("<td>").text(pur_erprod.prod_color),
								$("<td>").append(
												$("<input>").attr({
													type: "text",
													class: "form-control",
													readonly:"readonly",
													name: "pur_eprodList["+idx+"].pur_eprod_qty"
												}).val(pur_erprod.pur_erprod_qty)
											),	
								$("<td>").append(
												$("<input>").attr({
													type: "text",
													class: "form-control",
													name: "pur_eprodList["+idx+"].pur_eprod_cost"
												})
											)	
							);
		
						trTags.push(tr);
					 });
					 $("#eprodTable").html(trTags);
				}
			});
			
			
			
		</c:if>
	  </c:if>
	  
	  
</script>


						 
 