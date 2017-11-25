package com.xiaoshu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

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
}
