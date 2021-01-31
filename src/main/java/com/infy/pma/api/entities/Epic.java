package com.infy.pma.api.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Epic {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private UUID epicId;
	
	@NotEmpty
	@Size(min = 4, max = 64)
	private String name;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "project_id", nullable = true)
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = true)
	private Employee employee;
	
	public Epic() {
		
	}

	public Epic(String name, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Epic(UUID epicId, String name, Date startDate, Date endDate) {
		super();
		this.epicId = epicId;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Epic(String name, Date startDate, Date endDate, Project project) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.project = project;
	}
	
	public Epic(String name, Date startDate, Date endDate, Project project, Employee employee) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.project = project;
	}

	public UUID getEpicId() {
		return epicId;
	}

	public void setEpicId(UUID epicId) {
		this.epicId = epicId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
