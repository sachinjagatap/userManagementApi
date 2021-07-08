package com.userManagementApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userManagementApi.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
