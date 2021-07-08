package com.user_management_multidb_api.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTransactionConfig {
	
	@SuppressWarnings("deprecation")
	@Bean(name = "chainedTransactionManager")
	public PlatformTransactionManager chainedTransactionManager(
			@Qualifier("mySqlTransactionManager") PlatformTransactionManager mySqlTransactionManager,
			@Qualifier("mongoTransactionManager") MongoTransactionManager mongoTransactionManager) {
		return new ChainedTransactionManager(mySqlTransactionManager, mongoTransactionManager);
	}
}
