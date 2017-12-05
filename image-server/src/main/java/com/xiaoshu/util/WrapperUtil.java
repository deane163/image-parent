package com.xiaoshu.util;

import java.lang.reflect.Field;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * @title 
 *
 * @author Young
 *
 * @date 2017年6月21日下午5:01:05
 *
 * Copyright (C)2012-2017 深圳优必选科技 All rights reserved.
 */
public class WrapperUtil {

	public static  <T> Wrapper<T> getWrapper(Class<T> clazz){
		Wrapper<T> wrapper = new EntityWrapper<T>();
		Field[] fields  = clazz.getDeclaredFields();
		for(Field field:fields){
			if(field.isAnnotationPresent(TableLogic.class)){
				wrapper.isWhere(false);
			}
		}
		return wrapper;
	}
}
