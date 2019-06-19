<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<style>
.fileBox{ padding: 10px; }
</style>
<div>
	<h3>상신함</h3>
	<h5> &nbsp;결재 등록</h5>
	<form:form id="contentForm" method="post" enctype="multipart/form-data" modelAttribute="approval">
		<input id="ordCodeHidden" type="hidden" value="${saleOrPurOrdCode }" name="send_type_code">
		<div class="ap_metaData">
			<div class="row ap_mdWrap">
				<label class="col-xs-2 col-md-2">제목</label>
				<input type="text" class="form-control col-xs-2 col-md-4" name="elec_title" required value="${approval.elec_title }"/>
			</div>
			<div class="row ap_mdWrap">
				<label class="col-xs-2 col-md-2">일자</label>
				<input type="date" id="sangshinDate" class="form-control col-xs-2 col-md-4" name="elec_senddate" required value="${approval.elec_senddate }">
			</div>
			<div class="row ap_mdWrap">
				<label class="col-xs-2 col-md-2">분류</label>
				<!-- select -->
				<select class="custom-select col-xs-2 col-md-4" name="send_code" required >
					<option selected value="SEN001">발주</option>
					<option value="SEN002">주문</option>
					<option value="SEN003">출장</option>
					<option value="SEN004">회의</option>
					<option value="SEN005">세미나</option>
				</select> 
			</div>
			<div class="row ap_mdWrap">
				<label class="col-xs-2 col-md-2">참조자</label>
				<div class="col-xs-12 col-md-4 form-group input-group searchContainer">
<!-- 		             <input type="text"  class="form-control" name=""> -->
					 <p id="referNames" class="form-control"></p>
		             <span class="input-group-btn searchWrap">
		                 <button class="btn btn-default btnSearch" type="button"
		                 	data-toggle="modal" data-target="#referenceModal">
		                 	<i class="fa fa-plus" style="padding-top: 6px; "></i>
		                 </button> 
		             </span>
		         </div>
			</div>
			<hr color="#f8f8f8" style="margin:0 0 15px 0;">
			<div class="row ap_mdWrap">
				<label class="col-xs-2 col-md-2">결재선</label>
				<!-- select -->
				<select id="lineSelect" class="custom-select col-xs-2 col-md-4" name="fl_no" required>
					<c:forEach items="${fixLineList }" var="fixLine">
						<c:if test="${not empty fixLine.fl_no }">
							<option value="${fixLine.fl_no }">${fixLine.fl_title }</option>
						</c:if>
					</c:forEach>
				</select> 
<!-- 				<div class="col-xs-8 col-md-3"> -->
<!-- 					<button type="button" class="btn btn-primary col-xs-12"><i class="far fa-edit"></i>등록</button> -->
<!-- 				</div>	 -->
			</div>
			<div class="row ap_mdWrap">
				<label class="col-xs-2 col-md-2">양식</label>
				<!-- select -->
				<select id="formSelect" class="custom-select col-xs-2 col-md-4" name="elec_form_code" required>
					<c:forEach items="${ formList }" var="apForm">
						<option value="${apForm.elec_form_code }">${apForm.elec_form_title }</option>					
					</c:forEach>
				</select> 
				<div class="col-xs-8 col-md-3">
					<button type="submit" id="applyBtn" class="btn btn-primary col-xs-12">
						<i class="fas fa-check"></i>적용
					</button>
				</div>
			</div>
		</div>
	
		<div class="ap_content">
			<div class="ap_linesWrap">
				<label class="lineTitle">결재선</label>
<!-- 				<input type="hidden" name="apprLineList[0].authorized_id" /> -->
<!-- 				<input type="hidden" name="apprLineList[1].authorized_id" /> -->
<!-- 				<input type="hidden" name="apprLineList[2].authorized_id" /> -->
				<ul class="lineList" >
	<!-- 				<li><p class="position">사원(직책)</p>이사원(사원명)</li> -->
				</ul>
			</div>
			<div class="ap_mainWrap">
				<div class="ap_main">
					<div class="ap_formWrap">
						<textarea id="elec_content" cols="70" rows="30" name="elec_content">
	<%-- 						${ form.elec_form_html } --%>
						</textarea>	
					</div>
				</div>
				<div class="ap_attach">
					<!-- 파일 선택하기 -->
					<div class="input-group mb-3">
						<div class="custom-file fileBox">
							<label for="file"><i class="fas fa-paperclip"></i></label>
							<input type="file" name="elec_files" >
						</div>
	<!-- 					<div class="input-group-append"> -->
	<!-- 						<span class="input-group-text" id="inputGroupFileAddon02">등록</span> -->
	<!-- 					</div> -->
					</div>
				</div>
			</div>
			<div class="ap_btns">
				<button type="button" class="btn btn-outline-primary col-xs-4 col-md-2 offset-md-4">PDF 출력</button>
				<button id="submitBtn" type="submit" class="btn btn btn-primary col-xs-4 col-md-2" >완료</button>				
			</div>
		</div>
	</form:form>
</div>

<!-- 참조자 선택 시, .btnSearch 눌렀을 때의 모달창-->
	<div class="modal fade" id="referenceModal" tabindex="-1" role="dialog"
	   aria-labelledby="exampleModalLabel" aria-hidden="true">
	   <div class="modal-dialog" role="document">
	      <div class="modal-content">
	         <div class="modal-header">
	            <h5 class="modal-title" id="referenceModalLabel">참조자 지정</h5>
	            <button type="button" class="close" data-dismiss="modal"
	               aria-label="Close">
	               <span aria-hidden="true">&times;</span>
	            </button>
	         </div>
	         <div class="modal-body">
		         <form id="modalForm" action="/setting/settingPage" method="post">
		            <div class="container">
		               <div class="row">
		               	<div class="col">
		                  	<table class="table table-borderless">
		                  		<thead id="referListhead">
		                  		
		                  		</thead>
		                  		<tbody id="referListbody">
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

<script>
<c:if test="${ not empty message}">
	swal("등록 실패 ", "${message}" , "error");
	console.log("${ message }");
</c:if>

	//모달창에서 (참조자)선택하기 버튼을 클릭하면
	$("#selectBtn").click(function(){
		cnt = cnt + 1 ; // 버튼 누른 횟수 증가 -- unbind 처리로 인해, 계속 초기화 됨.. 다른 방법을 찾아야 함
		
		$(".checkAll").each(function(idx){
			var isClickedTr = $(this).prop('checked');
			if(isClickedTr){  // 선택된 상태면..
				empName = $(this).parent().parent().data("empname");
				empId = $(this).parent().parent().data("empid");
	//				destination = $(this).parent().parent().data("destination");
				console.log(empName);
				console.log(empId);
				$(this).prop('checked', false);
			}
		});
		// input 태그를 생성하고 name="referenceList[idx].emp_id" 로 셋팅하고(idx : #selectBtn 누른 횟수)
		// 이를 감싸고 있는 div .searchContainer 안에 .append(inputTag) 해주기
	//		var referInput = ;
	//		console.log(referInput);
		$(".searchContainer").append(
								$("<input>").attr({
									"type" : "hidden"
									, "name" : "referenceList["+ (cnt - 1) +"].emp_id"
									, "class" : "referInputTags"
								}).val(empId)
							);
		$("#referNames").append(empName +"  "); // 이름 추가
	});

	$(".btnSearch").on("click", function(){
		// 사원 리스트 가져오기
		$.ajax({
			url : "<c:url value='/elecAuthorization/line/getEmpList'/>"
			, method: "get"
//				, data: "emp_name=" + emp_name
			, dataType: "json"
			, success : function(resp){ // resp 에 empList 셋팅됨
				empFindList(resp);
				//console.log(empFindList(resp));
			}
			, error : function(errorResp){
				console.log(errorResp.status);
				swal("오류");
			}
		});
	});
	
	var cnt = 0;
	$("#listbody").on("click","tr",function(){
		// checkbox 하나만 선택되도록
		$('input[type="radio"][name="checkAll"]').click(function(){
			if($(this).prop('checked')){
				$('input[type="checkbox"][name="checkAll"]').prop('checked',false);
				$(this).prop('checked',true);
			}
		});
	});
	
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
//				console.log(empPosDep.emp_name);
			tr2 += "<tr class='clicktr' data-empname='"+empPosDep.emp_name+ "' data-empid='"+empPosDep.emp_id+"' data-depname='"+empPosDep.dep_name+ "' data-posname='"+empPosDep.pos_name+ "'><td colspan='3'>";
			tr2 += "<input type='checkbox' class='checkAll' name='checkAll' /></td>";
			tr2 += "<td colspan='4'>" + empPosDep.emp_name +"</td><td colspan='4'>"+empPosDep.emp_no+"</td><td colspan='4'>"+ 
					empPosDep.dep_name +"</td><td colspan='4'>"+empPosDep.pos_name+"</td></tr>";
		});
		
		$("#referListhead").html(tr1)
		$("#referListbody").html(tr2);
	}

	CKEDITOR.replace('elec_content', {
		height : 700,
	}); 
	
// 	CKEDITOR.editorConfig = function( config ) {
// 	    config.language = 'fr';
// 	    config.uiColor = '#AADC6E';
// 	    config.extraPlugins = 'colorbutton';
// 	};
	
	$(function(){
		// applyBtn 버튼을 클릭한 횟수를 저장할 변수
		var clickCnt = 0;
		
		$("#applyBtn").on("click", function(){
			clickCnt++; 
// 			alert(clickCnt + "번 클릭했습니다.");
			
			var flNo = $("#lineSelect").val(); // fl_no 가져오기
			var formCode = $("#formSelect").val(); // elec_form_code 가져오기
			var ordCode = $("#ordCodeHidden").val(); // ordCode 가져오기
			// ajax로 내보낼 data
			var allData = { "fl_no" : flNo, "elec_form_code" : formCode , "saleOrPurOrdCode" : ordCode};
			
			$.ajax({
				url : "${pageContext.request.contextPath}/elecAuthorization/sangshin/sangshinInsert",
				data : allData,
				dataType : "json",
				success : function(data){
					var fixLines = data.fixLines;
					var form = data.form;
					console.log(data.fixLines);
					
// 					// 결재선에 값 셋팅
// 					$(".lineTitle").text(data.fixLines.fl_title);
					var liTags = [];
					$(data.fixLines.fix_approvalList).each(function(idx, fixApproval){
						// ul li 태그에 직책명, 사원명 셋팅
						var li = $("<li>").append(
									$("<p>").text(fixApproval.pos_name)
											.addClass("position")
								).append(fixApproval.emp_name);
						liTags.push(li);
					});
					$(".lineList").html(liTags); // ul 태그에 li태그들 셋팅
					
					$(data.fixLines.fix_approvalList).each(function(idx, fixApproval){
						// input type="hidden" 에 우선순위, 결재자 셋팅
						// 예시 : <input type="hidden" name="apprLineList[0].authorized_id" /> 
						var inputHidden = $("<input>").attr({
											type : "hidden" 
											, name :  "apprLineList["+idx+"].authorized_id"
										})
												 	 .val(fixApproval.authorized_id);
						$(".ap_linesWrap").append(inputHidden); // 결재선 div 에 inputHidden 셋팅	
					});
					
// 					// 결재양식에 값 셋팅
// 					$("#elec_form_html").val(form.elec_form_html); 이렇게 하면 ckeditor가 인식할 수 없음
					CKEDITOR.instances.elec_content.setData(form.elec_form_html);
				}
			});
			return false;
		}); 
	});
</script>











