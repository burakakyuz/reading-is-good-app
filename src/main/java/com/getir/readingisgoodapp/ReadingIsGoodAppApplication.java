package com.getir.readingisgoodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.getir.readingisgoodapp")
public class ReadingIsGoodAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingIsGoodAppApplication.class, args);
	}

}
