package com.example.demo.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {

	Long getId();
	
	String geName();
	
	String getLastName();
	
	@Value("#{target.name + ' ' + target.lastName}")
	String getFullName();

}
