<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<%@ include file="./include/header.jsp" %>
<%@ include file="./include/nav.jsp" %>

<div class="content">
	<div class="timelinecontent">
		<div class="o-h">
			<div class="timelineprofile f-l">
				<img id="me" src="/resources/img/me.png" alt="" />
			</div>
			<span class="f-l mg-left10 mg-bottom10">꾸리</span>
			<button class="btn btn-default btn-sm f-r">수정</button>
		</div>
		<div class="mg-top30 mg-bottom10 c-b">
			실습 동영상
		</div>
		<div class="video">
			<video src="/resources/img/result.mp4" controls autoplay></video>
		</div>
		<div id="light1" class="mg-top10">
 			<img src="/resources/img/light-off.png" alt="" width="30" height="30" />
 			<button id="goodBtn1" class="btn btn-default btn-sm mg-right10">lightON!</button>
 			<hr />	
 		</div>
 		<button class="btn btn-warning btn-sm">댓글</button>
	</div>
	
	<div class="timelinecontent">
		<div class="o-h">
			<div class="timelineprofile f-l">
				<img id="me" src="/resources/img/me.png" alt="" />
			</div>
			<span class="f-l mg-left10 mg-bottom10">꾸리</span>
			<button class="btn btn-default btn-sm f-r">수정</button>
		</div>
		<div class="mg-top30 mg-bottom10 c-b">
				sixthFinger 구동 장면
		</div>
		<div class="video">
			<video src="/resources/img/sixfinger.mp4" controls autoplay></video>
		</div>
		<div id="light2" class="mg-top10">
 			<img src="/resources/img/light-off.png" alt="" width="30" height="30" />
 			<button id="goodBtn2" class="btn btn-default btn-sm mg-right10">lightON!</button>
 			<hr />	
 		</div>
 		<button class="btn btn-warning btn-sm">댓글</button>
	</div>
</div>

<%@ include file="./include/footer.jsp" %>

<script>
	let lightOn1 = false,
	    lightOn2 = false;
	$('#goodBtn1').on('click', ()=>{
		if(lightOn1 == false){
			console.debug(lightOn1);
			lightOn1 = true;
			$('#light1>img').attr({'src':'/resources/img/light-on.png'});
			$('#goodBtn1').text('lightOFF!');
		}else{
			console.debug(lightOn1);
			lightOn1 = false;
			$('#light1>img').attr({'src':'/resources/img/light-off.png'});
			$('#goodBtn1').text('lightON!');
		}
	})
	$('#goodBtn2').on('click', ()=>{
		if(lightOn2 == false){
			console.debug(lightOn2);
			lightOn2 = true;
			$('#light2>img').attr({'src':'/resources/img/light-on.png'});
			$('#goodBtn2').text('lightOFF!');
		}else{
			console.debug(lightOn2);
			lightOn2 = false;
			$('#light2>img').attr({'src':'/resources/img/light-off.png'});
			$('#goodBtn2').text('lightON!');
		}
	})
</script>