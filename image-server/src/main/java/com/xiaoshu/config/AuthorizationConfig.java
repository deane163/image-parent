package com.xiaoshu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.xiaoshu.interceptor.AuthorizationInterceptor;

/**
 * 
 * @author administrator
 *
 */
@Configuration
public class AuthorizationConfig extends WebMvcConfigurerAdapter{

	
	@Autowired
	private AuthorizationInterceptor authorizationInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authorizationInterceptor);
	}
}
