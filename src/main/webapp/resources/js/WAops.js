$(function(){
	//submenu 슬라이드 
	$('.level1>a').on('click', function(){
		$this = $(this);
		$('.level2').stop().slideUp(300);
		$this.parent().find('.level2').stop().slideDown(300);
		$('.level1>a').removeClass('level1-active');
		$this.addClass('level1-active');
	})
	
	$('.a-default').on('click', function(e){
		e.preventDefault();
	})
	
})

// sendAjax
const sendAjax = (url, fn, method, jsonData) => {
	let options = {
			method : method || 'GET',
			url : url,
			contentType : "application/json"
	};
	if(jsonData){
		options.data = JSON.stringify(jsonData);
	}
	
	$.ajax(options).always((responseText, statusText, ajaxResult)=>{
		let isSuccess = statusText === 'success';
		fn(isSuccess, responseText);
		if(!isSuccess){
			alert("오류가 발생했습니다. (errorMessage : " + responseText + ")");
		}
	});
};

