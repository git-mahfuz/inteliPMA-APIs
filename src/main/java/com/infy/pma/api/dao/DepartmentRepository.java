package com.infy.pma.api.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.infy.pma.api.entities.Department;

public interface DepartmentRepository extends CrudRepository<Department, String> {
	
	@Override
	public List<Department> findAll();

	public Department findByDepartmentId(String departmentId);
	
}
