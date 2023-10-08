package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.repo.RoleRepo;

@Service
public class RoleService {
	@Autowired
private RoleRepo roleRepo;
	public Role insertRole(Role role) {
		return roleRepo.save(role);
	}
}
