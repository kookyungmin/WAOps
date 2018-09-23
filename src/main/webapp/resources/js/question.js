const URL = "/questions";

const listPage = (cri) => {
	console.debug(cri);
	url = URL + "/all/" + cri.page + "/" + cri.perPageNum;
	
	sendAjax(url, (isSuccess, res) => {
		console.debug(res);
		if(isSuccess){
			let data = {};
			data.list = res;
			renderHbs('questionlist', data, 'tbody');
	 	}
	},'GET');
};

const update = (status) => {
	let jsonData = {};
	jsonData.title = $('#title').val();
	jsonData.content = $('#content').val();
	jsonData.score = $('#score').val();
	if(status === 'register'){
		jsonData.writer = $('#writer').val();
	}
	console.debug(jsonData);
	sendAjax(URL, (isSuccess, res) => {
		console.debug(res);
		if(isSuccess){
			if(status === 'register'){
				alert("등록이 완료되었습니다.");
				document.location.href="/questions/all";
			}
		}
	}, 'POST', jsonData);
};

const read = (qno) => {
	console.debug(qno);
	url = URL + "/" + qno;
	sendAjax(url, (isSuccess, res) => {
		if(isSuccess){
			console.debug(res);
			renderHbs('readQuestion', res);
		}
	}, 'GET');
};


const checkEdit = (status) => {
	let writer = $('#writer').val(),
	    title = $('#title').val(),
	    content = $('#content').val();
	let $btnModQuestion = $('#btnModQuestion');
	if(status === 'register' && (!writer||!title||!content)){
		$btnModQuestion.addClass('disabled');
	}else{
		$btnModQuestion.removeClass('disabled');
	}
}