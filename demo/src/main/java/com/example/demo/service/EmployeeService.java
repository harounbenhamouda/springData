package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.HRStatisticProjection;
import com.example.demo.Exceptions.EmployeeNotFoundException;
import com.example.demo.entity.Department;
import com.example.demo.entity.Emloyee;
import com.example.demo.projection.EmployeeProjection;
import com.example.demo.repo.DepartmentRepo;
import com.example.demo.repo.EmloyeeRepo;
@Service
public class EmployeeService {
@Autowired
private EmloyeeRepo employeeRepo;
private final DepartmentRepo departmentRepo;

@Autowired
public EmployeeService(DepartmentRepo departmentRepo) {
    this.departmentRepo = departmentRepo;
}

public List<Emloyee> getEmployees(){
	return employeeRepo.findAll();
	
}
public Emloyee geyEmployeeById(Long id) {
	return employeeRepo.findById(id).get();
}
public List<Emloyee> findByEmpAndDept(String empName, String deptName){
	
	return employeeRepo.findByNameContainingAndDepartmentNameContaining(empName, deptName);
}

public Emloyee createEmployee(@RequestBody Emloyee employeeRequest) {
	 Department department = departmentRepo.findById(employeeRequest.getDepartment().getId())
	            .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + employeeRequest.getDepartment().getId()));

	    Emloyee employee = new Emloyee();
	    employee.setName(employeeRequest.getName());
	    employee.setLastName(employeeRequest.getLastName());
	    employee.setSalary(employeeRequest.getSalary());
	    employee.setDepartment(department);

	    Emloyee savedEmployee = employeeRepo.save(employee);
	    return savedEmployee;
	    }



public HRStatisticProjection getHRStatic() {
	return employeeRepo.getHRStatistic();
}


@Transactional
public Long deleteByEmpAndDept(String empName, String deptName) {

	return employeeRepo.deleteByNameContainingAndDepartmentNameContaining(empName, deptName);
}
public List<Emloyee> filterEmployee(String empName,String sortCol,Boolean isAsc){
	if(empName.isEmpty()||empName.isBlank()||empName==null) {
		 empName=null;
	}
	return employeeRepo.filter(empName,Sort.by(isAsc ? Direction.ASC:Direction.DESC,sortCol));
}

public Page<EmployeeProjection> filter2(String name ,int pageNum, int pageSize, String sortCol, Boolean isAsc) {

	if (name.isEmpty() || name.isBlank() || name == null) {
		name = null;
	}
	
	// Sort object with List of Order objects.
//	List<Order> orders = new ArrayList<Order>();
//
//	Order order1 = new Order(isAsc ? Direction.ASC : Direction.DESC, sortCol);
//	orders.add(order1);
//
//	Order order2 = new Order(Sort.Direction.ASC, "title");
//	orders.add(order2);
	
	Pageable page = PageRequest.of(pageNum, pageSize, Sort.by(isAsc ? Direction.ASC : Direction.DESC  ,sortCol));
	
	return employeeRepo.filter2(name, page);
}


public Emloyee updateEmployee(Emloyee employee,Long id ) {
	Optional<Emloyee> optionalEmployee = employeeRepo.findById(id);
    if (optionalEmployee.isPresent()) {
        Emloyee existingEmployee = optionalEmployee.get();
       existingEmployee.setName(employee.getName());
       existingEmployee.setLastName(employee.getLastName());
       existingEmployee.setSalary(employee.getSalary());
        
      //  existingEmployee.setName(employee.getLastName());
       existingEmployee.setDepartment(employee.getDepartment());;
        return employeeRepo.save(existingEmployee);
    } else {
        throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
    }

}
}
