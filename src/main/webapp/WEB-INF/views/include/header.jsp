<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>WAops</title>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="/resources/css/WAOps.css" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<!--jQuery.2.1.4 -->
	<script src="/resources/js/jQuery-2.1.4-min.js"></script>
	<!-- jQuery Form -->
	<script src="/resources/js/jQueryForm-min.js"></script>
	<script src="/resources/js/sticky-kit.js"></script>
</head>
<body>
	<!-- header -->
	<header>
		<div class="header-btn f-r mg-bottom10">
			<button type="button" class="btn btn-warning mg-right10 mg-top10">사이트 소개</button>
			<button type="button" class="btn btn-default mg-right10 mg-top10">로그인</button>
			<button type="button" class="btn btn-default mg-right10 mg-top10">회원가입</button>
		</div>
		<div class="header-main mg-top10 bd-orange c-b o-h">
			<h1 class="f-l"><a href="#"><span>W</span>e <span>A</span>re devel<span>Op</span>er<span>s</span></a></h1>
			<div class="header-img f-r mg-right30 mg-top10">
				<a href="#">
					<img src="/resources/img/user.jpg" alt="">
				</a>
			</div>
		</div>
		<div class="header-search mg-top10 o-h">
			<select class="form-control w-15 f-l">
  				<option>회원 명</option>
  				<option>게시물 명</option>
			</select>
			<input type="text" class="form-control w-80 f-l" placeholder="Search">
			<button type="button" class="btn btn-default w-5 f-l" aria-label="Left Align">
 				 <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
			</button>
		</div>
	</header>