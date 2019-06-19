<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.js"></script>
<security:authorize access="isAuthenticated()">
	<security:authentication property="principal" var="user"/>
<%-- 	<security:authentication property="details" var="details"/> --%>
<%-- 	<h2>${user.emp_name }님 --%>
<%-- 	<a href="${pageContext.request.contextPath }/common/logout">로그아웃</a></h2> --%>
<!-- 	<div style="border:1px solid black;"> -->
<%-- 		${user }<hr/>${details } --%>
<!-- 	</div>  -->
</security:authorize>    
<style>
	.mainHeaderWrap { overflow: hidden; }
	.titleBox { margin: 10px 0 10px 0;  }
	.titlePTag { margin:0; font-weight: normal; color:#999;}
	.topBox { float: left; overflow: hidden; border-right: 1px solid #ececec; padding: 8px 15px; }
	.topBox:LAST-CHILD { border-right: 0; }
	.topBox > h4 { text-align: center;  font-weight: normal; color:#565656; }
	.imgBox { float: left; padding:0 4px; }
	.imgBox > img { width: 100px; height: 95px; }
	.imgBox > p { text-align: center; color:#888; }
	
	.topBoxWrap { overflow: hidden; margin-top: 10px; }
/* 	.targetBox p { margin: 15px 0;  } */
/* 	.mainContentWrap{ margin-top: 15px; } */
	.mainContentBox { overflow: hidden; }
	
	.mainProfitChartWrap { margin: 25px 0; max-width: 750px; }
	.mainProfitChartWrap > h4 { text-align: center; margin-bottom:-50px; color:#656565; }
	
	.locationWrap { margin-top: 15px; }
</style>
<div class="container col-md-12">
	<div class="mainHeaderWrap">
		<div class="titleBox">
			<h1>ISMS &nbsp;통합영업관리시스템</h1>
			<h4 class="titlePTag">Integrated Sales Management System</h4>		
		</div>
	</div>
	<div class="mainContentWrap">
		<div class="mainContentBox">
<!-- 			<h3>Target</h3> -->
<!-- 			<div class="targetBox"> -->
<%-- 				<img src="${pageContext.request.contextPath }/images/smallerEnterprises.jpg"> --%>
<!-- 				<p>ISMS는 영업과 구매, 재고관리 프로세스를 핵심으로 하는 중소기업을 타겟팅한 프로그램입니다.</p> -->
<!-- 			</div> -->
			<div class="topBoxWrap">
				<div class="topBox">
					<h4>ERP</h4>
					<div class="imgBox">
						<img src="${pageContext.request.contextPath }/images/icon5.png">
						<p>재고/매출 관리</p>
					</div>
				</div>
				<div class="topBox">
					<h4>GROUPWARE</h4>
					<div class="imgBox">
						<img src="${pageContext.request.contextPath }/images/icon1.png">
						<p>전자결재</p>
					</div>
					<div class="imgBox">
						<img src="${pageContext.request.contextPath }/images/icon2.png">
						<p>메일</p>
					</div>
					<div class="imgBox">
						<img src="${pageContext.request.contextPath }/images/icon3.png">
						<p>메신저</p>
					</div>
					<div class="imgBox">
						<img src="${pageContext.request.contextPath }/images/icon4.png">
						<p>일정관리</p>
					</div>
				</div>
				<div class="topBox">
					<h4>CRM</h4>
					<div class="imgBox">
						<img src="${pageContext.request.contextPath }/images/icon6.png">
						<p>거래처 관리</p>
					</div>
					<div class="imgBox">
						<img src="${pageContext.request.contextPath }/images/icon7.png">
						<p>상품검출</p>
					</div>
				</div>
			</div>
			<div class="mainProfitChartWrap">
				<h4>월별 실적 그래프</h4>
				<canvas id="myChart"></canvas>
			</div>
			<div class="locationWrap">
				<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3214.4745939286317!2d127.41796741556347!3d36.32504600200045!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x35654ed2a5624215%3A0xd5804494a6b5b505!2z64yA642V7J247J6s6rCc67Cc7JuQ!5e0!3m2!1sko!2skr!4v1559787282559!5m2!1sko!2skr" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
			</div>
		</div>
	</div>
</div>

<!-- 전체 매출 조회 리스트 -->
<script>


//	chart클래스를 인스턴스화. => 차트를 그리려는 캔버스의 2d컨텍스트를 전달하여 수행
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
//						for(var i=0; i<3; i++){
						'rgba(000,102,204,0.3)', 'rgba(000,102,204,0.3)','rgba(000,102,204,0.3)','rgba(000,102,204,0.3)','rgba(000,102,204,0.3)','rgba(000,102,204,0.3)'
//						}
				]
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
					display : true, //레이블 화면에 나오게함
					labelString : 'y축' //레이블
				}
			} ],
			xAxes : [ { //x축 설정
				scaleLabel : {
					display : true, //레이블 화면에 나오게함
					labelString : 'x축' //레이블
				}
			} ]
		},
		title : { //표 제목
			display : true, //화면에 출력
			fontSize : 40, //제목 폰트
			fontColor : '#666', //폰트 색상
			fontStyle : 'bold' //폰트 스타일 : bold-굵게
// 			text : '월별 실적조회' //제목
		}
	}
});
</script>








