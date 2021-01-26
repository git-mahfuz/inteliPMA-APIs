package com.infy.pma.api.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.pma.api.entities.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
	
	@Override
	List<Employee> findAll();
	
	Page<Employee> findAll(Pageable page);
	
	Employee findByEmployeeId(String employeeId);
	
}
