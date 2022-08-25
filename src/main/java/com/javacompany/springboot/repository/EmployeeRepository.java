package com.javacompany.springboot.repository;

import com.javacompany.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee , Long > {
}
