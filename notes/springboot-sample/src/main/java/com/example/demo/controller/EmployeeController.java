package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.manager.EmployeeManager;
import com.example.demo.model.Employee;
import javax.validation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

	@Autowired
	private EmployeeManager manager;
	
	@GetMapping(produces="application/json")
	public List<Employee> getEmployees() {
		return manager.findAll(); 
	}
	
	@GetMapping(path="/sort", produces="application/json")
	public List<Employee> getSortedEmployees()  {
		return manager.findAll(0, 5);
	}
	
	@GetMapping(path="/{id}", produces="application/json")
	public Employee getEmployee(@PathVariable int id)  {
		return manager.findById(id);
	}
	
	@PostMapping(consumes="application/json", produces="application/json") 
	public Employee setEmployee(@Valid @RequestBody Employee e) {
//		Employee em = new Employee(e.getRole());
		return e;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}

	
}
