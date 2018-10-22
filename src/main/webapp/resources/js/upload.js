const $fileDrop = $('.fileDrop'),
      $status = $('#status');
let gIsDirect = false,
    gUpFiles = [],
    gUri = window.location.pathname,
    gIsRegister = gUri.indexOf('/register') !== -1,
    gIsUpdate = gUri.indexOf('/update') !== -1,
    gIsEditing = gIsRegister || gIsUpdate;

$fileDrop.on('dragover dragenter', (evt) =>{
	evt.preventDefault();
	$fileDrop.addClass('fileDropFocus');
});

$fileDrop.on('dragleave', (evt) =>{
	evt.preventDefault();
	$fileDrop.removeClass('fileDropFocus');
	$fileDrop.css("border", "1px dashed #ddd");
});

$fileDrop.on('drop', (evt) => {
	evt.preventDefault();
	$fileDrop.removeClass('fileDropFocus');
	let files = evt.originalEvent.dataTransfer.files;
	console.debug("drop>>",files);
	$('#ajax-file').prop("files", files);
	$('#form_attach').submit();
});



$('#form_attach').ajaxForm({
	beforeSend : function(){
		let f = $('#ajax-file').val();
		$status.html('파일 업로드 중 : 0%');
	},
	
	uploadProgress : function(event, position, total, percentComplete){
		$status.html('파일 업로드 중 : ' + percentComplete + '%');
	},
	
	complete : function(xhr){
		console.debug("xhr>>>>", xhr);
		
		let resJson = xhr.responseJSON;
		if (xhr.status !== 200) {
			alert("업로드에 실패했습니다. (" + resJson + ")");
		}
		
		resJson.forEach( rj => {
			let jsonData = getFileInfo(rj);
			gUpFiles.push(jsonData);
		})
		
		$status.html('파일 업로드 완료 : ' + '100%');
		renderHbs('uploadedFiles', {upFiles: gUpFiles});
	}
});

const getFileInfo = (fullName) => {
		let fileName, imgsrc, getLink;
		let $isDirect = $('#isDirect'),
		    isDirect = $isDirect && $isDirect.length && $isDirect.val() == "true";
		
		isDirect = isDirect ? true : gIsDirect;
		console.debug(isDirect);
		/* http + // + localhost */
		const uphost = window.location.protocol + "//" + window.location.hostname;
		
		//이미지인 경우
		if(checkImageType(fullName)){
			if(isDirect) {
				//아파치
				imgsrc = uphost + "/uploads" + fullName;
			}else {
				//톰캣
				imgsrc = "/displatyFile?filName=" + fullName;
			}
			fileLink = fullName.substring(14); // 원본파일명 (/2018/09/28/s_ 이후 파일명) 
			let front = fullName.substring(0, 12), // /2018/09/28
			    end = fileLink;
			getLink = "/displayFile?fileName=" + front + end;
		}else {
			imgsrc = "/resources/img/file_icon.png";
			fileLink = fullName.substring(12);
			getLink = "/displayFile?fileName=" + fullName;
		}
		
		if(isDirect) {
			getLink += "&isDirect=true";
			console.debug(getLink);
		}
		
		fileName = fileLink.substring(fileLink.indexOf('_') + 1);
		let fileId = fileLink.substring(0, fileLink.indexOf('_'));
		
		return {
			fileName : fileName,
			imgsrc : imgsrc,
			getLink : getLink,
			fullName : fullName,
			fileId : fileId,
			gIsEditing : gIsEditing
		}
}


const checkImageType = (fullName , isDirect) => {
	if(isDirect){
		let index = fullName.indexOf('&');
		fullName = fullName.substring(0, index);
	}
	let pattern = /jpg$|png$|gif$/i; //^시작, $끝, i는 대소문자 구분 x
	return fullName.match(pattern);
}
