package com.infy.pma.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.infy.pma.api.dao.DepartmentRepository;
import com.infy.pma.api.dao.DesignationRepository;
import com.infy.pma.api.dao.EmployeeRepository;
import com.infy.pma.api.dao.ProjectRepository;
import com.infy.pma.api.entities.Department;
import com.infy.pma.api.entities.Designation;
import com.infy.pma.api.entities.Employee;
import com.infy.pma.api.entities.Project;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	DesignationRepository designationRepository;

	@Autowired
	ProjectRepository projectRepository;

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Page<Employee> findAll(Pageable page) {
		return employeeRepository.findAll(page);
	}

	public Employee save(Employee employee) {

		if (employee.getDepartment() != null) {
			Department dept = departmentRepository.findById(employee.getDepartment().getDepartmentId()).orElse(null);
			if (dept != null) {
				employee.setDepartment(dept);
			}
		}

		if (employee.getDesignation() != null) {
			Designation desg = designationRepository.findById(employee.getDesignation().getDesignationId())
					.orElse(null);
			if (desg != null) {
				employee.setDesignation(desg);
			}
		}

		if (employee.getProjects() != null) {
			if (!employee.getProjects().isEmpty()) {

				List<Project> projects = new ArrayList<>();

				employee.getProjects().stream().forEach((p) -> {
					Project project = projectRepository.findByProjectId(p.getProjectId());

					if (project != null)
						projects.add(project);
				});

				if (!projects.isEmpty())
					employee.setProjects(projects);
			}
		}

		return employeeRepository.save(employee);
	}
}
