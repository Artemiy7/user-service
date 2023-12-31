package com.mongotask.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ServletComponentScan
@EnableMongoRepositories(basePackages = "com.mongotask.api")
@EnableMongoAuditing
@SpringBootApplication
public class MongoTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoTaskApplication.class, args);
    }

}
