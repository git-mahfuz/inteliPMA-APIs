package com.infy.pma.api.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.infy.pma.api.enums.EmployementType;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employeeId")
public class Employee {

	@Id
	@NotEmpty
	private String employeeId;

	@NotEmpty
	@Size(min = 1, max = 32)
	private String firstName;

	@NotEmpty
	@Size(min = 1, max = 32)
	private String lastName;

	@Enumerated(EnumType.STRING)
	@NotNull
	private EmployementType type;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToOne
	@JoinColumn(name = "designation_id")
	private Designation designation;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfJoining;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastDateOfEmployment;

	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(name = "employee_project", 
			joinColumns = @JoinColumn(name="employee_id"),
			inverseJoinColumns = @JoinColumn(name="project_id"))
	private List<Project> projects;

	public Employee() {

	}

	public Employee(String employeeId, String firstName, String lastName, EmployementType type, Department department,
			Designation designation, Date dateOfJoining, Date lastDateOfEmployment, List<Project> projects) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
		this.department = department;
		this.designation = designation;
		this.dateOfJoining = dateOfJoining;
		this.lastDateOfEmployment = lastDateOfEmployment;
		this.projects = projects;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public EmployementType getType() {
		return type;
	}

	public void setType(EmployementType type) {
		this.type = type;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Date getLastDateOfEmployment() {
		return lastDateOfEmployment;
	}

	public void setLastDateOfEmployment(Date lastDateOfEmployment) {
		this.lastDateOfEmployment = lastDateOfEmployment;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", type="
				+ type + ", department=" + department + ", designation=" + designation + ", dateOfJoining="
				+ dateOfJoining + ", lastDateOfEmployment=" + lastDateOfEmployment + ", projects=" + projects + "]";
	}

}
