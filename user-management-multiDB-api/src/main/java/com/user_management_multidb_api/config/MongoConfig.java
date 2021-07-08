package com.user_management_multidb_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableMongoRepositories(basePackages = {"com.user_management_multidb_api.mongodb"})
public class MongoConfig {
	
	@Bean(name = "mongoTransactionManager")
    public MongoTransactionManager  mongoTransactionManager(MongoDatabaseFactory  mongoDbFactory) {
		return new MongoTransactionManager(mongoDbFactory);
	}
}
