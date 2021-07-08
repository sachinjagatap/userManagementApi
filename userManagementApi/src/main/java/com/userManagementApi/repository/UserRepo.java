package com.userManagementApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import com.userManagementApi.entities.User;


public interface UserRepo extends JpaRepository<User, Integer> {

	public abstract Optional<User> findByuserName(String userName);
}
