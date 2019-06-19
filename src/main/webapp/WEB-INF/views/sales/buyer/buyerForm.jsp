<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>	
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style>
    #errors{color: red;}
    #icon{size: 10x; color: red; padding-top: 5px;}
</style>
<form:form commandName="client" id="buyerform" method="post" class="col-xs-12 col-md-10">
	<div class="row">
		<div
			style="border: 0.5px solid #E6E6E6; padding: 20px 0;">
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
				<input type="text" name="cl_name" value="${client.cl_name }"	class="form-control" placeholder="거래처명을 입력하세요" >
				<div class="valid-feedback">입력 완료</div>
				<form:errors id="errors" path="cl_name" element="span" cssClass="errors"></form:errors>
			<c:choose>
				<c:when test="${not empty client.cl_no }">
					<br>
					<label for="validationServer01">작성자명</label> 
					<input type="text" name="emp_name" value="${client.emp_name }" readonly="readonly"	class="form-control" placeholder="거래처명을 입력하세요" >
<%-- 					<input type="hidden" id = "cl_empid" name="cl_empid" value="${client.cl_empid }"/> --%>
					
					<div class="valid-feedback">입력 완료</div>
				</c:when>
			</c:choose>	
			</div>
			<br>
			<div  class="col-xs-12 col-md-10">
				<label for="validationServer02">거래처 대표자명</label> 
				<input type="text" name="cl_charger" value="${client.cl_charger }" class="form-control" placeholder="대표자명"
					>
				<div class="invalid-feedback">거래처 대표자명을 입력하세요</div>
			</div>
			<br>
			<div  class="col-xs-12 col-md-10">
				<label for="validationServer02">거래처 전화번호</label> 
				<input type="text" name="cl_tel" value="${client.cl_tel }" class="form-control" placeholder="전화번호" >
				<div class="invalid-feedback">전화번호를 입력하세요</div>
				<form:errors id="errors" path="cl_tel" element="span" cssClass="errors"></form:errors>
			</div>
			<br>
			<div  class="col-xs-12 col-md-10">
				<label for="validationServerUsername">이메일</label> 
				<input type="email" name="cl_mail" value="${client.cl_mail }"	class="form-control" placeholder="name@example.com">
				<form:errors id="errors" path="cl_mail" element="span" cssClass="errors"></form:errors>
				<div class="invalid-feedback">이메일 입력</div>
			</div>
			<br>
			<div class="row" style="padding-left: 15px";>
				<div  class="col-xs-12 col-md-10">
					<label for="validationServer05">우편번호</label>
					 <input type="text" class="form-control" name="cl_zip" id="zipCode" value="${client.cl_zip }"  readonly="readonly" placeholder="ex) 12345"> 
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
			
			
			<br>
<!-- 		 	<div> -->
<%-- 	      		<input type="hidden" name="cl_empid" id="emp_id" value="${emp_id }" class="form-control"> --%>
<!-- 	   		</div> -->
			<br>
<!-- 			<div class="row"  style="padding-left: 15px";> -->
<!-- 				<div  class="col-xs-12 col-md-10" > -->
<!-- 					<label for="validationServer02">품목</label>  -->
<%-- 					<input type="text" value="${client.cl_items }" readonly="readonly" name="cl_items" id="cl_items" class="form-control" placeholder="품목명"> --%>
<!-- 					<div class="invalid-feedback">품목명을 입력하세요</div> -->
<!-- 				</div> -->
<!-- 				<div style="padding-top:40px";> -->
<!-- 					<button type="button" class="btn btn-outline-primary" -->
<!-- 	                           data-toggle="modal" data-target="#myModal"> -->
<!-- 	                                 검색</button> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<div style="padding-top: 30px";  class="col-xs-12 col-md-10">
				<table class="table"  frame=void>
					<th>은행명</th>
					<th>계좌번호</th>
					<th>예금주</th>
					<th>수령인</th>
					<tr>
						<td><input type="text" value="${client.cl_bank }" class="form-control" name="cl_bank"/></td>
						<td><input type="text" value="${client.cl_bankno }" class="form-control" name="cl_bankno"/></td>
						<td><input type="text" value="${client.cl_depository }" class="form-control" name="cl_depository"/></td>
						<td><input type="text" value="${client.cl_receive }" class="form-control" name="cl_receive"/></td>
					</tr>
				</table>
			</div>
			<div  class="col-xs-12 col-md-10">
				<label for="validationServer02">비고</label> 
				<input type="text" value="${client.cl_detail }"  name="cl_detail" class="form-control" placeholder="특기사항">
			</div>
		</div>
		
		
	
		<div class="btnCenterWrap">
			<c:choose>
				<c:when test="${empty client.cl_no }">
					<button class="btn btn-primary" id="insertBtn" type="submit"
						style="padding-bottom: 10px; margin: 10px 0px 10px 0px;">등록
					</button>
				</c:when>
				<c:when test="${not empty client.cl_no }">
					<button class="btn btn-primary" id="modifyBtn" type="submit"
						style="padding-bottom: 10px; margin: 10px 0px 10px 0px;">수정
					</button>
					<button class="btn btn-primary" id="delBtn" type="button"
						style="padding-bottom: 10px; margin: 10px 0px 10px 0px;">삭제
					</button>
				</c:when>
			</c:choose>
		</div>
	</div>
	</form:form>

<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">품목 검색</h4>
        </div>
        <div class="modal-body">
			<form action="${pageContext.request.contextPath }/salesTeam/buyerManage/buyerInsert" method="post">
                     <div class="container">
                        <div class="row">
                           <div class="col">
                              <input class="form-control" id="item_name" name="item_name" type="text" placeholder="품목명을 입력하세요">
                           </div>
                           <div class="col">
                              <button class="btn btn-primary" id="findItem" type="button">검색하기</button>
                           </div>
                        </div>
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
          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        	<button type="button" id="selectBtn" class="btn btn-primary" data-dismiss="modal">선택하기</button>
        </div>
      </div>
      
    </div>
  </div>


<script>
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
		
		//모달창에 띄어질 내용들을 function 함수로 만듬
		function itemFindList(resp){
			var itemList = resp;
			var tr1 = $("<tr>").append(
				$("<td>").text("품목명")
				,$("<td>").text("")
			)
			var tr2 = null;
			$(itemList).each(function(idx,item){
				tr2 = $("<tr>").append(
					$("<td class='name'>").text(item)
					,$("<td><input type='checkbox' class='checkAll' name='checkAll'/></td>")
				)
			});
			$("#listhead").html(tr1);
			$("#listbody").html(tr2);
		}
		
		//검색하기 버튼을 눌렀을때 발생하는 ajax
		$("#findItem").click(function(event){
			var item_name = $("#item_name").val();
			$.ajax({
				url:"<c:url value='/salesTeam/buyerManage/item'/>"
				,method: "post"
				,dataType:"json"
				,data:{
					item_name:item_name
				}
				,success:function(resp){
					itemFindList(resp);
				}
				,error:function(error){
  					alert("에러");
  				}
			});//ajax end
			return false;
		});//findItem end
		
		
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
		  		$("#buyerform").attr("action","${pageContext.request.contextPath}/salesTeam/buyerManage/buyerInsert");
		  		$("#buyerform").submit();
		  	})
 		</c:when>
		<c:otherwise>
		  	//수정하기 버튼을 눌렀을때 발생
		  	$("#buyerform").submit(function(){
		  		var cl_no= $("#cl_noID").val();
		  		var cl_empid=$("#cl_empid").val();
// 		  		swal(수정성공);
		  		$("#buyerform").attr("action","${pageContext.request.contextPath}/salesTeam/buyerManage/detailBuyer/"+cl_no);
		  		return true;
		  	})
			
		  	//삭제하기 버튼을 눌렀을때 발생
		  	$("#delBtn").click(function(){
		  		var cl_no= $("#cl_noID").val();
		  		var cl_empid=$("#cl_empid").val();
		  		location.href="${pageContext.request.contextPath}/salesTeam/buyerManage/buyerDelete/"+cl_no+"/"+cl_empid;
		// 		$("#buyerform").attr("action","${pageContext.request.contextPath}/salesTeam/buyerManage/buyerDelete/"+cl_no);
// 		  		$("#buyerform").submit();
		  	})
		</c:otherwise>		
 	</c:choose>
  	 

	 <c:if test="${not empty msg}">
			swal("${msg}");
			<c:remove var="msg" scope="session"/>
	</c:if> 

</script>




