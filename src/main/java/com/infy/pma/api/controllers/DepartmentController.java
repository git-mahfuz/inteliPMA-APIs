package com.infy.pma.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.pma.api.entities.Department;
import com.infy.pma.api.services.DepartmentService;

@RestController
@RequestMapping(path = "/api/v1/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@GetMapping(consumes = "application/json")
	public ResponseEntity<List<Department>> fetchAllDepartment() {

		return new ResponseEntity<List<Department>>(departmentService.findAll(), HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<Department> save(@Valid @RequestBody Department department) {

		return new ResponseEntity<Department>(departmentService.save(department), HttpStatus.CREATED);
	}

}
