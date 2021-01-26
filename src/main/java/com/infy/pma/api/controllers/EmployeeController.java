package com.infy.pma.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infy.pma.api.entities.Employee;
import com.infy.pma.api.services.EmployeeService;

@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping(consumes = "application/json")
	public ResponseEntity<Page<Employee>> fetchAllEmployee(@RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {

		return new ResponseEntity<Page<Employee>>(employeeService.findAll(PageRequest.of(page, size)),
				HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<Employee> save(@Valid @RequestBody Employee employee) {

		return new ResponseEntity<Employee>(employeeService.save(employee), HttpStatus.CREATED);
	}

}
