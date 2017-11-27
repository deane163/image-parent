package com.xiaoshu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoshu.service.FileService;

@Component(value = "fileService")
public class FileServiceImpl implements FileService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public String createNewdFile(MultipartFile file) {
		//保存文件到本地服务器
		logger.info("save new file into local disk ...");
		return null;
	}

	@Override
	public String replaceLast(String text, String regex, String replacement) {
		
		return text.replaceFirst("(?s)" + regex + "(?!.*?" + regex + ")", replacement);
	}

}
