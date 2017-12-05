package com.xiaoshu.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.xiaoshu.entity.UserToken;
import com.xiaoshu.service.TokenService;
import com.xiaoshu.service.UserTokenService;
import com.xiaoshu.util.WrapperUtil;
/**
 * 
 * @author administrator
 *
 */
@Component
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private UserTokenService tokenService;


	@Override
	public UserToken createToken(String userName, String password) {
		Wrapper<UserToken>  wrapper = WrapperUtil.getWrapper(UserToken.class);
		wrapper.andNew(" name = {0} and password = {1} ", userName,password);
		
		UserToken user = tokenService.selectOne(wrapper);
		if(null != user){
			String uid = UUID.randomUUID().toString();
			String token = uid.replace("-", "");
			user.setToken(token);
			user.setExpiredTime(DateUtils.addHours(new Date(), 72).getTime());
			//更新数据
			tokenService.updateById(user);
			return user;
		}else{
			return null;
		}
		
	}

	@Override
	public boolean checkToken(String token) {
		Wrapper<UserToken>  wrapper = WrapperUtil.getWrapper(UserToken.class);
		wrapper.andNew(" token = {0} ", token);
		UserToken user = tokenService.selectOne(wrapper);
		//验证数据库中是否有该Token
		if(null != user){
			return true;
		}
		return false;
	}

	@Override
	public void deleteToken(String token) {
		Wrapper<UserToken>  wrapper = WrapperUtil.getWrapper(UserToken.class);
		wrapper.andNew(" token = {0} ", token);
		UserToken user = tokenService.selectOne(wrapper);
		user.setToken(StringUtils.EMPTY);
		tokenService.updateById(user);
	}

}
