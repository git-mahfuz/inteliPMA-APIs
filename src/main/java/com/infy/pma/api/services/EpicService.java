package com.infy.pma.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.pma.api.dao.EpicRepository;
import com.infy.pma.api.entities.Epic;

@Service
public class EpicService {

	@Autowired
	EpicRepository epicRepository;
	
	public Epic save(Epic epic) {
		return epicRepository.save(epic);
	}

	public List<Epic> findAll() {
		return epicRepository.findAll();
		
	}

	
}
