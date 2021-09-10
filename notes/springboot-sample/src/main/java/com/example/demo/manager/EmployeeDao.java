package com.example.demo.manager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.model.Employee;

public interface EmployeeDao extends PagingAndSortingRepository<Employee, Integer>{

}

//CrudRepository<Employee, Integer>