package com.userManagementApi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userManagementApi.Dto.UserDto;
import com.userManagementApi.entities.User;
import com.userManagementApi.service.RoleService;
import com.userManagementApi.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping(value = "/getUsers")
	public List<User> getUsers(){
		
		List<User> userLists = userService.getUsers();
		return userLists;
	}
	
	@GetMapping(value = "/getUser/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id){
		
		ResponseEntity<User> user = userService.getUserById(id);
		
		return user;
	}
   
	@PostMapping(value="/requestNewRole")
	public ResponseEntity<User> requestNewRole(@RequestBody UserDto userDto){
		ResponseEntity<User> user = roleService.requestNewRole(userDto);
		return user;
	}
	
}
