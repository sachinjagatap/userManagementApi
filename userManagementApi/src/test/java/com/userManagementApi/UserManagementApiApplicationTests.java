package com.userManagementApi;


import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import com.userManagementApi.entities.Permission;
import com.userManagementApi.entities.Role;
import com.userManagementApi.entities.User;
import com.userManagementApi.repository.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserManagementApiApplicationTests {

	@Autowired
	UserRepo userRepo;
	
	private User user;
	
	private Role role;
	
	private Permission permission;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@Transactional
	@Commit
	public void test(){
            Date  d = Date.valueOf(LocalDate.now());
            
		user = new User();
		user.setUserName("sachin.jagtap");
		user.setPassword(bCryptPasswordEncoder.encode("123"));
		user.setCreatedDate(d);
		user.setRequestedRoleId(0);
		
		role = new Role();
		role.setName("ROLE_ADMIN");
		role.setDescription("Admin has the access to create, update, read and delete a user");
       
        Set<Permission> permissionSet = new HashSet<Permission>();
		permission = new Permission();
		permission.setName("create");
		permission.setDescription("allows to create a user");
		
		permissionSet.add(permission);
		
		role.setPermissions(permissionSet);
		
		user.setRole(role);
		
		userRepo.save(user);
	}
	
}
