package com.revature.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.manager.AccountManagerImpl;

@Configuration
public class AppConfig {

	@Bean
	public AccountManagerImpl getStudent() {
		return new AccountManagerImpl();
	}
	
}
