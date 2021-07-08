package com.userManagementApi.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.userManagementApi.entities.Permission;
import com.userManagementApi.entities.Role;
import com.userManagementApi.entities.User;
import com.userManagementApi.repository.PermissionRepo;
import com.userManagementApi.repository.UserRepo;

@Service
public class PermissionService {


	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PermissionRepo permissionRepo;
	
	Logger log = LoggerFactory.getLogger(PermissionService.class);
	
		
	// method for creating permission
		@Transactional
		public ResponseEntity<Permission> createPermission(Permission permission) {
			List<Permission> permissions = permissionRepo.findAll();
			
			boolean permissionExists = permissions.stream().anyMatch(existingPermission -> existingPermission.equals(permission));

			if (permissionExists == true) {
				throw new RuntimeException("permission already exists !");
			}
			permissionRepo.save(permission);
			return ResponseEntity.status(HttpStatus.CREATED).body(permission);
		}
		
		
	// method for assigning permission to user role 
	@Transactional
	public ResponseEntity<User> assignPermissionsToUserRole(int permissionId,int userId) {
		User user = userRepo.findById(userId)
							.orElseThrow(() -> new RuntimeException("user does not exists with id :-" + userId));

		Role role = user.getRole();
		Set<Permission> permissionSetObj = role.getPermissions();
		
		Permission permission = permissionRepo.findById(permissionId)
		              .orElseThrow(() -> new RuntimeException("user does not exists with id :-" + userId));
		
		permissionSetObj.add(permission);

		User updatedUser = userRepo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
	}
}
