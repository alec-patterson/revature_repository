package aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/Employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@GetMapping
	public String getEmployee() {
		service.create();
		return "employees";
	}
	
	@GetMapping(path="/{id}")
	public String getEmployeeById(@PathVariable int id) {
		service.findById();
		return "employee" + id;
	}
}
