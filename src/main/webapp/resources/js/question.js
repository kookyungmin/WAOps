const URL = "/questions/";

let gTitle = null,
    gContent = null,
    gQno = 0,
    gPage = 1,
    gPerPageNum = 5,
    gIsEditFile = false;

const listPage = (page, perPageNum) => {
	console.debug(page);
	gPage = page || gPage;
	gPerPageNum = perPageNum || gPerPageNum;
	url = URL + "all/" + gPage + "/" + gPerPageNum;
	
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
	if($('#btnModQuestion').attr('class').includes('disabled')) return;
	let jsonData = {};
	jsonData.title = $('#title').val();
	jsonData.content = $('#content').val();
	jsonData.score = $('#score').val();
	
	let fileNames = [];
	gUpFiles.forEach(file => {
		fileNames.push(file.fullName);
	});
	console.log("fileNames>>>>" ,fileNames);
	if(fileNames.length > 0){
		jsonData.fileNames = fileNames;
	}
	
	if(!isEdit){
		method = 'POST';
		jsonData.writer = $('#writer').val();
		url = URL;
	}else{
		deleteFileNames = [];
		gDeleteFiles.forEach((df) => {
			deleteFileNames.push(df.fullName);
		});
		jsonData.deleteFileNames = deleteFileNames;
		method = 'PUT';
		url = URL + gQno;
	}
	
	sendAjax(url, (isSuccess, res) => {
		console.debug(res);
		if(isSuccess){
			if(!isEdit){
				alert("등록이 완료되었습니다.");
				postToUrl('/questions/all', { page : 1, perPageNum : 5});
			}else {
				deleteFiles((isSucess, res) => {
					if(isSuccess){
						document.location.href = "/questions/read?qno=" + gQno + "&page=" + gPage + "&perPageNum=" + gPerPageNum;
						alert("수정이 완료되었습니다.");
					}
				}, gDeleteFiles);				
			}
		}
	}, method, jsonData);
};

const read = (qno, page, perPageNum, loginUserID) => {
	gPage = page || gPage;
	gPerPageNum = perPageNum || gPerPageNum;
	gQno = qno || gQno;
	url = URL + qno;
	sendAjax(url, (isSuccess, res) => {
		if(isSuccess){
			console.debug(res);
			gTitle = res.title;
			gContent = res.content;
			res.page = gPage;
			res.perPageNum = gPerPageNum;
			renderHbs('readQuestion', res);
			$('#btnModQuestion').addClass('disabled');
			writer = $('#writer').text();
			if(loginUserID == writer) {
				$('#updateQuestionBtn').removeClass('hidden');
				$('#deleteQuestionBtn').removeClass('hidden');
			}
		}
	}, 'GET');
};

const remove = (qno) => {
	if(!confirm("정말로 삭제하시겠습니까??")) return;
	console.debug(qno);
	url = URL + qno;
	sendAjax(url, (isSuccess, res) => {
		if(isSuccess){
			alert("글이 삭제되었습니다.");
			deleteFiles((isSuccess, res) => {
				if(isSuccess){
					postToUrl('/questions/all',{'page' : gPage, 'perPageNum' : gPerPageNum});
				
				}
			}, gLoadedFiles);
			
		}
	}, 'DELETE');
}

const cancle = (isEdit) => {
	if(!isEdit){
		const registCancle = (isSuccess, res) => {
			if(isSuccess){
				postToUrl('/questions/all',{'page' : gPage, 'perPageNum' : gPerPageNum});
			}
		};
		deleteFiles(registCancle, gUpFiles);
	}else{
		const updateCancle = (isSuccess, res) => {
			if(isSuccess){
				window.location.href = '/questions/read?qno=' + gQno + '&page=' + gPage + '&perPageNum=' + gPerPageNum;
			}
		};
		deleteFiles(updateCancle, gUpFiles);
	}
}

const changePerPageNum = () => {
	gPerPageNum = $('#perPageNum').val() || gPerPageNum;
	listPage();
}

const checkEdit = (isEdit, selectChanged) => {
	
	selectChanged = selectChanged || false;
	
	let title = $('#title').val(),
	    content = $('#content').val();
	let $btnModQuestion = $('#btnModQuestion');
	
	if(!title || !content){
		$btnModQuestion.addClass('disabled');
	}else if(isEdit && title === gTitle && content === gContent && !selectChanged && !gIsEditFile){
		$btnModQuestion.addClass('disabled');
	}else {
		$btnModQuestion.removeClass('disabled');
	}
}
