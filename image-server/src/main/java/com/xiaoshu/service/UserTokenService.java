package com.xiaoshu.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.xiaoshu.entity.UserToken;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author administrator
 * @since 2017-12-05
 */
public interface UserTokenService extends IService<UserToken> {
	
	public List<UserToken> selectByNamePassword(String name, String passwrod);
	
}
