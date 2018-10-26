<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
<div class="content">	
	<script id="readQuestion" type="text/x-handlebars-template" class="mg-top30">
		<div class="mg-bottom10">
			<h2>{{title}}</h2>
			<span class="mg-right30">작성자: {{writer}} </span>
			<span class="mg-right30">채택점수: {{score}}</span>
    	</div>
    	<div class="mg-bottom10 o-h">
    		<h4 class="f-l">내용:</h4>
    		<div class="f-r">
  				<a href="/questions/update?qno={{qno}}&page={{page}}&perPageNum={{perPageNum}}">
					<button class="btn btn-warning btn-sm">수정</button>
				</a>
  				<button class="btn btn-danger btn-sm" onclick=remove({{qno}})>삭제</button>
  				<a href="javascript:postToUrl('/questions/all',{ 'page' : {{page}}, 'perPageNum' : {{perPageNum}} });">
					<button class="btn btn-primary btn-sm">목록</button>
				</a>
  			</div>
    	</div>
  		<div class="mg-bottom10">  		
  			<textarea class="form-control" id="content" rows="15" readonly>{{content}}</textarea>
 		</div>
		<span> {{lightcnt}}명의 유저가 lightON! 하셨습니다. </span>
	</script>
	<div id="light" class="mg-top10">
 		<img src="/resources/img/light-off.png" alt="" width="30" height="30" />
 		<button id="goodBtn" class="btn btn-default btn-sm mg-right10">lightON!</button>
 		<hr />	
 	</div>
 	
 	<%@include file="uploadedFiles.jsp" %>
 	
 	<div id="insertReply" class="mg-bottom30">
 		<h4>답변 등록</h4>
 		<input id="insertReplyer" class= "form-control w-15 mg-bottom10" 
 			oninput="checkEditReply(false)" placeholder= "작성자 입력"/>
 		
 		<textarea id="insertReplyText" rows="4" class="form-control mg-bottom10" 
 			oninput="checkEditReply(false)" placeholder="답글 입력"></textarea>
 		
 		<button id="btnRegisterReply" class="btn btn-warning btn-sm" 
 			onclick="updateReply(false, $('#insertReplyText'), $('#insertReplyer'))">등록</button>
 	</div>
 	 
 	<script id="replies" type="text/x-handlebars-template" class="mg-bottom15">
 		{{#each list}}
			<div id="reply" class="mg-bottom30 o-h">

 				<span id="replyWriter{{rno}}">작성자: {{replyer}}</span>

				<span class="f-r">작성일: {{fullTime updatedate}}</span>

 				<textarea id="replyText{{rno}}" rows="4" class="form-control mg-top10 mg-bottom10" readonly
					oninput="checkEditReply(true)">{{replytext}}</textarea>
 				
				<button id="btnUpdateReply{{rno}}" class="btn btn-primary btn-sm hidden"
					onclick="updateReply(true, $('#replyText{{rno}}'))">수정</button>
				
				<button id="btnDeleteReply{{rno}}" class="btn btn-danger btn-sm hidden"
					onclick="deleteReply()">삭제</button>
				
				<button id="btnCancleReply{{rno}}" class="btn btn-default btn-sm hidden"
					onclick="clickCancleReply({{rno}})">취소</button>
				
				<button class="btnModReply btn btn-default btn-sm f-r" onclick="clickModBtnReply({{rno}})">댓글 수정</button>
 			</div>
		{{/each}}
		{{#if list}}
			<div class="center">
				<ul class="pagination  pagination-sm mg-0">
    				<li {{#unless pageMaker.prev}}class="disabled"{{/unless}}>
    					<a href="javascript:;" onclick="replylistPage({{pageData.prevPage}})" aria-label="Previous">
    						<span aria-hidden="true">&laquo;</span>
    					</a>
    				</li>
					{{#each pageData.pages as |page|}}
    					<li {{#if (eq ../currentPage page)}}class="active"{{/if}}>
							<a href="javascript:;" onclick="replylistPage({{page}})">{{page}}</a>
						</li>
    				{{/each}}
    				<li {{#unless pageMaker.next}}class="disabled"{{/unless}}>
      					<a href="javascript:;" onclick="replylistPage({{pageData.nextPage}})" aria-label="Next">
        					<span aria-hidden="true">&raquo;</span>
      					</a>
    				</li>
  				</ul>
  			</div>
		{{/if}}	
 	</script> 	
</div> 	
<%@ include file="../include/footer.jsp" %>

<script src="/resources/js/reply.js"></script>
<script>
	read(${qno}, ${cri.page}, ${cri.perPageNum});
	replylistPage();
	showAttaches(${qno});
</script>
