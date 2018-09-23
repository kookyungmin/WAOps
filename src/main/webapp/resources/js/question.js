const URL = "/questions";

let gTitle = null,
    gContent = null,
    gQno = 0;

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

const update = (isEdit) => {
	let jsonData = {};
	jsonData.title = $('#title').val();
	jsonData.content = $('#content').val();
	jsonData.score = $('#score').val();
	if(!isEdit){
		method = 'POST';
		jsonData.writer = $('#writer').val();
		url = URL;
	}else{
		method = 'PUT';
		url = URL + "/" + gQno;
	}
	
	sendAjax(url, (isSuccess, res) => {
		console.debug(res);
		if(isSuccess){
			if(!isEdit){
				alert("등록이 완료되었습니다.");
				document.location.href = "/questions/all";
			}else {
				alert("수정이 완료되었습니다.");
				document.location.href = "/questions/read?qno=" + gQno;
			}
		}
	}, method, jsonData);
};

const read = (qno) => {
	console.debug(qno);
	url = URL + "/" + qno;
	sendAjax(url, (isSuccess, res) => {
		if(isSuccess){
			console.debug(res);
			gTitle = res.title;
			gContent = res.content;
			gQno = res.qno;
			renderHbs('readQuestion', res);
		}
	}, 'GET');
};

const remove = (qno) => {
	if(!confirm("정말로 삭제하시겠습니까??")) return;
	console.debug(qno);
	url = URL + "/" + qno;
	sendAjax(url, (isSuccess, res) => {
		if(isSuccess){
			alert("글이 삭제되었습니다.");
			document.location.href="/questions/all";
		}
	}, 'DELETE');
}

const checkEdit = (isEdit, selectChanged) => {
	selectChanged = selectChanged || false;
	
	let writer = $('#writer').val(),
	    title = $('#title').val(),
	    content = $('#content').val();
	let $btnModQuestion = $('#btnModQuestion');
	
	if(!writer || !title || !content){
		$btnModQuestion.addClass('disabled');
	}else if(isEdit && title === gTitle && content === gContent && !selectChanged){
		$btnModQuestion.addClass('disabled');
	}else {
		$btnModQuestion.removeClass('disabled');
	}
}