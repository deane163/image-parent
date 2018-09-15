package com.xiaoshu.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xiaoshu.annotation.Authorization;
import com.xiaoshu.config.Constant;
import com.xiaoshu.service.TokenService;

/**
 * 拦截器操作，对SpringMVC 进行拦截操作,验证Token是否合法
 * 
 * @author Administrator
 *
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
	
	@Autowired
	private TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod method = (HandlerMethod) handler;

		// 如果方法和类中都没有@Authorization注解，则直接跳过
		if (method.getBean().getClass().getAnnotation(Authorization.class) == null
				&& method.getMethodAnnotation(Authorization.class) == null) {
			return true;
		}
		logger.info("<====start check the Token infomation===> on time: {}",new Date());
		String token = request.getHeader(Constant.AUTHORIZATION);
		if (!tokenService.checkToken(token)) {
			//无权限访问
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}

		return true;
	}
}
