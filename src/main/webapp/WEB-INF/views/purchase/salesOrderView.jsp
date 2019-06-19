<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<h3>주문서 상세조회</h3>
<br>
<br>
   <div>
      <table class="table table-bordered">
         <c:set value="${saleOrdList }" var="sale_ord" />
         <tr>
            <th class="thwidth">주문번호</th>
            <td td colspan="4">${sale_ord.sale_ord_code}</td>
         </tr>
         <tr>   
            <th class="thwidth">주문일자</th>
            <td td colspan="4">${sale_ord.sale_ord_date }</td>
         </tr>
         <tr>
            <th class="thwidth">주문자</th>
            <td td colspan="4">${sale_ord.cl_charger }</td>
         </tr>
         <tr>
            <th class="thwidth">주문자전화번호</th>
            <td td colspan="4">${sale_ord.cl_tel }</td>
         </tr>
         <tr>
            <th class="thwidth">수령자</th>
            <td td colspan="4">${sale_ord.cl_receive }</td>
         </tr>
         <tr>
            <th class="thwidth">수령자주소</th>
            <td colspan="4">${sale_ord.cl_add1 }</td>
         </tr>
         <tr>
            <th class="thwidth">수령자상세주소</th>
            <td colspan="4">${sale_ord.cl_add2 }</td>
         </tr>
         <tr>
            <th class="thwidth">결제방법</th>
            <td colspan="4">${sale_ord.payment }</td>
         </tr>
         <tr>
            <th class="thwidth">결제금액</th>
            <td colspan="4">${sale_ord.total_cost }</td>
         </tr>
         <tr>
            <th class="thwidth">거래처코드</th>
            <td colspan="3">${sale_ord.cl_no }</td>
         <tr>
         <tr>
            <th class="thwidth">거래처명</th>
            <td colspan="3">${sale_ord.cl_name }</td>
         </tr>
         <tr>
            <th class="thwidth">출고상태</th>
            <td colspan="3">${sale_ord.sale_ord_complete }</td>
         </tr>
      </table>
      <div class="col-xs-12 col-md-10">
      <table class="table">
         <thead>
         <tr>
            <th>상품코드</th>
            <th>상품명</th>
            <th>색상</th>
            <th>크기</th>
            <th>수량</th>
            <th>단가</th>
         </tr>
         </thead>
         <tbody>
         <c:forEach items="${sale_ord.sale_oprodList }" var="oprod" >
             <tr>
               <td>${oprod.prod_no }</td>
               <td>${oprod.prod_name }</td>
               <td>${oprod.prod_color }</td>
               <td>${oprod.prod_size }</td>
               <td>${oprod.sale_oprod_qty }</td>
               <td>${oprod.sale_oprod_cost }</td>
            </tr>
         </c:forEach>
         </tbody>
       </table>
	<br>
	<br>
      <a class="btn btn-outline-primary col-xs-4 col-md-2" href='<c:url value="/pdf/salesOrderView.do?what=${sale_ord.sale_ord_code}"/>' >
      PDF출력</a> 
      
   </div>


   
   
   
   
   
   
   
   
   