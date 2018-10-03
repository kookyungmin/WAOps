<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ page session="false" %>

<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
<div class="content">
	<h1>질문/답변</h1>
	<div class="mg-bottom10 right">
 		<select class="form-control w-15 display-i" id="perPageNum" onchange="changePerPageNum()">
  			<option value="5">5개씩 보기</option>
 			<option value="10">10개씩 보기</option>
  			<option value="15">15개씩 보기</option>
		</select>
	</div>
	<table class="table table-hover mg-top30">
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
					<td><a href="/questions/read?qno={{qno}}&page={{../currentPage}}&perPageNum={{../perPageNum}}">{{title}}</a></td>
					<td><a href="#">{{writer}}</a></td>
					<td>{{score}}</td>
					<td>{{lightcnt}}</td>
					<td>{{viewcnt}}</td>
					<td>{{fullTime regdate}}</td>
				</tr>
			{{/each}}
		</script>
	</table>
	<script id="pagination" type="text/x-handlebars-template">
		<a href="/questions/register"><button class="btn btn-warning">질문 등록</button></a>
  		<div class="center">
			<ul class="pagination mg-0">
    			<li {{#unless pageMaker.prev}}class="disabled"{{/unless}}>
    				<a href="javascript:;" onclick="listPage({{pageData.prevPage}})" aria-label="Previous">
    					<span aria-hidden="true">&laquo;</span>
    				</a>
    			</li>

				{{#each pageData.pages as |page|}}
    				<li {{#if (eq ../currentPage page)}}class="active"{{/if}}>
						<a href="javascript:;" onclick="listPage({{page}})">{{page}}</a>
					</li>
    			{{/each}}
    		
				<li {{#unless pageMaker.next}}class="disabled"{{/unless}}>
      				<a href="javascript:;" onclick="listPage({{pageData.nextPage}})" aria-label="Next">
        				<span aria-hidden="true">&raquo;</span>
      				</a>
    			</li>
  			</ul>
		</div>
  	</script>
</div>
<%@ include file="../include/footer.jsp" %>

<script>
	listPage(${cri.page}, ${cri.perPageNum});
</script>

