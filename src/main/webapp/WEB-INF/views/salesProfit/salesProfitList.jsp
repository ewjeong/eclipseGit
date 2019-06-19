
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.js"></script>
<script>
	//2. 검색 버튼을 눌렀을 때 searchType, searchWord, page를 json데이터로 넘긴다.
	//3. 응답 성공 시, 해당되는 데이터의 페이지를 보여준다
	
// 	 <c:if test="${not empty monthProfit}">
//	 var emp = ${monthProfit};
// 	 console.log(${monthProfit[0].rel_date});
//   		alert("${monthProfit[0].rel_date}");
// 	 </c:if> 
// 	 <c:if test="${not empty employeeProfit}">
//   		alert("${employeeProfit[1].soc}");
// 	 </c:if> 


	

	//페이지 변수 받아와서 페이징 처리 함수
	function paging(page) {
		$("#searchForm").find("input[name='page']").val(page);
// 		$("#searchForm").find("input[name='searchType']").val();
// 		$("#searchForm").find("input[name='searchWord']").val();
		$("#searchForm").submit();
	}

	function makeProfitPaging(resp) {
	
		var type = $("#type").val();//all
		$("#hiddenType").val($("#type").val());
		console.log($("#hiddenType").val());
		$("#hiddenWord").val($("#word").val());
		
		var pagingHTML = resp.pagingHTML;
		var profitList = resp.dataList;
		console.log(profitList);
		var trTags = [];
		if(profitList != null && profitList.length > 0){
		$(profitList).each(
				function(idx, profit) {
					var money = profit.sale_oprod_qty * profit.sale_oprod_cost;
					var tr = $("<tr>").prop("id", profit.rel_no).append(

						$("<td>").text(profit.rel_no),
						$("<td>").text(profit.rel_date),
						$("<td>").text(profit.item_name),
						$("<td>").text(profit.prod_name),
						$("<td>").text(profit.cl_no),
						$("<td>").text(profit.cl_name),
						$("<td>").text(profit.sale_oprod_qty),
						$("<td>").text(money)
					);
					trTags.push(tr);
				});
		}else{
			var tr = $("<tr>").append(
				$("<td>").text("검색결과가 없습니다.")
				.attr("colspan","8")
				.css("text-align","center")
			);
			trTags.push(tr);
		}
			
		$("#listBody").html(trTags);
		$("#pagingArea").html(resp.pagingHTML);

	}

	$(document).ready(function() {

		$("#searchBtn").on("click", function(e) {

			$("#searchForm").find("input[name='page']").val("1");
			e.preventDefault();

			$("#searchForm").submit();
		});

		$("#searchForm").on("submit", function() {

			var datas = $(".search").serialize();
			console.log(datas);
			
			
			$.ajax({
				url : '${pageContext.request.contextPath}/salesProfitList.do',
				data : datas,
				//dataType : "json",
				success : function(resp) {
					console.log(resp.dataList);
					console.log(resp);
					makeProfitPaging(resp);
				},
				error : function(errorResp){
					console.log(errorResp.status);
					swal("오류");
				}

			});
			//동기요청 취소
			return false;
		});
		paging(1);

		$("excelBtn").click(function() {
			
			$("excelForm").submit();
		});

	});
</script>

<div>
	<div style="padding-top: 20px;">  
		<div class="col-xs-10 col-md-9">
			<canvas id="myChart"></canvas>
		</div>
	</div>
		<form id="searchForm">
	<div class="container" style="margin: 30px;" >
	<div class="row justify-content-end" >
			<div class="col-2">
				<select class="form-control  search" id="type"
					name="searchType" aria-label="Example select with button addon">
					<option value="all" ${pagingVO.searchType eq 'all'? "selected":"" }>선택하기</option>
					<option value="item_name"
						${pagingVO.searchType eq 'item_name'? "selected":"" }>품목명</option>
					<option value="prod_name"
						${pagingVO.searchType eq 'prod_name'? "selected":"" }>제품명</option>
					<option value="cl_name"
						${pagingVO.searchType eq 'cl_name'? "selected":"" }>거래처명</option>
				</select>
			</div>
			<div class="  col-3">
				<input type="text" id="word" class="search input-group form-control" name="searchWord"
					value="${pagingVO.searchWord }">
			</div>
<!-- 		<div class="form-control  col-1"> -->
				<!-- 							페이징처리 하기 위한 폼 -->
			<span class="input-group-btn input-group-append">
				<button class="btn btn-default btnSearch" id="searchBtn" type="submit">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
				<input type="hidden" class="search" name="page">
			
<!-- 		</div> -->
</div>
</div>
		</form>

	<div style="padding-top: 30px; padding-right: 20px;">
		<div>
			<div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>매출번호</th>
							<th>매출일자</th>
							<th>품목명</th>
							<th>제품명</th>
							<th>거래처코드</th>
							<th>거래처명</th>
							<th>수량</th>
							<th>매출</th>
						</tr>
					</thead>
					<tbody id="listBody">
						<%-- 						<c:set var="profitList" value="${pagingVO.dataList }"></c:set> --%>
						<%-- 						<c:forEach var="profit" items="${profitList }"> --%>
						<%-- 							<tr id="${profit.rel_no }"> --%>
						<%-- 								<td>${profit.rel_no }</td> --%>
						<%-- 								<td>${profit.rel_date }</td> --%>
						<%-- 								<td>${profit.item_name }</td> --%>
						<%-- 								<td>${profit.prod_name }</td> --%>
						<%-- 								<td>${profit.cl_no }</td> --%>
						<%-- 								<td>${profit.cl_name }</td> --%>
						<%-- 								<td>${profit.sale_oprod_qty }</td> --%>
						<%-- 								<td>${profit.sale_oprod_qty*profit.sale_oprod_cost }</td> --%>
						<!-- 							</tr> -->
						<%-- 						</c:forEach> --%>
					</tbody>
					<tfoot>
					<tr >
					<td colspan="8">
						<div id="pagingArea" class="pageWrap">${pagingVO.pagingHTML }
						</div>
					</td>
					</tr>
					</tfoot>
				</table>
			</div>
			<form
				action="${pageContext.request.contextPath }/salesProfitListExcel.do"
				id="excelForm" method="post">
				<div style="padding-bottom: 20px;">
					<input type="hidden" id="hiddenWord" name="hiddenWord"> <input
						type="hidden" id="hiddenType" name="hiddenType">
					<button class="btn btn-outline-primary col-xs-4 col-md-2"
						id="excelBtn">엑셀 출력</button>
				</div>
			</form>
			
		</div>
	</div>
</div>
<script>
	// 	chart클래스를 인스턴스화. => 차트를 그리려는 캔버스의 2d컨텍스트를 전달하여 수행
	var ctx = document.getElementById('myChart').getContext('2d');

	// 	실제 데이터 값 세팅: 막대와 선 그래프 두개. 배열의 형태로 입력한다.
	
var data = {
		datasets : [
			// 여기서부터
			
			  
			
				{
					label : '월별매출', //범례
					data : [ 
						<c:set var="monthInputProfit" value="${monthInputList}"></c:set>
						<c:forEach var="monthInput" items="${monthInputProfit}">
							${monthInput.soc},
						</c:forEach>
					], //막대그래프 데이터
							
					backgroundColor : [ //막대그래프 색상
// 						for(var i=0; i<3; i++){
							
							'rgba(000,102,204,0.3)', 'rgba(000,102,204,0.3)','rgba(000,102,204,0.3)','rgba(000,102,204,0.3)','rgba(000,102,204,0.3)','rgba(000,102,204,0.3)'
							
// 						}
					],
					
				},
				  
			//여기까지 포문돌려서 월별 사원의 그래프를 만든다. data는 해당월 실적, label은 사원명 background는 색 랜덤..?
				{
					label : '순이익', //범례
					data : [ 
						<c:set var="pureList" value="${pureProfitList}"></c:set>
						<c:forEach var="pureProfit" items="${pureList}">
							${pureProfit.soc - pureProfit.poc},
						</c:forEach>
						
						], //라인데이터	
					backgroundColor : '#94B695', //라인 채우기색
					borderColor : '#94B695', //라인 색
					fill : false, //라인 아래의 영역을 채우기 선택

					type : 'line' //그래프 종류
				} ],
		
		labels : [
			<c:set var="monthList" value="${monthProfit }"></c:set>
			<c:forEach var="month" items="${monthList }">
				'${month.rel_date}',
			</c:forEach>	
				
			]
	//x축 레이블
	};

	var mixedChart = new Chart(ctx, {
		type : 'bar', //메인그래프 종류
		data : data, //위의 데이터값
		options : { //옵션
			scales : {
				yAxes : [ { //y축 설정
					ticks : {
						beginAtZero : true
					//값을 0부터 시작? true: 0부터 시작, false:데이터 시작값부터
					},
					scaleLabel : {
						display : true //레이블 화면에 나오게함
						//labelString : 'y축' //레이블
					}
				} ],
				xAxes : [ { //x축 설정
					barPercentage: 0.5,
					scaleLabel : {
						display : true //레이블 화면에 나오게함
						//labelString : 'x축' //레이블
					}
				} ]
			},
			title : { //표 제목
				display : true, //화면에 출력
				fontSize : 40, //제목 폰트
				fontColor : '#666', //폰트 색상
				fontStyle : 'bold', //폰트 스타일 : bold-굵게
				text : '월별 실적조회' //제목
			}
		// 	    	,animation:{
		// 	    		//duration : 400,	//애니메이션 그리는데 걸리는 시간, milliseconds
		// 	    		easing : 'easyInQuad',
		// 	    		onProgress: function(animation){	//애니메이션의 각 단계마다 호출 
		// 	    			console.log('onProgress Data =>' +data);
		// 	    		},
		// 	    		onComplete : function(animation){	//애니메이션 끝날 때 호출
		// 	    			console.log('onComplete data=>'+data);
		// 	    		}
		// 	    	}
		}
	});
	
	
// 	function getRandomColorHex(){
// 		var hex = "0123456789ABCDEF",
// 		color = "#";
// 		for(var i=1; i<=6; i++){
// 			color += hex[Math.floor(Math.ra)]
// 		}
// 	}

</script>

