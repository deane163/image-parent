package com.xiaoshu.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoshu.service.ImageUploadService;
import com.xiaoshu.util.OkHttpRestUtils;

/**
 * 负载文件的上传操作
 * @author Administrator
 *
 */

@Component
public class ImageUploadServiceImpl implements ImageUploadService {

	@Override
	public String uploadSingleImage(MultipartFile file, Map<String, Object> paramMap,
			String serverIp) throws IOException {
		paramMap.put("fileName", file.getOriginalFilename());
		return OkHttpRestUtils.post(serverIp +"/uploadSingle", file.getBytes(), paramMap);
	}

	@Override
	public String uploadMultiImage(Map<String, Object> paramsMap,
			String serverIp) {
		return null;
	}

	@Override
	public String uploadMultiImageNew(HashMap<String, Object> paramsMap,
			String serverIp) {
		return null;
	}

}
