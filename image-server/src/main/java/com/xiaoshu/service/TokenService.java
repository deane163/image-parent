package com.xiaoshu.service;

import com.xiaoshu.entity.UserToken;

public interface TokenService {

	/**
	 * 创建Token
	 * @param userName
	 * @param password
	 * @return
	 */
	public UserToken createToken(String userName, String password);
	
	/**
	 * 检查Token
	 * @param token
	 * @return
	 */
	public boolean checkToken(String token);
	
	/**
	 * 删除Token
	 * @param token
	 */
	public void deleteToken(String token);
}
