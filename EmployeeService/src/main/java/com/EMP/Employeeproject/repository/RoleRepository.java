package com.EMP.Employeeproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EMP.Employeeproject.bean.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
