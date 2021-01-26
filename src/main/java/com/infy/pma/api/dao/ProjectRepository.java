package com.infy.pma.api.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.pma.api.entities.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String> {
	
	@Override
	List<Project> findAll();
	
}
