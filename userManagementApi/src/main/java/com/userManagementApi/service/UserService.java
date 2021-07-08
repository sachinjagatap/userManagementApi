package com.userManagementApi.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.userManagementApi.entities.User;
import com.userManagementApi.repository.UserRepo;


@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	// method for retrieving  user lists
	@Transactional
	public List<User> getUsers(){
		List<User> userLists = userRepo.findAll();
		return userLists;
	}  
	
	// method for retrieving user by id
	@Transactional
	public ResponseEntity<User> getUserById(int id){
		User user = userRepo.findById(id)
				    .orElseThrow(()-> new RuntimeException("User does not exists with id : " + id));;

		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	
	// method for adding a new user
	@Transactional
	public ResponseEntity<User> addUser(User user) {
		List<User> userList = userRepo.findAll();
		boolean userExists = userList.stream().anyMatch(existingUser -> existingUser.equals(user));

		if (userExists == true) {
			throw new RuntimeException("User Already exists ... ");
		}

		String encodePassword = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encodePassword);
		User userObj = userRepo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userObj);
	}
	
	// method for updating user
	@Transactional
	public ResponseEntity<User> updateUser(User user){
	    User existingUser = userRepo.findById(user.getId())
	    		       .orElseThrow(()-> new RuntimeException("User does not exists with id : " + user.getId()));
	        
	    existingUser.setUserName(user.getUserName());
	    existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
	    existingUser.setRole(user.getRole());
	    existingUser.setRequestedRoleId(user.getRequestedRoleId());
		     
	         User userObj = userRepo.save(existingUser);
	         
		return ResponseEntity.status(HttpStatus.OK).body(userObj);
	}
	
	// method for deleting user
	@Transactional	
	public ResponseEntity<User> deleteUser(int userId){
		    User existingUser = userRepo.findById(userId)
		    		       .orElseThrow(()-> new RuntimeException("User does not exists with id : " + userId));
		        
		    userRepo.delete(existingUser);
		         
			return ResponseEntity.status(HttpStatus.OK).build();
		}
	
	

}
