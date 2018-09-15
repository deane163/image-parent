package com.xiaoshu.util;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 *
 * @Description : 功能说明：OKHttp 的GET、POST、PUT、DELETE方法
 * ---------------------------------
 * @Author : deane.administrator
 * @Date : Create in 2017年12月15日下午4:45:14
 * 
 * Copyright (C)2013-2017 小树盛凯科技 All rights reserved.
 */
public class OkHttpRestUtils {

	private static OkHttpClient client;
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	public static final MediaType MULTIPART_FILE = MediaType.parse("multipart/form-data");
	
	/**
	 * Http GET 方法
	 * @param url
	 * @param paramsMap
	 * @return
	 * @throws IOException
	 */
	public static String get(String url,Map<String,Object> paramsMap)  {
		try {
			String urlParams = prepareParams(paramsMap);
			if(urlParams == null || urlParams.trim().length()<1) {
				
			}else {
				url += ("?" + urlParams);
			}
			System.out.println(url);
			Request request = new Request.Builder().url(url).get().build();
			Response response = getClientInstance().newCall(request).execute();
			if (response.isSuccessful()) {
				return response.body().string();
			} else {
				throw new IOException("Unexpected code " + response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * HTTP POST 添加数据操作，不用Token验证
	 * @param url
	 * @param json
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, String json) {
		try {
			RequestBody body = RequestBody.create(JSON, json);
			Request request = new Request.Builder().url(url).post(body).build();

			Response response = getClientInstance().newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 直接进行文件的上传操作
	 * @param url
	 * @param file
	 * @param tokenName
	 * @param tokenStr
	 * @return
	 */
	public static String post(String url,File file) {
		try {
			RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
			RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
					.addFormDataPart("file", file.getName(), fileBody)
					.build();
					
			Request request = new Request.Builder().url(url).post(requestBody).build();
			Response response = getClientInstance().newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 直接进行Map信息
	 * @param url
	 * @param file
	 * @param tokenName
	 * @param tokenStr
	 * @return
	 */
	public static String post(String url,byte[] file, Map<String,Object> params) {
		try {
			RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
			Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
			builder.addFormDataPart("file", "file", fileBody);
			if(params.size() > 0){
				Set<String> keys = params.keySet();
				for(String key:keys){
					if(params.get(key) instanceof File){
						 continue;
					}
					if(params.get(key) instanceof String){
						builder.addFormDataPart(key, (String)params.get(key));
					}else{
						builder.addFormDataPart(key, String.valueOf(params.get(key)));
					}
				}
			}
			RequestBody requestBody = builder.build();		
			Request request = new Request.Builder().url(url).post(requestBody).build();
			Response response = getClientInstance().newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 直接进行文件的上传操作
	 * @param url
	 * @param file
	 * @param tokenName
	 * @param tokenStr
	 * @return
	 * @throws IOException 
	 */
	public static String post(String url,Map<String,Object> params) throws IOException {
		Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
		if(null != params &&  params.size() > 0){
			Set<String> keys = params.keySet();
			for(String key : keys){
				if(params.get(key) instanceof MultipartFile){
					RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), ((MultipartFile)params.get(key)).getBytes());
					builder.addFormDataPart(key, "fileName", fileBody);
				} else if (params.get(key) instanceof File){
					RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file2byte((File)params.get(key)) );
					builder.addFormDataPart(key, "fileName", fileBody);
				} else if (params.get(key) instanceof String){
					builder.addFormDataPart(key, (String)params.get(key));
				} else {
					builder.addFormDataPart(key, String.valueOf(params.get(key)));
				}
			}
		}
		RequestBody requestBody = builder.build();		
		Request request = new Request.Builder().url(url).post(requestBody).build();
		Response response = getClientInstance().newCall(request).execute();
		return response.body().string();
	}
	
	/**
	 * HTTP PUT 更新数据操作，不需要Token认证
	 * @param url
	 * @param json
	 * @param tokenName
	 * @param tokenString
	 * @return
	 */
	public static String put(String url,String json) {
		try {
			RequestBody body = RequestBody.create(JSON, json);
			Request request = new Request.Builder().url(url).put(body).build();
			
			//OKHTTP 访问，返回结果信息
			Response response = getClientInstance().newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * HTTP PUT 更新数据操作，需要Token认证
	 * @param url
	 * @param json
	 * @param tokenName
	 * @param tokenString
	 * @return
	 */
	public static String put(String url,String json, String tokenName,String tokenString) {
		try {
			RequestBody body = RequestBody.create(JSON, json);
			Request request;
			if(null == tokenName || tokenName.trim().length() <1){
				 request = new Request.Builder().url(url).put(body).build();
			}else{
				 request = new Request.Builder().url(url).put(body).addHeader(tokenName, tokenString).build();
			}
			//OKHTTP 访问，返回结果信息
			Response response = getClientInstance().newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * HTTP DELETE 删除数据操作,不需要Token认证
	 * @param url
	 * @param paramsMap
	 * @param tokenName
	 * @param tokenString
	 * @return
	 */
	public static String delete(String url,Map<String ,Object> paramsMap){
		try {
			String paramsUrl = prepareParams(paramsMap);
			if(paramsUrl == null || paramsUrl.trim().length() <1) {
				
			}else {
				url += ("?" + prepareParams(paramsMap));
			}
			
			Request request = new Request.Builder().url(url).delete().build();
			//OKHTTP 访问，返回结果信息
			Response response = getClientInstance().newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			
		}
		return null;
	}
	
	/**
	 * HTTP DELETE 删除数据操作
	 * @param url
	 * @param paramsMap
	 * @param tokenName
	 * @param tokenString
	 * @return
	 */
	public static String delete(String url,Map<String ,Object> paramsMap, String tokenName,String tokenString){
		try {
			String paramsUrl;
			paramsUrl = prepareParams(paramsMap);
			if(paramsUrl == null || paramsUrl.trim().length() <1) {
				
			}else {
				url += ("?" + prepareParams(paramsMap));
			}
			
			Request request;
			if(null == tokenName || tokenName.trim().length() <1){
				 request = new Request.Builder().url(url).delete().build();
			}else{
				 request = new Request.Builder().url(url).delete().addHeader(tokenName, tokenString).build();
			}
			//OKHTTP 访问，返回结果信息
			Response response = getClientInstance().newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			
		}
		return null;
	}
	
	/**
	 * 设计模式，懒汉模式，需要的时候才创建对象，并返回对象。
	 * @return
	 */
	public static OkHttpClient getClientInstance(){
		if(null == client ){
			synchronized (OkHttpRestUtils.class) {
				if(null == client){
					 client = new OkHttpClient();
				}
			}
		}
		return client;
	}
	
	/**
	 * 参数规范化
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String prepareParams(Map<String,Object> params) throws UnsupportedEncodingException {
		StringBuilder strBuilder = new StringBuilder();
		if( null == params || params.isEmpty()) {
			return strBuilder.append("").toString();
		}else {
			Set<String> keys = params.keySet();
			for(String key :keys) {
				if(strBuilder.length() < 1) {
					if(params.get(key) instanceof String) {
						strBuilder.append(key).append("=").append(URLEncoder.encode((String)params.get(key),"UTF-8"));
					}else {
						strBuilder.append(key).append("=").append(params.get(key));
					}
					
				}else {
					if(params.get(key) instanceof String) {
						strBuilder.append("&").append(key).append("=").append(URLEncoder.encode((String)params.get(key),"UTF-8"));
					}else {
						strBuilder.append("&").append(key).append("=").append(params.get(key));
					}
					
				}
			}
		}
		return strBuilder.toString();
	}
	
	/**
	 * File 转 byte[]
	 * @param file
	 * @return
	 */
	public static byte[] file2byte(File file)
    {
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return buffer;
    }
}
