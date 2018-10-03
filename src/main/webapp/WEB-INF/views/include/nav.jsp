<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!-- nav -->
    <div id="sidebar" class="w-15 mg-top10 bd-orange f-l mg-bottom10 mg-right30">
		<section class="nav">
			<li class="level1 home"><a href="#">내 정보</a></li>
			<li class="level1"><a href="#">타임 라인</a></li>
			<li class="level1 community"><a href="#" class="a-default">커뮤니티</a>
				<ul class="level2">
					<li><a href="#">학원/회사 평가</a></li>
					<li><a href="#">스터디 모집</a></li>
					<li class="question">
						<a href="javascript:postToUrl('/questions/all',{'page':1,'perPageNum':5});">질문/답변</a>
					</li>
				</ul>
			</li>
			<li class="level1"><a href="#">교육</a></li>
			<li class="level1"><a href="#">건의사항</a></li>
		</section>
	</div>
	<script>
		//사이드 바 고정
		$('#sidebar').stick_in_parent();
		$myCondition = $('.' + `${myCondition}`);
		$myConditionParent = $myCondition.parent();
		if($myConditionParent.attr('class') === 'level2'){
			$myConditionParent.parent().children('a').addClass('level1-active');
			$myConditionParent.stop().show();
			$myCondition.children('a').addClass('level2-active');
		}else{
			$('.' + `${myCondition}` +'>a').addClass('level1-active');
		}
	</script>