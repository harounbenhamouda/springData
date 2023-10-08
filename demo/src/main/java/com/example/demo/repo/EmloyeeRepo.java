package com.example.demo.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.HRStatisticProjection;
import com.example.demo.entity.Emloyee;
import com.example.demo.projection.EmployeeProjection;
@Repository
//@Transactional(readOnly = true)

public interface EmloyeeRepo extends JpaRepository<Emloyee,Long>{
	List<Emloyee> findByNameContainingAndDepartmentNameContaining(String empName, String deptName);	
	Long deleteByNameContainingAndDepartmentNameContaining(String empName, String deptName);
	/***NATIVE QUERY*/
	@Query(value = "select * from emloyee emp where emp.first_Name like :empName", nativeQuery = true)
	List<Emloyee> filterNative(@Param("empName") String name);
	
	/**JPQl Query*/
	@Query("SELECT emp FROM Emloyee emp WHERE (:empName IS NULL OR emp.name LIKE %:empName%)")
	List<Emloyee> filter(@Param("empName") String name,Sort sort);
	
	/**JPQL Qury with pagination*/
	@Query(value = "select emp from #{#entityName} emp where (:empName is null or emp.name like :empName)")
	Page<EmployeeProjection> filter2(@Param("empName") String name, Pageable pageable);

	
	@Query(value = "select (select count(*) from hr_departments) deptCount,"
			+ " (select count(*) from emloyee) empCount,"
			+ " (select count(*) from sec_users ) userCount"
			, nativeQuery = true)
	HRStatisticProjection getHRStatistic ();
	

}
