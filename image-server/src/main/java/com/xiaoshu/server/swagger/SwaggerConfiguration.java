package com.xiaoshu.server.swagger;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@EnableConfigurationProperties
@ConditionalOnClass(SwaggerProperties.class)
@AutoConfigureAfter(SwaggerProperties.class)
public class SwaggerConfiguration {

	@Autowired
	private SwaggerProperties properties;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("group1")
				.apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.paths(PathSelectors.any()).build();

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring Boot中使用Swagger2构建RESTful APIs")
				.description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
				.termsOfServiceUrl("http://blog.didispace.com/")
				.contact(new Contact(properties.getContactName(), properties
								.getContactUrl(), properties.getContactEmail()))
				.version("1.0.0").build();
	}
}
