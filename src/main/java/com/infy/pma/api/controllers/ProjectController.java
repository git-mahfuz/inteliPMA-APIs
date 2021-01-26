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

import com.infy.pma.api.entities.Project;
import com.infy.pma.api.services.ProjectService;

@RestController
@RequestMapping(path = "/api/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@GetMapping(consumes = "application/json")
	public ResponseEntity<List<Project>> fetchAllProject() {

		return new ResponseEntity<List<Project>>(projectService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Project> save(@Valid @RequestBody Project project) {
		
		return new ResponseEntity<Project>(projectService.save(project), HttpStatus.CREATED);
	}
}
