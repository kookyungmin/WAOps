<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<%@ include file="./include/header.jsp" %>
<%@ include file="./include/nav.jsp" %>

<video id="resultVideo" src="" controls autoplay></video>
<video id="sixfingerVideo" src="" controls autoplay></video>
<img id="me" src="" alt="" />
<%@ include file="./include/footer.jsp" %>

<script>
	const uphost = window.location.protocol + "//" + window.location.hostname;
	$('#resultVideo').attr({'src': uphost + '/uploads/result.mp4'});
	$('#sixfingerVideo').attr({'src': uphost + '/uploads/sixfinger.mp4'});
	$('#me').attr({'src': uphost + '/uploads/me.png'})
</script>