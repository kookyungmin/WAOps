<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ page session="false" %>

<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
	<script id="readQuestion" type="text/x-handlebars-template" class="w-80 f-l mg-top30">
		<div class="mg-bottom10">
			<h2>{{title}}</h2>
			<span class="mg-right30">작성자: {{writer}} </span>
			<span class="mg-right30">채택점수: {{score}}</span>
			<span>첨부파일: </span>
    	</div>
    	<div class="mg-bottom10 o-h">
    		<h4 class="f-l">내용:</h4>
    		<div class="f-r">
  				<button id="btnModQuestion" class="btn btn-warning btn-sm">수정</button>
  				<button class="btn btn-danger btn-sm">삭제</button>
  				<a href="/questions/all"><button class="btn btn-primary btn-sm">목록</button></a>
  			</div>
    	</div>
  		<div class="mg-bottom10">  		
  			<textarea class="form-control" id="content" rows="15" readonly="readonly">{{content}}</textarea>
 		</div>
		<span> {{lightcnt}}명의 유저가 lightON! 하셨습니다. </span>
	</script>
	<div class="light w-80 f-l mg-top10">
 		<img src="/resources/img/light-off.png" alt="" width="30" height="30" />
 		<button id="goodBtn" class="btn btn-default btn-sm mg-right10">lightON!</button>
 		<hr />	
 	</div>
<%@ include file="../include/footer.jsp" %>

<script>
	read(${qno});
	let lightOn = false;
	$('#goodBtn').on('click', ()=>{
		if(lightOn == false){
			console.debug(lightOn);
			lightOn = true;
			$('.light>img').attr({'src':'/resources/img/light-on.png'});
			$('#goodBtn').text('lightOFF!');
		}else{
			console.debug(lightOn);
			lightOn = false;
			$('.light>img').attr({'src':'/resources/img/light-off.png'});
			$('#goodBtn').text('lightON!');
		}
	})
</script>
