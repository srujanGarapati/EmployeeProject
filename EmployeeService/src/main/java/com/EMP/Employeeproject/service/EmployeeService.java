package com.EMP.Employeeproject.service;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.EMP.Employeeproject.bean.Employee;
import com.EMP.Employeeproject.bean.Role;
import com.EMP.Employeeproject.repository.EmployeeRepo;
import com.EMP.Employeeproject.repository.RoleRepository;

@Component
public class EmployeeService {
	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
	private RoleRepository roleRepo;

	public Employee getEmployee(long id) {
		if (empRepo.findById(id).isPresent())
			return empRepo.findById(id).get();
		else
			return null;
	}

	public Employee addEmployee(Employee emp) {
		return empRepo.save(emp);
	}

	public Employee updateEmployee(Employee emp) {
		if(empRepo.findById(emp.getId()).isPresent()&&roleRepo.findById(emp.getRole().getRole()).isPresent())
			return empRepo.save(emp);
		else
			return null;
	}

	public void deleteEmployee(long id) {
		empRepo.deleteById(id);
	}

	public void deleteAllEmployeeWithSpecificRole(Role role) {
		List<Employee> list = empRepo.getAllEmployeeWithSpecificRole(role);
		if (list.size() != 0)
			empRepo.deleteInBatch(list);
	}
	
	public String getAdderssle(long roleId) {
		Employee emp = getEmployee(roleId);
		String address = "";
			try {
				Field field = emp.getClass().getDeclaredField("address");
				field.setAccessible(true);
				address=(String) field.get(emp);
				field.setAccessible(false);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return address;
	}
	
}
