package server.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringClientTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClientTwoApplication.class, args);
	}

}
