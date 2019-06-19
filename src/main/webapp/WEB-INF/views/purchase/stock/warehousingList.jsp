<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019.05.14   정은우      최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<h3>입고 조회</h3>
<br>
	<form id="searchHiddenForm" name="searchHiddenForm">
		<input type="hidden" name="prod_name" id="prod_name"/>
		<input type="hidden" name ="ware_date" id="ware_date"/>
		<input type="hidden" name="page" />
	</form>
<div class="row">
    <div class="col-xs-6 col-md-2 offset-md-7 paddingNone input-group">
	   	<input type="text" class="form-control" placeholder="상품명" 
	   			id="prodText" name="prod_name" value="${pagingVO.searchData.prod_name }" />
	   	<span class="input-group-btn searchWrap">
	   		<input type="hidden" name="page" />
	   		<button class="btn btn-default btnSearch" type="button" id="prodbtn">
	   			<i class="fa fa-search"></i>
	   		</button>
	   	</span>
	</div>
   	<div class="col-xs-5 col-md-3 paddingNone input-group">
   		<input type="date" class="form-control" id="estimateDate"
   			name ="ware_date" value="${pagingVO.searchData.ware_date }" >
   		<span class="input-group-btn searchWrap">
	   		<input type="hidden" name="page" />
	   		<button class="btn btn-default btnSearch" type="button" id="datebtn">
	   			<i class="fa fa-search"></i>
	   		</button>
	   	</span>
    </div>
</div>    
<br>
<table class="table">
   <thead>
 	 <!-- 여기 -->
   </thead>
   <tbody id="listBody">
   	<!-- 여기 -->	
   </tbody>
   <tfoot>
  	<tr>
   		<td colspan="10" id ="pagingArea">
  			${pagingVO.pagingHTML }
  		</td>
   	</tr>
   </tfoot>
</table>
<button class="btn btn-outline-primary col-xs-4 col-md-2"
		id="excelBtn">엑셀 출력</button> 

<script type="text/javascript">
	<c:if test="${ not empty message}">
		swal("입고 등록 완료", "입고 등록이 완료되었습니다." , "success");
	</c:if>

	  function paging(page){
		  var searchHiddenForm = $("#searchHiddenForm");
		  
		  searchHiddenForm.find("input[name='page']").val(page);
		  searchHiddenForm.submit();
	  }
	  
	  $(document).ready(function(){

		  $("#prodbtn").on('click', function(e){
			 $("#prod_name").val($("#prodText").val());
			 var name = $("#prod_name").val();
			 $("#searchHiddenForm").submit();
	      })
	   
	      $("#datebtn").on('click', function(e){
		     $("#ware_date").val($("#estimateDate").val());
		     var date = $("#ware_date").val();
		     $("#searchHiddenForm").submit();
	      })
	
	      
	      $("#searchHiddenForm").on("submit",function(e){
			  var queryString = $(this).serialize();
			  $.ajax({
				url:"${pageContext.request.contextPath}/purchasingTeam/stockManage/warehousingList",  
				data : queryString,
				dataType:"json",
				success:function(resp){
					var wareList = resp.dataList;
					var pagingHTML = resp.pagingHTML;
					var trTags=[];
			
					var th = $("<tr>").append(
								$("<th>").text("입고일")
								,$("<th>").text("발주서코드")
								,$("<th>").text("품목분류명")
								,$("<th>").text("상품코드")
								,$("<th>").text("상품명")
								,$("<th>").text("상품크기")
								,$("<th>").text("상품색상")
								,$("<th>").text("입고수량")
								,$("<th>").text("거래처코드")
								,$("<th>").text("거래처명")
								,$("<th>").text("담당자")
							) 
					trTags.push(th);
					
					$(wareList).each(function(idx, ware){
						var tr = $("<tr>").prop("id", ware.ware_date)
						.append(
								$("<td>").text(ware.ware_date)
								,$("<td>").text(ware.pur_ord_code)
								,$("<td>").text(ware.item_name)
								,$("<td>").text(ware.prod_no)
								,$("<td>").text(ware.prod_name)
								,$("<td>").text(ware.prod_size)
								,$("<td>").text(ware.prod_color)
								,$("<td>").text(ware.pur_oprod_qty)
								,$("<td>").text(ware.cl_no)
								,$("<td>").text(ware.cl_name)
								,$("<td>").text(ware.emp_name)
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
	    	document.searchHiddenForm.action = "${pageContext.request.contextPath}/wareExcel.do";
	    	document.searchHiddenForm.method = "POST";
	    	document.searchHiddenForm.submit();
// 	 	    document.actionForm.action = "${pageContext.request.contextPath}/purchasingTeam/salesOrderSheet/orderSheetList";
// 	 	    document.actionForm.method = "GET";
		});
		  
		  paging(1);
	      
	      
	  });
	  
</script>

