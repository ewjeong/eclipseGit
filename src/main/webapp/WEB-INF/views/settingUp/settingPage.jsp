<%@page import="kr.or.ddit.vo.EmployeeVO"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>


<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>


<c:if test="${not empty message}">
swal(
	"완료",
	"${message}",
	"success"
);  
</c:if> 

	var empLi;
	var empname;
	var empno;
	var depname;
	var posname;
	
	$(function(){
		
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
			if(empList != null && empList.length > 0){
				
				$(empList).each(function(idx, empPosDep){
					console.log(empPosDep.emp_name);
	// 				tr2 = $("<tr>").append(
	// 					$("<td>").text(empPosDep.emp_name)
	// 					,$("<td>").text(empPosDep.dep_name)
	// 					,$("<td>").text(empPosDep.pos_name)
	// 				);
					tr2 += "<tr class='clicktr' data-empname='"+empPosDep.emp_name+ "' data-empno='"+empPosDep.emp_no+"' data-depname='"+empPosDep.dep_name+ "' data-posname='"+empPosDep.pos_name+ "'><td colspan='3'><input type='checkbox' class='checkAll' name='checkAll' /></td><td colspan='4'>" 
							+ empPosDep.emp_name +"</td><td colspan='4'>"+empPosDep.emp_no+"</td><td colspan='4'>"+ 
							empPosDep.dep_name +"</td><td colspan='4'>"+empPosDep.pos_name+"</td></tr>";
					console.log("test");
// 			trTags.push(tr2);
				
				});
			}else{
				tr2 = "<tr><td colspan='4' style='text-align:center;''> 해당 사원이 없습니다 </td></tr>"
			}	
			$("#listhead").html(tr1)
			$("#listbody").html(tr2);
		}
		
		$("#findEmp").click(function(event){
			//event.preventDefault();
			var emp_name = $("#emp_name").val(); //input태그의 값을 가져온다.
			if(emp_name == ''|| emp_name == null ){
				swal("해당 사원이 없습니다.");
				return false;
			}
			$.ajax({
				url : "<c:url value='/setting/settingPage'/>"
				,method:"post"
				,data: "emp_name=" + emp_name
				//,dataType:"json"
				,success : function(resp){
					empFindList(resp);
					//console.log(empFindList(resp));
				}
				,error : function(errorResp){
					console.log(errorResp.status);
					swal("오류");
				}
			});
			return false;
		});
		
		
		//var emp_name = $("clicktr").data("empname");
		//var idx = empLi[$("clicktr").data("idx")];
		$("#listbody").on("click","tr",function(){
			$('input[type="checkbox"][name="checkAll"]').click(function(){
				if($(this).prop('checked')){
					$('input[type="checkbox"][name="checkAll"]').prop('checked',false);
					$(this).prop('checked',true);
				}
			});
			//document.getElementById("inputemp_name").value = emp_name;
			$("#selectBtn").click(function(){
				
				$(".checkAll").each(function(idx){
					var tr = $(this).prop('checked');
					if(tr){
						empname = $(this).parent().parent().data("empname");
						empno = $(this).parent().parent().data("empno");
						depname = $(this).parent().parent().data("depname");
						posname = $(this).parent().parent().data("posname");
						console.log(empname);
						console.log(empno);
						console.log(depname);
						console.log(posname);
					}
				})
			$("#inputemp_name").val(empname);
			$("#inputemp_no").val(empno);
			$("#inputpos_name").val(posname).prop("selected", true);
			$("#inputdep_name").val(depname).prop("selected", true);
				
			})
	
		});
		
		$("#okBtn").click(function(){
			$("#authForm").attr("action", "${pageContext.request.contextPath}/setting/updateAuth.do"); 
			$("#authForm").submit();
		});

		$("#resignBtn").click(function(){
			$("#authForm").attr("action", "${pageContext.request.contextPath}/setting/updateDel.do"); 
			$("#authForm").submit();
		});
		
		
		$("#menuOk").click(function(){
			$("#menuForm").submit();
		})
		
		function enter(){
			if(event.keyCode==13){
				$("#modalForm").submit();
				
			}
		}
	});
</script>



         <!-- body 부분 -->
            
            <div style="width: 900px;">  
<!--                <div class="col" style="padding-bottom: 50px;"> -->
               
<!--                   <h3>페이지 설정</h3> -->
<!--                   <p>한 페이지에 보여지는 게시글 수를 설정합니다.</p> -->
<!--                   <br>     -->
<!-- 				<div class="row">   -->
<!--                   <div class="col"> -->
<!--                      <select class="form-control"> -->
<!--                         <option>10</option>   -->
<!--                         <option>30</option> -->
<!--                         <option>50</option> -->
<!--                      </select>     -->
<!--                   </div> -->
<!--                   <div class="col"> -->
<!--                         <button type="button" class="btn btn-primary">확인</button> -->
<!--                   </div>   -->
<!--                   </div>   -->
<!--                </div> -->
          
               <br>
               <div class="col" style="padding-bottom: 50px;">
                  <h3>권한 설정</h3>
                  <p>사원들의 권한을 부여합니다.</p>
                  <br>
                  <form id="authForm"  method="post">
                 	<button type="submit" style="display : none;">검색</button>
                  <div class="row">
                     <div class="col xs-2 md-2">
                        <input class="form-control" name="inputemp_name" id="inputemp_name" type="text"  readonly="readonly">
                     </div>
                     <div class="col xs-1 md-1">
                        <button type="button" class="btn btn-outline-primary"
                           data-toggle="modal" data-target="#exampleModal">
                           사원 검색</button>
                     </div>
                     <div class="col xs-2 md-2">
                        <input class="form-control" name="inputemp_no" id="inputemp_no" type="text"  readonly="readonly" >
                     </div>
                     <div class="col xs-2 md-2">  
                        <select class="form-control" name="inputdep_name" id="inputdep_name">
                           <option>-</option>
                           <option>영업1팀</option>
                           <option>영업2팀</option>
                           <option>영업3팀</option>
                           <option>영업4팀</option>
                           <option>영업5팀</option>
                           <option>구매1팀</option>
                           <option>구매2팀</option>
                           <option>구매3팀</option>
                           <option>구매4팀</option>
                           <option>구매5팀</option>
                        </select>
                     </div>
                     <div class="col xs-2 md-2">
                        <select class="form-control" name="inputpos_name" id="inputpos_name">
                           <option>-</option>
                           <option>사원</option>
                           <option>대리</option>
                           <option>과장</option>
                           <option>팀장</option>
                           <option>상무</option>
                        </select>
                     </div>
                     <div class="col xs-1 md-1">
                        <button type="button" id="okBtn" class="btn btn-outline-primary">확인</button>
                     </div>
                     <div class="col xs-2 md-1 offset-col-md-3">
                        <button type="button" id="resignBtn" class="btn btn-outline-secondary">퇴사처리</button>
                     </div>
                  </div>
                  </form>
               </div>
               <hr>
               <br>
               <div class="col">
                  <h3>메뉴 관리</h3>
                  <p>메뉴 활성/비활성 상태를 설정합니다.</p>
                  <br>
                  <form id="menuForm" method="post" action="${pageContext.request.contextPath}/settingUp/menuManage.do">
                  <div class="row">
                     <div class="col col-xs-4 col-md-4">
                        <select class="form-control" name="menu_name">
                           <option>주문서조회</option>
                           <option>재고관리</option>
                           <option>품목관리</option>
                           <option>발주관리</option>
                           <option>견적관리(구매)</option>
                           <option>거래처관리(구매)</option>
                           <option>활동관리</option>
                           <option>거래처관리(영업)</option>
                           <option>주문관리</option>
                           <option>견적관리(영업)</option>
                           <option>영업팀활동관리</option>
                           <option>사원관리</option>
                           <option>전자결재</option>
                           <option>매출관리</option>
                           <option>마이페이지</option>
                        </select> 
                     </div>
                     <div class=" col col-xs-3 col-md-3">
                        <input type="radio" name="activity" value="활성">활성 
                        &nbsp;&nbsp;&nbsp;
                        <input type="radio" name="activity" value="비활성">비활성
                     </div>
                     <div class="col col-xs-2 col-xs-2">
                        <button type="button" id="menuOk" class="btn btn-outline-primary">확인</button>
                     </div>
                  </div>
                  </form>
               </div>
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
                  <form action="/setting/settingPage" id="modalForm" method="post" onsubmit="return false;">
                     <div class="container">
                        <div class="row">
                           <div class="col">  
                              <input class="form-control" id="emp_name" name="emp_name" type="text"  onkeypress="enter();" />
                           </div>
                           <div class="col">
                              <button class="btn btn-primary" id="findEmp" type="button">검색하기</button>
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
                     <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">닫기</button>
                     <button type="button" id="selectBtn" class="btn btn-primary" data-dismiss="modal">선택하기</button>
                  </div>
               </div>
            </div>
         </div>



   <!-- 리스트확인하기 버튼을 눌렀을 때의 모달창 -->
        
