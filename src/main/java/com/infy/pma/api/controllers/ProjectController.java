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

import com.infy.pma.api.entities.Project;
import com.infy.pma.api.services.ProjectService;

@RestController
@RequestMapping(path = "/api/v1/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@GetMapping(consumes = "application/json")
	public ResponseEntity<Page<Project>> fetchAllProject(@RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {

		return new ResponseEntity<Page<Project>>(projectService.findAll(PageRequest.of(page, size)),
				HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Project> save(@Valid @RequestBody Project project) {
		
		return new ResponseEntity<Project>(projectService.save(project), HttpStatus.CREATED);
	}
}
