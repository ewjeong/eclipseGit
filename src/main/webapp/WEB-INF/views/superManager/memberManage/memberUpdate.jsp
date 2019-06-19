<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    


<div style="border: 0.5px solid #E6E6E6; padding: 20px 0;">
	<div  class="col-xs-12 col-md-10">
<div class="col-xs-12 col-md-5">
		<h3>사원 수정</h3>
    </div>
    <hr>
<!-- 	<br> -->
  <form id="selectForm" method="post">
<!-- 	<div class="row"> -->
		<div class="col-xs-12 col-md-5">
	      <label>사원명</label> 
	      <input type="text" class="form-control" value="${ emp.emp_name}" name="emp_name" id="emp_name" readonly="readonly">
	    </div>
<!-- 	</div> -->
	<div class="col-xs-12 col-md-5">
      <label>사원코드</label> 
      <input type="text" class="form-control" value="${ emp.emp_no}" name="emp_no" id="emp_no" readonly="readonly">
    </div>
    <br>
    <div class="col-xs-12 col-md-12" style="padding-left: 30px;">
      <label>부서명</label> 
      <div class="row">
      <div>
	      <select class="form-control" name="dep_code" id="dep_code">
	      	<option value="">부서 선택</option>
	      	<option value="p001" ${emp.dep_code eq 'p001'? "selected" : ""}>구매1팀</option>
	      	<option value="p002" ${emp.dep_code eq 'p002'? "selected" : ""}>구매2팀</option>
	      	<option value="p003" ${emp.dep_code eq 'p003'? "selected" : ""}>구매3팀</option>
	      	<option value="p004" ${emp.dep_code eq 'p004'? "selected" : ""}>구매4팀</option>
	      	<option value="p005" ${emp.dep_code eq 'p005'? "selected" : ""}>구매5팀</option>
	      	<option value="s001" ${emp.dep_code eq 's001'? "selected" : ""}>영업1팀</option>
	      	<option value="s002" ${emp.dep_code eq 's002'? "selected" : ""}>영업2팀</option>
	      	<option value="s003" ${emp.dep_code eq 's003'? "selected" : ""}>영업3팀</option>
	      	<option value="s004" ${emp.dep_code eq 's004'? "selected" : ""}>영업4팀</option>
	      	<option value="s005" ${emp.dep_code eq 's005'? "selected" : ""}>영업5팀</option>
	      </select>
      </div>
      <div>
	      <input type="text" class="form-control" value="${ emp.dep_name}" name="dep_name" id="dep_name">
      </div>
      </div>
      
    </div>
	<div class="col-xs-12 col-md-12" style="padding-left: 30px;">
      <label>직책명</label>
      <div class="row">
	      <div>
		       <select  class="form-control" name="pos_code" id="pos_code">
		       <option value="">직급 선택</option>
		       	<option value="e001" ${emp.pos_code eq 'e001'? "selected":""}>사원</option>
		       	<option value="e004" ${emp.pos_code eq 'e004'? "selected":""}>대리</option>
		       	<option value="e002" ${emp.pos_code eq 'e002'? "selected":""}>과장</option>
		       	<option value="e003" ${emp.pos_code eq 'e003'? "selected":""}>팀장</option>
		       </select>
	      </div>
	      <div>
	      	<input type="text" class="form-control" value="${ emp.pos_name}" name="pos_name" id="pos_name">
	      </div>
      </div>
      
    </div>
	<br>
	<div class="col-xs-12 col-md-12" style="padding-left: 30px;">
  	   <label>퇴사 여부</label>
  	    <div class="row">
  	    	<div>
		  	   <select class="form-control" name="emp_del" id="emp_dels">
		  	   <option value="">퇴사여부 선택</option>
		  	   	<option value="N" ${emp.emp_del eq 'N' ? "selected":""}>N</option>
		  	   	<option value="Y" ${emp.emp_del eq 'Y' ? "selected":""}>Y</option>
		  	   </select> 
  	    	</div>
  	    	<div>
		      <input type="text" class="form-control" value="${ emp.emp_del}" name="emp_del" id="emp_del">
  	    	</div>
      </div>
  	</div>
  	<br>
	<div class="col-xs-12 col-md-5">
		<div class="row">
  		<div class="col-xs-4 col-md-4">
  			<button type="button" id = "modifyBtn" class="form-control btn btn-primary">수정</button>
  		</div>
<!--   		<div class="col-xs-4 col-md-4"> -->
<!--   			<button type="button" class="form-control">취소</button> -->
<!-- 		</div>  		 -->
  		</div>
	</div>
  </form>	
  </div>
  </div>
  
  <script>
// 	수정하기 버튼을 클릭했을때
		
		$("#modifyBtn").click(function(){
			
			dep_code =$('#dep_code option:selected').val();
			$("#dep_codeHI").val( dep_code);
				 
			console.log($('#dep_code option:selected').val());
			
			
			pos_code = $("#pos_code option:selected").val();
			$("#pos_codeHI").val(pos_code);
			console.log($("#pos_code option:selected").val());
			
			emp_del = $("#emp_dels option:selected").val();
			$("#emp_del").val(emp_del);
			console.log($("#emp_dels option:selected").val());
			
			emp_no = $("#emp_no").val();
			console.log(emp_no);
			
			$("input[id='dep_name']").val($('#dep_code option:selected').text());
			$("input[id='pos_name']").val($("#pos_code option:selected").text());
			
			console.log($('#dep_code option:selected').text());
			console.log($("#pos_code option:selected").text());
			
// 			var OK;
			
			$.ajax({
				url:'${pageContext.request.contextPath}/superManager/employeeManage/Updateemployee/'+emp_no
				,type:'post'
				,data:{
					dep_code:dep_code
					,pos_code:pos_code
					,emp_del:emp_del
					,emp_no:emp_no
				}
				,dataType:'json'
				,success:function(resp){
// 					swal("수정성공");
					swal(resp.OK);
				}
			})//ajax end
			
		});//modihidden end	
		
		
		
		
		
		
  </script>
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  