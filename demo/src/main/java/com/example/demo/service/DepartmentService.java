package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.repo.DepartmentRepo;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepo departmentRepo;
	
	
	public Department findById (Long id) {
		
	 return departmentRepo.findById(id).orElseThrow();
	}
	
	
	public Department insert(Department dept) {

		return departmentRepo.save(dept);
	}

	public Department update(Department dept,Long id) {

		 Department current = departmentRepo.findById(id).orElseThrow();

		current.setName(dept.getName());

		return departmentRepo.save(current);
	}
	
	
	public List<Department> findAll(){
		
		return departmentRepo.findAll();
	}
	
	
	/*public int deleteByName(String deptName) {
		
		return departmentRepo.deleteByName(deptName);
	}*/
}
