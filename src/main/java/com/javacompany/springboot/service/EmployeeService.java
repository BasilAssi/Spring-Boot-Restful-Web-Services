package com.javacompany.springboot.service;

import com.javacompany.springboot.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long id);
    Employee updateEmployee(Employee employee ,Long id);
    void deleteEmployeeByID(Long id);
}
