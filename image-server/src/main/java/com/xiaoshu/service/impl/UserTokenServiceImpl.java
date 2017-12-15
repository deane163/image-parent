package com.xiaoshu.service.impl;

import java.util.List;

import com.xiaoshu.entity.UserToken;
import com.xiaoshu.mapper.UserTokenMapper;
import com.xiaoshu.service.UserTokenService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @Description : 服务实现类
 * ---------------------------------
 * @Author : deane.administrator
 * @Date : Create in 2017年12月15日下午4:45:01
 * 
 * Copyright (C)2013-2017 小树盛凯科技 All rights reserved.
 */
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements UserTokenService {

	@Autowired
	private UserTokenMapper userTokenMapper;
	
	@Override
	public List<UserToken> selectByNamePassword(String name, String passwrod) {
		// TODO Auto-generated method stub
		return userTokenMapper.selectByNamePassword(name, passwrod);
	}
	
}
