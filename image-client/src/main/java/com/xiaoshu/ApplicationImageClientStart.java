package com.xiaoshu;

import java.util.Date;

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
 * The Image client for single image upload and batch images upload
 * 
 * @author Administrator
 *
 */

@SpringBootApplication
public class ApplicationImageClientStart extends SpringBootServletInitializer {
	private static Logger logger = LoggerFactory.getLogger(ApplicationImageClientStart.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationImageClientStart.class);
	}

	public static void main(String[] args) {
		logger.info("start The Image client ... on date:{}",new Date());
		SpringApplication.run(ApplicationImageClientStart.class, args);
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
