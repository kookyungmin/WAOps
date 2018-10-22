package com.kookyungmin.waops.controller;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kookyungmin.waops.service.QuestionService;
import com.kookyungmin.waops.util.FileUtils;

@Controller
public class UploadController {
	
	@Inject
	private QuestionService service;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@Resource(name = "uploadDirectPath")
	private String uploadDirectPath;
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@RequestMapping(value = "/uploadAjax" , method = RequestMethod.POST)
	public ResponseEntity<String[]> uploadFormAjax(MultipartFile[] files,
											       Integer qno,
											       boolean isDirect) throws Exception{
		int length = files == null ? 0 : files.length;
		logger.debug("uploadFormAjax>>>> files.length={}", length);
		
		try {
			String[] uploadedFiles = new String[length];
			for(int i = 0; i < length; i++) {
				//isDirect가 true 이면, 아파치에 파일을 저장
				String updir = isDirect ? uploadDirectPath : uploadPath;
				uploadedFiles[i] = FileUtils.uploadFile(files[i], updir);
			}
			return new ResponseEntity<>(uploadedFiles, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(new String[] { e.getMessage() }, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName, boolean isDirect) throws Exception {
		logger.debug("download Ajax>>> fileName={}, isDirect={}", fileName, isDirect);
		String updir = isDirect ? uploadDirectPath : uploadPath;
		
		try(InputStream in = new FileInputStream(updir + fileName)) {
			String formatName = FileUtils.getFileExtension(fileName);
			MediaType mType = FileUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			if(mType != null) {
				headers.setContentType(mType);
			} else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				//UTF-8로 쪼갠 다음 IOS-8859-1F로 바꾸어줌 (파일 내려갈때 안깨짐)
				String dsp = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				headers.add("Content-Disposition", "attachment; filename=\"" + dsp + "\"");
			}
			return new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
