package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/HelloWorld")
public class HelloWorld {

	@GetMapping(produces="applcation/json")
	public String getHello() {
		System.out.println("invoked");
		return "hello world from RestController /HelloWorld";
	}
	
}
