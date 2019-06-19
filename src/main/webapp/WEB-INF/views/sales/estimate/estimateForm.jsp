<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
	
	<c:choose>
		<c:when test="${empty sale_est_listVO.sale_est_no }">
			<h3>견적서 등록</h3>
				<!-- 거래처 검색하는 버튼 -->
				<div class="row offset-md-6 paddingNone" >
				<form name="clientSearchForm" class="form-inline">
					<div class="row">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label for="searchClient">거래처 검색  &nbsp;&nbsp;</label>
						<div class="col-xs-12 col-md-12 form-group input-group">
							<input type="text" id="cl_input"  class="form-control" readonly="readonly" /> 
							<span class="input-group-btn searchWrap" >
								<button id = "clientBtn" type="button" class="btn btn-default btnSearch"
				                        data-toggle="modal" data-target="#myModal">
				            	<i class="fa fa-search" ></i>
				            	</button>
							</span>	
						</div>
				   	</div>
				   	&nbsp;&nbsp;&nbsp;
				 </form>
				  <!-- 제품 검색하는 버튼  -->
		<form name="prodSearchForm" class="form-inline">
			<div class="row">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label for="searchPurEst">제품 검색 &nbsp;&nbsp;</label>
				<div class="col-xs-12 col-md-12 form-group input-group">
					<input type="text" id="prod_input" name="prod_input" class="form-control" readonly="readonly" /> 
					<span class="input-group-btn searchWrap" >
						<button id = "prodBtn" type="button" class="btn btn-default btnSearch"
		                        data-toggle="modal" data-target="#searchPurEst">
		            	<i class="fa fa-search" ></i>
		            	</button>
					</span>	
				</div>
		   	</div>
		</form>
		</div>
		<br>
		
		<!-- 바디  -->
	<form action="${pageContext.request.contextPath }/salesTeam/estimateManage/estimateView">
		<input type="hidden" name="what" value="${param.what }" >
		<input type="hidden" name="hidden_cl_no" class="form-control">
	</form>
		<form id="estForm" method="post">
			<table class="table table-bordered">
				<tr>
					<th class="thwidth">거래처코드</th>
					<td colspan="4"><input readonly="readonly" required="required" class="form-control" type="text" name="cl_no"></td>
				</tr>
				<tr>
					<th class="thwidth">거래처명</th>
					<td colspan="4"><input readonly="readonly" required="required" class="form-control" type="text" name="cl_name"></td>
				</tr>
				<tr>
					<th class="thwidth">주문자</th>
					<td td colspan="4"><input readonly="readonly" required="required" class="form-control" type="text" name="cl_charger"></td>
				</tr>
				
				<tr>
					<th class="thwidth">견적담당사원</th>
						
					<td td colspan="4">
						<security:authentication property="principal" var="authEmp"/>
						<security:authorize access="isAuthenticated()">
							<input type="text" name="emp_name" required="required" value="${authEmp.emp_name}" class="form-control" readonly="readonly" />
							<input type="hidden" name="emp_id" required="required" value="${authEmp.emp_id}" class="form-control" />
						</security:authorize>
					</td>
				</tr>
				<tr>
					<th class="thwidth">특기사항</th>
					<td td colspan="4"><input class="form-control" type="text" name="sale_detail"></td>
				</tr>
			</table>
			<div class="col-xs-12 col-md-12">
			<table class="table">
				<thead id="prodListHead">
				<tr>
					<th>상품코드</th>
					<th>품목명</th>
					<th>상품명</th>
					<th>색상</th>
					<th>크기</th>
					<th>수량</th>
					<th>6개월치 평균 단가</th>
				</tr>
				</thead>
				<tbody id="prodListBody">
					
					
				</tbody>
			 </table>	
		
		</div>
  
   <br>
   <div class="col-xs-4 col-md-6">
      <button type="submit" class="btn btn-primary col-xs-2 col-md-3">등록</button>
   </div>
</form>
		
		
		</c:when>
	</c:choose>	
	
	
	
	<!--  ////////////////////////////////////////////////////////////////////////////////////////////////////////   -->
	
	<!--견적서 수정   -->
<%-- 	<c:choose> --%>
<%-- 		<c:when test="${not empty sale_est_listVO.sale_est_no }"> --%>
<!-- 			<h3>견적서 수정</h3> -->
<!-- 			<form name="clientSearchForm" class="form-inline"> -->
<!-- 				<div class="row"> -->
<!-- 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 					<label for="searchClient">거래처 검색  &nbsp;&nbsp;</label> -->
<!-- 					<div class="col-xs-12 col-md-12 form-group input-group"> -->
<!-- 						<input type="text" id="cl_input"  class="form-control" readonly="readonly" />  -->
<!-- 						<span class="input-group-btn searchWrap" > -->
<!-- 							<button id = "clientBtn" type="button" class="btn btn-default btnSearch" -->
<!-- 			                        data-toggle="modal" data-target="#myModal"> -->
<!-- 			            	<i class="fa fa-search" ></i> -->
<!-- 			            	</button> -->
<!-- 						</span>	 -->
<!-- 					</div> -->
<!-- 			   	</div> -->
<!-- 			   	&nbsp;&nbsp;&nbsp; -->
<!-- 			 </form> -->
			 
<!-- 			 	 제품 검색하는 버튼  -->
<!-- 		<form name="prodSearchForm" class="form-inline"> -->
<!-- 			<div class="row"> -->
<!-- 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 				<label for="searchPurEst">제품 검색 &nbsp;&nbsp;</label> -->
<!-- 				<div class="col-xs-12 col-md-12 form-group input-group"> -->
<!-- 					<input type="text" id="prod_input" name="prod_input" class="form-control" readonly="readonly" />  -->
<!-- 					<span class="input-group-btn searchWrap" > -->
<!-- 						<button id = "prodBtn" type="button" class="btn btn-default btnSearch" -->
<!-- 		                        data-toggle="modal" data-target="#searchPurEst"> -->
<!-- 		            	<i class="fa fa-search" ></i> -->
<!-- 		            	</button> -->
<!-- 					</span>	 -->
<!-- 				</div> -->
<!-- 		   	</div> -->
<!-- 		</form> -->
		
		
		
		
		
 			<!-- 바디  --> 
<%-- 	<form action="${pageContext.request.contextPath }/salesTeam/estimateManage/estimateView"> --%>
<%-- 		<input type="hidden" name="what" value="${param.what }" > --%>
<!-- 		<input type="hidden" name="hidden_cl_no" class="form-control"> -->
<!-- 	</form> -->
<!-- 		<form id="estForm" method="post"> -->
<!-- 			<table class="table table-bordered"> -->
<!-- 				<tr> -->
<!-- 					<th class="thwidth">거래처코드</th> -->
<%-- 					<td colspan="4"><input class="form-control" value="${sale_est_listVO.cl_no }" type="text" name="cl_no"></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<th class="thwidth">거래처명</th> -->
<%-- 					<td colspan="4"><input class="form-control" value="${sale_est_listVO.cl_name }" type="text" name="cl_name"></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<th class="thwidth">주문자</th> -->
<%-- 					<td td colspan="4"><input class="form-control" value="${sale_est_listVO.cl_charger }" type="text" name="cl_charger"></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<th class="thwidth">견적담당사원</th> -->
						
<!-- 					<td td colspan="4"> -->
<%-- 						<security:authentication property="principal" var="authEmp"/> --%>
<%-- 						<security:authorize access="isAuthenticated()"> --%>
<%-- 							<input type="text" name="emp_name" value="${authEmp.emp_name}" class="form-control" readonly="readonly" /> --%>
<%-- 							<input type="hidden" name="emp_id" value="${authEmp.emp_id}" class="form-control" /> --%>
<%-- 						</security:authorize> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 			<div class="col-xs-12 col-md-12"> -->
<!-- 			<table class="table"> -->
<!-- 				<thead id="prodListHead"> -->
<!-- 				<tr> -->
<!-- 					<th>상품코드</th> -->
<!-- 					<th>품목명</th> -->
<!-- 					<th>상품명</th> -->
<!-- 					<th>색상</th> -->
<!-- 					<th>크기</th> -->
<!-- 					<th>수량</th> -->
<!-- 					<th>6개월치 평균 단가</th> -->
<!-- 					<th></th> -->
<!-- 				</tr> -->
<!-- 				</thead> -->
<!-- 				<tbody id="prodListBody"> -->
<%-- 					<c:forEach items="${sale_est_listVO.sale_eprodList }" var="sale_eprod"> --%>
<!-- 					 	<tr> -->
<%-- 							<td><input type="text" readonly="readonly" class="form-control" value="${sale_eprod.prod_no }"></td> --%>
<%-- 							<td><input type="text" readonly="readonly" class="form-control" value="${sale_eprod.item_name}"</td> --%>
<%-- 							<td><input type="text" readonly="readonly" class="form-control" value="${sale_eprod.prod_name}"></td> --%>
<%-- 							<td><input type="text" readonly="readonly" class="form-control" value="${sale_eprod.prod_color}"</td> --%>
<%-- 							<td><input type="text" readonly="readonly" class="form-control" value="${sale_eprod.prod_size}"></td> --%>
<%-- 							<td><input type="number" value="${sale_eprod.sale_eprod_qty}" class="form-control"></td> --%>
<%-- 							<td><input type="text" readonly="readonly" class="form-control" value="${sale_est_listVO.ware_avg_cost.prod_cost }"></td> --%>
<!-- 							<td><button type="button" class="btn btn-outline-primary">X</button></td> -->
<!-- 						</tr> -->
<%-- 					</c:forEach> --%>
					
<!-- 				</tbody> -->
<!-- 			 </table>	 -->
		
<!-- 		</div> -->
  
<!--    <br> -->
<!--    <div class="col-xs-4 col-md-6"> -->
<!--       <button type="submit" id="modifyEstBtn" class="btn btn-primary col-xs-2 col-md-3">수정</button> -->
<!--    </div> -->
<!-- </form> -->
		
		
		
		
<%-- 		</c:when> --%>
<%-- 	</c:choose> --%>
		
		 
		 
	
				

	
	
	<!-- 클라이언트 찾기에 대한 모달  -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
	   <div class="modal-content">
        <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">거래처 리스트</h5>
	        <button type="button" id="closeBtn" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
       	  <div class="modal-body">
	      	
   			<!-- 거래처를 검색하는  창-->
			<form id="clientModalForm" method="post">
		      <div class="col">
		      		<div class="row">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<div class="col-xs-12 col-md-12 form-group input-group">
										<select id="clientSearchType" name="searchType">
											<option value="all" ${pagingVO.searchType eq 'all' ? "selected" : "" }>전체</option>
											<option value="cl_no" ${pagingVO.searchType eq 'cl_no' ? "selected" : "" }>거래처코드</option>
											<option value="cl_name" ${pagingVO.searchType eq 'cl_name' ? "selected" : "" }>거래처명</option>
										</select>
										<input type="hidden" name="page"/> 
										<input type="text" id="clientSearchWord" name="searchWord" class="form-control"/> 
										<span class="input-group-btn searchWrap" >
										<!--여기서 searchProdBtn 버튼을 누르면, 검색 결과 리스트가 페이징처리되어 나타나게된다.  -->
											<button id = "searchClientBtn" type="submit" class="btn btn-default btnSearch"
							                        data-toggle="modal">
							            	<i class="fa fa-search" ></i>
							            	</button>
										</span>	
								</div>
					</div>
			 	</div>
			</form>
	      	
	      	
	          	<table class="table">
				   <thead id="clientListHead">

				   </thead>
				    <tbody id="clientListBody">

					</tbody>
					<tfoot>
						<tr>
							<td id="clientPagingArea" colspan="5">
								${pagingVO.pagingHTML }
							</td>
						</tr>
					</tfoot>
	          	</table>	
          	</div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">취소</button>
	        <button type="button" id="insertClientBtn" class="btn btn-outline-primary">등록</button>
	      </div>
	    </div>
	  </div>
	</div>




	<!-- searchPurEst 에 대한 모달 (제품 리스트 띄우기) -->
	<form id="prodModalForm" name="prodModalForm" method="post">
		<div class="modal fade" id="searchPurEst" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">제품 리스트</h5>
		        <button type="button" id="prodCloseBtn" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      	<div class="modal-body">
		      	
		      	<!-- 제품을 검색하는  창-->
		      	<div class="col">
		      		<div class="row">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="col-xs-12 col-md-12 form-group input-group">
								<select id="prodSearchType" name="searchType">
									<option value="all" ${pagingVO.searchType eq 'all' ? "selected" : "" }>전체</option>
									<option value="prod_no" ${pagingVO.searchType eq 'prod_no' ? "selected" : "" }>상품코드</option>
									<option value="prod_name" ${pagingVO.searchType eq 'prod_name' ? "selected" : "" }>상품명</option>
									<option value="item_name" ${pagingVO.searchType eq 'item_name' ? "selected" : "" }>품목명</option>
								</select>
								<input type="hidden" name="page"/>
								<input type="text" id="prodSearchWord" name="searchWord" class="form-control"  /> 
								<span class="input-group-btn searchWrap" >
	
									<!--여기서 searchProdBtn 버튼을 누르면, 검색 결과 리스트가 페이징처리되어 나타나게된다.  -->
									<button id = "searchProdBtn" type="submit" class="btn btn-default btnSearch"
					                        data-toggle="modal">
					            	<i class="fa fa-search" ></i>
					            	</button>
								</span>
									
							</div>
					   	</div>
				</div>
				
					
				<!-- 제품 정보 -->
		          	<table class="table">
					   <thead id="prodModalListHead">
					     <tr>
						<th class="radiobtn"></th>
							<th>제품번호</th>
							<th>품목명</th>
							<th>제품명</th>
							<th>제품크기</th>
							<th>제품색상</th>
						</tr>
					    </thead>
					    <tbody id="prodModalListBody">
				   			
						</tbody>
						<tfoot>
							<tr>
								<td id="prodPagingArea" colspan="6">
									${pagingVO.pagingHTML }
								</td>
							</tr>
						</tfoot>
		          	</table>	
	          	</div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">취소</button>
		        <!-- 등록 버튼을 누르게 되면, 해당하는 제품의 정보가 견적서 등록 폼에 채워지게된다. -->
		        <button type="button" id="insertProdBtn" class="btn btn-outline-primary">등록</button>
		      </div>
		    </div>
		  </div>
		</div>
	</form>
	
   

<script>
   <!-- date picker를 사용하기 위한 script 설정 -->
     $( "#estimateDate" ).datepicker({
            changeMonth: true, 
            dateFormat: 'yy-mm-dd',
            dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
            dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
     });
   	
     //거래처 페이징 처리
  function pagingBuyer(page){
		$("#clientModalForm").find("input[name='searchType']").val($("#clientSearchType").val());
		$("#clientModalForm").find("input[name='searchWord']").val($("#clientSearchWord").val());
		$("#clientModalForm").find("input[name='page']").val(page);
		$("#clientModalForm").submit();
  }

    
  	//모달창에서 거래처 검색
  	function retrieveClient(resp){
  		var clientList = resp.dataList;
  		var pagingHTML = resp.pagingHTML;
  		var trTags = [];
  		
  		var th= $("<tr>").append(
  					$("<th style='width:10px;'>").text("")
  					,$("<th>").text("거래처코드")
  					,$("<th>").text("거래처명")
  					,$("<th>").text("주문자명")
  					,$("<th>").text("주문자 전화번호")
  				)
  		
  		//모달창에서  검색을 했을 때의 결과(반복문)
  		$(clientList).each(function(idx, client){ //향상된 for문 돌리기
  			console.log(client);
  			var tr = $("<tr>").append(
  					$("<td><input type='radio' class='radioCheck' name='radioCheck' value='"+client.cl_no+"'>")		
					,$("<td>").text(client.cl_no)
					,$("<td>").text(client.cl_name)
					,$("<td>").text(client.cl_charger)
					,$("<td>").text(client.cl_tel)
  				)
  				trTags.push(tr);
  		});
  		$("#clientListHead").html(th);
  		$("#clientListBody").html(trTags);
  		$("#clientPagingArea").html(resp.pagingHTML);
  	}
  	
		$("#searchClientBtn").on("click", function(event){
			var searchType = $("#clientSearchType").val();
			var searchWord = $("#clientSearchWord").val();
			$("#clientModalForm").submit();
		})
	
		$("#clientModalForm").on("submit",function(){
			var queryString = $(this).serialize();
				$.ajax({
					url: "${pageContext.request.contextPath}/salesTeam/estimateManage/estimateClientLists",
					data: queryString,
					dataType: "json",
					success: function(resp){
						console.log(resp);
						retrieveClient(resp);
					}
				});
				//동기요청 취소
				return false;
				
			}); 
				
	
	$("#insertClientBtn").click(function(){
		var cl_no = $("input[name='radioCheck']:checked").val(); //클릭한 라디오버튼값을 넘겨준다.
		$("#myModal").modal('hide');
		
		
		$.ajax({
			url: "${pageContext.request.contextPath}/salesTeam/estimateManage/estimateClient/"+cl_no,
			method: "GET",
			dataType: "json",
			success: function(resp){
				$("#cl_input").val(resp.cl_name);
				$("[name='cl_no']").val(resp.cl_no);
				$("[name='cl_name']").val(resp.cl_name);
				$("[name='cl_charger']").val(resp.cl_charger);
			}
		
			
		});
	})
	
	
	
	
	 /* --------------------제품 리스트 조회 */
	
	 
	 
	 
	// 제품 페이징 처리
	//맨 처음에 값들 세팅되도록 주는 설정
	function pagingProd(page){
		
		$("#prodModalForm").find("input[name='searchType']").val($("#prodSearchType").val());
		$("#prodModalForm").find("input[name='searchWord']").val($("#prodSearchWord").val());
		$("#prodModalForm").find("input[name='page']").val(page);
		$("#prodModalForm").submit();
	}
	 
	//제품 검색 모달에 대한 페이징 비동기 처리
	function retrieveProd(resp){
		//success인 경우
		var prodList = resp.dataList;
		var pagingHTML = resp.pagingHTML;
		var trTags = [];
		
		var th = $("<tr>").append(
			$("<th class='radiobtn'>").text(""),
			$("<th>").text("제품번호"),
			$("<th>").text("품목명"),
			$("<th>").text("제품명"),
			$("<th>").text("제품크기"),
			$("<th>").text("제품색상")
		)
		
		//모달 창에서 검색을 했을 때의 결과 (반복문)
		$(prodList).each(function(idx, prod){
			var td = $("<tr>").append(
				$("<td><input type='radio' name='prodRadioCheck' value='"+prod.prod_no+"' >"),
				$("<td>").text(prod.prod_no),
				$("<td>").text(prod.item_name),
				$("<td>").text(prod.prod_name),
				$("<td>").text(prod.prod_size),
				$("<td>").text(prod.prod_color)
				
			)
			trTags.push(td);
		});
		$("#prodModalListHead").html(th);
		$("#prodModalListBody").html(trTags);
		$("#prodPagingArea").html(resp.pagingHTML);
	}
	
	
	//검색버튼을 눌렀을 때 비동기작업이 이루어진다.
		$("#searchProdBtn").on("click", function(event){
			
			$("#prodModalForm").submit();
		})
	
		$("#prodModalForm").on("submit",function(){
			var queryString = $(this).serialize();
// 			$("#prodSearchForm").submit(); //searchForm을 보낸다
			$.ajax({
				url: "${pageContext.request.contextPath}/salesTeam/estimateManage/estimateProdLists",
				data: queryString,
				dataType: "json",
				success: function(resp){
					console.log(resp);
					retrieveProd(resp);
				},
				error : function(errorResp) {
					console.log(errorResp.status);
					swal("오류");
				}
			});
			//동기요청 취소
			return false;
			
		}); 
			

	$("#insertProdBtn").click(function(){ //등록하기 버튼을 클릭하면
		var prod_no = $("input[name='prodRadioCheck']:checked").val(); //클릭한 라디오버튼값을 넘겨준다.
		$("#searchPurEst").modal('hide');
		
		$.ajax({
			url: "${pageContext.request.contextPath}/salesTeam/estimateManage/estimateProd/"+prod_no,
			method: "GET",
			dataType: "json",
			success: function(resp){ //resp는 여기서 pagingVO<ProductVO>
					$("#prod_input").val(resp.prod_name);
					var prodListTr = $("#prodListBody").find("tr");
					var idx = prodListTr.length;
					
						var tr = $("<tr>").prop("id", resp.prod_no)
									.append(
										$("<td>").append(
											$("<input>").attr({
												type: "number",
												name: "sale_eprodList["+idx+"].prod_no",
												'class': "form-control",
												required: 'required',
												readonly: 'readonly'
											}).val(resp.prod_no)		
										),
										$("<td>").append(
											$("<input>").attr({
												type: "text",
												name: "prod_name",
												'class': "form-control",
												required: 'required',
												readonly: 'readonly'
											}).val(resp.prod_name)
										),
										$("<td>").append(
											$("<input>").attr({
												type: "text",
												name: "item_name",
												'class': "form-control",
												required: 'required',
												readonly: 'readonly'
											}).val(resp.item_name)		
										),
										$("<td>").append(
											$("<input>").attr({
												type: "text",
												name: "prod_color",
												'class': "form-control",
												required: 'required',
												readonly: 'readonly'
											}).val(resp.prod_color)		
										),
										$("<td>").append(
											$("<input>").attr({
												type: "text",
												name: "prod_size",
												'class': "form-control",
												required: 'required',
												readonly: 'readonly'
											}).val(resp.prod_size)		
										),
										$("<td>").append(
											$("<input>").attr({
												type: "text",
												name: "sale_eprodList["+idx+"].sale_eprod_qty",
												'class': "form-control",
												required: 'required',
											})		
										),
										$("<td>").append(
											$("<input>").attr({
												type: "text",
												name: "sale_eprodList["+idx+"].sale_eprod_cost",
												'class': "form-control",
												required: 'required',
												readonly: 'readonly'
											}).val(resp.prod_cost)		
										),
						);
				
					$("#prodListBody").append(tr);

			}
		
		});
	});
	

     
</script>