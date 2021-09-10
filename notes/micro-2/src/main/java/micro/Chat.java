package micro;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class Chat {
	
	public static void sendChat() {
		RestTemplate rest = new RestTemplate();
		String sourceUrl = "http://localhost:8080/Chat";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Scanner scan = new Scanner(System.in);
		String input = "";
		while (input != "quit") {
			System.out.println("> ");
			input = scan.nextLine();
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("message", input);
			HttpEntity<Map> request = new HttpEntity<Map>(map, headers);
			ResponseEntity<String> response = rest.postForEntity(sourceUrl, request, String.class);
		}
	}
}
