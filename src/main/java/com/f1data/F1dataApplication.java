package com.f1data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@SpringBootApplication(exclude= {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableScheduling
public class F1dataApplication {

	public static void main(String[] args) {
		SpringApplication.run(F1dataApplication.class, args);
	}

    @Bean
	protected MongoTemplate mongoTemplate() {
		return new MongoTemplate(
				new MongoClient(new MongoClientURI("mongodb://dev:dev%40mongo2018@ds149252.mlab.com:49252/heroku_g61mfl20")), "heroku_g61mfl20");
	}


}
