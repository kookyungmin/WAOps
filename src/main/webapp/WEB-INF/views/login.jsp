<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>

<%@ include file="./include/header.jsp" %>
<%@ include file="./include/nav.jsp" %>
<div class="content">
	<h1>로그인</h1>
	<div class="form-horizontal mg-top70">
		<div class="form-group">
			<label for="id" class="col-sm-2 control-label">ID</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="id"
					placeholder="ID">
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="password"
					placeholder="Password">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label> <input id="useCookie" type="checkbox"> Remember me
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<a href="${google_url}"><img src="/resources/img/googleLogin.png" width="200" height="34" /></a>
				<a href="${naver_url}"><img src="/resources/img/naverLogin.png" width="200" height="34" /></a>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button onclick="login()" class="btn btn-warning">로그인</button>
				<a href="#"><button class="btn btn-default">회원가입</button></a>
			</div>
		</div>
	</div>
</div>

<script src="/resources/js/login.js"></script>
<script>
	let fail = '${loginStatus}';
	if(fail == "Failed") {
		alert("아이디와 비밀번호를 다시 한 번 확인해주세요!");
	}
</script>	
<%@ include file="./include/footer.jsp" %>
