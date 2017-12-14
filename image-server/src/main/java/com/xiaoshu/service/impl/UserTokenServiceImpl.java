package com.xiaoshu.service.impl;

import java.util.List;

import com.xiaoshu.entity.UserToken;
import com.xiaoshu.mapper.UserTokenMapper;
import com.xiaoshu.service.UserTokenService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author administrator
 * @since 2017-12-05
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
