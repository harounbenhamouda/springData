package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.HRStatisticProjection;
import com.example.demo.Exceptions.EmployeeNotFoundException;
import com.example.demo.entity.Emloyee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Emloyee> createEmployee(@RequestBody Emloyee employee) {
        Emloyee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
    @GetMapping("/emp-dept")
    public List<Emloyee>findByEmpAndDept(@RequestParam String empName, @RequestParam String deptName){
		
    	List<Emloyee> employe=employeeService.findByEmpAndDept(empName, deptName);
		 return employe;
	}
    @GetMapping("/filter")
    public List<Emloyee> filterByName(@RequestParam String empName ,@RequestParam String sortCol,@RequestParam Boolean isAsc){
    	List<Emloyee> emloyee=employeeService.filterEmployee(empName,sortCol,isAsc);
    	return emloyee;
    }
    @GetMapping("/filter2")
	public ResponseEntity<?> findByName(@RequestParam String name ,@RequestParam int pageNum, @RequestParam int pageSize
			, @RequestParam String sortCol , @RequestParam Boolean isAsc) {

		return ResponseEntity.ok(employeeService.filter2(name, pageNum, pageSize, sortCol, isAsc));
	}

    @DeleteMapping("/emp-dept")
	public ResponseEntity<Long> deleteByEmpAndDept(@RequestParam String empName,@RequestParam String deptName) {

		return ResponseEntity.ok(employeeService.deleteByEmpAndDept(empName, deptName));
	}
    @GetMapping("/{id}")
    public ResponseEntity<Emloyee> getEmployee(@PathVariable Long id) {
        Emloyee employee = employeeService.geyEmployeeById(id);
        return ResponseEntity.ok(employee);
    }
    @GetMapping("statistic")
    public HRStatisticProjection getHRStatic() {
    	return employeeService.getHRStatic();
    }

    @GetMapping
    public ResponseEntity<List<Emloyee>> getAllEmployees() {
        List<Emloyee> employees = employeeService.getEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emloyee> updateEmployee(@PathVariable Long id, @RequestBody Emloyee updatedEmployee) {
        Emloyee updated = employeeService.updateEmployee(updatedEmployee, id);
        return ResponseEntity.ok(updated);
    }

   /* @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }*/

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}
