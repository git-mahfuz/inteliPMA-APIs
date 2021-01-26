package com.infy.pma.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.pma.api.dao.ProjectRepository;
import com.infy.pma.api.entities.Project;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	public Project save(Project project) {
		return projectRepository.save(project);
	}
}
