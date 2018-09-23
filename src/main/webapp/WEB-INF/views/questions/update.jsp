<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
<%@ page session="false" %>

<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
	
	<h1>질문 수정</h1>
	
	<script id="readQuestion" type="text/x-handlebars-template" class="w-80 f-l mg-top30">
		<div class="mg-bottom10">
			<label for="writer">작성자</label>
    		<input type="text" class="form-control" id="writer" value="{{writer}}" oninput="checkEdit(${isEdit})" readonly="readonly">
    	</div>
    	<div class="mg-bottom10">
    		<label for="title">제목</label>
    		<input type="text" class="form-control" id="title" value="{{title}}" oninput="checkEdit(${isEdit})">
  		</div>
  		<div class="mg-bottom10">
  			<label for="content">내용</label>  		
  			<textarea class="form-control" id="content" rows="15" oninput="checkEdit(${isEdit})">{{content}}</textarea>
 		</div>
 		<div class="mg-bottom10 w-15">
 			<label for="score">채택 점수</label>
 			<select onchange="checkEdit(${isEdit},true)" class="form-control" id="score">
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
	</script>
  	<div class="mg-bottom30">
  		<button id="btnModQuestion" class="btn btn-warning" onclick="update(${isEdit})">수정</button>
  		<a href="/questions/read?qno=${qno}"><button class="btn btn-danger">취소</button></a>
  	</div>
	
<%@ include file="../include/footer.jsp" %>

<script>
	read(${qno});
	$('#btnModQuestion').addClass('disabled');
</script>