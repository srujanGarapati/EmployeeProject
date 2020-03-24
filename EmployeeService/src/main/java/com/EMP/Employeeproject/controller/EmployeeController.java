package com.EMP.Employeeproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EMP.Employeeproject.bean.Employee;
import com.EMP.Employeeproject.bean.Role;
import com.EMP.Employeeproject.service.EmployeeService;
import com.EMP.Employeeproject.service.RoleService;

@RestController
@CrossOrigin
public class EmployeeController {
	@Autowired
	private EmployeeService Es;
	
	@Autowired
	private RoleService rs;
	
	@GetMapping(value = "/Employee/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") long id) {
		Employee emp = Es.getEmployee(id);
		if (emp != null)
			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		else
			return new ResponseEntity<Employee>(emp, HttpStatus.NOT_FOUND);

	}

	@PostMapping(value = "/Employee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) {
		Role role = emp.getRole();
		if(rs.getRole(role.getRole()) != null) {
		Employee employee = Es.addEmployee(emp);
		if (employee != null)
			return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		else
			return new ResponseEntity<Employee>(emp, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Employee>(emp, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/Employee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp) {
		Employee employee = Es.updateEmployee(emp);
		if (employee != null)
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		else
			return new ResponseEntity<Employee>(emp, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/Employee")
	public ResponseEntity<String> addRole(@RequestParam("id") long id) {
		try {
			Es.deleteEmployee(id);
			return new ResponseEntity<String>("Employee " + id + " has been deleted sucessfully", HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<String>("Employee " + id + " has not deleted sucessfully", HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>("Please check parameter variables once again", HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/Employee/address")
	public ResponseEntity<String> address(@RequestParam("id") long id) {
		String address ="";
		try {
			address=Es.getAdderssle(id);
			return new ResponseEntity<String>("Employee " + address + " has been deleted sucessfully", HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<String>("Employee " + address + " has not deleted sucessfully", HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>("Please check parameter variables once again", HttpStatus.OK);
		}
	}

}
