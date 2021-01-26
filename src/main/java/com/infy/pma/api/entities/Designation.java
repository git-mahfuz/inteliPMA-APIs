package com.infy.pma.api.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Designation {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",
	  strategy = "uuid2")
	private String designationId;
	
	@OneToMany(mappedBy = "designation", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employees;
	
	@NotEmpty
	@Size(min = 3, max = 32)
	private String name;

	public Designation() {
		
	}

	public Designation(String designationId, String name) {
		super();
		this.designationId = designationId;
		this.name = name;
	}

	public String getDesignationId() {
		return designationId;
	}

	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Designation [designationId=" + designationId + ", employees=" + employees + ", name=" + name + "]";
	}

	
	
}
