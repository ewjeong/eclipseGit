<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019.05.13   정은우      최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
 
<h3>영업팀 주문서 조회</h3>
<br>
<br>
	<form id="searchHiddenForm" name="searchHiddenForm">
		<input type="hidden" name ="sale_ord_date" id="sale_ord_date"/>
		<input type="hidden" name="cl_name" id="cl_name"/>
		<input type="hidden" name="page" />
	</form>
   	<div class="row" >
	   	<div class="col-xs-6 col-md-2 offset-md-7 paddingNone input-group">
		   	<input type="text" class="form-control" placeholder="거래처명" 
		   			id="clText" name="cl_name" value="${pagingVO.searchData.cl_name }" />
		   	<span class="input-group-btn searchWrap">
		   		<input type="hidden" name="page" />
		   		<button class="btn btn-default btnSearch" type="button" id="clbtn">
		   			<i class="fa fa-search"></i>
		   		</button>
		   	</span>
		</div>
	    <div class="col-xs-5 col-md-3 paddingNone input-group">
	   		<input type="date" class="form-control" id="estimateDate"
	   			name ="sale_ord_date" value="${pagingVO.searchData.sale_ord_date }" >
	   		<span class="input-group-btn searchWrap">
		   		<input type="hidden" name="page" />
		   		<button class="btn btn-default btnSearch" type="button" id="datebtn">
		   			<i class="fa fa-search"></i>
		   		</button>
		   	</span>
	    </div>
	</div> 
<br>
<table class="table table-hover">
   <thead>
	<!-- 여기 -->
	</thead>
   <tbody id="listBody">
	<!-- 여기 -->
   </tbody>
   <tfoot>
  	<tr>
   		<td colspan="7" id ="pagingArea" style="">
  			${pagingVO.pagingHTML }
  		</td>
   	</tr>
   </tfoot>
</table>

<button class="btn btn-outline-primary col-xs-4 col-md-2"
		id="excelBtn">엑셀 출력</button> 

<script type="text/javascript">

	  //페이징처리함수
	  function paging(page){
		  var searchHiddenForm = $("#searchHiddenForm");

		  searchHiddenForm.find("input[name='page']").val(page);
		  searchHiddenForm.submit();
	  }
	  
// 	  //리스트 AJAX
// 	  function salesOrdList(resp){
// 		  var salesOrdList = resp.dataList;
// 		  var pagingHTML = resp.pagingHTML;
// 		  var trTag = [];
// 		  $(salesOrdList).each(function(idx, sale_ord){
// 				 var tr = $("<tr>").prop("id", resp.pur_ord_code)
// 					.append(
// 						$("<td>").text(sale_ord.sale_ord_date),	
// 						$("<td>").text(sale_ord.sale_ord_code),	
// 						$("<td>").text(sale_ord.cl_charger),
// 						$("<td>").text(sale_ord.cl_name),
// 						$("<td>").text(sale_ord.payment),
// 						$("<td>").text(sale_ord.sale_ord_complete)	
// 					);
// 				trTags.push(tr);
// 		  });
// 		  $("#listBody").html(trTag);
// 		  $('#pagingArea').html(resp.pagingHTML);
// 	  }
	  
	  
	  $(document).ready(function() {
		  
		  $("#clbtn").on('click', function(e){
			 $("#cl_name").val($("#clText").val());
			 var name = $("#cl_name").val();
			 $("#searchHiddenForm").submit();
		  })
		  
		  $("#datebtn").on('click', function(e){
			 $("#sale_ord_date").val($("#estimateDate").val());
			 var date = $("#sale_ord_date").val();
			 $("#searchHiddenForm").submit();
		  })

		  $("#searchHiddenForm").on("submit",function(e){
			  var queryString = $(this).serialize();
			  $.ajax({
				url:"${pageContext.request.contextPath}/purchasingTeam/salesOrderSheet/orderSheetList",  
				data : queryString,
				dataType:"json",
				success:function(resp){
					var saleOrdList = resp.dataList;
					var pagingHTML = resp.pagingHTML;
					var trTags=[];
			
					var th = $("<tr>").append(
								$("<th>").text("주문일자")
								,$("<th>").text("주문코드")
								,$("<th>").text("주문자명")
								,$("<th>").text("거래처명")
								,$("<th>").text("결제방법")
								,$("<th>").text("처리단계")
							) 
					trTags.push(th);
					
					$(saleOrdList).each(function(idx, order){
						var tr = $("<tr>").prop("id", order.sale_ord_code)
						.append(
								$("<td>").text(order.sale_ord_date)
								,$("<td>").text(order.sale_ord_code)
								,$("<td>").text(order.cl_charger)
								,$("<td>").text(order.cl_name)
								,$("<td>").text(order.payment)
								,$("<td>").text(order.sale_ord_complete)
							   );
						trTags.push(tr);	   
					});
					$("#listBody").html(trTags);
					$('#pagingArea').html(resp.pagingHTML);
				}
			  });
			  //동기요청취소
			  return false;
			  
			  
			  
	 	  });

		//엑셀출력
	    $("#excelBtn").click(function() {
	    	document.searchHiddenForm.action = "${pageContext.request.contextPath}/SalesOrderSheetExcel.do";
	    	document.searchHiddenForm.method = "POST";
	    	document.searchHiddenForm.submit();
// 	    	document.searchHiddenForm.action = "${pageContext.request.contextPath}/purchasingTeam/salesOrderSheet/orderSheetList";
// 	    	document.searchHiddenForm.method = "GET";
	    });
		  
		//리스트바디의 tr 태그를 누르면 상세조회
		$("#listBody").on("click","tr",function(){
			var what = $(this).prop("id");
			location.href="${pageContext.request.contextPath}/purchasingTeam/salesOrderSheet/orderSheetView?what="+what;
		});
				
		paging(1);
		
	    
	}); 
	

</script>












