package com.infy.pma.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.pma.api.entities.Epic;
import com.infy.pma.api.services.EpicService;

@RestController
@RequestMapping(path = "/api/v1")
public class EpicController {

	@Autowired
	EpicService epicService;
	
	@GetMapping(path = "/epic", consumes = "application/json")
	public List<Epic> findAll() {
		return epicService.findAll();
	}
	
	@PostMapping(path = "/epic", consumes = "application/json")
	public ResponseEntity<Epic> save(Epic epic) {
		return new ResponseEntity<Epic>(epicService.save(epic), HttpStatus.CREATED);
	}
}
