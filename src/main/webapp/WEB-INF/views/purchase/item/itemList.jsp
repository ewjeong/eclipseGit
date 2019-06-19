<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="${pageContext.request.contextPath }/js/paging.js"></script>

<div class="col-xs-12 col-md-5">
	<h3>품목리스트 조회</h3>
</div>

<!-- searchHiddenForm을 통해 컨트롤러로 넘긴다. -->
<div class="col-xs-12 col-md-12 form-group input-group ">
	<form id="searchForm" name="searchForm" class="form-inline offset-md-8">
		<div class="col-xs-12 col-md-7 offset-md-5 input-group">
			<select class="form-control col-xs-7 col-md-7 search" id="searchType"
				name="searchType"  aria-label="Example select with button addon">
				<option value="all" ${pagingVO.searchType eq 'all'? "selected":"" }>전체</option>
				<option value="item_code"
					${pagingVO.searchType eq 'item_code'? "selected":"" }>품목코드</option>
				<option value="item_name"
					${pagingVO.searchType eq 'item_name'? "selected":"" }>품목명</option>
			</select>
			<input type="text" id="searchWord" class="search form-control col-xs-5 col-md-5"
				name="searchWord" value="${pagingVO.searchWord }">
			<input type="hidden" class="search" name="page">
<!-- 			<div class="col-xs-1 col-md-1 form-group input-group"> -->
				<!-- 							페이징처리 하기 위한 폼 -->
				<span class="input-group-btn searchWrap">
					<button class="btn btn-default btnSearch" id="searchBtn"
						type="submit">
						<i class="fa fa-search"></i>
					</button>
				</span>
<!-- 			</div> -->
		</div>
	</form>
</div>



<div class="col-xs-12 col-md-12">
	<table class="table">
		<thead>
			<tr>
				<th>품목분류코드</th>
				<th>하위품목명</th>
				<th>상위품목명</th>
<!-- 				<th>상위품목코드</th> -->
				<th></th>
			</tr>
		</thead>
		<form name="delete" onsubmit="return false;"
			action="${pageContext.request.contextPath }/purchasingTeam/itemManage/itemList"
			method="get">
			<tbody id="listBody">

				<c:set var="itemList" value="${pagingVO.dataList }" />
				<c:forEach var="item" items="${itemList }">
					<tr>
						<td>${item.item_code }</td>
						<td>${item.item_name }</td>
						<td>${item.top_item_name }</td>
<%-- 						<td>${item.top_item_code }</td> --%>
						<td><button type="submit" id="${item.item_code }" 
								class="delete form-control btn btn-secondary" data-toggle="modal"
								data-target=".exampleModal">삭제</button></td>
					</tr>
				</c:forEach>

			</tbody>
		</form>
		<tfoot>
			<tr>
				<td colspan="5">
					<div id="pagingArea" class="pageWrap">${pagingVO.pagingHTML }
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
</div>

<!-- 엑셀 출력하기  -->
<%-- <form action="${pageContext.request.contextPath }/"></form> --%>


<script type="text/javascript">
	// 	makePaging({
	// 		formTagName:"searchHiddenForm",
	// 		functionName:"${pagingVO.functionName }",
	// 		submitHandler:function(event){
	// 			event.preventDefault();
	// 			var queryString = $(event.target).serialize();
	// 			event.target.page.value="";
	// 			$.ajax({
	// 				url : "${pageContext.request.contextPath }/purchasingTeam/itemManage/itemList",
	// 				data : queryString,
	// 				dataType : "json", // request header(Accept), response header(Content-Type)
	// 				success : function(resp) {
	// 					var itemList = resp.dataList;
	// 					var pagingHTML = resp.pagingHTML;
	// 					var trTags = [];
	// 					$(itemList).each(function(idx, item){
	// 						var tr = $("<tr>")
	// 										.append(
	// 											$("<td>").text(item.item_code)	
	// 											,$("<td>").text(item.item_name)	
	// 										);
	// 						trTags.push(tr);
	// 					});
	// 					$("#listBody").html(trTags);
	// 					$('#pagingArea').html(resp.pagingHTML);
	// 				},
	// 				error : function(errorResp) {
	// 					console.log(errorResp.status);
	// 				}
	// 			});
	// 			return false; // 동기 요청 취소
	// 		}
	// 	});




//

function deleteItem(item_code){
		event.preventDefault();
		$.ajax({
			url : "${pageContext.request.contextPath}/purchasingTeam/itemManage/itemList/"+ item_code,
			method : "GET",
			dataType : "json",
			//contentType: false,
			//data: {item_code:item_code},
			success : function(resp) { //여기서 resp는 Object 형식인 것이다 (마샬링 때문에 Object 형태로 넘어온 것이다)
				//그래서 이 Object 형식으로 넘어간 resp를 확인하기 위해서는 JSON.stringify()를 통해 넘어온 데이터를 확인할 수 있다.
				dataListModify(resp);
			},
			error : function(errorResp) {
				console.log(errorResp.status);

			}
		})
		return false;

}
		
function paging(page) {
$("#searchForm").find("input[name='searchType']").val($("#searchType").val());
$("#searchForm").find("input[name='searchWord']").val($("#searchWord").val());
$("#searchForm").find("input[name='page']").val(page);
$("#searchForm").submit();

}


	//////////////////////////////////////////

	$("#searchBtn").on("click", function(event) {
		paging(1);
		// 		$("#searchForm").find("input[name='page']").val("1"); //페이지를 1로 설정 (검색했을 때 1페이지가 나오게)
// 		e.preventDefault();
		$("#searchForm").submit();
		
	});

	$("#searchForm").on("submit", function() {
		var datas = $(this).serialize(); //class가 search 인 (searchType과, searchWord를 serialize())
		console.log(datas);
		$.ajax({
			url : "${pageContext.request.contextPath}/purchasingTeam/itemManage/itemLists",
			data : datas,
			dataType: "json",	
			success : function(resp) { //컨트롤러로부터 받는 데이터 (resp)
				console.log(resp.dataList);// item
				console.log(resp);
				dataListModify(resp);
			},
			error : function(errorResp) {
				console.log(errorResp.status);

				swal("오류");
			}
		});
		return false;
	});
	
	function dataListModify(resp) {
		var itemList = resp.dataList;
		var pagingHTML = resp.pagingHTML;
		var trTags = [];
		$(itemList).each(function(idx, item) {
					var tr = $("<tr>").append(
								$("<td>").text(item.item_code),
// 								$("<td>").text(item.top_item_code),
								$("<td>").text(item.top_item_name),
								$("<td>").text(item.item_name),
								$("<td>").append(
									$("<input>").attr({
										type:"button",
										'class': "form-control btn-secondary",
										id: "deleteId",
										name: "deleteItem",
										onclick: "deleteItem('"+item.item_code +"');",
										value: "삭제"
										
									})
								)
					);
					
					trTags.push(tr);
				});
		$("#listBody").html(trTags);
		$("#pagingArea").html(resp.pagingHTML);
	}
	
	

	$("#excelBtn").click(function() {
		$("#excelForm").submit();
	});
	
	<c:if test="${not empty message }">
		swal("등록완료", "품목 등록이 완료되었습니다.", "success");
	</c:if>
</script>