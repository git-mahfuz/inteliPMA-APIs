package com.infy.pma.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.infy.pma.api.dao.EmployeeRepository;
import com.infy.pma.api.dao.ProjectRepository;
import com.infy.pma.api.entities.Employee;
import com.infy.pma.api.entities.Project;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	public Page<Project> findAll(Pageable page) {
		return projectRepository.findAll(page);
	}

	public Project save(Project project) {

		if (project.getEmployees() != null) {
			if (!project.getEmployees().isEmpty()) {

				List<Employee> employees = new ArrayList<>();

				project.getEmployees().stream().forEach((e) -> {
					Employee employee = employeeRepository.findByEmployeeId(e.getEmployeeId());

					if (employee != null)
						employees.add(employee);
				});

				if (!employees.isEmpty())
					project.setEmployees(employees);

			}
		}

		return projectRepository.save(project);
	}
}
