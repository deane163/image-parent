package com.xiaoshu.service;

import java.util.HashMap;
import java.util.Map;

/**
 * 负载文件的上传操作
 * @author Administrator
 *
 */
public interface ImageUploadService {

	/**
	 * 上传文件到指定的服务器上
	 * 
	 * @param fileName
	 * @param url
	 * @param fileByte
	 * @return
	 */
	public String uploadSingleImage(byte[] file, Map<String, Object> paramMap, String serverIp);

	/**
	 * 批量上传文件到图片服务器上
	 * 
	 * @param paramsMap
	 * @param serverIp
	 * @return
	 */
	public String uploadMultiImage(Map<String, Object> paramsMap, String serverIp);

	/**
	 * 批量上传文件到图片服务器上,重点排序
	 * 
	 * @param paramsMap
	 * @param serverIp
	 * @return
	 */
	public String uploadMultiImageNew(HashMap<String, Object> paramsMap, String serverIp);
}
