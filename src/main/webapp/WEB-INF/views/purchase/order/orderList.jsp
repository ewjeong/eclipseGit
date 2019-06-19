<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<h3>발주서 조회</h3>
<br>
	<form id="searchHiddenForm" name="searchHiddenForm">
		<input type="hidden" name="cl_name" id="cl_name"/>
		<input type="hidden" name ="pur_ord_date" id="pur_ord_date"/>
		<input type="hidden" name="page" />
	</form>
<div class="row">
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
   			name ="pur_ord_date" value="${pagingVO.searchData.pur_ord_date }" >
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

	</thead>
	<tbody id="listBody">

   </tbody>
   <tfoot>
  	<tr>
   		<td colspan="6" id ="pagingArea">
  			${pagingVO.pagingHTML }
  		</td>
   	</tr>
   </tfoot>
</table>
<button class="btn btn-outline-primary col-xs-4 col-md-2"
		id="excelBtn">엑셀 출력</button>
		
		 
<script type="text/javascript">
	
	<c:if test="${ not empty message}">
		swal("발주서 등록 완료", "발주서 등록이 완료되었습니다." , "success");
	</c:if>
	
	function paging(page){
		  var searchHiddenForm = $("#searchHiddenForm");
		  
		  searchHiddenForm.find("input[name='page']").val(page);
		  searchHiddenForm.submit();
	}
	  
	  
		  
	  $("#clbtn").on('click', function(e){
		 $("#cl_name").val($("#clText").val());
		 var name = $("#cl_name").val();
		 $("#searchHiddenForm").submit();
	  })
		  
	  $("#datebtn").on('click', function(e){
		 $("#pur_ord_date").val($("#estimateDate").val());
		 var date = $("#pur_ord_date").val();
		 $("#searchHiddenForm").submit();
	  })
	  
	  $("#searchHiddenForm").on("submit",function(e){
		  var queryString = $(this).serialize();
		  $.ajax({
			url:"${pageContext.request.contextPath}/purchasingTeam/orderManage/orderList",  
			data : queryString,
			dataType:"json",
			success:function(resp){
				var orderList = resp.dataList;
				var pagingHTML = resp.pagingHTML;
				var trTags=[];
	
				var th = $("<tr>").append(
							$("<th>").text("발주요청일자")
							,$("<th>").text("발주코드")
							,$("<th>").text("담당자명")
							,$("<th>").text("거래처코드")
							,$("<th>").text("거래처명")
							,$("<th>").text("결재상태")
						
						) 
				trTags.push(th);
				
				$(orderList).each(function(idx, order){
					var tr = $("<tr>").prop("id", order.pur_ord_code)
					.append(
							$("<td>").text(order.pur_ord_date)
							,$("<td>").text(order.pur_ord_code)
							,$("<td>").text(order.emp_name)
							,$("<td>").text(order.cl_no)
							,$("<td>").text(order.cl_name)
							,$("<td>").text(order.elec_comple)
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
		     document.searchHiddenForm.action = "${pageContext.request.contextPath}/orderExcel.do";
		     document.searchHiddenForm.method = "POST";
		     document.searchHiddenForm.submit();
	//	 	 document.actionForm.action = "${pageContext.request.contextPath}/purchasingTeam/salesOrderSheet/orderSheetList";
	//	 	 document.actionForm.method = "GET";
		 });
		  
		//리스트바디의 tr 태그를 누르면 상세조회
		$("#listBody").on("click","tr",function(){
			var what = $(this).prop("id");
			location.href="${pageContext.request.contextPath}/purchasingTeam/orderManage/orderView?what="+what;
		});
				
		paging(1);
</script>
