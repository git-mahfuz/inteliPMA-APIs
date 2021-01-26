package com.infy.pma.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.pma.api.dao.DesignationRepository;
import com.infy.pma.api.entities.Designation;

@Service
public class DesignationService {
	
	@Autowired
	DesignationRepository designationRepository;
	
	public List<Designation> findAll() {
		
		return designationRepository.findAll();
	}
	
	public Designation save(Designation designation) {
		
		return designationRepository.save(designation);
	}
}
