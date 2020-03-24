package com.EMP.Employeeproject.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.EMP.Employeeproject.bean.Employee;
import com.EMP.Employeeproject.bean.Role;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	
	@Query("select e from Employee e where e.role=?1")
	 List<Employee> getAllEmployeeWithSpecificRole(Role role);
}
