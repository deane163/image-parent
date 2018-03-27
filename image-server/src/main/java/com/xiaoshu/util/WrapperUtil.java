package com.xiaoshu.util;

import java.lang.reflect.Field;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

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
 * @Description : 
 * ---------------------------------
 * @Author : deane.administrator
 * @Date : Create in 2017年12月15日下午4:46:16
 * 
 * Copyright (C)2013-2017 小树盛凯科技 All rights reserved.
 */
public class WrapperUtil {

	public static  <T> Wrapper<T> getWrapper(Class<T> clazz){
		Wrapper<T> wrapper = new EntityWrapper<T>();
		Field[] fields  = clazz.getDeclaredFields();
		for(Field field : fields){
			if(field.isAnnotationPresent(TableLogic.class)){
				wrapper.isWhere(false);
			}
		}
		return wrapper;
	}
}
