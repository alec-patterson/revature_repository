package jwt.Util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/login")
public class HomeController {

	@PostMapping(consumes="application/json", produces="application/json")
	public ResponseEntity<Object> getEmployeeLogin(@RequestBody Map<String, Object> employee) {
		User user = new User();
		JwtUtility jj = new JwtUtility();
		user.setName("alec");
		user.setPassword("test");
		user.setName("alec patterson");
		String token = jj.generateToken(user);
		System.out.println(token);
		
		Map<String, Object> jwtToken = new HashMap<>();
		jwtToken.put("token", token);
		jwtToken.put("username", jj.getUsernameFromToken(token));
		
		return new ResponseEntity<>(jwtToken, HttpStatus.CREATED);
//		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	@PostMapping(path="/getname", consumes="application/json", produces="application/json")
	public ResponseEntity<Object> getEmployeeName(@RequestBody Map<String, Object> employee) {
		JwtUtility jj = new JwtUtility();
		boolean valid = jj.validateToken((String)employee.get("token"));
		//		String username = jj.getUsernameFromToken((String)employee.get("token"));
		System.out.println("here");
//		System.out.println(username);
		
		return new ResponseEntity<>(valid, HttpStatus.CREATED);
//		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}
}

