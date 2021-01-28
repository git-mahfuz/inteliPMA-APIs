package com.infy.pma.api.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.infy.pma.api.enums.ProjectStage;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "projectId")
public class Project {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String projectId;

	@NotEmpty
	@Size(min = 4, max = 80)
	private String name;

	@Enumerated(EnumType.STRING)
	@NotNull
	private ProjectStage stage;

	private String description;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "employee_project", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
	private List<Employee> employees;

	public Project() {

	}

	public Project(String projectId, String name, ProjectStage stage, String description, Date startDate, Date endDate,
			List<Employee> employees) {
		super();
		this.projectId = projectId;
		this.name = name;
		this.stage = stage;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employees = employees;
	}

	

	public Project(String projectId, String name, ProjectStage stage,
			String description, Date startDate, Date endDate) {
		super();
		this.projectId = projectId;
		this.name = name;
		this.stage = stage;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProjectStage getStage() {
		return stage;
	}

	public void setStage(ProjectStage stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", name=" + name + ", stage=" + stage + ", description="
				+ description + ", startDate=" + startDate + ", endDate=" + endDate + ", employees=" + employees + "]";
	}

}
