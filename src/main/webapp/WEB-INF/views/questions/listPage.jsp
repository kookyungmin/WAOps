<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ page session="false" %>

<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>

	<h1>질문/답변</h1>
	
	<table class="table table-hover w-80 mg-top30">
		<thead>
			<tr>
				<td class="w-10">번호</td>
				<td class="w-30">제목</td>
				<td class="w-15">작성자</td>
				<td class="w-10">채택점수</td>
				<td class="w-10"><img src="/resources/img/light-on.png" width="30" height="30" alt="" /></td>
				<td class="w-10">조회수</td>
				<td class="w-15">작성일</td>
			</tr>
		</thead>
		<script id="questionlist" type="text/x-handlebars-template">
			{{#each list}}
				<tr>
					
					<td>{{qno}}</td>
					<td><a href="/questions/read?qno={{qno}}">{{title}}</a></td>
					<td><a href="#">{{writer}}</a></td>
					<td>{{score}}</td>
					<td>{{lightcnt}}</td>
					<td>{{viewcnt}}</td>
					<td>{{fullTime regdate}}</td>
				</tr>
			{{/each}}
		</script>
	</table>
	<a href="/questions/register"><button class="btn btn-warning">질문 등록</button></a>
<%@ include file="../include/footer.jsp" %>

<script>
	Criteria = {
		page : 1,
		perPageNum : 10
	};
	listPage(Criteria);
</script>

