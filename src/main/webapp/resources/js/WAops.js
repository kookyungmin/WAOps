$(function(){

	//submenu 슬라이드 
	$('.level1>a').on('click', function(){
		$('.level2').stop().slideUp(300);
		$(this).parent().find('.level2').stop().slideDown(300);
		$('.level1>a').removeClass('nav-active');
		$(this).addClass('nav-active');
	})

	$('.level2').on('click', function(){
		$(this).stop().slideUp(300);
	})

	$('.a-default').on('click', function(e){
		e.preventDefault();
	})
	
})