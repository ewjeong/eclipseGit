<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<style>
 	#errors{color: red;}
 	#icon{display:none; size: 10x; color: red; padding-top: 5px;}
</style>

	<form:form commandName="item" action="${pageContext.request.contextPath }/purchasingTeam/itemManage/itemInsert" method="post">
	
		<div class="col-xs-12 col-md-5">
			<h3>품목 등록</h3>
		</div>
		<br><br><br>
		<div class="col-xs-12 col-md-5">
	      	<label>상단품목명</label>
	     </div>
	      	 
	    <div id="top" class="row">
	    	<div class="col">
				  <select name="top_item_code" class="form-control" >
				      <c:forEach items="${top_item_codeList }" var="item">
					      <option id="${item.item_code }">${item.item_name}(${item.item_code })</option>
				      </c:forEach>
			      </select>
		    </div>
	     	<div class="col"> 
		    	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">신규 상단품목 등록</button>
	   		</div>
	   	</div>
	   	<br><br>
	   	<div class="col-xs-12 col-md-5">
	      <label>하단품목명</label> 
	    </div>
	    
	    <div class="row">
	    	<div class="col">
	      		<input type="text" name="item_name" class="form-control">
	   		</div>
	   		<div class="col">
	   			<button type="submit" id="itemInsertBtn" class="btn btn-primary col-xs-4 col-md-2">등록</button>
		    </div>
	   	</div>
	   	
	   	<br>
<%-- 		&nbsp;&nbsp;&nbsp;&nbsp;<i id="icon" class="fas fa-exclamation-circle"></i>&nbsp;&nbsp;<form:errors id="errors" path="item_name" element="span" cssClass="error"/> --%>
	   	<div class="col-xs-12 col-md-5">
	   	</div>
   	</form:form>
   	
   	
   	<!-- Modal -->
<!-- commandName: 카멜표기법때문에 이렇게 해야함 ( 컨트롤러 쪽에서 item ) -->
<form:form commandName="item" name="createTopItem" action="${pageContext.request.contextPath }/purchasingTeam/itemManage/itemInsert" method="post">
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">상단코드 등록하기</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	       	<label>품목분류명</label>
	       	<input type="text" name="item_name" id="topItemName" class="form-control"/>
	      </div>
	      <div class="row">
		&nbsp;&nbsp;&nbsp;&nbsp;<i id="icon" class="fas fa-exclamation-circle"></i>&nbsp;&nbsp;<form:errors id="errors" path="item_name" element="span" cssClass="error"/>
		  </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
	        <button type="button" id="topItemCodeInsertBtn" class="btn btn-primary">등록</button>
	      </div>
	    </div>
	  </div>
	</div>
</form:form>

<script type="text/javascript">
	$("#topItemCodeInsertBtn").click(function(event){
		var item_code = $("#item_code").val();
		var item_name = $("#topItemName").val();
		var result = 
			swal("상위 코드를 등록하시겠습니까?", {
				  buttons: ["취소", "등록"],
				});
		
		if(result){
			//Yes
			//등록버튼을 누르면, 추가가 되서 select 옵션이 생성된다.(비동기)
			$.ajax({
				url: "${pageContext.request.contextPath}/purchasingTeam/itemManage/itemInsert",
				method: "POST",
				dataType: "json",
				data: {item_code: item_code, item_name: item_name},
				success: function(resp){
					console.log(resp);
					$('select[name=top_item_code]').find('option').remove();
					for(var idx=0; idx<resp.top_item_codeList.length; idx++){
						$('select[name=top_item_code]').append("<option id='"+resp.top_item_codeList[idx].item_code+"'>"+resp.top_item_codeList[idx].item_name+"("+resp.top_item_codeList[idx].item_code+")"+"</option>")
					}
// 					alert(JSON.stringify(resp));
					$("#exampleModal").modal("hide");
				},
				error: function(errorResp){
					console.log(errorResp.status);
				},
			})
			
			return false;
			
			//모달창이 알아서 나가지면서 
		}else{
			return;
		}
	});

	<c:if test="${not empty message }">
	<c:if test="${message eq 'none' }">
		swal("등록 실패", "품목 등록에 실패했습니다." , "warning");
		//에러메세지가 있으면 #icon을 show한다
			$("#icon").show();
			//다시 발주서 정보 셋팅해준다 
			//#inputOrdNo에 value="${ware.pur_ord_code } 셋팅해준걸 가져와서 다시 ajax처리
// 			var inputOrdNo = $("#inputOrdNo").val();
			
	</c:if>
 </c:if>	  
	
	
	
	//먼저 1.선택된 select박스 즉 아이템 코드가 새로 등록하는 코드의 부모 코드가 된다.
	// 2. 이름을 입력받고 새로운 품목을 생성한다.
	
// $("#itemInsertBtn").click(function(){
// 	여기서 아이템 등록버튼을 누르면 form태그를 통해 보내주게된다
// 	})
	
	
	
</script>
   	