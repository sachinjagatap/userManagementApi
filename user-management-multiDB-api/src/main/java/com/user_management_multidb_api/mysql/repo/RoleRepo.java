package com.user_management_multidb_api.mysql.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user_management_multidb_api.mysql.entities.Role;


public interface RoleRepo extends JpaRepository<Role, Integer> {

}
