<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<style>
	.lineListWrap{ margin-left : 8px; }
	.searchContainer{ padding:0; padding-left:3px; }
	.lineCreate { padding:10px;  margin:0; }
	.lineForm { background: none; border:1px solid #ccc;  padding: 10px 10px 0 10px;  border-radius: 5px; margin:15px 0;}
	.lineTitleWrap { padding:10px; }
	.lineSelector { margin: 5px 0;}
</style>

<script>
<c:if test="${ not empty message}">
	swal("등록 완료", "결재선 등록이 완료되었습니다." , "success");
	console.log("${ message }");
</c:if>	
	$(function(){
		var clickedBtn = ''; // .btnSearch 중에서 클릭된 버튼을 저장할 변수
		var empName = '';    // 모달창에서 선택된 사원의 이름이 저장될 변수
		var empId = '';      // 모달창에서 선택된 사원의 아이디가 저장될 변수
// 		var destination = '';
		
		function empFindList(resp){
			var empList = resp;
			 empLi = resp;
			
			var tr1 = $("<tr>").append(
					$("<td colspan='4'>").text("사원명")
					,$("<td colspan='4'>").text("사번")
					,$("<td colspan='4'>").text("부서명")
					,$("<td colspan='4'>").text("직급")
			);
			var tr2 = null;
			$(empList).each(function(idx, empPosDep){
// 				console.log(empPosDep.emp_name);
				tr2 += "<tr class='clicktr' data-empname='"+empPosDep.emp_name+ "' data-empid='"+empPosDep.emp_id+"' data-depname='"+empPosDep.dep_name+ "' data-posname='"+empPosDep.pos_name+ "'><td colspan='3'>";
				tr2 += "<input type='checkbox' class='checkAll' name='checkAll' /></td>";
				tr2 += "<td colspan='4'>" + empPosDep.emp_name +"</td><td colspan='4'>"+empPosDep.emp_no+"</td><td colspan='4'>"+ 
						empPosDep.dep_name +"</td><td colspan='4'>"+empPosDep.pos_name+"</td></tr>";
			});
			
			$("#listhead").html(tr1)
			$("#listbody").html(tr2);
		}
		
		// 돋보기 버튼을 클릭했을 때,
		$(".btnSearch").on("click", function(){
			clickedBtn = $(this); // 눌려진 버튼
			console.log(clickedBtn);
			
			$.ajax({
				url : "<c:url value='/elecAuthorization/line/getEmpList'/>"
				,method:"get"
// 				,data: "emp_name=" + emp_name
				,dataType:"json"
				,success : function(resp){
					empFindList(resp);
					//console.log(empFindList(resp));
				}
				,error : function(errorResp){
					console.log(errorResp.status);
					swal("오류");
				}
			});
		});
		
		$("#listbody").on("click","tr",function(){
			// checkbox 하나만 선택되도록
			$('input[type="radio"][name="checkAll"]').click(function(){
				if($(this).prop('checked')){
					$('input[type="checkbox"][name="checkAll"]').prop('checked',false);
					$(this).prop('checked',true);
				}
			});
			
			//document.getElementById("inputemp_name").value = emp_name;
			$("#selectBtn").click(function(){
				$(".checkAll").each(function(idx){
					var isClickedTr = $(this).prop('checked');
					if(isClickedTr){  // 선택된 상태면..
						empName = $(this).parent().parent().data("empname");
						empId = $(this).parent().parent().data("empid");
// 						destination = $(this).parent().parent().data("destination");
						console.log(empName);
						console.log(empId);
					}
				});
				
				$(clickedBtn).parent().prev().val(empName);  // 클릭된 버튼 근처의 input 태그를 찾아서 값 셋팅
				$(clickedBtn).parent().prev().prev().val(empId); // 클릭된 버튼 근처의 input 태그를 찾아서 값 셋팅
			})
		});
	});
</script>

<!-- 결재선 조회 -->
<div class="wrap_appr">
	<h3>결재선 관리</h3>
	<form class="lineForm" method="post" action="${pageContext.request.contextPath }/elecAuthorization/line/lineInsert">
		<div class="lineTitleWrap">
			<p>결재선 등록하기</p>
			<input type="text" name="fl_title" class="form-control col-xs-10 col-md-10" placeholder="결재선 제목을 입력하세요." required>
			<select name="send_code" class="custom-select col-xs-2 col-md-2 lineSelector" required>
				<option selected value="SEN001">발주</option>
				<option value="SEN002">주문</option>
				<option value="SEN003">출장</option>
				<option value="SEN004">회의</option>
				<option value="SEN005">세미나</option>
			</select>	
		</div>
		<div class="row lineCreate">
			 <div class="col-xs-12 col-md-2 form-group input-group searchContainer">
			 	 <input type="hidden" id="firstEmpId" name="fix_approvalList[0].authorized_id" >
	             <input type="text" id="firstEmpName" class="form-control" > 
<!-- 	             	name="fix_approvalList[0].authorized_id"> -->
	             <span class="input-group-btn searchWrap">
	                 <button class="btn btn-default btnSearch" type="button" id="firstBtn"
	                 	data-toggle="modal" data-target="#exampleModal">
	                 	<i class="fa fa-search"></i>
	                 </button>
	             </span>
	         </div>
	         <div class="col-xs-12 col-md-2 form-group input-group searchContainer">
	         	 <input type="hidden" id="secondEmpId" name="fix_approvalList[1].authorized_id" >
	             <input type="text" id="secondEmpName" class="form-control"> 
<!-- 	             	name="fix_approvalList[1].authorized_id"> -->
	             <span class="input-group-btn searchWrap">
	                 <button class="btn btn-default btnSearch" type="button" id="secondBtn"
	                 	data-toggle="modal" data-target="#exampleModal">
	                 	<i class="fa fa-search"></i>
	                 </button>
	             </span>
	         </div>
	         <div class="col-xs-12 col-md-2 form-group input-group searchContainer">
	         	 <input type="hidden" id="thirdEmpId" name="fix_approvalList[2].authorized_id" >
	             <input type="text" id="thirdEmpName" class="form-control">
<!-- 	             	 name="fix_approvalList[2].authorized_id"> -->
	             <span class="input-group-btn searchWrap">
	                 <button class="btn btn-default btnSearch" type="button" id="thirdBtn"
	                 	data-toggle="modal" data-target="#exampleModal">
	                 	<i class="fa fa-search"></i>
	                 </button>
	             </span>
	         </div>
	         <div class="col-xs-12 col-md-2 form-group input-group searchContainer">
	         	 <input type="hidden" id="thirdEmpId" name="fix_approvalList[3].authorized_id" >
	             <input type="text" id="thirdEmpName" class="form-control">
<!-- 	             	 name="fix_approvalList[3].authorized_id"> -->
	             <span class="input-group-btn searchWrap">
	                 <button class="btn btn-default btnSearch" type="button" id="thirdBtn"
	                 	data-toggle="modal" data-target="#exampleModal">
	                 	<i class="fa fa-search"></i>
	                 </button>
	             </span>
	         </div>
	         &nbsp;
	         <div class="col-xs-12 col-md-1 form-group input-group">
	         	<button type="submit" class="btn btn-outline-primary" 	>
	         		<i class="fas fa-plus"></i>
	         	</button>
	         </div>
		</div>
	</form>
	
	<div class="row lineListWrap">
		<table class="table table-sm approTable">
			<thead>
				<tr>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col" colspan="2">결재자</th>
				</tr>
			</thead>
			<tbody id="lineListBody">
				<c:set value="${pagingVO.dataList }" var="fixLineList" />
				<c:if test="${not empty fixLineList }">
					<c:forEach items="${fixLineList }" var="fixLine">
						<tr id="${fixLine.fl_no }">
							<td>${fixLine.fl_title }</td>
							<td>${ fixLine.emp_name }</td> <!-- 결재선 작성자 이름 --> 
							<td>
								${fixLine.first } &nbsp;|&nbsp; ${fixLine.second }
								<c:if test="${not empty fixLine.last }">
									 &nbsp;|&nbsp; ${fixLine.last }
								</c:if>
							</td>
							<td><a class="delBtn"><i class="fas fa-minus-square fa-lg"></i></a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<!-- 페이지네이션 -->
		<div class="pageWrap">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item disabled"><a class="page-link" href="#" tabindex="-1" aria-disabled="true">&lt;</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">&gt;</a></li>
				</ul>
			</nav>
		</div>
		
		<!--사원 검색하기 버튼을 눌렀을 때의 모달창-->
         <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
            aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
               <div class="modal-content">
                  <div class="modal-header">
                     <h5 class="modal-title" id="exampleModalLabel">사원 검색</h5>
                     <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                     </button>
                  </div>
                  <div class="modal-body">
                  <form action="/setting/settingPage" method="post">
                     <div class="container">
                        <div class="row">
                        	<div class="col">
                           	<table class="table table-borderless">
                           		<thead id="listhead">
                           		
                           		</thead>
                           		<tbody id="listbody">
                           		</tbody>
                           	</table>
                           </div>
                        </div>
                     </div>
                     </form> 
                  </div>
                  <div class="modal-footer">
                     <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">닫기</button>
                     <button type="button" id="selectBtn" class="btn btn-primary" data-dismiss="modal">선택하기</button>
                  </div>
               </div>
            </div>
         </div>
         
		
	</div>
</div>