package com.xiaoshu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 
 * @author Administrator
 *
 */

@SpringBootApplication
public class ApplicationImageClientStart extends SpringBootServletInitializer {
	private static Logger logger = LoggerFactory.getLogger(ApplicationImageClientStart.class);

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(ApplicationImageClientStart.class);
	}

	public static void main(String[] args) {
		logger.info("start The Image client ...");
		SpringApplication.run(ApplicationImageClientStart.class, args);
	}
}
