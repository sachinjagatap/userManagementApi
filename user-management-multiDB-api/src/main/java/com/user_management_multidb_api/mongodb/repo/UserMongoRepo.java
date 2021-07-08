package com.user_management_multidb_api.mongodb.repo;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.user_management_multidb_api.mongodb.entities.UserMongo;


public interface UserMongoRepo extends MongoRepository<UserMongo, Integer> {

	public abstract Optional<UserMongo> findByuserName(String userName);
	
	public abstract void deleteByuserName(String userName);
	
}
