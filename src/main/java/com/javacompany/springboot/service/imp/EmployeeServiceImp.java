package com.javacompany.springboot.service.imp;

import com.javacompany.springboot.exceptions.ResourceNotFoundException;
import com.javacompany.springboot.model.Employee;
import com.javacompany.springboot.repository.EmployeeRepository;
import com.javacompany.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {


    private EmployeeRepository employeeRepository;

    @Autowired

    /** if a class , which is configured as Spring bean , has only one constructor , @Autowired annotation
     *  can be omitted and Spring will use that constructor and inject all necessary dependencies
    **/
    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        }else {
            throw new ResourceNotFoundException("Employee" , "ID" , id);
        }

//        return  employeeRepository.findById(id).orElseThrow(() ->
//         new ResourceNotFoundException("Employee" , "ID" , id) );
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {

        // we need to check whether employee with given id is existed in DB or not
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow( () ->
        new ResourceNotFoundException("Employee" , "ID" , id) );

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        //save existing employee to database
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployeeByID(Long id) {
        //check whether  employee exist in DB or not
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("Employee" , "ID" , id) );
        employeeRepository.deleteById(id);
    }
}


