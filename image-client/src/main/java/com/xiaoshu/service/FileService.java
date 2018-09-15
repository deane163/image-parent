package com.xiaoshu.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Administrator
 *
 */
public interface FileService {

	/**
	 * save image to locate Direction
	 * @param file
	 * @return
	 */
	public String createNewdFile(MultipartFile file,String fileName);

	/**
	 * 
	 * @param text
	 * @param regex
	 * @param replacement
	 * @return
	 */
	public String replaceLast(String text, String regex, String replacement);
}
