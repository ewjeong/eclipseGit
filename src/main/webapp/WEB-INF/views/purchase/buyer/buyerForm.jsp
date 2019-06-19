<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<style>
 	#errors{color: red;}
 	#icon{display:none; size: 10x; color: red; padding-top: 5px;}
</style>

<form id="buyerform" method="post" class="col-xs-12 col-md-10">
	<div class="row">
		<div style="border: 0.5px solid #E6E6E6; padding: 20px 0;">
			<div  class="col-xs-12 col-md-10">
<!-- 			cl_no가 없으면 신규등록 있으면 상세조회 -->
			<c:choose>
				<c:when test="${empty client.cl_no }">
					<h3>거래처 신규등록</h3>
				</c:when>
				<c:when test="${not empty client.cl_no }">
					<h3>거래처 상세조회/수정</h3>
				</c:when>
			</c:choose>
				<input type ="hidden" id = "cl_noID" name = "cl_no" value="${client.cl_no }">
				<input type ="hidden" id = "cl_empid" name = "cl_empid" value="${client.cl_empid }">
				<hr>
				<label for="validationServer01">거래처명</label> 
				<input type="text" name="cl_name" value="${client.cl_name }" class="form-control" placeholder="거래처명을 입력하세요" />
				<div class="row">
				&nbsp;&nbsp;&nbsp;&nbsp;<i id="icon" class="fas fa-exclamation-circle"></i>&nbsp;&nbsp;<form:errors id="errors" path="cl_name" element="span" cssClass="error"/>
				</div>
			<c:choose>
				<c:when test="${not empty client.cl_no }">
					<br>
					<label for="validationServer01">작성자명</label> 
					<input type="text" name="emp_name" value="${client.emp_name }" readonly="readonly"	class="form-control" placeholder="거래처명을 입력하세요"/>
<%-- 					<input type="hidden" id = "cl_empid" name="cl_empid" value="${client.cl_empid }"/> --%>
				</c:when>
			</c:choose>	
			</div>
			<br>
			<div  class="col-xs-12 col-md-10">
				<label for="validationServer02">거래처 대표자명</label> 
				<input type="text" name="cl_charger" value="${client.cl_charger }" class="form-control" placeholder="대표자명" />
			</div>
			<br>
			<div  class="col-xs-12 col-md-10">
				<label for="validationServer02">거래처 전화번호</label> 
				<input type="text" name="cl_tel" value="${client.cl_tel }" class="form-control" placeholder="전화번호" />
				<div class="row">
				&nbsp;&nbsp;&nbsp;&nbsp;<i id="icon" class="fas fa-exclamation-circle"></i>&nbsp;&nbsp;<form:errors id="errors" path="cl_tel" element="span" cssClass="error"/>
				</div>
			</div>
			<br>
			<div  class="col-xs-12 col-md-10">
				<label for="validationServerUsername">이메일</label> 
				<input type="email" name="cl_mail" value="${client.cl_mail }" class="form-control" placeholder="name@example.com" />
				<div class="row">
				&nbsp;&nbsp;&nbsp;&nbsp;<i id="icon" class="fas fa-exclamation-circle"></i>&nbsp;&nbsp;<form:errors id="errors" path="cl_mail" element="span" cssClass="error"/>
				</div>
			</div>
			<br>
			<div class="row" style="padding-left: 15px";>
				<div  class="col-xs-12 col-md-10">
					<label for="validationServer05">우편번호</label>
					 <input type="text" class="form-control" name="cl_zip" id="zipCode" value="${client.cl_zip }" readonly="readonly" placeholder="ex) 12345" > 
				</div>
				<div style="padding-top:40px";>
						 <input type="button" class="btn btn-primary" onclick="sample4_execDaumPostcode()" value="검색"/>
				</div>
			</div>
			<br>
			<div  class="col-xs-12 col-md-10">
				<label for="validationServer05">주소</label> 
				<input type="text" class="form-control" name="cl_add1" id="add1"  value="${client.cl_add1}" readonly="readonly" placeholder="ex) 대전시 중구 대흥동">
			</div>
			<br>
			<div  class="col-xs-12 col-md-10">
				<label for="validationServer05">상세주소</label> 
				<input type="text" class="form-control" name="cl_add2" id="add2" value="${client.cl_add2 }" placeholder="ex) 123-1번지 101호">
			</div>
			
			
<!-- 			<br> -->
<!-- 		 	<div> -->
<%-- 	      		<input type="hidden" name="cl_empid" id="emp_id" value="${emp_id }" class="form-control"> --%>
<!-- 	   		</div> -->
<!-- 			<br> -->
<!-- 			<div class="row"  style="padding-left: 15px";> -->
<!-- 				<div  class="col-xs-12 col-md-10" > -->
<!-- 					<label for="validationServer02">품목</label>  -->
<%-- 					<input type="text" value="${client.cl_items }" readonly="readonly" name="cl_items" id="cl_items" class="form-control" placeholder="품목명"> --%>
<!-- 				</div> -->
<!-- 				<div style="padding-top:40px";> -->
<!-- 					<button type="button" class="btn btn-outline-primary" -->
<!-- 	                           data-toggle="modal" data-target="#myModal"> -->
<!-- 	                                 검색</button> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<div style="padding-top: 30px";  class="col-xs-12 col-md-10">
				<table class="table" frame=void>
					<th>은행명</th>
					<th>계좌번호</th>
					<th>예금주</th>
					<th>수령인</th>
					<tr>
						<td>
							<input type="text" value="${client.cl_bank }" class="form-control" name="cl_bank"/>
						</td>
						<td>
							<input type="text" value="${client.cl_bankno }" class="form-control" name="cl_bankno"/>
						</td>
						<td>
							<input type="text" value="${client.cl_depository }" class="form-control" name="cl_depository"/>
						</td>
						<td>
							<input type="text" value="${client.cl_receive }" class="form-control" name="cl_receive"/>
						</td>
					</tr>
				</table>
			</div>
			<div  class="col-xs-12 col-md-10">
				<label for="validationServer02">비고</label> 
				<input type="text" value="${client.cl_detail }"  name="cl_detail" class="form-control" placeholder="특기사항">
			</div>
		</div>
		
		
	
		<div class="col-xs-4 col-md-7 btnCenterWrap">
			<c:choose>
				<c:when test="${empty client.cl_no }">
					<br>
					<br>
					<button class="btn btn-primary col-xs-2 col-md-3" id="insertBtn" type="submit">등록</button>
				</c:when>
				<c:when test="${not empty client.cl_no }">
					<br>
					<br>
					<button class="btn btn-primary col-xs-2 col-md-3" id="modifyBtn" type="submit">
					수정</button>
					<button class="btn btn-secondary col-xs-2 col-md-3" id="delBtn" type="submit">
					삭제</button>
				</c:when>
			</c:choose>
		</div>
		
	</div>
</form>

<script type="text/javascript">
	//주소찾기
	function sample4_execDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				var fullRoadAddr = data.roadAddress; //도로명주소 변수
				//swal(fullRoadAddr);
				var extraRoadAddr = ''; //도로명 조합형 주소 변수
	
				//법정동명이 있을 경우 추가
				//법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraRoadAddr += data.bname;
				}
	
				//건물명이 있고, 공동주택일 경우 추가
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraRoadAddr += (extraRoadAddr !== '' ? ', '
							+ data.buildingName : data.buildingName);
				} 
	
				// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraRoadAddr !== '') {
					extraRoadAddr = ' (' + extraRoadAddr + ')';
				}
				// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
				if (fullRoadAddr !== '') {
					fullRoadAddr += extraRoadAddr;
				}
	
				//우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('zipCode').value = data.zonecode; //다섯자리 새우편번호 사용
				if (data.jibunAddress) {
					document.getElementById('add1').value = data.jibunAddress; //지번
				} else {
					document.getElementById('add1').value = fullRoadAddr; //도로명
				}
			}
		}).open();
		
	}

	
	$(function(){
		
		//거래처리스트 검색후 리스트바디에 출력 
	  	//옆에 체크박스를 선택하면 true로
	  	$("#listbody").on("click","tr",function(){
	  		$('input[type="checkbox"][name="checkAll"]').click(function(){
	  			if($(this).prop('checked')){
	  				$('input[type="checkbox"][name="checkAll"]').prop('checked',false);
	  				$(this).prop('checked',true);
	  			}
	  		});
		
		
	  	//선택하기 버튼을 누르면 화면에 띄어지게한다
		$("#selectBtn").click(function(){
			$(".checkAll").each(function(idx){
				var ck = $(this).prop('checked');
				if(ck){
					item_namee = $(this).parent().parent().find(".name").text();
					console.log(item_namee);
				}
			})
			$("#cl_items").val(item_namee);
		  	})
		});	//selectBtn end	
		
		
 	});	//function end
	 	
	<c:choose>
 		<c:when test="${empty client.cl_no}">
		 	//등록하기 버튼을 눌렀을때 발생
		  	$("#buyerform").submit(function(){
		  		$("#buyerform").attr("action","${pageContext.request.contextPath}/purchasingTeam/buyerManage/buyerInsert");
		  		$("#buyerform").submit();
		  	})
 		</c:when>
		<c:otherwise>
		  	//수정하기 버튼을 눌렀을때 발생
		  	$("#buyerform").submit(function(){
		  		var cl_no= $("#cl_noID").val();
		  		var cl_empid=$("#cl_empid").val();
		  		$("#buyerform").attr("action","${pageContext.request.contextPath}/purchasingTeam/buyerManage/detailBuyer/"+cl_no);
		  		return true;
		  	});
			
		  	//삭제하기 버튼을 눌렀을때 발생
		  	$("#delBtn").click(function(){
		  		var cl_no= $("#cl_noID").val();
		  		var cl_empid=$("#cl_empid").val();
		  		location.href="${pageContext.request.contextPath}/purchasingTeam/buyerManage/buyerDelete/"+cl_no"/"+cl_empid;
// 		  		$("#buyerform").submit();
		  	});
		</c:otherwise>		
 	</c:choose>
 	
 	<c:if test="${not empty message }">
		<c:if test="${message eq 'modify'}">
			swal("거래처 수정 성공", "거래처 수정에 성공했습니다.", "success");
		</c:if>
		<c:if test="${message eq 'NoModify'}">
			swal("거래서 수정 실패", "필수 데이터가 누락되었습니다.", "warning");
		</c:if>
		<c:if test="${message eq 'NoID'}">
			swal("거래처 수정 실패", "등록한 아이디와 일치하지 않습니다", "warning");
		</c:if>
	</c:if>

</script>





