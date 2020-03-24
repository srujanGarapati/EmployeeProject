package com.EMP.Employeeproject.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeid_generator")
	@SequenceGenerator(name = "employeeid_generator", sequenceName = "employeeid_generator", initialValue = 10000, allocationSize = 20)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "address")
	private String address;
//	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Role role;

	public Employee() {
		super();
	}

	public Employee(long id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return this.role;
	}

}
