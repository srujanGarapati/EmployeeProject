package com.EMP.Employeeproject.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Role")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role {
	@Id
	@Column(name="role")
	private String role;
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy = "role")
	private List<Employee> employees;
	
	public Role() {
		super();// TODO Auto-generated constructor stub
	}

	public Role(String role, String description) {
		super();
		this.role = role;
		this.description = description;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Role [role=" + role + ", description=" + description + "]";
	}
	
	
	
}
