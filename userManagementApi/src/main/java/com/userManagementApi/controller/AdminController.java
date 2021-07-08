package com.userManagementApi.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userManagementApi.Dto.UserDto;
import com.userManagementApi.entities.Permission;
import com.userManagementApi.entities.Role;
import com.userManagementApi.entities.User;
import com.userManagementApi.service.PermissionService;
import com.userManagementApi.service.RoleService;
import com.userManagementApi.service.UserService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
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
	
	@PostMapping(value = "/addUser")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user){
		   
		   ResponseEntity<User> userObj = userService.addUser(user);
		return userObj;
	}
	
	@PutMapping(value="/updateUser")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user){
		   ResponseEntity<User> userObj = userService.updateUser(user);
		return userObj;
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int userId){
		ResponseEntity<User> deleteUser = userService.deleteUser(userId);
		return deleteUser;
	}
	
	@PostMapping(value = "/createRoles")
	public ResponseEntity<Role> createRole(@Valid @RequestBody Role role){
		   
		ResponseEntity<Role> roleObj = roleService.createRoles(role);
		return roleObj;
	}
	
	@PostMapping(value="/assignRoleToUser/{roleId}/{userId}")
	public ResponseEntity<User> assignRoleToUser(@PathVariable("roleId") int roleId , @PathVariable("userId") int userId){
		ResponseEntity<User> userObj = roleService.assignRoleToUser(roleId,userId);
		return userObj;
	}
	
	
	@PostMapping(value = "/createPermission")
	public ResponseEntity<Permission> createPermission(@Valid @RequestBody Permission permission ){
		   
		ResponseEntity<Permission> permissionObj = permissionService.createPermission(permission);
		return permissionObj;
	}
	
	@PostMapping(value="/assignPermissionsToUserRole/{permissionId}/{userId}")
	public ResponseEntity<User> assignPermissionsToUserRole(@PathVariable("permissionId") int permissionId, @PathVariable("userId") int userId){
		ResponseEntity<User> userObj = permissionService.assignPermissionsToUserRole(permissionId, userId);
		return userObj;
	}
	
	@PostMapping(value = "/updateUserRole")
	public ResponseEntity<User> updateUserRole(UserDto userDto) {
		
		return roleService.updateUserRole(userDto);
	}
}
