package org.cluster.dev.controller;
import java.util.*;

import org.cluster.dev.model.Employee;
import org.cluster.dev.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	 @Autowired
	    private EmployeeService employeeService;
	 
//	    @RequestMapping("/")
//	    @ResponseBody
//	    public String welcome() {
//	        return "Welcome to RestTemplate Example.";
//	    }
	 
	    // URL:
	    // http://localhost:8080/SomeContextPath/employees
	    // http://localhost:8080/SomeContextPath/employees.xml
	    // http://localhost:8080/SomeContextPath/employees.json
	    @RequestMapping(value = "/employees", //
	            method = RequestMethod.GET, //
	            produces = { MediaType.APPLICATION_JSON_VALUE, //
	                    MediaType.APPLICATION_XML_VALUE })
	    @ResponseBody
	    public List<Employee> getEmployees() {
	        List<Employee> list = employeeService.getAllEmployees();
	        return list;
	    }
	 
	    // URL:
	    // http://localhost:8080/SomeContextPath/employee/{empNo}
	    // http://localhost:8080/SomeContextPath/employee/{empNo}.xml
	    // http://localhost:8080/SomeContextPath/employee/{empNo}.json
	    @RequestMapping(value = "/employee/{empNo}", //
	            method = RequestMethod.GET, //
	            produces = { MediaType.APPLICATION_JSON_VALUE, //
	                    MediaType.APPLICATION_XML_VALUE })
	    @ResponseBody
	    public Employee getEmployee(@PathVariable("empNo") String empNo) {
	        return employeeService.getEmployee(empNo);
	    }
	 
	    // URL:
	    // http://localhost:8080/SomeContextPath/employee
	    // http://localhost:8080/SomeContextPath/employee.xml
	    // http://localhost:8080/SomeContextPath/employee.json
	 
	    @RequestMapping(value = "/employee", //
	            method = RequestMethod.POST, //
	            produces = { MediaType.APPLICATION_JSON_VALUE, //
	                    MediaType.APPLICATION_XML_VALUE })
	    @ResponseBody
	    public Employee addEmployee(@RequestBody Employee emp) {
	 
	        System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());
	 
	        return employeeService.addEmployee(emp);
	    }
	 
	    // URL:
	    // http://localhost:8080/SomeContextPath/employee
	    // http://localhost:8080/SomeContextPath/employee.xml
	    // http://localhost:8080/SomeContextPath/employee.json
	    @RequestMapping(value = "/employee", //
	            method = RequestMethod.PUT, //
	            produces = { MediaType.APPLICATION_JSON_VALUE, //
	                    MediaType.APPLICATION_XML_VALUE })
	    @ResponseBody
	    public Employee updateEmployee(@RequestBody Employee emp) {
	 
	        System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());
	 
	        return employeeService.updateEmployee(emp);
	    }
	 
	    // URL:
	    // http://localhost:8080/SomeContextPath/employee/{empNo}
	    @RequestMapping(value = "/employee/{empNo}", //
	            method = RequestMethod.DELETE, //
	            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	    @ResponseBody
	    public void deleteEmployee(@PathVariable("empNo") String empNo) {
	 
	        System.out.println("(Service Side) Deleting employee: " + empNo);
	 
	        employeeService.deleteEmployee(empNo);
	    }
	 
	}

//ref: https://o7planning.org/en/11645/crud-restful-web-service-with-spring-boot-example

