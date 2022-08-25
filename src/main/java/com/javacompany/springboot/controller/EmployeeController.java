package com.javacompany.springboot.controller;


import com.javacompany.springboot.model.Employee;
import com.javacompany.springboot.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**  @RESTController is a convenient annotation that combines @Controller and @ResponseBody ,which eliminates
 * the need to annotate every request handling method of the controller class with @ResponseBody annotation
 */

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        super();
        this.employeeService=employeeService;
    }

    //build create employee REST API
    // using responseEntity class because we can provide complete response for this class, and we can add the status
    @PostMapping   /** @RequestBody annotation allows us to retrieve the request's body and automatically convert it to java Object*/
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {

     return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // build get all employees REST API
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //build get employee by id REST API
    // http://localhost:8080/api/employess/1
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long employeeId){

        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    //build update employee REST API
    // http://localhost:8080/api/employess/1
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable ("id") long id
                                      ,@RequestBody Employee employee){

        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
    }

    //build delete employee REST API
    //http:localhost:8080/api/employees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id){
        //delete employee from DB
        employeeService.deleteEmployeeByID(id);
        return new ResponseEntity<String>("Employee deleted successfully" ,HttpStatus.OK);
    }

}
