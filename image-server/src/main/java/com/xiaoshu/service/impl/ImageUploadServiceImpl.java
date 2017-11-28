package com.xiaoshu.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

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
	public String uploadSingleImage(byte[] file, Map<String, Object> paramMap,
			String serverIp) {
		
		String result = OkHttpRestUtils.post(serverIp +"/uploadSingle", file, paramMap);
		return result;
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
