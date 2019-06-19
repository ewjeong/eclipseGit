<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <script src="${pageContext.request.contextPath}/lastProject/src/main/webapp/js/sockjs.js"></script> --%>

<div class="approWrap">
	<div class="approHeader">
		<h3>결재함</h3>
		<p>${approval.elec_no}번 <span class="titleBold">${approval.elec_title }</span> 기안서</p>
	</div>
	<div class="elecLine">
		<ul class="lineList">
		<c:forEach items="${ lineList }" var="line">
			<li>
				<p class="position">${line.pos_name }</p>
				${line.emp_name }
			</li>
		</c:forEach>
		</ul>
	</div>
	<div class="ap_content" >
		<textarea id="elec_content" cols="70" rows="30" name="elec_content" readonly="readonly">
<%-- 			${approval.form.elec_content } --%>
			${approval.elec_content }
		</textarea>
		<div class="fileWrap">
			<p>첨부파일</p>
			<c:if test="${not empty approval.fileList }">
				<c:forEach items="${approval.fileList }" var="attach" varStatus="vs">
					<c:url value="/elecAuthorization/download" var="downloadURL">
						<c:param name="what" value="${attach.attach_no }"></c:param>
					</c:url>
					<a href="${ downloadURL }" title="파일크기:${attach.attach_fancysize }">${attach.attach_orgname }</a>
					&nbsp;&nbsp;
					${not vs.last? "|" : ""}
					&nbsp;&nbsp;			
				</c:forEach>
			</c:if>
			<c:if test="${empty approval.fileList }">
				<p class="noneFile">등록된 첨부파일이 없습니다.</p>
			</c:if>
		</div>
	</div>
	<div class="ap_btns">
<%-- 		<c:if test="${empty approval.instead_id}"> --%>
			<button id="approBtn" type="button" class="btn btn-outline-primary col-xs-4 col-md-2 offset-md-2"
				<%-- onclick="location.href='${pageContext.request.contextPath}/elecAuthorization/approval?what='+${approval.elec_no}" --%>
			>승인</button>
			<button id="rejectBtn" type="button" class="btn btn-outline-primary col-xs-4 col-md-2" >반려</button>
			<!-- 대결자가 지정되어 있다면 대결 버튼 숨겨야 함 -->
			
			<button id="insteadBtn" type="button" class="btn btn-outline-primary col-xs-4 col-md-2" 
				data-toggle="modal" data-target="#exampleModal">대결</button>		
			
			<button id="" type="button" class="btn btn-outline-primary col-xs-4 col-md-2" 
				onclick="location.href='${pageContext.request.contextPath}/elecAuthorization/jeonGyeol?what='+${approval.elec_no}"
			>전결</button>	
<%-- 		</c:if>			 --%>
	</div>
		
	<!-- 문서번호와 대결자를 갖고 있는 폼 -->
	<form id="insteadForm" name="insteadForm" method="get" action="${pageContext.request.contextPath}/elecAuthorization/daeGyeol">
		<input type="hidden" name="elec_no" value="${approval.elec_no}">
		<input type="hidden" id="insteadHidden" name="instead_id">
	</form>
	<!-- 문서번호와 반려사유를 갖고 있는 폼  -->
	<form id="rejectForm" name="rejectForm" method="get" action="${pageContext.request.contextPath}/elecAuthorization/reject">
		<input type="hidden" name="elec_no" value="${approval.elec_no}">
		<input type="hidden" id="rejectHidden" name="rejectMsg">
	</form>
</div>

<!--대결버튼을 눌렀을 때의 모달창-->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	   aria-labelledby="exampleModalLabel" aria-hidden="true">
	   <div class="modal-dialog" role="document">
	      <div class="modal-content">
	         <div class="modal-header">
	            <h5 class="modal-title" id="exampleModalLabel">대결자 지정</h5>
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
<script>
	// 승인버튼 클릭 시, 
	$("#approBtn").on("click", function(){
// 		socket.send();
		location.href="${pageContext.request.contextPath}/elecAuthorization/approval?what=" + ${approval.elec_no};
	});

	// 반려버튼 클릭 시, 반려 메세지 적을 수 있는 창 띄운 후 컨트롤러로 이동 -----------------------------------------
	var txt;
	$("#rejectBtn").on("click", function(){
		var rejectMsg = prompt("반려 사유 : ");
		if (rejectMsg == null || rejectMsg == "") {
		   txt = "User cancelled the prompt.";
		} else {
		   txt = rejectMsg ;
		}
		$("#rejectHidden").val(txt);
		// 문서번호와 반려 메세지를 컨트롤러로 넘겨야 한다.
		$("#rejectForm")[0].submit();
	});
	
	// 대결버튼 클릭 시, 대결자를 고를 수 있는 창 띄운 후 컨트롤러로 이동 --------------------------------------------
	$("#insteadBtn").on("click", function(){
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
		
	$("#listbody").on("click","tr",function(){
		// checkbox 하나만 선택되도록
		$('input[type="radio"][name="checkAll"]').click(function(){
			if($(this).prop('checked')){
				$('input[type="checkbox"][name="checkAll"]').prop('checked',false);
				$(this).prop('checked',true);
			}
		});
		// 모달창에서 (대결자)선택하기 버튼을 클릭하면 
		$("#selectBtn").click(function(){
			$(".checkAll").each(function(idx){
				var isClickedTr = $(this).prop('checked');
				if(isClickedTr){  // 선택된 상태면..
					empName = $(this).parent().parent().data("empname");
					empId = $(this).parent().parent().data("empid");
//						destination = $(this).parent().parent().data("destination");
					console.log(empName);
					console.log(empId);
				}
			});
			$("#insteadHidden").val(empId); // 지정된 대결자를 컨트롤러로 보낼 폼 안의 input type="hidden" 태그에 셋팅
			$("#insteadForm").submit();
		})
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
		
		$("#listhead").html(tr1)
		$("#listbody").html(tr2);
	}

	// ckeditor 설정
	CKEDITOR.replace('elec_content', {
		height : 700,
		toolbarGroups: [
	       {
	         "name": "insert",
	         "groups": ["insert"]
	       },
	     ],
	     // Remove the redundant buttons from toolbar groups defined above.
	     removeButtons: 'Underline,Strike,Subscript,Superscript,Anchor,Styles,Specialchar'
	});
	var content = $("#elec_content").val();
	CKEDITOR.instances.elec_content.setData(content);
	 
</script>