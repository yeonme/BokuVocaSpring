package me.yeon.bokuvoca.common.utils;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileControl {
	public static String fileSave(MultipartFile file, String username, String uploadPath) {
		File path = new File(uploadPath);
		if(!path.exists() && !path.isDirectory()){
			path.mkdirs();
		}
		
		int ext = file.getOriginalFilename().indexOf(".");
		String newfilename = username + file.getOriginalFilename().substring(ext,file.getOriginalFilename().length());
		File newfile = new File(uploadPath + '/' + newfilename);
		if(newfile.exists()){
			newfile.delete();
		}
		
		try {
			file.transferTo(newfile);
			return newfilename;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
