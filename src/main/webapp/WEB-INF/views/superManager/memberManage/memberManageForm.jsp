<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="container col-md-8 col-xs-4">
			<div class="row">
				<div class="col">
					<label for="validationServer01">아이디</label> 
					<label>&nbsp;|&nbsp;</label>
					<label>ISMS001</label>
				</div>
				<br>
				<div class="col">
					<label for="validationServer02">이름</label> 
					<label>&nbsp;|&nbsp;</label>
					<label>이진경</label>
				</div>
				<br>
				<div class="col">
					<label for="validationServer03">생년월일</label> 
					<label>&nbsp;|&nbsp;</label>
					<label>1995년 00월 00일</label>
				</div>
				<br>
				<div class="col">
					<label for="validationServerUsername">이메일</label>
					<input type="email"
						class="form-control" placeholder="name@example.com">
					<div class="invalid-feedback">이메일 입력</div>
				</div>
				<br>
				<div class="col">
					<label for="validationServer05">주소</label> <input type="text"
						class="form-control" placeholder="ex) 대전시 중구 대흥동" required>
					<button type="button" class="btn btn-primary">찾기</button>
				</div>
				<div class="col">
					<label for="validationServer05">상세주소</label> <input type="text"
						class="form-control" placeholder="ex) 123-1번지 101호" required>
				</div>
				<br>
				<div class="col">
					<label for="validationServer05">핸드폰</label>
					<label>&nbsp;|&nbsp;</label>
					<label>000-0000-0000</label>
				</div>
				<br>
				<div class="col">
					<label for="validationServer05">성별</label>
					<label>&nbsp;|&nbsp;</label>
					<label>여성</label>
				</div>
				<br>
				<div class="col">
					<label for="validationServer05">부서명</label>
					<select>
						<option >
					</select>
					</div>
				<br>
				<div class="col">
					<label for="validationServer05">성별</label>
					<label>&nbsp;|&nbsp;</label>
					<label>여성</label>
				</div>
				
				<div class="col">
					<button class="btn btn-primary" type="button" onclick='location.href="<c:url value="/myPage/myPage" />";'
					 style="padding-bottom: 10px; margin: 10px 0px 10px 0px;" >확인</button>
				</div>
			</div>
			<div class="col"></div>
		</div>

	</div>