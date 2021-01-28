package com.infy.pma.api.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.pma.api.entities.Designation;
import com.infy.pma.api.services.DesignationService;

@WebMvcTest(controllers = DesignationController.class)
class DesignationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DesignationService designationService;
	
	
	@Test
	void fetchListOfDesigantionWithValidRequestAndGetSuccessResponse() throws Exception {
		
		Designation designationOne = new Designation(UUID.randomUUID().toString(), "Support");
		Designation designationTwo = new Designation(UUID.randomUUID().toString(), "Network");
		
		List<Designation> designations = Arrays.asList(designationOne, designationTwo);
		
		when(designationService.findAll()).thenReturn(designations);
		
		mockMvc.perform(
					get("/api/v1/designation")
						.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$").isNotEmpty())
					.andExpect(jsonPath("$[0].name", containsString("Support")));
		
	}
	
	@Test
	void fetchListOfDesigantionWithValidRequestAndGetEmptyListResponse() throws Exception {
		
		List<Designation> designations = new ArrayList<>();
		
		when(designationService.findAll()).thenReturn(designations);
		
		mockMvc.perform(
					get("/api/v1/designation")
						.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$").isEmpty());
	}
	
	@Test
	void fetchListOfDesigantionWithoutContentTypeAndGetUnsupportedMediaTypeErrorResponse() throws Exception {
		
		List<Designation> designations = new ArrayList<>();
		
		when(designationService.findAll()).thenReturn(designations);
		
		mockMvc.perform(
					get("/api/v1/designation")
				)
					.andDo(print())
					.andExpect(status().isUnsupportedMediaType());
	}
	
	@Test
	void createANewDesignationWithValidDataAndGetSuccessResponse() throws Exception {
		
		Designation designation = new Designation(UUID.randomUUID().toString(), "Support");
		
		when(designationService.save(designation)).thenReturn(designation);
		
		mockMvc.perform(
					post("/api/v1/designation")
						.content(new ObjectMapper().writeValueAsString(designation))
						.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(status().isCreated());
		
		
	}
	
	@Test
	void createANewDesignationWithoutValidDataAndGetBadRequestResponse() throws Exception {
		
		Designation designation = new Designation(UUID.randomUUID().toString(), "Support");
		
		when(designationService.save(designation)).thenReturn(designation);
		
		mockMvc.perform(
					post("/api/v1/designation")
						.content(new ObjectMapper().writeValueAsString(null))
						.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(status().isBadRequest());
		
		
	}
	
	@Test
	void createANewDesignationWithoutValidNameLengthAndGetBadRequestResponse() throws Exception {
		
		Designation designation = new Designation(UUID.randomUUID().toString(), "S");
		
		when(designationService.save(designation)).thenReturn(designation);
		
		mockMvc.perform(
					post("/api/v1/designation")
						.content(new ObjectMapper().writeValueAsString(designation))
						.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(jsonPath("$.errors").isArray())
					.andExpect(status().isBadRequest());
		
		
	}

}
