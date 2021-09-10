package com.example.demo.manager;

import java.util.List;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository
public class EmployeeManagerImpl implements EmployeeManager{

	@Autowired
	private EmployeeDao dao;
	
	public List<Employee> findAll() {
		return StreamSupport.stream(dao.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
	public List<Employee> findAll(int start, int count) {
		return StreamSupport.stream(dao.findAll(PageRequest.of(start, count)).spliterator(), false)
				.collect(Collectors.toList());
	}
	
	public Employee findById(int id) {
		return dao.findById(id).get();
	}
	
}
