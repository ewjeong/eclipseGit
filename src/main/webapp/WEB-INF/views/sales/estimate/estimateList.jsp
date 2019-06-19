<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="${pageContext.request.contextPath }/js/paging.js"></script>

<style>
.fa-edit {
	color: white;
}
</style>


	<h3>견적서 리스트조회</h3>
	<div class="row">
		<form id="searchForm" name="searchForm" class="form-inline offset-md-8">
		
			<input type="hidden" name="page" />
			
			<div class="col-xs-12 col-md-9 offset-md-4 input-group">
				<select name="searchType" class="form-control">
					<option value="all">전체</option>
					<option value="emp_name">담당자</option>
					<option value="cl_name">거래처명</option>
					<option value="prod_name">품명</option>
				</select>
				<input class="form-control col-md-9" id="searchWord" type="text" name="searchWord" value="${pagingVO.searchWord }"/> 
					<span class="input-group-btn searchWrap">
					<button class="btn btn-default btnSearch" id="searchBtn" type="button">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form>
	</div>

<div style="padding-top: 10px;">


<div>
	<table class="table">
		<thead>
			<tr>
				<td>견적서번호</td>
				<td>견적일자</td>
				<td>담당사원</td>
				<td>거래처코드</td>
				<td>거래처명</td>
			</tr>
		</thead>
		<tbody id="listBody">
			<c:set value="${pagingVO.dataList }" var="saleEstList" />
			<c:if test="${not empty saleEstList }">
				<c:forEach items="${saleEstList }" var="saleEst">
					<tr class="showView" id="${saleEst.sale_est_no }" >
						<td>${saleEst.sale_est_no}</td>
						<td>${saleEst.sale_est_date }</td>
						<td>${saleEst.emp_name }</td>
						<td>${saleEst.cl_no}</td>
						<td>${saleEst.cl_name }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty saleEstList }">
				<tr>
					<td colspan="6">견적리스트의 내용이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td id="pagingArea" colspan="5">
					${pagingVO.pagingHTML }
				</td>
			</tr>
		</tfoot>
	</table>
	
	<form action="${pageContext.request.contextPath }/salesListExcel" id="execelForm" method="post">
		<input type="hidden" id="hiddenType" name="hiddenType">
		<input type="hidden" id="hiddenWord" name="hiddenWord">
		<button type="submit" id="excelBtn"  class="btn btn-outline-primary col-xs-4 col-md-2" >엑셀 출력</button>
		
	</form>
	
	
		
</div>

<script type="text/javascript">
	
	function paging(page){
		$("#searchForm").find("input[name='page']").val(page);
		console.log(page);
		$("#searchForm").submit();
	}
	
	$("#searchBtn").on("click", function(event){
		$("#searchForm").submit();
	})
	
	$("#searchForm").on("submit", function(){
		var datas = $(this).serialize();
		$.ajax({
			url: "${pageContext.request.contextPath}/salesTeam/estimateManage/estimateLists",
			data: datas,
			dataType: "json",
			success: function(resp){
				dataListModify(resp);
			},
			error: function(errorResp){
				console.log(errorResp.status);
				swal("오류");
			}
		});
		return false;
	});
	
	function dataListModify(resp){
		var estList = resp.dataList;
		var pagingHTML = resp.pagingHTML;
		var trTags = [];
		$(estList).each(function(idx, est){
			var tr = $("<tr>").prop("id", est.sale_est_no)
				.append(
					$("<td>").text(est.sale_est_no),
					$("<td>").text(est.sale_est_date),
					$("<td>").text(est.emp_name),
					$("<td>").text(est.cl_no),
					$("<td>").text(est.cl_name)
				);
			trTags.push(tr);
		});
		$("#listBody").html(trTags);
		$("#pagingArea").html(resp.pagingHTMl);
		
	}
	
	paging(1);
	
	$(".regbtn").click(function(event) {
		var id = $(this).attr('id');
		alert(id);
		location.href = "${pageContext.request.contextPath}/salesTeam/orderManage/orderInsert?";
	})
	
	
	$("#listBody").on("click","tr",function(){
		var what = $(this).prop("id");
		location.href="${pageContext.request.contextPath}/salesTeam/estimateManage/estimateView?what="+what;
	});
	
	<c:if test="${not empty message}">
		swal("등록완료", "건적서 등록이 완료되었습니다." ,"success");
	</c:if>
	
// 	$("#excelBtn").click(function(){
// 		$("excelForm").submit();
// 	})
</script>
