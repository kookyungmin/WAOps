package com.kookyungmin.waops.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	private static Map<String, MediaType> mediaMap;
	static {
		mediaMap = new HashMap<>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	public static String uploadFile(MultipartFile file, String uploadPath) throws Exception {
		String fileName = UUID.randomUUID().toString() +
				          "_" +
				          file.getOriginalFilename();
		
		String dirName = getCurrentUploadPath(uploadPath);
		File target = new File(dirName, fileName);
		FileCopyUtils.copy(file.getBytes(), target);
		String ext = getFileExtension(fileName);
		
		String uploadedFileName = null;
		if (getMediaType(ext) != null) {
			uploadedFileName = makeCropImage(uploadPath, dirName, fileName);
		}else {
			uploadedFileName = makeIcon(uploadPath, dirName, fileName);
		}
		return uploadedFileName;
	}
	
	public static String makeCropImage(String uploadPath, String dirName, String fileName) throws Exception{
		BufferedImage srcImg = ImageIO.read(new File(dirName, fileName));
		int width = srcImg.getWidth();
		int height = srcImg.getHeight();
		int min = Math.min(width, height);
		BufferedImage tmpImg = Scalr.crop(srcImg, (width - min) / 2, (height - min) / 2, min, min);
		BufferedImage cropImg = Scalr.resize(tmpImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 150);
		String ext = getFileExtension(fileName);
		String cropName = dirName + File.separator + "c_" + fileName;
		File newFile = new File(cropName);
		ImageIO.write(cropImg, ext.toUpperCase(), newFile);
		return cropName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	public static String getCurrentUploadPath(String uploadPath) throws Exception {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1; // 0 ~ 11 값이므로
		int d = cal.get(Calendar.DATE);
		return makeDir(uploadPath, String.valueOf(y), len2(m), len2(d));
	}
	
	public static String makeDir(String uploadPath, String... paths) throws Exception {
		for(String path : paths) {
			uploadPath += File.separator + path;
			File tmpFile = new File(uploadPath);
			if(tmpFile.exists()) {
				continue;
			}else {
				tmpFile.mkdir();
			}
		}
		return uploadPath;
	}

	/**
	 * 
	 * uploadPath = uploadPath
	 * dirName = dirName = uploadPath\2018\09\20
	 * fileName = abc.ppt
	 * 
	 */
	
	
	public static String makeIcon(String uploadPath, String dirName, String fileName) {
		// uploadPath\2018\09\20\abc.ppt 
		String iconName = dirName + File.separator + fileName;
		
		// uploadPath/2018/09/20/abc.ppt 로 변경
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}



	public static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	public static MediaType getMediaType(String ext) {
		return mediaMap.get(ext.toUpperCase());
	}
	
	public static String len2(int n) {
		return new DecimalFormat("00").format(n).toString();
	}
}
