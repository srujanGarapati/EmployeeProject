package com.EMP.Employeeproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.EMP.Employeeproject.bean.Role;
import com.EMP.Employeeproject.repository.RoleRepository;

@Component
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private EmployeeService empService;
	
	public Role getRole(String role) {
		Optional<Role> roleBean = roleRepository.findById(role);
		if(roleBean.isPresent())
			return roleBean.get();
		else
			return null;
	}
	
	public Role addRole(Role role) {
		return roleRepository.save(role);
	}
	
	public Role updateRole(Role role) {
		if( roleRepository.findById(role.getRole()).isPresent())
			return roleRepository.save(role);
		else 
			return null;
	}
	
	public void deleteRole(String role) {
		empService.deleteAllEmployeeWithSpecificRole(getRole(role));
		roleRepository.deleteById(role);
	}

}
