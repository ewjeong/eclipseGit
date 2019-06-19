<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

    <h3>거래처 상세조회</h3>
	

	<div>
		<table class="table table-bordered">
<!-- 			<tr> -->
<!-- 				<th class="thwidth">주문일자</th> -->
<!-- 				<td colspan="3">2019.05.10</td> -->
<!-- 			</tr> -->
			<tr>
				<th class="thwidth">거래처코드</th>
				<td class="tdwidth">ISMS001</td>
				
				<th class="thwidth">거래처명</th>
				<td class="tdwidth">샘송컴퓨터</td>
			</tr>
			<tr>
				<th class="thwidth">거래처 담당자</th>
				<td class="tdwidth">정은우</td>
			</tr>
			<tr>
				<th class="thwidth">거래처 전화번호</th>
				<td class="tdwidth">010-0000-000</td>
			</tr>
			<tr>
				<th class="thwidth">거래처 이메일</th>
				<td class="tdwidth">abc123@gmail.com</td>
				<th class="thwidth">수량</th>
				<td class="tdwidth"></td>
			</tr>
			<tr>
				<th class="thwidth">거래처 주소</th>
				<td class="tdwidth">대전광역시 중구 대흥동</td>
				<th class="thwidth">상세주소</th>
				<td class="tdwidth">영민빌딩 2층 대덕인재개발원 207호</td>
			</tr>
			<tr>
				<th class="thwidth">담당자</th>
				<td class="thwidth">이진경</td>
			</tr>
			<tr>
				<th class="thwidth">거래처 상세정보</th>
				<td  colspan="3"></td>
			</tr>
		</table>
		<div class="btnCenterWrap">
			<button type="button" class="btn btn-primary" onclick="location.href='<c:url value="/salesTeam/buyerManage/buyerUpdate" />';">수정</button>
			<button type="button" class="btn btn-primary" onclick="location.href='<c:url value="/salesTeam/buyerManage/buyerDelete" />';">삭제</button>
		</div>
	</div>