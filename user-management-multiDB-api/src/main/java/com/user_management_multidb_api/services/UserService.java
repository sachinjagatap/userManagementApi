package com.user_management_multidb_api.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user_management_multidb_api.exceptions.ResourceNotFoundException;
import com.user_management_multidb_api.mongodb.entities.UserMongo;
import com.user_management_multidb_api.mongodb.repo.UserMongoRepo;
import com.user_management_multidb_api.mysql.entities.User;
import com.user_management_multidb_api.mysql.repo.UserRepo;


@Service
public class UserService {

	@Autowired
	private UserRepo  mysqlRepo;
	@Autowired
	private UserMongoRepo mongoRepo;
	 
	private UserMongo   userMongo;

	
	
	// method for retrieving  mySql user lists
	@Transactional
	public List<User> getUsersFromMySql(){
		List<User> userLists = mysqlRepo.findAll();
		return userLists;
	} 
	
	// method for retrieving  mongodb user lists
	@Transactional
	public List<UserMongo> getUsersFromMongoDb(){
		List<UserMongo> userLists = mongoRepo.findAll();
		return userLists;
	} 
	
	// method for retrieving mysql user by id

	@Transactional()
	public ResponseEntity<User> getUserById (int id) {
		User user = mysqlRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User does not exists with id : " + id));
		;

		return ResponseEntity.status(HttpStatus.OK).body(user);
	} 
	
	
	// method for adding a new user
	
	@Transactional(transactionManager = "chainedTransactionManager")
	public ResponseEntity<Object> addUser(User user) {
		
		List<User> mySqlUserList = mysqlRepo.findAll();
		List<UserMongo> mongoUserList = mongoRepo.findAll();

		ObjectMapper  objMap = new ObjectMapper();
		objMap.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		UserMongo mongoUser = objMap.convertValue(user, UserMongo.class);
		
		boolean mySqlUserExists = mySqlUserList.stream().anyMatch(existingUser -> existingUser.equals(user));
		boolean mongoUserExists = mongoUserList.stream().anyMatch(existingUser -> existingUser.equals(mongoUser));

		if (mySqlUserExists == true || mongoUserExists == true) {
			throw new ResourceNotFoundException("User Already exists");
		}

		User mySqlUserObj = mysqlRepo.save(user);
		
		mongoUser.setId(mySqlUserObj.getId());
		UserMongo mongoUserObj = mongoRepo.insert(mongoUser);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	 	
	// method for updating user
	
	@Transactional(transactionManager = "chainedTransactionManager")
	public ResponseEntity<User> updateUser(User user) {
		User existingUser = mysqlRepo.findById(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User does not exists with id : " + user.getId()));

		existingUser.setUserName(user.getUserName());
		existingUser.setPassword(user.getPassword());
		existingUser.setRole(user.getRole());
		existingUser.setRequestedRoleId(user.getRequestedRoleId());

		User userObj = mysqlRepo.save(existingUser);
		
		/*****************mongo user update part ************/
		
		ObjectMapper  objMap = new ObjectMapper();
		objMap.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES , false);
		
		UserMongo mongoUser = objMap.convertValue(user, UserMongo.class);

		UserMongo existingMongoUser = mongoRepo.findById(mongoUser.getId())
				                     .orElseThrow(()->
				                                  new ResourceNotFoundException("User does not exists with id : " + mongoUser.getId()));
		
		existingMongoUser.setUserName(mongoUser.getUserName());
		existingMongoUser.setPassword(mongoUser.getPassword());
		existingMongoUser.setRequestedRoleId(mongoUser.getRequestedRoleId());
		
		mongoRepo.save(existingMongoUser);
		
		return ResponseEntity.status(HttpStatus.OK).body(userObj);
	} 
	
	// method for deleting user

	@Transactional(transactionManager = "chainedTransactionManager")
	public ResponseEntity<User> deleteUser(int userId) {
		User existingUser = mysqlRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User does not exists with id : " + userId));

		mysqlRepo.delete(existingUser);
		
		/*****************mongo user delete part ************/
		
		UserMongo existingMongoUser = mongoRepo.findById(userId)
		                                       .orElseThrow(()->
                                new ResourceNotFoundException("User does not exists with id : " + userId));

		mongoRepo.delete(existingMongoUser);
		return ResponseEntity.status(HttpStatus.OK).build();
	}	

}

