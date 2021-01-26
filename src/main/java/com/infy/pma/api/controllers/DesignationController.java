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

import com.infy.pma.api.entities.Designation;
import com.infy.pma.api.services.DesignationService;

@RestController
@RequestMapping(path = "/api/v1/designation")
public class DesignationController {

	@Autowired
	DesignationService designationService;

	@GetMapping(consumes = "application/json")
	public ResponseEntity<List<Designation>> fetchAllDesignation() {

		return new ResponseEntity<List<Designation>>(designationService.findAll(), HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<Designation> save(@Valid @RequestBody Designation designation) {

		return new ResponseEntity<Designation>(designationService.save(designation), HttpStatus.CREATED);
	}

}
