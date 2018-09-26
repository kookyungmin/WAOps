<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
<%@ page session="false" %>

<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
	
	<h1>질문 등록</h1>
	
	<div class="w-80 f-l mg-top30">
		<div class="mg-bottom10">
			<label for="writer">작성자</label>
    		<input type="text" class="form-control" id="writer" placeholder="작성자를 입력하시오." oninput="checkEdit(false)">
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
 		<div class="mg-bottom30">
    		<label for="inputFile">File input</label>
    		<input type="file" id="inputFile">
  		</div>
  		<div class="mg-bottom30">
  			<button id="btnModQuestion" class="btn btn-warning" onclick="update(false)">등록</button>
  			<a href="/questions/all"><button class="btn btn-danger">취소</button></a>
  		</div>
	</div>
<%@ include file="../include/footer.jsp" %>
<script>
	$('#btnModQuestion').addClass('disabled');
</script>