<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019.05.13   정은우      최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<h3>재고 조회</h3>
<br>
<div class="row">
	<form id="actionForm" name="actionForm">
		<input type="hidden" name ="prod_name" id="prod_name"/>
		<input type="hidden" name="page" />
	</form>
	 <div class="col-xs-6 col-md-2 offset-md-10 paddingNone input-group">
	   	<input type="text" class="form-control" placeholder="상품명" 
	   			id="prodText" name="prod_name" value="${pagingVO.searchData.prod_name }" />
	   	<span class="input-group-btn searchWrap">
	   		<button class="btn btn-default btnSearch" type="button" id="prodbtn">
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
   		<td colspan="7" id ="pagingArea">
  			${pagingVO.pagingHTML }
  		</td>
   	</tr>
   </tfoot>
</table>

<button class="btn btn-outline-primary col-xs-4 col-md-2"
		id="excelBtn">엑셀 출력</button> 
               
<script>
	  $( "#DatePicker" ).datepicker({
	         changeMonth: true, 
	         dateFormat: 'yy-mm-dd',
	         dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
	         dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
	         monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	         monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	  });
	  
	  //페이징처리함수
	  function paging(page){
		  var actionForm = $("#actionForm");
		  
		  actionForm.find("input[name='page']").val(page);
		  actionForm.submit();
	  }
	  
	  $(document).ready(function(){

		  $("#prodbtn").on('click', function(e){
				$("#prod_name").val($("#prodText").val());
				var name = $("#prod_name").val();
				
				$("#actionForm").submit();
	      })
	      
	      $("#actionForm").on("submit",function(e){
			  var queryString = $(this).serialize();
			  $.ajax({
				url:"${pageContext.request.contextPath}/purchasingTeam/stockManage/stockList",  
				data : queryString,
				dataType:"json",
				success:function(resp){
					var stockList = resp.dataList;
					var pagingHTML = resp.pagingHTML;
					var trTags=[];
					var th = $("<tr>").append(
								$("<th>").text("상품코드")
								,$("<th>").text("품목분류명")
								,$("<th>").text("상품명")
								,$("<th>").text("상품크기")
								,$("<th>").text("상품색상")
								,$("<th>").text("재고수량")
								,$("<th>").text("단가")
							) 
					trTags.push(th);
					
					
					if(stockList != null && stockList.length > 0){
						$(stockList).each(function(idx, stock){
							var tr = $("<tr>").prop("id", stock.prod_no)
							.append(
									$("<td>").text(stock.prod_no)
									,$("<td>").text(stock.item_name)
									,$("<td>").text(stock.prod_name)
									,$("<td>").text(stock.prod_size)
									,$("<td>").text(stock.prod_color)
									,$("<td>").text(stock.stock)
									,$("<td>").text(stock.avg_cost)
								   );
							trTags.push(tr);	   
						});
					}else{ 
						var tr = $("<tr>").append(
										$("<td>").text("검색어에 해당하는 상품이 없습니다.")
										.attr("colspan", "7")
										.css("text-align", "center")
							   		);
						trTags.push(tr);	   
					}
					
					$("#listBody").html(trTags);
					$('#pagingArea').html(resp.pagingHTML);
				}
			  });
			  //동기요청취소
			  return false;
			  
			  
			  
	 	  });
		  
		//엑셀출력
	    $("#excelBtn").click(function() {
	    	document.actionForm.action = "${pageContext.request.contextPath}/stockExcel.do";
	    	document.actionForm.method = "POST";
	    	document.actionForm.submit();
//	 	    document.actionForm.action = "${pageContext.request.contextPath}/purchasingTeam/salesOrderSheet/orderSheetList";
//	 	    document.actionForm.method = "GET";
		});
		  
		  paging(1);
		  
	  });
	  
	  
</script>         











      
