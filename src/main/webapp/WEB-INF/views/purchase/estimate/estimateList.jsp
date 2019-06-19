<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019.05.15   정은우      최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>견적서 조회</h3>
<br>
	<form id="searchHiddenForm" name="searchHiddenForm">
		<input type="hidden" name="cl_name" id="cl_name"/>
		<input type="hidden" name ="pur_est_date" id="pur_est_date"/>
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
   			name ="pur_est_date" value="${pagingVO.searchData.pur_est_date }" >
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
	<tbody>	

	</tbody>
	<tbody id="listBody">

    </tbody>
    <tfoot>
  	<tr>
   		<td colspan="5" id ="pagingArea">
  			${pagingVO.pagingHTML }
  		</td>
   	</tr>
    </tfoot>
</table>

<script type="text/javascript">

	<c:if test="${ not empty message}">
		swal("견적서 등록 완료", "견적서등록이 완료되었습니다." , "success");
	</c:if>
	
	  function paging(page){
		  var searchHiddenForm = $("#searchHiddenForm");
		  
		  searchHiddenForm.find("input[name='page']").val(page);
		  searchHiddenForm.submit();
	  }
	  
	  $(document).ready(function(){
		  
		  $("#clbtn").on('click', function(e){
			 $("#cl_name").val($("#clText").val());
			 var name = $("#cl_name").val();
			 $("#searchHiddenForm").submit();
		  })
			  
		  $("#datebtn").on('click', function(e){
			 $("#pur_est_date").val($("#estimateDate").val());
			 var date = $("#pur_est_date").val();
			 $("#searchHiddenForm").submit();
		  })
		  
		  $("#searchHiddenForm").on("submit",function(e){
			  var queryString = $(this).serialize();
			  $.ajax({
				url:"${pageContext.request.contextPath}/purchasingTeam/estimateManage/estimateList",  
				data : queryString,
				dataType:"json",
				success:function(resp){
					var estList = resp.dataList;
					var pagingHTML = resp.pagingHTML;
					var trTags=[];
				
					var th = $("<tr>").append(
								$("<th>").text("견적코드")
								,$("<th>").text("견적일자")
								,$("<th>").text("거래처코드")
								,$("<th>").text("거래처명")
								,$("<th>").text("견적금액")
							
							) 
					trTags.push(th);
		         
					$(estList).each(function(idx, est){
						var tr = $("<tr>").prop("id", est.pur_est_no)
						.append(
								$("<td>").text(est.pur_est_no)
								,$("<td>").text(est.pur_est_date)
								,$("<td>").text(est.cl_no)
								,$("<td>").text(est.cl_name)
								,$("<td>").text(est.est_cost)
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
			location.href="${pageContext.request.contextPath}/purchasingTeam/estimateManage/estimateView?what="+what;
		});
				
		paging(1);
		  
		  
	  });
	  
</script>

