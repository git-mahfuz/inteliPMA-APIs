package com.infy.pma.api.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.pma.api.entities.Department;
import com.infy.pma.api.services.DepartmentService;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentService departmentService;

	@Test
	void fetchListOfDepartmentAsAValidRequest() throws Exception {

		Department department1 = new Department("Engineering");
		Department department2 = new Department("HR & Accounts");

		List<Department> departments = Arrays.asList(department1, department2);

		when(departmentService.findAll()).thenReturn(departments);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/department")
					.contentType(MediaType.APPLICATION_JSON)
			)
				.andDo(print()).andExpect(jsonPath("$[0].name", containsString("Engineering")))
				.andExpect(status().isOk());
	}

	@Test
	void fetchListOfDepartmentAsAInValidRequest() throws Exception {

		Department department1 = new Department("Engineering");
		Department department2 = new Department("HR & Accounts");

		List<Department> departments = Arrays.asList(department1, department2);

		when(departmentService.findAll()).thenReturn(departments);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/department")
			)
				.andDo(print())
				.andExpect(status().isUnsupportedMediaType());
	}

	@Test
	void addANewDepartment() throws Exception {

		Department department = new Department("Business Development");

		when(departmentService.save(department)).thenReturn(department);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/v1/department")
					.content(new ObjectMapper().writeValueAsString(department)
			)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated());

	}
	
	@Test
	void addANewDepartmentWithNoNameExpectABadRequestResponse() throws Exception {

		Department department = new Department();

		when(departmentService.save(department)).thenReturn(department);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/department")
				.content(new ObjectMapper().writeValueAsString(department))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isBadRequest());

	}

}
