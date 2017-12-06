package com.iu.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileSaver {
	// 1. FileOutputStream
	public String save1(String filePath, MultipartFile multipartFile) throws Exception {
		String fileName = multipartFile.getOriginalFilename();
		fileName = fileName.substring(fileName.lastIndexOf("."));
		fileName = UUID.randomUUID().toString()+fileName;
		
		File file = new File(filePath, fileName);
		FileOutputStream fo = null;
		byte[] fileData = multipartFile.getBytes();
		
		try {
			fo = new FileOutputStream(file);
			fo.write(fileData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fo.close();
		}
		
		return fileName;
	}
	
	// 2. transferTo
	public String save2(String filePath, MultipartFile multipartFile) throws Exception {
		String fileName = multipartFile.getOriginalFilename();
		fileName = fileName.substring(fileName.lastIndexOf("."));
		fileName = UUID.randomUUID().toString()+fileName;
		
		File file = new File(filePath, fileName);
		multipartFile.transferTo(file);
		
		return fileName;
	}
	
	// 3. FileCopyUtils
	public String save3(String filePath, MultipartFile multipartFile) throws Exception {
		String fileName = multipartFile.getOriginalFilename();
		fileName = fileName.substring(fileName.lastIndexOf("."));
		fileName = UUID.randomUUID().toString()+fileName;
		
		File file = new File(filePath, fileName);
		byte[] fileData = multipartFile.getBytes();
		FileCopyUtils.copy(fileData, file); 
		
		return fileName;
	}
}
