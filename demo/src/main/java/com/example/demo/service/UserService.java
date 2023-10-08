package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;

    @Transactional

	public User insertUser(User user) {
	return	userRepo.save(user);
	}

	public User findById(Long id) {

		return userRepo.findById(id).orElseThrow();
	}
	public User update(User emp) {

		User current = userRepo.findById(emp.getId()).orElseThrow();

		current.setUsername(emp.getUsername());
		current.setPassword(emp.getPassword());

		return userRepo.save(current);
	}

	public List<User> findAll() {

		return userRepo.findAll();
	}
}
