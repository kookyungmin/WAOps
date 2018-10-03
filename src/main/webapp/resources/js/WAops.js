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
	
	let lightOn = false;
	$('#goodBtn').on('click', ()=>{
		if(lightOn == false){
			console.debug(lightOn);
			lightOn = true;
			$('#light>img').attr({'src':'/resources/img/light-on.png'});
			$('#goodBtn').text('lightOFF!');
		}else{
			console.debug(lightOn);
			lightOn = false;
			$('#light>img').attr({'src':'/resources/img/light-off.png'});
			$('#goodBtn').text('lightON!');
		}
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

const makePageData = (pageMaker) => {
	let pageData = {
		prevPage : 0,
		nextPage : 0,
		pages : []
	};
	
	if(pageMaker.prev)
		pageData.prevPage = pageMaker.startPage - 1;
	
	for(let i = pageMaker.startPage; i <= pageMaker.endPage; i++){
		pageData.pages.push(i);
	}
	
	if(pageMaker.next)
		pageData.nextPage = pageMaker.endPage + 1;
	return pageData;
}

const postToUrl = (path, params) => {
	let form = document.createElement("form");
	form.setAttribute("method", "POST");
	form.setAttribute("action", path);
	for(let key in params) {
		let hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", key);
		hiddenField.setAttribute("value", params[key]);
		form.appendChild(hiddenField);
	};
	document.body.appendChild(form);
	form.submit();
};

