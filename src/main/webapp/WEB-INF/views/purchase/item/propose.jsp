<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>상품검출서비스</h3>
<br>
<br>
<style>
#imageInput {
	width: 30%;
	height: 50%;
	border: 1px solid #E6E6E6;
}
.prodResp{ font-size: 18px; }
 .proposeImg { width: 100px; }
</style>
<c:if test="${empty exist }">
	<form id="productDetect" name="productDetect" method="post"
		enctype="multipart/form-data">
	<!-- 버튼을 클릭하면? -->
		
		<div class="form-group filebox">
		찾으려는 상품 사진 &nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;
	       <label for="file"><i class="fas fa-paperclip"></i></label>
			<input type="file" id="fileInput"  name="upload" accept="image/*" /><br>
	    </div>
	    <br>
		<img id="thumbnail" style="width: 20%; margin-top: 10px;" src="" alt="image" />
		
		<input type="hidden" id="resultData" value="${result }">
		<button id="prodSearchBtn" style="width:180px;" type="submit" class="image_url_detect btn btn-outline-primary"
			data-section="product">검색</button>
		<input type="hidden" value="noExist" name="exist">
	</form>
</c:if>
<br>
<c:set var="result" value="${result.result }"/>
	
	<hr>
	<div class="row">
	상품검출 결과는
	<c:forEach items="${result.objects }" var="object" varStatus="status">
		${object.prodName }
		<input class="prodResp" type="hidden" value="${object.prodName }">
	</c:forEach>
	입니다
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<form id="appendTem" >
			<button type="button" id="resultView" style="width:180px;" class="btn btn-outline-primary">상품 조회결과 보기</button>
		</form>
	</div>
	<hr>
	<br>
	<br>



<table class="table">
	<thead id="prodhead">
	</thead>
	<tbody id="prodAdd">
			
	</tbody>	
</table>

<c:if test="${ not empty product.prod_imgBase64 }">
	<img src="data:image/*;base64, ${prod.prod_imgBase64 }" class="proposeImg">
</c:if>

<div>

</div>

<script>

	$("input[name='upload']").on("change", function(e) {
		
		var files = fileInput.files; //선택한 파일들(배열)
		for(var i = 0; i<files.length; i++){
			var file = files[i]; //하나씩 꺼낸다
			var imageType = /image.*/;
			if(!file.type.match(imageType)){ //만약에 imageType이file타입과 일치하지않으면
				continue;
			}
			var img = document.getElementById("thumbnail"); //썸네일이라는 아이디의 img태그를 가져옴
			img.file = file; //file을 집어넣음
			var reader = new FileReader(); //file을 읽어들이는 reader 객체
			reader.onload = (function(aImg){
				return function(e){
					aImg.src = e.target.result; 
				};
			})(img);
			reader.readAsDataURL(file);
		}
		
// 		var paramOne =<c:out value="${result}"/>
	
	});
	
	//먼저, 파일을 입력해서 변화가 생기면, 그 파일에 대한 정보를 읽어서 어떤 상품이 있는지를 파악한다.
	
	
	function save(files){
		for(var i = 0; i<files.length; i++){
			var file = files[i]; //하나씩 꺼낸다
			var imageType = /image.*/;
			if(!file.type.match(imageType)){ //만약에 imageType이file타입과 일치하지않으면
				continue;
			}
			var img = document.getElementById("thumbnail"); //썸네일이라는 아이디의 img태그를 가져옴
			img.file = file; //file을 집어넣음
			var reader = new FileReader(); //file을 읽어들이는 reader 객체
			reader.onload = (function(aImg){
				return function(e){
					aImg.src = e.target.result; 
				};
			})(img);
			reader.readAsDataURL(file);
		}
	}
	
		
	$("#resultView").on("click",function(event){
		var item_name = $(".prodResp").val();
		if(item_name == 't-shirts'){
			item_name = '티셔츠';
		}else if(item_name == 'hat'){
			item_name = '모자';
		}else if(item_name == 'sports shoes'){
			item_name = '운동화';
		}else if(item_name == 'one-piece'){
			item_name = '원피스';
		}
		$.ajax({
			url: "${pageContext.request.contextPath}/purchasingTeam/itemManage/proposeProductAjax",
			method: "GET",
			data: {'item_name': item_name},
			dataType: "json",
			success: function(resp){ //성공하면 리스트를 받음
				console.log(JSON.stringify(resp));
				productView(resp);
			
			},
			error: function(errorResp){
				alert(errorResp.status);
				console.log(errorResp.status);
			}
		});
// 		return false;
 	});
// }
	
	function productView(resp){ //list가 들어가있음
		
		var trTags = [];
		var headtr = $("<tr>").append(
			$("<td>").text("상품명"),		
			$("<td>").text("품목코드"),		
			$("<td>").text("품목명"),		
			$("<td>").text("크기"),		
			$("<td>").text("가격"),		
			$("<td>").text("개요"),		
			$("<td>").text("색상"),		
			$("<td>").text("갯수"),		
			$("<td>").text("이미지")	
		);
		
		$(resp).each(function(idx, product){
				
				var tr = $("<tr>").append(
					$("<td>").text(product.prod_name),		
					$("<td>").text(product.item_code),		
					$("<td>").text(product.item_name),		
					$("<td>").text(product.prod_size),		
					$("<td>").text(product.prod_cost),		
					$("<td>").text(product.prod_outline).css({
						"overflow":"hidden"
						, "text-overflow":"ellipsis"
						, "white-space":"nowrap"
						, "width" : "130px"
					}),	
					$("<td>").text(product.prod_color),		
					$("<td>").text(product.prod_qty),		
					$("<td>").append(
							$("<img>",{
								src:'data:image/*;base64,'+ product.prod_imgBase64
							}).css({
								"width": "100px"
							})
					)
				);
				trTags.push(tr);
			});
			$("#prodhead").html(headtr);
			$("#prodAdd").html(trTags);
	}

	
</script>