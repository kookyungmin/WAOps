<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>

<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
<div class="content">
	<h1>질문 등록</h1>
	
	<div class="mg-top30">
		<div class="mg-bottom10">
			<label for="writer">작성자</label>
    		<input type="text" class="form-control" id="writer" readonly="readonly" value="${loginUser.uid}">
    	</div>
    	<div class="mg-bottom10">
    		<label for="title">제목</label>
    		<input type="text" class="form-control" id="title" placeholder="제목을 입력하시오." oninput="checkEdit(false)">
  		</div>
  		<div class="mg-bottom10">
  			<label for="content">내용</label>  		
  			<textarea class="form-control" id="content" rows="15" oninput="checkEdit(false)"></textarea>
 		</div>
 		<div class="mg-bottom10 w-15">
 			<label for="score">채택 점수</label>
 			<select class="form-control" id="score">
  				<option value="5">5</option>
 				<option value="10">10</option>
  				<option value="20">20</option>
  				<option value="50">50</option>
  				<option value="100">100</option>
			</select>
		</div>
 		<div class="fileUpload mg-bottom10">
			<label for="">파일 첨부</label>
			<div class="fileDrop form-control">
				파일을 올려놓으세요!
			</div>
			<span id="status" class="center mg-bottom10"></span>
		</div>
		
		<%@include file="../uploadedFiles.jsp" %>
		
		<form id="form_attach" action="/uploadAjax"  method="post" enctype="multipart/form-data">
			<input  id="isDirect" value="true" name="isDirect" class="hidden" />
			<input id="ajax-file" type="file" name="files"  class="hidden"/>		
		</form>
		
  		<div class="mg-bottom30">
  			<button id="btnModQuestion" class="btn btn-warning" onclick="update(false)">등록</button>
  			<button class="btn btn-danger" onclick="cancle(false)">취소</button>
  		</div>
	</div>
</div>
<%@ include file="../include/footer.jsp" %>

<script>
	$('#btnModQuestion').addClass('disabled');
</script>