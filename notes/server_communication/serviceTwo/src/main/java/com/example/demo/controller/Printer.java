package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path="/Printer")
public class Printer {

	@GetMapping(produces="application/json")
	public String print() {
		RestTemplate rest = new RestTemplate();
		String resourceUrl = "http://localhost:8080/HelloWorld";
		System.out.println("resourceUrl was called");
		ResponseEntity<String> response = rest.getForEntity(resourceUrl, String.class);
		return response.getBody();
	}
	
}
