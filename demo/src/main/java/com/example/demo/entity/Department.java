package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hr_departments")
@Data @NoArgsConstructor @AllArgsConstructor


public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "depratment_gen")
//	@SequenceGenerator(name = "depratment_gen" , sequenceName = "department_seq", initialValue = 100)
//	
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "depratment_gen")
//	@TableGenerator(name = "depratment_gen", table = "depratment_seq", allocationSize = 1, initialValue = 20)
	
	
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "department" , fetch = FetchType.LAZY)
	//@JsonManagedReference
     private List<Emloyee> employees;
}
