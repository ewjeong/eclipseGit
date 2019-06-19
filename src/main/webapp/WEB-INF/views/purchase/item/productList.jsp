<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019.06.05   정은우      최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
 <script>
<c:if test="${ not empty message}">
	<c:if test="${ message eq 'OK'}">
		swal("등록 완료", "상품 등록이 완료되었습니다." , "success");	
	</c:if>
	<c:if test="${ message eq 'FAILED'}">
		swal("등록 완료", "상품 등록이 완료되었습니다." , "error");
	</c:if>
</c:if>	
</script>
<h3>상품 조회</h3>
<br>
	<form id="searchHiddenForm" name="searchHiddenForm">
		<input type="hidden" name="prod_name" id="prod_name"/>
		<input type="hidden" name="page" />
	</form>
   	<div class="row" >
	   	<div class="col-xs-6 col-md-2 offset-md-10 paddingNone input-group">
		   	<input type="text" class="form-control" placeholder="상품명" 
		   			id="prodText" name="prod_name" value="${pagingVO.searchData.prod_name }" />
		   	<span class="input-group-btn searchWrap">
		   		<input type="hidden" name="page" />
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
   		<td colspan="6" id ="pagingArea">
  			${pagingVO.pagingHTML }
  		</td>
   	</tr>
   </tfoot>
</table>

<script type="text/javascript">
	
	  //페이징처리함수
	  function paging(page){
		  var searchHiddenForm = $("#searchHiddenForm");

		  searchHiddenForm.find("input[name='page']").val(page);
		  searchHiddenForm.submit();
	  }
	  
	  
	  $(document).ready(function() {
		  
		  $("#prodbtn").on('click', function(e){
			 $("#prod_name").val($("#prodText").val());
			 var name = $("#prod_name").val();
			 $("#searchHiddenForm").submit();
		  })
		  
		  $("#searchHiddenForm").on("submit",function(e){
			  var queryString = $(this).serialize();
			  $.ajax({
				url:"${pageContext.request.contextPath}/purchasingTeam/itemManage/productList",  
				data : queryString,
				dataType:"json",
				success:function(resp){
					var prodList = resp.dataList;
					var pagingHTML = resp.pagingHTML;
					var trTags=[];
			
					var th = $("<tr>").append(
								$("<th>").text("상품번호")
								,$("<th>").text("품목명")
								,$("<th>").text("상품명")
								,$("<th>").text("상품크기")
								,$("<th>").text("상품색상")
								,$("<th>").text("단가")
							) 
					trTags.push(th);
					
					$(prodList).each(function(idx, prod){
						var tr = $("<tr>").prop("id", prod.prod_no)
						.append(
								$("<td>").text(prod.prod_no)
								,$("<td>").text(prod.item_name)
								,$("<td>").text(prod.prod_name)
								,$("<td>").text(prod.prod_size)
								,$("<td>").text(prod.prod_color)
								,$("<td>").text(prod.prod_cost)
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

		//리스트바디의 tr 태그를 누르면 상세조회
		$("#listBody").on("click","tr",function(){
			var what = $(this).prop("id");
			location.href="${pageContext.request.contextPath}/purchasingTeam/itemManage/productView?what="+what;
		});
				
		paging(1);
		
	    
	}); 
	

</script>












