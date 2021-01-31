package com.infy.pma.api.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.pma.api.entities.Epic;

@Repository
public interface EpicRepository extends CrudRepository<Epic, UUID>{
	
	List<Epic> findByName(String name);
	
	@Override
	List<Epic> findAll();
}
