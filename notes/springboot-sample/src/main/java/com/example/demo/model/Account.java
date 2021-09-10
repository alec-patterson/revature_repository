package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Account {

	private String name;

	public Account(String name) {
		super();
		this.name = name;
	}
	
	public Account() {
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
