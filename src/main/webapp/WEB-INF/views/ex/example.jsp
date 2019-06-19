<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   			<div class="container col-md-8">


				<div class="col">
					<label for="validationServerUsername">이메일</label> <label
						for="validationServer02">이름</label> <input type="email"
						class="form-control" placeholder="name@example.com">
					<div class="invalid-feedback">이메일 입력</div>
				</div>
				<div class="col">
					<label for="validationServer03">생년월일</label> <input type="date"
						class="form-control" placeholder="생년월일" required>
					<div class="invalid-feedback">생년월일 양식 확인</div>
				</div>
				<div class="col">
					<label for="validationServer05">주소</label> <input type="text"
						class="form-control" placeholder="ex) 대전시 중구 대흥동" required>
					<button type="button" class="btn btn-primary col-xs-2 col-md-4">검색</button>
				</div>


				<button type="button" class="btn btn-outline-primary"
					style="width: 150px; height: 40px;" data-toggle="modal"
					data-target="#exampleModal">버튼 1</button>
				<button type="button" class="btn btn-primary"
					style="width: 150px; height: 40px;" data-toggle="modal"
					data-target="#exampleModal">버튼 2</button>

				<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">First</th>
							<th scope="col">Last</th>
							<th scope="col">Handle</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">1</th>
							<td>Mark</td>
							<td>Otto</td>
							<td>@mdo</td>
						</tr>
						<tr>
							<th scope="row">2</th>
							<td>Jacob</td>
							<td>Thornton</td>
							<td>@fat</td>
						</tr>
						<tr>
							<th scope="row">3</th>
							<td colspan="2">Larry the Bird</td>
							<td>@twitter</td>
						</tr>
					</tbody>
				</table>

				<!-- 페이지네이션 -->
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<li class="page-item disabled"><a class="page-link" href="#"
							tabindex="-1" aria-disabled="true">&lt;</a></li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item"><a class="page-link" href="#">&gt;</a></li>
					</ul>
				</nav>

				<input type="radio" name="activity">활성 <input type="radio"
					name="activity">비활성
				<table class="table table-sm">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">First</th>
							<th scope="col">Last</th>
							<th scope="col">Handle</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">1</th>
							<td>Mark</td>
							<td>Otto</td>
							<td>@mdo</td>
						</tr>
						<tr>
							<th scope="row">2</th>
							<td>Jacob</td>
							<td>Thornton</td>
							<td>@fat</td>
						</tr>
						<tr>
							<th scope="row">3</th>
							<td colspan="2">Larry the Bird</td>
							<td>@twitter</td>
						</tr>
					</tbody>
				</table>

				<!-- select -->
				<div class="input-group">
					<select class="custom-select" id="inputGroupSelect04"
						aria-label="Example select with button addon">
						<option selected>선택하기</option>
						<option value="1">One</option>
						<option value="2">Two</option>
						<option value="3">Three</option>
					</select>
					<div class="input-group-append">
						<button class="btn btn-outline-secondary" type="button">선택</button>
					</div>
				</div>

				<!-- 파일 선택하기 -->
				<div class="input-group mb-3">
					<div class="custom-file">
						<input type="file" class="custom-file-input" id="inputGroupFile02">
						<label class="custom-file-label" for="inputGroupFile02"
							aria-describedby="inputGroupFileAddon02">파일을 선택하십시오</label>
					</div>
					<div class="input-group-append">
						<span class="input-group-text" id="inputGroupFileAddon02">등록</span>
					</div>
				</div>

				<!-- 체크박스  -->
				<input type="checkbox" >체크박스입니다.
				
				<!-- 뱃지 -->
				<span class="badge badge-pill badge-secondary">new</span>

				<!-- breadcrumb -->
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">선택사항1</a></li>
						<li class="breadcrumb-item"><a href="#">선택사항2</a></li>
						<li class="breadcrumb-item active" aria-current="page">막힌사항</li>
					</ol>
				</nav>

				<!-- 카드  -->
				<div class="card" style="width: 18rem;">
					<img src="${pageContext.request.contextPath }/resources/images/company.jpeg" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">이것도 쓸 수 있으면 쓰면 좋을듯?</h5>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>
						<a href="#" class="btn btn-primary">버튼3</a>
					</div>
				</div>
				
				<div class="toast" role="alert" aria-live="assertive"
					aria-atomic="true">
					<div class="toast-header">
						<img src="..." class="rounded mr-2" alt="..."> <strong
							class="mr-auto">Bootstrap</strong> <small>11 mins ago</small>
						<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="toast-body">Hello, world! This is a toast
						message.</div>
				</div>


				<!-- 튤립  -->
				<button type="button" class="btn btn-secondary"
					data-toggle="tooltip" data-placement="top" title="데헷데헷">
					마우스를 갖다대보세요</button>







				<form>
					<div class="form-group">
						<label for="exampleFormControlInput1">Email address</label> <input
							type="email" class="form-control" id="exampleFormControlInput1"
							placeholder="name@example.com">
					</div>
					<div class="form-group">
						<label for="exampleFormControlSelect1">Example select</label> <select
							class="form-control" id="exampleFormControlSelect1">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select>
					</div>
					<div class="form-group">
						<label for="exampleFormControlSelect2">Example multiple
							select</label> <select multiple class="form-control"
							id="exampleFormControlSelect2">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select>
					</div>
					<div class="form-group">
						<label for="exampleFormControlTextarea1">Example textarea</label>
						<textarea class="form-control" id="exampleFormControlTextarea1"
							rows="3"></textarea>
					</div>
				</form>



				<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#exampleModal2">모달창 버튼입니다.</button>

				<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">모달창</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="container">
								이것은 모달창입니다.
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">닫기</button>
							<button type="button" class="btn btn-primary">오키오키</button>
						</div>
					</div>
				</div>
			</div>
			
			
			<!-- date picker 사용 -->
			
			<table class="table">
				<tr>
					<td>날짜</td>
					<td><input type="date" id="testDatepicker"></td>
				</tr>
			</table>
			

			</div>
			<script>
				<!-- date picker를 사용하기 위한 script 설정 -->
				  $( "#testDatepicker" ).datepicker({
				         changeMonth: true, 
				         dateFormat: 'yy-mm-dd',
				         showOn: 'button',
				         dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
				         dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
				         monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				         monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				  });
			</script>