<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.js"></script>
		
<%-- 	<form id="excelForm" method="post" action="${pageContext.request.contextPath}/performanceListExcel"> --%>
<!-- 		<input type="hidden" name="emp_name" id="emp_nameHI"/> -->
<!-- 		<input type="hidden" name="rel_date" id="rel_dateHI"/> -->
<!-- 		<div class="col-md-4 offset-md-7" style="padding-bottom: 20px;"> -->
<!-- 			<button type="button" id="excelBtn" class="btn btn-outline-primary">다운로드</button> -->
<!-- 		</div> -->
<!-- 	</form>		 -->
			
	<form id="searchForm">
					
			<div class="row col-xs-12 col-md-7 form-group input-group offset-md-6">
<!-- 				<div class="" style="float: right; "> -->
						<div class="input-group col-md-5">
							<input type="text" id="emp_name" name="emp_name" class="form-control"	placeholder="사원명 검색"> 
							<span class="input-group-btn searchWrap">
								<button class="btn btn-default btnSearch" id="nameBtn" type="button">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
						<div class="input-group col-md-5">
							<input type="hidden" name="page"/>
							<input type="date" id="rel_date" name="rel_date" class="form-control"> 
							<span class="input-group-btn searchWrap">
								<button class="btn btn-default btnSearch" id="dateBtn" type="button">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
				</div>
<!-- 			</div> -->
	</form>	
		
		<div style="padding-top: 30px; padding-right: 20px;">
			<div>
				<div>
					<table class="table table-hover" >
						<thead>
							<tr>
								<th>매출일자</th>
								<th>사원코드</th>
								<th>사원명</th>
								<th>거래처코드</th>
								<th>거래처명</th>
								<th>상품명</th>
								<th>수량</th>
								<th>공급액</th>
								<th>매출</th>
							</tr>
						</thead>
						<tbody id="listBody">
<!-- 							<tr> -->
<!-- 								<td>#</td> -->
<!-- 								<td>#</td> -->
<!-- 								<td>#</td> -->
<!-- 								<td>#</td> -->
<!-- 								<td>#</td> -->
<!-- 								<td>#</td> -->
<!-- 								<td>#</td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>#</td> -->       
<!-- 								<td>#</td> -->
<!-- 								<td>#</td> -->
<!-- 								<td>#</td> -->
<!-- 								<td>#</td> -->
<!-- 								<td>#</td> -->
<!-- 								<td>#</td> -->
<!-- 							</tr> -->
						</tbody>
						<tfoot>
							<tr>
								<td colspan="9" id="pagingArea">${pagingVO.pagingHTML }</td>
							</tr>
						</tfoot>
						
					</table>
	<form id="excelForm" method="post" action="${pageContext.request.contextPath}/performanceListExcel">
		<input type="hidden" name="emp_name" id="emp_nameHI"/>
		<input type="hidden" name="rel_date" id="rel_dateHI"/>
		<div>
			<button type="button" id="excelBtn" class="btn btn-outline-primary">엑셀출력</button>
		</div>
	</form>		
<!-- 					차트 -->
			</div>
				<div style="padding-top: 20px;">
					<div >
						<canvas id="myChart"></canvas>	
					</div>
					      
				</div>
				
			</div>
		</div>
		
		
		
		
	<script>
	
	
	function paging(page){
		$("input[name='emp_name']").val($("#emp_name").val());
		console.log($("#emp_name").val());
		
		$("input[name='rel_date']").val($("#rel_date").val());
		console.log($("#rel_date").val());
		
		$("input[name='page']").val(page);
		console.log($("input[name=page]").val());
		$("#searchForm").submit();
	}
	
	//emp_name이라는 전역변수 생성
	
// 	console.log("global:"+globalemp_name);
	
	//tbody listBody채우기
	function listbody(resp){
		var salesList = resp.dataList;
		var pagingHTML = resp.pagingHTML;
		var trTag=[];
		
		if(salesList !=null && salesList.length>0){
			$(salesList).each(function(idx,sales){
				var tr = $("<tr>").append(
					$("<td>").text(sales.rel_date)
					,$("<td>").text(sales.emp_no)          
					,$("<td>").text(sales.emp_name)
					,$("<td>").text(sales.cl_no)
					,$("<td>").text(sales.cl_name)
					,$("<td>").text(sales.prod_name)
					,$("<td>").text(sales.sale_oprod_qty)
					,$("<td>").text(sales.sale_oprod_cost)
					,$("<td>").text(sales.income)
				);     
				trTag.push(tr);
			})//saleList end
		}else{
			var tr = $("<tr>").append(
					$("<td>").text("검색어에 해당하는 활동이 없습니다.")
					.attr("colspan", "10")
					.css("text-align", "center")
		   		);
			trTag.push(tr);
		}
		$("#listBody").html(trTag);
		$("#pagingArea").html(pagingHTML);
	}//listbody function end
	
	
	
	$(function(){
		//엑셀 다운로드
		//다운로드 버튼을 눌렀을때
		$("#excelBtn").on("click",function(){
			$("#emp_nameHI").val($("#emp_name").val());
			console.log($("#emp_name").val());
			$("#rel_dateHI").val($("#rel_date").val());
			console.log($("#rel_date").val());
			
// 			location.href="${pageContext.request.contextPath}/performanceListExcel";
			$("#excelForm").submit();
			
		})

		//처음에 페이지가 렌더링될때부터 그려진것
		//이름검색
		$("#nameBtn").click(function(){
			paging(1);
			$("#searchForm").submit();
		})
		
		//날짜검색
		$("#dateBtn").click(function(){
			paging(1);
			$("#rel_date").val();
			console.log($("#rel_date").val());
			$("#searchForm").submit();
		})
			
		
		//페이징처리
		$("#searchForm").submit(function(){
			
			
			var queryString = $(this).serialize();
			
			//전역변수에 emp_name의  value값을 셋팅
			globalemp_name = $("#emp_name").val();
			console.log($("#emp_name").val());
			
			
			$.ajax({
				url :'${pageContext.request.contextPath}/superManager/emplScheduleManage/performanceList'
				,data : queryString
				,dataType : 'json'
				,success:function(resp){
					listbody(resp);
					makeChart(globalemp_name);
					
				}
			});//ajax end
			//동기요청취소
    		return false;
		})//searchForm end
		
// 		var emp_name = emp_name;//$("#emp_name").val();
		var globalemp_name= $("#emp_name").val();
		console.log("chart:"+globalemp_name);
		
		//매출차트 로딩
		makeChart();
		
		
		paging(1);
	})//$function end
	
	function makeChart(emp_name){
// 		var emp_name = globalEmp_name;//$("#emp_name").val();
		console.log("chart:"+emp_name);
		//매출차트 ajax
		$.ajax({
			url:'${pageContext.request.contextPath}/superManager/emplScheduleManage/chart'
			,data:{
				emp_name:emp_name?emp_name:""//이름 검색 조건
			}
			,dataType:'json'
			,success:function(chart){
				salesChart(chart);
			}
		});//ajax end
		
	}
	
	function salesChart(chart){
		//	 	chart클래스를 인스턴스화. => 차트를 그리려는 캔버스의 2d컨텍스트를 전달하여 수행
		 		var ctx = document.getElementById('myChart').getContext('2d');
				
		 		var chartList = chart.dataList;
		 		console.log(chartList);
				
		 		var chartData = [];
		     	$(chartList).each(function(i,ch){
	            	chartData.push(ch.sales);
	            	console.log(chartData);
	           	})
       
	           	var monthLabel =[];
		     	$(chartList).each(function(i,ch){
		    		 monthLabel.push(ch.month);
		    		 console.log(monthLabel[0]);
		    	 })
		    	 
		    	var label=[];
		     	$(chartList).each(function(i,ch){
	     			label.push(ch.emp_name);
		     	})
		     	console.log("label:"+label);
		     	
		     	//중복된 사람 제거
		     	var single = label.reduce((a,b)=>{
		     		if(a.indexOf(b)<0) a.push(b);
		     		return a;
		     	},[]);
		     	console.log("single:"+single);
		     	
		     	//중복된 날짜 제거
		     	var month = monthLabel.reduce((a,b)=>{
		     		if(a.indexOf(b)<0) a.push(b);
		     		return a;
		     	},[]);
		     	console.log("month:"+month);
		     	
		    	 
				
		     	
		     	var colorCode=[];
			     	for(var i=0; i<single.length;i++){
		            	var color="#"+Math.round(Math.random()*0xffffff).toString(16);
	           			colorCode.push(color);
// 	            		return colorCode;
	            	}
		     	
		     	
			     	
		     	var dataset=[];
		     	//포문 사람수만큼
		     	//조회된사람 과
		     	//데이터셋 사이즈 3개
		     	//사람수를 세라
		     	//그 사람수를 맞추어서 반복문을 돌려서
		     	//객체하나의 명세를 데이터셋에 계속 푸쉬해라
		     	//그 데이터셋을 datasets안에 넣기
		     	
		     	console.log(single.length);
		     	/*
		     	for(var i = 0; i<single.length;i++){
					var aa =[];
					
					aa ={
							label:single[i],
							data:chartData,
							backgroundColor:colorCode[i]
						}
					dataset.push(aa);
					console.log("aa:"+aa);
					console.log("222chart:"+chartData);
				}
		     	*/
		     	
				for(var i = 0; i<single.length;i++){
						var tempLabel = single[i];
						var tempData = 0;
						var tempDataArr = [];
						var tempColor = colorCode[i];
		     		
		     		for(var k=0; k<month.length; k++){
						
						$(chartList).each(function(ii,ch){
							if(ch.month==month[k] && ch.emp_name==tempLabel){
								tempData = ch.sales;
								return;
							}
			            	
			           	});
						
						console.log("----->" + tempData);
						tempDataArr.push(tempData);
					}
		     		console.log(">>>>>>>" + tempDataArr);
					var aa ={
							label:tempLabel,
							data:tempDataArr,
							backgroundColor:tempColor
						}
					dataset.push(aa);
					console.log("aa:"+aa);
		     	}
				
				
				
		//	 	실제 데이터 값 세팅: 막대와 선 그래프 두개. 배열의 형태로 입력한다.
		 		var data = {
				
// 							dataset,
		 			        datasets: 
// 		 			            label: single,	//범례
// 		 			            data: chartData,	//막대그래프 데이터
// 		 			        	backgroundColor:[		//막대그래프 색상
// 		 			            	'rgba(255,99,13,0.6)',             
// 		 			            	'rgba(54,162,235,0.6)',             
// 		 			            	'rgba(255,206,86,0.6)',             
// 		 			            	'rgba(75,192,192,0.6)',
// 		 			            	'rgba(255,99,13,0.6)',             
// 		 			            	'rgba(54,162,235,0.6)',             
// 		 			            	'rgba(255,206,86,0.6)',             
// 		 			            	'rgba(75,192,192,0.6)',
// 		 			            	'rgba(255,99,13,0.6)',             
// 		 			            	'rgba(54,162,235,0.6)',             
// 		 			            	'rgba(255,206,86,0.6)',             
// 		 			            	'rgba(75,192,192,0.6)'
// // 		 			        		colorCode
// 		 		                ]
		 			        	dataset
		 			        
		 			          ,
					  
		 			     labels: month
					    	 
		 			      //x축 레이블
		 	 	    };
				
					
		 		var globalemp_name= $("#emp_name").val();
		    	 	if(!globalemp_name){
		    	 		globalemp_name='전체';
		    	 	}
		    	 
		 			var mixedChart = new Chart(ctx, {		
		 			    type: 'bar',	//메인그래프 종류
		 			    data: data,		//위의 데이터값
		 			    options: {		//옵션
		 			    	scales:{
		 			    		yAxes:[{	//y축 설정
		 			    			ticks:{
		 			    				beginAtZero:true	//값을 0부터 시작? true: 0부터 시작, false:데이터 시작값부터
		 			    			},
		 			    			 scaleLabel: {
		 			                     display: true,		//레이블 화면에 나오게함
		 			                     labelString: '매출액'	//레이블
		 			                 }
		 			    		}],
		 			    		xAxes:[{			//x축 설정
		 			    			 scaleLabel: {	 
		 			                     display: true,    //레이블 화면에 나오게함
		 			                     labelString: '월별' //레이블
		 			                 }
		 			    		}]
		 			    	},
		 		    	title:{					//표 제목
		 			    	display:true,		//화면에 출력
		 			    	fontSize :40,		//제목 폰트
		 			    	fontColor:'#666',	//폰트 색상
		 			    	fontStyle:'bold',	//폰트 스타일 : bold-굵게
		 			    	text: globalemp_name+' 사원 실적조회'	//제목
		 			   	   }

		 			    }
		 			});
				
				
		 	}	
	
	
	

	</script>
