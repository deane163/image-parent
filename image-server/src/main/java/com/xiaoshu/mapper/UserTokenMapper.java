package com.xiaoshu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xiaoshu.entity.UserToken;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Administrator
 * @since 2017-12-05
 */
public interface UserTokenMapper extends BaseMapper<UserToken> {

	public List<UserToken> selectByNamePassword(@Param("name") String name,@Param("password") String passwrod);
}