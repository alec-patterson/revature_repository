package micro;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/Chat")
public class ChatController {
	
	@PostMapping(consumes="application/json", produces="application/json")
	public ResponseEntity<Object> getEmployeeLogin(@RequestBody Map<String, Object> message) {
		System.out.println(message.get("message"));
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	@GetMapping(produces="application/json")
	public String test() {
		return "fuck";
	}
}