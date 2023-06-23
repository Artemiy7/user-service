package com.mongotask.api.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collections;

@RequiredArgsConstructor
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    private final MongoDataConfig mongoDataConfig;

    @Override
    protected String getDatabaseName() {
        return mongoDataConfig.getDatabase();
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        ConnectionString connectionString = new ConnectionString(mongoDataConfig.getConnectionString());
        builder.applyConnectionString(connectionString);
        builder.credential(MongoCredential.createCredential(mongoDataConfig.getUsername(), mongoDataConfig.getAuthenticationDatabase(), mongoDataConfig.getPassword().toCharArray()))
                .applyToClusterSettings(settings -> settings.hosts(Collections.singletonList(new ServerAddress(mongoDataConfig.getHost(), 27017))));
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), mongoDataConfig.getDatabase());
    }
}












