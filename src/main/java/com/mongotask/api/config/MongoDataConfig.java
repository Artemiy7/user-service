package com.mongotask.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoDataConfig {
    private String authenticationDatabase;
    private String username;
    private String password;
    private String database;
    private String port;
    private String host;
    private String connectionString;
}
