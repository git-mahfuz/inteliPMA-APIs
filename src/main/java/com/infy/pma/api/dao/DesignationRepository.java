package com.infy.pma.api.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.infy.pma.api.entities.Designation;

public interface DesignationRepository extends CrudRepository<Designation, String> {
	
	@Override
	public List<Designation> findAll();

	public Designation findByDesignationId(String designationId);
	
}
