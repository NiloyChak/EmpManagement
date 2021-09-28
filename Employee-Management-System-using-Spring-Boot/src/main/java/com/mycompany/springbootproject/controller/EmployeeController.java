package com.mycompany.springbootproject.controller;

import com.mycompany.springbootproject.model.Employee;
import com.mycompany.springbootproject.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//RESTful APIs
@Slf4j // It offers a generic API making the logging independent of the actual implementation.
@Controller // allows us to auto-detect implementation classes through the classpath scanning.
@ResponseBody // tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object.
@RequestMapping("employees") // the annotation is used to map web requests to Spring Controller methods.
public class EmployeeController {

    @Autowired // Enabling annotation injection, we can use autowiring on properties, setters, and constructors.
    private EmployeeService employeeService;

    @GetMapping("/getall") // annotation that acts as a shortcut for @RequestMapping.
    @ApiOperation("Fetch All Employee Records") // annotation to describe the endpoint and its response type
    public List<Employee> getEmployees() {
        log.info("INSIDE getEmployees!!");
        return employeeService.getAll();
    }

    @ApiOperation("Save a New Employee Record!") // annotation to describe the endpoint and its response type
    @PostMapping("/create") // Annotation for mapping HTTP POST requests onto specific handler methods.
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        log.info("Inside Employee %s", employee);
        Employee cust = employeeService.addEmployee(employee);
        return new ResponseEntity<>(cust, HttpStatus.CREATED);
    }

    @ApiOperation("Get A Employee By ID") // annotation to describe the endpoint and its response type
    @GetMapping("/getbyid/{id}") // annotation that acts as a shortcut for @RequestMapping.
    public Employee fetchById(@PathVariable int id) {
        return employeeService.fetchById(id);
    }

    @ApiOperation("Update An Existing Employee") // annotation to describe the endpoint and its response type
    @PutMapping("/update/{id}") // Annotation for mapping HTTP POST requests onto specific handler methods.
    public void update(@PathVariable int id,@Valid @RequestBody Employee employee){
        log.info("Updating a employee!!");
        employeeService.udpateEmployee(id, employee);
    }

    @ApiOperation("Delete A Employee") // annotation to describe the endpoint and its response type
    @DeleteMapping("/delete/{id}") // annotation maps HTTP DELETE requests onto specific handler methods
    public ResponseEntity<Void> delete(@PathVariable int id){
        log.info("Deleting a employee with id %d", id);
        employeeService.deleteEmployee(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


