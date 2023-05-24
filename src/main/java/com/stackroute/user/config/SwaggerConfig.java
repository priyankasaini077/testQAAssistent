package com.stackroute.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*As in this class we are implementing Swagger So annotate the class with @Configuration and 
 * @EnableSwagger2
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/*
	 * Annotate this method with @Bean . This method will return an Object of Docket.
	 * This method will implement logic for swagger
	 */


	 @Bean
	   public ApiInfo getApiInfo() {
		   return new ApiInfoBuilder()
				   .title("Spring boot Swagger Project for User Application")
				   .version("1.0")
				   .license("(c) StackRoute")
				   .description("List of all Endpoints used in User Restful Api")
		           .build();
	   }

	   @Bean
		public Docket productApi() {
			return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
					.paths(PathSelectors.any())
					.build()
					.pathMapping("/")
					.apiInfo(getApiInfo())
					.useDefaultResponseMessages(false);
	   }
}
