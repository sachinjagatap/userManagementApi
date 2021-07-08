package com.userManagementApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userManagementApi.entities.Permission;

public interface PermissionRepo extends JpaRepository<Permission, Integer> {

}
