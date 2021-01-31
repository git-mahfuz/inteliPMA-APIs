package com.infy.pma.api.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.pma.api.entities.Epic;
import com.infy.pma.api.services.EpicService;

@WebMvcTest(controllers = EpicController.class)
public class EpicControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	EpicService epicService;
	
	@Test
	void findAll() throws Exception {
		
		Epic epic = new Epic("UX Design", new Date(), new Date());
		
		when(epicService.findAll()).thenReturn(Collections.singletonList(epic));
		
		mockMvc.perform(
					get("/api/v1/epic")
					.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(jsonPath("@").isNotEmpty())
					.andExpect(jsonPath("@").isArray())
					.andExpect(jsonPath("@.[0].name").value("UX Design"))
					.andExpect(status().isOk());
	}
	
	@Test
	void tryingtoAddAnEpicAndExpectingSuccess() throws Exception {
		
		Epic epic = new Epic("UX Design", new Date(), new Date());
		
		when(epicService.save(epic)).thenReturn(epic);
		
		mockMvc.perform(
					post("/api/v1/epic")
					.content(new ObjectMapper().writeValueAsString(epic))
					.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(status().isCreated());
	}
	
}
