package com.example.demo.manager;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeManager {

	public List<Employee> findAll();

	public Employee findById(int id);
	
	public List<Employee> findAll(int start, int count);
}
