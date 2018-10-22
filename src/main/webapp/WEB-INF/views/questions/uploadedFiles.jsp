<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>

<script src="/resources/js/upload.js" type="text/javascript"></script>



<script id="uploadedFiles" type="text/x-handlebars-template" class="well o-h">
	{{#each upFiles}}
		<li id={{fileId}} class="f-l mg-right10">
			<div class="hidden">{{fullName}}</div>
			<div class="mailbox-attachment-info">
				<span class="mailbox-attachments-icon has-img">
					<img src="{{imgsrc}}" alt="Attachement" />
				</span>
			</div>
			<div class="mailbox-attachment-info center mg-top5">
				<a href="javascript:;" onclick="showOriginal('{{getLink}}')" class="mailbox-attachment-name">
					{{fileName}}
				</a>
				{{#if gIsEditing}}
					<a href="javascript:;" onclick="deleteFile()" class="btn btn-default btn-xs pull-right delbtn">
						<i class="fa fa-fw fa-remove"></i>
					</a>
				{{/if}}
			</div>
		</li>
	{{else}}
		<li>첨부파일이 없습니다.</li>
	{{/each}}		
</script>

<div id="original-image-modal" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-lg o-h center" role="document">
		<img src="" alt="" />
	</div>
</div>

<script>
	const showOriginal = (link) => {
		let $originalImageModal = $('#original-image-modal');
		console.debug(link);
		if(checkImageType(link, true)){
			//이미지면 보여주고
			$originalImageModal.find('img').attr('src', link);
			$originalImageModal.modal('show');
		}else{
			//아니면 다운로드
			window.location.href = link;
		}
	}
</script>

