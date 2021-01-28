package com.infy.pma.api.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.pma.api.entities.Employee;
import com.infy.pma.api.entities.Project;
import com.infy.pma.api.enums.ProjectStage;
import com.infy.pma.api.services.ProjectService;

@WebMvcTest(controllers = ProjectController.class)
class ProjectControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProjectService projectService; 
	
	private List<Project> projects;
	
	private List<Employee> employees;
	
	@Before
	public void initialize() {
		
		Project project1 = new Project();
		project1.setProjectId(UUID.randomUUID().toString());
		project1.setName("Deployment");
		project1.setDescription("Some description");
		project1.setStage(ProjectStage.INPROGRESS);
		project1.setStartDate(new Date());
		project1.setEndDate(new Date());
		project1.setEmployees(employees);
		
		this.projects.add(project1);
		
		Project project2 = new Project();
		project2.setProjectId(UUID.randomUUID().toString());
		project2.setName("Development");
		project2.setDescription("Some description");
		project2.setStage(ProjectStage.INPROGRESS);
		project2.setStartDate(new Date());
		project2.setEndDate(new Date());
		project2.setEmployees(employees);
		
		this.projects.add(project2);
	}
	
	@Test
	void fetchListOfProjectAsAValidRequest() throws Exception {
		
		when(projectService.findAll()).thenReturn(this.projects);
		
		mockMvc.perform(
					get("/api/v1/project?page=0&size=10")
					.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(status().isOk());
	}
	
	@Test
	void fetchListOfProjectWithotuPaginationParams() throws Exception {
		
		when(projectService.findAll()).thenReturn(this.projects);
		
		mockMvc.perform(
					get("/api/v1/project")
					.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(status().isBadRequest());
	}
	
	@Test
	void addANewProjectWithValidDataAndGetSuccessResponse() throws Exception {
		
		Project project = new Project(
				UUID.randomUUID().toString(), 
				"Testing", 
				ProjectStage.INPROGRESS, 
				"Some description", 
				new Date(), 
				new Date()
			);		
		
		when(projectService.save(project)).thenReturn(project);
		
		mockMvc.perform(
					post("/api/v1/project")
					.content(new ObjectMapper().writeValueAsString(project))
					.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(status().isCreated());
	}
	
	@Test
	void addANewProjectWithoutDataAndGetErrorResponse() throws Exception {
		
		Project project = new Project();
		
		when(projectService.save(project)).thenReturn(project);
		
		mockMvc.perform(
					post("/api/v1/project")
					.content("{}")
					.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(status().isBadRequest());
	}

}
