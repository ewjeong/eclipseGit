<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019.06.05   정은우      최초작성
* 2019.06.07   이초연      상품 이미지
* Copyright (c) ${year} by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<h3>상품 상세조회</h3>
<br>
<br>
   <div>
      <table class="table table-bordered">
         <c:set value="${prodList }" var="prod" />
         <tr>
            <th class="thwidth">상품번호</th>
            <td colspan="4">${prod.prod_no}</td>
         </tr>
         <tr>   
            <th class="thwidth">품목명</th>
            <td colspan="4">${prod.item_name }</td>
         </tr>
         <tr>
            <th class="thwidth">상품명</th>
            <td colspan="4">${prod.prod_name }</td>
         </tr>
         <tr>
            <th class="thwidth">상품크기</th>
            <td colspan="4">${prod.prod_size }</td>
         </tr>
         <tr>
            <th class="thwidth">상품색상</th>
            <td colspan="4">${prod.prod_color }</td>
         </tr>
         <tr>
            <th class="thwidth">상품사진</th>
            <td colspan="4">
            	<!-- 데이터 스키마 활용 ==> https://en.wikipedia.org/wiki/Data_URI_scheme -->
            	<c:if test="${ not empty prod.prod_imgBase64 }">
            		<img src="data:image/*;base64, ${prod.prod_imgBase64 }" width="300px">
            	</c:if>
            	<c:if test="${ empty prod.prod_imgBase64 }">
            		등록된 상품 이미지가 없습니다.
            	</c:if>
            </td>
         </tr>
         <tr>
            <th class="thwidth">단가</th>
            <td colspan="3">${prod.prod_cost }</td>
         <tr>
            <th class="thwidth">상품설명</th>
            <td colspan="4">${prod.prod_outline }</td>
         </tr>
         <tr>
            <th class="thwidth">상품상세설명</th>
            <td colspan="4">${prod.prod_details }</td>
         </tr>
    </table>     
</div>


   
   
   
   
   
   
   
   
   