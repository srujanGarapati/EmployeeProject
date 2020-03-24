package com.EMP.Employeeproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

import com.EMP.Employeeproject.bean.Role;
import com.EMP.Employeeproject.service.RoleService;

@RestController
@CrossOrigin
public class RoleController {

	@Autowired
	private RoleService Rs;

	@GetMapping(value = "/Role/{role}")
	public ResponseEntity<Role> getRole(@PathVariable("role") String role) {
		Role roleBean = Rs.getRole(role);
		if (roleBean != null)
			return new ResponseEntity<Role>(roleBean, HttpStatus.OK);
		else
			return new ResponseEntity<Role>(roleBean, HttpStatus.NOT_FOUND);

	}

	@PostMapping(value = "/Role")
	public ResponseEntity<Role> addRole(@RequestBody Role roleBean) {
		Role role = Rs.addRole(roleBean);
		if (role != null)
			return new ResponseEntity<Role>(role, HttpStatus.CREATED);
		else
			return new ResponseEntity<Role>(role, HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = "/Role")
	public ResponseEntity<Role> UpdateRole(@RequestBody Role roleBean) {
		Role role = Rs.updateRole(roleBean);
		if (role != null)
			return new ResponseEntity<Role>(role, HttpStatus.OK);
		else
			return new ResponseEntity<Role>(roleBean, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/Role")
	public ResponseEntity<String> DeleteRole(@RequestParam("role") String role) {
		try {
			Rs.deleteRole(role);
			return new ResponseEntity<String>("Role " + role + " has been deleted sucessfully", HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<String>("Role " + role + " has not deleted sucessfully", HttpStatus.BAD_REQUEST);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<String>("Role " + role + " has not deleted sucessfully", HttpStatus.NOT_FOUND);
		}
	}

}
