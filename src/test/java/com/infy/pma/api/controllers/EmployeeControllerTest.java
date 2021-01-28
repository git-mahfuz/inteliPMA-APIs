package com.infy.pma.api.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.pma.api.entities.Department;
import com.infy.pma.api.entities.Designation;
import com.infy.pma.api.entities.Employee;
import com.infy.pma.api.entities.Project;
import com.infy.pma.api.enums.EmployementType;
import com.infy.pma.api.services.EmployeeService;

@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	EmployeeService employeeService;
	
	private static List<Employee> employees = new ArrayList<Employee>();
	
	private static List<Project> projects = new ArrayList<Project>();
	
	@BeforeAll
	public static void initialize() {
		
		Employee employee1 = new Employee();
		employee1.setEmployeeId(UUID.randomUUID().toString());
		employee1.setFirstName("John");
		employee1.setLastName("Doe");
		employee1.setDepartment(
					new Department()
				);
		employee1.setDesignation(
					new Designation()
				);
		employee1.setType(EmployementType.INTERN);
		employee1.setDateOfJoining(new Date());
		employee1.setLastDateOfEmployment(new Date());
		employee1.setProjects(projects);
		
		employees.add(employee1);
		
		Employee employee2 = employee1;
		employee2.setEmployeeId(UUID.randomUUID().toString());
		
		employees.add(employee2);
		
	}
	
	@Test
	void doingAValidRequestToGetAListOfEmployeeAndExpectingSuccessResponse() throws Exception {
		
		when(employeeService.findAll()).thenReturn(employees);
		
		mockMvc.perform(
					get("/api/v1/employee?page=0&size=5")
					.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(status().isOk());
	}
	
	@Test
	void doingAnInvlidRequestToGetAListOfEmployeeAndExpectingBadRequestResponse() throws Exception {
		
		when(employeeService.findAll()).thenReturn(employees);
		
		mockMvc.perform(
					get("/api/v1/employee")
					.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(jsonPath("$.errors").isNotEmpty())
					.andExpect(status().isBadRequest());
	}
	
	@Test
	void doinaAValidRequestToAddAnEmployeeAndExpectingSuccessResponse() throws Exception {
		
		when(employeeService.save(employees.get(0))).thenReturn(employees.get(0));
		
		mockMvc.perform(
					post("/api/v1/employee")
					.content(new ObjectMapper().writeValueAsString(employees.get(0)))
					.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(status().isCreated());
	}
	
	@Test
	void doinaAnInValidRequestToAddAnEmployeeAndExpectingBadRequestResponse() throws Exception {
		
		when(employeeService.save(employees.get(0))).thenReturn(employees.get(0));
		
		mockMvc.perform(
					post("/api/v1/employee")
					.content("{}")
					.contentType(MediaType.APPLICATION_JSON)
				)
					.andDo(print())
					.andExpect(jsonPath("$.errors").isNotEmpty())
					.andExpect(status().isBadRequest());
	}

}
