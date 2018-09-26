const URL = "/questions";

let gTitle = null,
    gContent = null,
    gQno = 0,
    gPage = 1,
    gPerPageNum = 5;

const listPage = (page, perPageNum) => {
	console.debug(page);
	gPage = page || gPage;
	gPerPageNum = perPageNum || gPerPageNum;
	url = URL + "/all/" + gPage + "/" + gPerPageNum;
	
	sendAjax(url, (isSuccess, res) => {
		console.debug(res);
		if(isSuccess){
			res.currentPage = gPage;
			res.perPageNum = gPerPageNum;
			renderHbs('questionlist', res, 'tbody');
			res.pageData = makePageData(res.pageMaker);
			renderHbs('pagination', res);
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
				postToUrl('/questions/all', { page : 1, perPageNum : 5});
			}else {
				alert("수정이 완료되었습니다.");
				document.location.href = "/questions/read?qno=" + gQno + "&page=" + gPage + "&perPageNum=" + gPerPageNum;
			}
		}
	}, method, jsonData);
};

const read = (qno, page, perPageNum) => {
	gPage = page || gPage;
	gPerPageNum = perPageNum || gPerPageNum;
	gQno = qno || gQno;
	url = URL + "/" + qno;
	sendAjax(url, (isSuccess, res) => {
		if(isSuccess){
			console.debug(res);
			gTitle = res.title;
			gContent = res.content;
			res.page = gPage;
			res.perPageNum = gPerPageNum;
			renderHbs('readQuestion', res);
			$('#btnModQuestion').addClass('disabled');
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
			postToUrl('/questions/all',{'page' : gPage, 'perPageNum' : gPerPageNum});
		}
	}, 'DELETE');
}

const changePerPageNum = () => {
	gPerPageNum = $('#perPageNum').val() || gPerPageNum;
	listPage();
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
