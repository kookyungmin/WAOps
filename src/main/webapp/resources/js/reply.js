let gReplyPage = 1,
	gRno = 0,
	gReplyText = null,
	replyURL = "/replies/";

const replylistPage = (page) => {
	gReplyPage = page || gReplyPage;
	url = replyURL + "qno/" + gQno + "/all/" + gReplyPage;
	console.debug(url);
	sendAjax(url, (isSuccess, res) => {
		console.debug(res);
		if(isSuccess){
			$('#btnRegisterReply').addClass('disabled');
			res.currentPage = gReplyPage;
			res.pageData = makePageData(res.pageMaker);
			renderHbs('replies', res);
	 	}
	},'GET');
};

const updateReply = (isEdit, $replyText, $replyer) => {
	if(!isEdit && $('#btnRegisterReply').attr('class').includes('disabled')) return;
	if(isEdit && $('#btnUpdateReply' + gRno).attr('class').includes('disabled')) return;
	
	let jsonData = {};
	jsonData.qno = gQno;
	jsonData.replytext = $replyText.val();
	if(!isEdit){
		jsonData.replyer = $replyer.val();
		method = 'POST';
		url = replyURL;
	}else{
		method = 'PUT';
		url = replyURL + gRno;
	}
	
	sendAjax(url, (isSuccess, res) => {
		console.debug(res);
		if(isSuccess){
			if(!isEdit){
				alert("댓글 등록이 완료되었습니다.");
				$replyText.val("");
				replylistPage(res.lastPage);
				window.scrollTo(0, document.body.scrollHeight);
			}else {
				alert("댓글 수정이 완료되었습니다.");
				replylistPage(gReplyPage);
			}
		}
	}, method, jsonData);
}

const deleteReply = () => {
	url = replyURL + gRno;
	sendAjax(url, (isSuccess, res) => {
		if(isSuccess){
			if(!confirm("정말로 삭제하시겠습니까??")) return;
			alert("댓글 삭제가 완료되었습니다.");
			replylistPage(gReplyPage);
		}
	}, 'DELETE');
}


const clickModBtnReply = (rno) => {
	replyer = $('#replyWriter' + rno).text();
	if(replyer != loginUserID){
		console.debug(replyer)
		alert("본인 댓글만 수정가능합니다!");
		return;
	}
	gRno = rno || gRno;
	gReplyText = $('#replyText' + rno).val() || gReplyText;
	$('.btnModReply').addClass('hidden')
	$('#btnUpdateReply' + rno).removeClass('hidden');
	$('#btnUpdateReply' + rno).addClass('disabled');
	$('#btnDeleteReply' + rno).removeClass('hidden');
	$('#btnCancleReply' + rno).removeClass('hidden');
	$('#replyText' + rno).removeAttr('readonly');
}

const clickCancleReply = (rno) => {
	gRno = 0;
	gReplyText = null;
	$('.btnModReply').removeClass('hidden');
	$('#replyText' + rno).attr({'readonly':'readonly'});
	$('#btnUpdateReply' + rno).addClass('hidden');
	$('#btnDeleteReply' + rno).addClass('hidden');
	$('#btnCancleReply' + rno).addClass('hidden');
}

const checkEditReply = (isEdit) => {
	let replyer,
		replyText,
		$btnModReply;
	if(isEdit){
		replyText = $('#replyText' + gRno).val();
		$btnModReply = $('#btnUpdateReply' + gRno);
	}else{
		replyText = $('#insertReplyText').val();
		$btnModReply = $('#btnRegisterReply');
	}
	
	
	if(!replyText){
		$btnModReply.addClass('disabled');
	}else if(isEdit && replyText === gReplyText){
		$btnModReply.addClass('disabled');
	}else {
		$btnModReply.removeClass('disabled');
	}
}