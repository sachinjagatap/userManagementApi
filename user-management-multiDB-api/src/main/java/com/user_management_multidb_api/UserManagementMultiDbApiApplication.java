package com.user_management_multidb_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class UserManagementMultiDbApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementMultiDbApiApplication.class, args);
	}

}
