package com.userManagementApi.service;

import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.userManagementApi.Dto.UserDto;
import com.userManagementApi.entities.Role;
import com.userManagementApi.entities.User;
import com.userManagementApi.repository.RoleRepo;
import com.userManagementApi.repository.UserRepo;

@Service
public class RoleService {

	@Autowired
	RoleRepo roleRepo;

	@Autowired
	UserRepo userRepo;
	
	Logger log = LoggerFactory.getLogger(RoleService.class);
	
	// method for adding role
	@Transactional
	public ResponseEntity<Role> createRoles(Role role) {
		List<Role> roles = roleRepo.findAll();
		boolean roleExists = roles.stream().anyMatch(existingRole -> existingRole.equals(role));

		log.info("inside createRoles method, roleExists = {}",roleExists);
		if (roleExists == true) {
			throw new RuntimeException("role already exists !");
		}
		roleRepo.save(role);
		return ResponseEntity.status(HttpStatus.CREATED).body(role);
	}

	// method for updating role
	@Transactional
	public ResponseEntity<Role> updateRoles(Role role) {

		Role existingRole = roleRepo.findById(role.getId())
				                    .orElseThrow(() -> new RuntimeException("Role does not exists with id : " + role.getId()));

		existingRole.setName(role.getName());

		Role roleObj = roleRepo.save(existingRole);
		return ResponseEntity.status(HttpStatus.CREATED).body(roleObj);
	}
	
	// method for retrieving  roles
	@Transactional
	public List<Role> getRoles(){
		List<Role> roles = roleRepo.findAll();
		return roles;
	} 
	
	// method for assigning role to a user
	@Transactional
	public ResponseEntity<User> assignRoleToUser(int roleId,int userId) {
		User user = userRepo.findById(userId)
				            .orElseThrow(() -> new RuntimeException("user does not exists with id :-" + userId));
		
		Role role = roleRepo.findById(roleId)
				    .orElseThrow(() -> new RuntimeException("role does not exists with id :-" + userId));
		
		user.setRole(role);

		User updatedUser = userRepo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
	}
	

    // method for requesting new role
	@Transactional
	public ResponseEntity<User> requestNewRole(UserDto userDto){
		
		User updatedUser;
		
		if(userDto.getRequestedRoleId() != 0 && (userDto.getCurrentRoleId() != userDto.getRequestedRoleId())) {
        	 User user = userRepo.findById(userDto.getUserId())
	    		       .orElseThrow(()-> new RuntimeException("User does not exists with id : " + userDto.getUserId()));
        	 
 		     user.setRequestedRoleId(userDto.getRequestedRoleId());
 		     
 		     updatedUser = userRepo.save(user);
        }else {
        	throw new RuntimeException("Requested role should not be empty or same as current role");
        }
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser) ;
	}



    // method for updating user's role 
	@Transactional
	public ResponseEntity<User> updateUserRole(UserDto userDto) {

		User user = userRepo.findById(userDto.getUserId())
				    .orElseThrow(() -> new RuntimeException("User does not exists with id : " + userDto.getUserId()));

		Role role = roleRepo.findById(userDto.getRequestedRoleId()).orElseThrow(
				() -> new RuntimeException("role does not exists with id :-" + userDto.getRequestedRoleId()));

		user.setRole(role);
		user.setRequestedRoleId(0);

		User updatedUser = userRepo.save(user);

		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}
}
