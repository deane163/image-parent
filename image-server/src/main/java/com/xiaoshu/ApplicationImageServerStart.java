package com.xiaoshu;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * 图片服务器启动类，负责上传的操作
 * @author Administrator
 *
 */
@MapperScan("com.xiaoshu.mapper*")
@SpringBootApplication
public class ApplicationImageServerStart extends SpringBootServletInitializer {

	private static Logger logger = LoggerFactory.getLogger(ApplicationImageServerStart.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationImageServerStart.class);
	}

	public static void main(String[] args) {
		logger.info("start The Image Server ...");
		SpringApplication.run(ApplicationImageServerStart.class, args);
	}
	
	/**
	 * 字符拦截，使用UTF-8编码
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		registrationBean.setFilter(characterEncodingFilter);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
}
