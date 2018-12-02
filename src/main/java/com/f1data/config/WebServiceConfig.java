package com.f1data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class WebServiceConfig {

    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
        	  .apiInfo(baseApiInfo())
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())
          .build();                                           
    }
	
	private ApiInfo baseApiInfo() {
		return new ApiInfoBuilder()
				.title("F1 Data API")
				.description("Here you will find all about F1 data records.")
				.contact(new Contact("Tiago Moretto", "https://github.com/tmoretto", "moretto.tiago@gmail.com"))
				.version("1.0.0")
				.build();
	}
	
}