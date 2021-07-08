package com.user_management_multidb_api.mysql.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.user_management_multidb_api.mysql.entities.User;


public interface UserRepo extends JpaRepository<User, Integer> {

	public abstract Optional<User> findByuserName(String userName);
}
