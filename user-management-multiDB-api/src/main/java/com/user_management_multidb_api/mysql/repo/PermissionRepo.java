package com.user_management_multidb_api.mysql.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.user_management_multidb_api.mysql.entities.Permission;


public interface PermissionRepo extends JpaRepository<Permission, Integer> {

}