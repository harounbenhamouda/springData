package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
@RestController
@RequestMapping("/api/departments")

public class DepartmentController {
 private final DepartmentService departmentservice;
 @Autowired
  public DepartmentController(DepartmentService departmentservice){
	this.departmentservice = departmentservice;}
   @GetMapping("/All")
   public ResponseEntity<List<Department>> getAllDepartments(){
	   List<Department> departments=departmentservice.findAll();
	   return ResponseEntity.ok(departments);
   }
   @GetMapping("/{id}")
   public ResponseEntity<Department> findDepartmentById(@PathVariable Long id){
   
   Department department=departmentservice.findById(id);
   return ResponseEntity.ok(department);}
   @PostMapping("/create")
 public   ResponseEntity<Department> createDepartment(@RequestBody Department department){
	   Department depar=departmentservice.insert(department);
	   return ResponseEntity.ok(depar);
   }
   @PutMapping("/update/{id}")
   public ResponseEntity<Department> updateDepartment(@RequestBody Department department,  Long id){
	   
    Department depar= departmentservice.update(department,id);
    return ResponseEntity.ok(depar);}
   
   
   
   

   }
