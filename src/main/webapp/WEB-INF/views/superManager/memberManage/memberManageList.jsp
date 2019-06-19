<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
	
<div style="border: 0.5px solid #E6E6E6; padding: 20px 0;">
	<div  class="col-xs-12 col-md-10">
	<div class="col-xs-12 col-md-5">
		<h3>사원 조회</h3>
    </div>
    <hr>
	<br>
  <form id="selectForm">
	<div class="row">
	<div class="col-xs-12 col-md-5" style="padding-left: 30px;">
      <label>사원명</label> 
      <input type="text" class="form-control" name="emp_name" id="emp_name" readonly="readonly">
    </div>
	<div style="padding-top:40px";>
		<button type="button" class="btn btn-primary"
                         data-toggle="modal" data-target="#myModal">
                  검색</button>
	</div>
	</div>
	<div class="col-xs-12 col-md-5">
      <label>사원코드</label> 
      <input type="text" class="form-control" name="emp_no" id="emp_no" readonly="readonly">
    </div>
	<div class="col-xs-12 col-md-5">
      <label>아이디</label> 
      <input type="text" class="form-control" name="emp_id" id="emp_id" readonly="readonly">
    </div>
	<div class="col-xs-12 col-md-5">
      <label>생년월일</label> 
      <input type="text" class="form-control" name="emp_bir" id="emp_bir" readonly="readonly">
    </div>
	<div class="col-xs-12 col-md-5">
      <label>우편번호</label> 
      <input type="text" class="form-control" name="emp_zip" id="emp_zip" readonly="readonly">
    </div>
	<div class="col-xs-12 col-md-5">
      <label>주소</label> 
      <input type="text" class="form-control" name="emp_add1" id="emp_add1" readonly="readonly">
    </div>
	<div class="col-xs-12 col-md-5">
      <label>상세주소</label> 
      <input type="text" class="form-control" name="emp_add2" id="emp_add2" readonly="readonly">
    </div>
	<div class="col-xs-12 col-md-5">
      <label>핸드폰</label> 
      <input type="tel" class="form-control" name="emp_hp" id="emp_hp" readonly="readonly">
    </div>

    
	<div class="col-xs-12 col-md-5" >
      <label>부서명</label> 
<!--       <div class="row"> -->
<!-- 	      <select name="dep_code" id="dep_code"> -->
<!-- 	      	<option value="p001">구매1팀</option> -->
<!-- 	      	<option value="p002">구매2팀</option> -->
<!-- 	      	<option value="p003">구매3팀</option> -->
<!-- 	      	<option value="p004">구매4팀</option> -->
<!-- 	      	<option value="p005">구매5팀</option> -->
<!-- 	      	<option value="s001">영업1팀</option> -->
<!-- 	      	<option value="s002">영업2팀</option> -->
<!-- 	      	<option value="s003">영업3팀</option> -->
<!-- 	      	<option value="s004">영업4팀</option> -->
<!-- 	      	<option value="s005">영업5팀</option> -->
<!-- 	      </select> -->
<!--       </div> -->
      <input type="text" class="form-control" name="dep_name" id="dep_name" readonly="readonly">
      
    </div>
	<div class="col-xs-12 col-md-5">
      <label>직책명</label>
<!--       <div class="row"> -->
<!--        <select name="pos_code" id="pos_code"> -->
<!--        	<option value="e001">사원</option> -->
<!--        	<option value="e004">대리</option> -->
<!--        	<option value="e002">과장</option> -->
<!--        	<option value="e003">팀장</option> -->
<!--        </select> -->
<!--       </div> -->
      <input type="text" class="form-control" name="pos_name" id="pos_name" readonly="readonly">
      
    </div>
	<br>
	<div class="col-xs-12 col-md-5">
  	   <label>탈퇴 여부</label>
<!--   	    <div class="row"> -->
<!-- 	  	   <select name="emp_del" id="emp_dels"> -->
<!-- 	  	   	<option value="N">N</option> -->
<!-- 	  	   	<option value="Y">Y</option> -->
<!-- 	  	   </select>  -->
	      <input type="text" class="form-control" name="emp_del" id="emp_del" readonly="readonly">
      </div>
<!--   	</div> -->
  	<br>
<!-- 	<div class="col-xs-12 col-md-5"> -->
<!-- 		<div class="row"> -->
<!--   		<div class="col-xs-4 col-md-4"> -->
<!--   			<button type="button" id = "modifyBtn" class="form-control">수정</button> -->
<!--   		</div> -->
  		<div class="col-xs-4 col-md-4">
  			<button type="button" id="goModify"
  			onclick='location.href="<c:url value="/superManager/employeeManage/Updateemployee" />";' 
  			class="form-control btn btn-primary">수정	</button>
		</div>  		
<!--   		</div> -->
<!-- 	</div> -->
  		</form>	
	</div>
</div>
	
	
<!-- 	사원명,부서명,직급명검색모달 -->
	<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">사원 검색</h4>
        </div>
        <div class="modal-body">
			<form action="${pageContext.request.contextPath }/superManager/employeeManage/employeeList" id="searchForm" method="post">
                    <div class="container">
                    </div>
                       <div class="row">
							<select id="searchType" name="searchType">
								<option value="all" ${pagingVO.searchType eq 'all' ? "selected":"" }>전체</option>	
								<option value="emp_name" ${pagingVO.searchType eq 'emp_name' ? "selected":"" }>사원명</option>	
								<option value="dep_name" ${pagingVO.searchType eq 'dep_name' ? "selected":"" }>부서명</option>	
								<option value="pos_name" ${pagingVO.searchType eq 'pos_name' ? "selected":"" }>직급명</option>	
							</select>
								<input type="hidden" name="page"/>
								<input type="text" name="searchWord" id="searchWord" value="${pagingVO.searchWord }"/>
	                            	<button class="btn btn-primary" id="findBtn" type="button">검색하기</button>
                       </div>
				</form>
                        <div class="row">
                        	<div class="col">
                           	<table class="table table-borderless">
                           		<thead id="listhead">
                           		</thead>
                           		<tbody id="listbody">
                           		</tbody>
                           		<tfoot >
                           			<tr>
	                          			<td id="listfoot" colspan="4">
											${pagingVO.pagingHTML }
	                          			</td>
                           			</tr>
                           		</tfoot>
                           	</table>
                           </div>
                        </div>
                     </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default btn btn-outline-secondary" data-dismiss="modal">닫기</button>
        	<button type="button" id="selectBtn" class="btn btn-primary" data-dismiss="modal">선택하기</button>
        </div>
      </div>
      
    </div>
  </div>
	
	
<!-- 	<form id="modiHidden"> -->
<!-- 		<input type="hidden" class="form-control" name="dep_code" id="dep_codeHI" > -->
<!-- 		<input type="hidden" class="form-control" name="pos_code" id="pos_codeHI"> -->
<!-- 		<input type="hidden" class="form-control" name="emp_del" id="emp_delHI"> -->
<!-- 		<input type="hidden" class="form-control" name="emp_no" id="emp_noHI"> -->
		
<!-- 	</form> -->
	
<script>
	
	function paging(page){
		$("#searchForm").find("input[name='searchType']").val($("#searchType").val());
		$("#searchForm").find("input[name='searchWord']").val($("#searchWord").val());
		$("#searchForm").find("input[name='page']").val(page);
		$("#searchForm").submit();
	}
	
	//모달창에서 사원검색
		function retrieveEmp(resp){
			
			var empList = resp.dataList;
			var pagingHTML = resp.pagingHTML;
			var trTags=[];
			
			var th=$("<tr>").append(
							$("<th>").text("")
							,$("<th>").text("부서명")
							,$("<th>").text("직급명")
							,$("<th>").text("사원명")
							,$("<th>").text("사원번호")
							
					)
			
			
			$(empList).each(function(idx,emp){
				console.log(emp);
				var tr = $("<tr>").append(
							$("<td><input type='checkbox' class='checkAll' name='checkAll'/></td>")
							,$("<td>").text(emp.dep_name)
							,$("<td>").text(emp.pos_name)
							,$("<td>").text(emp.emp_name)
							,$("<td id='empNO'>").text(emp.emp_no)
					)
					trTags.push(tr);
				});
				$("#listhead").html(th);	
				$("#listbody").html(trTags);	
				$("#listfoot").html(resp.pagingHTML);
				
		}
	
		
		$(document).ready(function(){
			
				
			$("#findBtn").on("click",function(event){
				$("#searchWord").val($("#searchWord").val());
				$("#searchType").val($("#searchType").val());
				$("#searchForm").submit();
			});
					
					
			$("#searchForm").on("submit",function(){
				var queryString = $(this).serialize();
				$.ajax({
					 url:'${pageContext.request.contextPath}/superManager/employeeManage/employeeList'
					,data : queryString
					,dataType : "json"
					,success:function(resp){
						retrieveEmp(resp);
						
					}
				});
				return false;
			
			});
			
			paging(1);
			
			
			//선택하기를 클릭했을때 발생하는 함수
			$("#selectBtn").click(function(){
// 				//체크된값을
				$(".checkAll").each(function(idx){
					var tr = $(this).prop('checked');
					if(tr){
						emp_no= $(this).parent().parent().find("#empNO").text();
						console.log(emp_no);
					}
				})
				$("#emp_no").val(emp_no);
				
				
				$.ajax({
					url:"${pageContext.request.contextPath}/superManager/employeeManage/empOne"
					,data:{
						emp_no:emp_no
					}
					,dataType:"json"
					,success:function(emp){
						//input태그에 값을 셋팅한다.
						$("input[name='emp_name']").val(emp.emp_name);
						$("input[name='emp_no']").val(emp.emp_no);
						$("input[name='emp_id']").val(emp.emp_id);
						$("input[name='emp_bir']").val(emp.emp_bir);
						$("input[name='emp_zip']").val(emp.emp_zip);
						$("input[name='emp_add1']").val(emp.emp_add1);
						$("input[name='emp_add2']").val(emp.emp_add2);
						$("input[name='emp_hp']").val(emp.emp_hp);
						$("input[name='dep_name']").val(emp.dep_name);
						$("input[name='pos_name']").val(emp.pos_name);
						$("input[name='emp_del']").val(emp.emp_del);
						
					}
					
					
				})//ajax end
				
				
			});//selectBtn end
			
			
			
			
// 			//셀렉트박스를 선택했을때 change되는 함수
// 			$("#dep_code option:selected").on("change",function(){
// 				$("input[name='dep_name']").val(modi.dep_name);
// 			})
// 			$("#pos_name option:selected").on("change",function(){
// 				$("input[name='pos_name']").val(modi.pos_name);
// 			})
// 			$("#emp_dels option:selected").on("change",function(){
// 				$("input[name='emp_del']").val(modi.emp_del);
// 			})
			
						
			
			
			//수정하기 버튼을 클릭했을때
				
// 			$("#modifyBtn").click(function(){
				
// 				dep_code =$('#dep_code option:selected').val();
// 				$("#dep_codeHI").val( dep_code);
					 
// 				console.log($('#dep_code option:selected').val());
				
				
// 				pos_code = $("#pos_code option:selected").val();
// 				$("#pos_codeHI").val(pos_code);
// 				console.log($("#pos_code option:selected").val());
				
// 				emp_del = $("#emp_dels option:selected").val();
// 				$("#emp_del").val(emp_del);
// 				console.log($("#emp_dels option:selected").val());
				
// 				emp_no = $("#emp_no").val();
// 				console.log(emp_no);
				
// 				console.log($('#dep_code option:selected').text());
// 				console.log($("#pos_code option:selected").text());
				
// 				$.ajax({
// 					url:'${pageContext.request.contextPath}/superManager/employeeManage/ModifyEmployee'
// 					,type:'post'
// 					,data:{
// 						dep_code:dep_code
// 						,pos_code:pos_code
// 						,emp_del:emp_del
// 						,emp_no:emp_no
// 					}
// 					,dataType:'json'
// 					,success:function(){
// 						$("input[id='dep_name']").val($('#dep_code option:selected').text());
// 						$("input[id='pos_name']").val($("#pos_code option:selected").text());
// 						$("input[name='emp_del']").val($("#emp_dels option:selected").val());
// 					}
// 				})//ajax end
				
// 			});//modihidden end	
				
			//수정하기 버튼을 눌렀을때 사원코드를 가지고 수정폼으로 이동
			$("#goModify").on("click",function(){
				var emp_no = $("#emp_no").val();
				console.log($("#emp_no").val());
				location.href="${pageContext.request.contextPath}/superManager/employeeManage/Updateemployee/"+emp_no
			})
			
			
		});
		
</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	