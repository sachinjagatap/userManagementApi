package com.user_management_multidb_api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user_management_multidb_api.mongodb.entities.UserMongo;
import com.user_management_multidb_api.mysql.entities.User;
import com.user_management_multidb_api.services.UserService;

@RestController
@RequestMapping(value = "/userManagement")
public class ControllerClass {

	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/getMySqlUsers",produces ="APPLICATION/JSON")
	public List<User> getMySqlUsers() {
		return userService.getUsersFromMySql();
	}
	
	@GetMapping(path = "/getMongoUsers",produces ="APPLICATION/JSON")
	public List<UserMongo> getMongoUsers() {
		return userService.getUsersFromMongoDb();
	}
	
	@GetMapping(path = "/getMySqlUser/{id}",produces ="APPLICATION/JSON")
	public ResponseEntity<User> getMySqlUserById( @PathVariable("id") int userId) {
		return userService.getMySqlUserById(userId);
	}
	
	@GetMapping(path = "/getMongoUser/{id}",produces ="APPLICATION/JSON")
	public ResponseEntity<UserMongo> getMongoUserById( @PathVariable("id") int userId) {
		return userService.getMongoUserById(userId);
	}
	
	@PostMapping(path = "/addUser",consumes = "APPLICATION/JSON",produces = "APPLICATION/JSON")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		return userService.addUser(user);
	}
	
	@PutMapping(value="/updateUser/{id}" ,consumes = "APPLICATION/JSON",produces = "APPLICATION/JSON")
	public ResponseEntity<User> updateUser(@PathVariable("id") int userId, @Valid @RequestBody User user){
		   ResponseEntity<User> userObj = userService.updateUser(userId,user);
		return userObj;
	}
	
	@DeleteMapping(value="/deleteUser/{id}",produces = "APPLICATION/JSON")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int userId){
		ResponseEntity<User> deleteUser = userService.deleteUser(userId);
		return deleteUser;
	}
}
