package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	
	public void run(String... args) throws Exception {
		
		if (userService.findAll().isEmpty()) {		
		// create roles 
		Role role1 = new Role();		
		role1.setName("Admin");
		
		Role role2 = new Role();		
		role2.setName("User");
		
		roleService.insertRole(role1);
		roleService.insertRole(role2);
		
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(role1);
		
		
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(role2);
		
		
		// create users 
		User user1 = new User();
		user1.setUsername("Admin");
		user1.setPassword("123");
		user1.setRoles(adminRoles);
		
		userService.insertUser(user1);
		
		User user2 = new User();
		user2.setUsername("user");
		user2.setPassword("123");
		user2.setRoles(userRoles);
		
		userService.insertUser(user2);
		}
	}


}
