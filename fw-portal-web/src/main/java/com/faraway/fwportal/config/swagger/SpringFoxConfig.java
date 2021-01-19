package com.faraway.fwportal.config.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.faraway.fwportal.controller")).paths(PathSelectors.any())
				.build().useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, responseMessageForGET()).apiInfo(apiInfo());
	}

	@SuppressWarnings("serial")
	private List<ResponseMessage> responseMessageForGET() {
		return new ArrayList<ResponseMessage>() {
			{
				add(new ResponseMessageBuilder().code(500).message("500 message").build());
				add(new ResponseMessageBuilder().code(403).message("Forbidden!").build());

				add(new ResponseMessageBuilder().code(200).message("OK,FOUND!").build());
			}
		};
	}

	private ApiInfo apiInfo() {
		springfox.documentation.service.Contact contact = new springfox.documentation.service.Contact(
				"Nicholas Barbosa", "https://faraway.com.br/", "nicholas.barbosa0604@hotmail.com");
		return new ApiInfoBuilder().title("FarAway REST API.")
				.description(
						"Spring Boot REST API for communication between systems.Many of these datas came from SEFAZ.")
				.version("1.0.0").license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0").contact(contact).build();
	}
}
