package com.infy.pma.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.pma.api.dao.DepartmentRepository;
import com.infy.pma.api.entities.Department;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	public List<Department> findAll() {
		
		return departmentRepository.findAll();
	}
	
	public Department save(Department department) {
		
		return departmentRepository.save(department);
	}
}
