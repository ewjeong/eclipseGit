<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019.05.27   정은우      최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<h3>견적요청서 조회</h3>
<br>
	<form id="searchHiddenForm" name="searchHiddenForm">
		<input type="hidden" name="cl_name" id="cl_name"/>
		<input type="hidden" name ="pur_er_date" id="pur_er_date"/>
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
   			name ="pur_er_date" value="${pagingVO.searchData.pur_er_date }" >
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
   		<td colspan="4" id ="pagingArea">
  			${pagingVO.pagingHTML }
  		</td>
   	</tr>
    </tfoot>
</table>
<script type="text/javascript">

	<c:if test="${ not empty message}">
		swal("견적요청서 등록 완료", "견적요청서 등록이 완료되었습니다." , "success");
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
			 $("#pur_er_date").val($("#estimateDate").val());
			 var date = $("#pur_er_date").val();
			 $("#searchHiddenForm").submit();
		  })
		  
		  $("#searchHiddenForm").on("submit",function(e){
			  var queryString = $(this).serialize();
			  $.ajax({
				url:"${pageContext.request.contextPath}/purchasingTeam/estimateManage/estimateReqList",  
				data : queryString,
				dataType:"json",
				success:function(resp){
					var erList = resp.dataList;
					var pagingHTML = resp.pagingHTML;
					var trTags=[];

					var th = $("<tr>").append(
								$("<th>").text("견적요청서번호")
								,$("<th>").text("견적요청일자")
								,$("<th>").text("거래처코드")
								,$("<th>").text("거래처명")
							
							) 
					trTags.push(th);
					
					$(erList).each(function(idx, er){
						var tr = $("<tr>").prop("id", er.pur_er_no)
						.append(
								$("<td>").text(er.pur_er_no)
								,$("<td>").text(er.pur_er_date)
								,$("<td>").text(er.cl_no)
								,$("<td>").text(er.cl_name)
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
			location.href="${pageContext.request.contextPath}/purchasingTeam/estimateManage/estimateReqView?what="+what;
		});
				
		paging(1);
		  
		  
	  });
</script>
