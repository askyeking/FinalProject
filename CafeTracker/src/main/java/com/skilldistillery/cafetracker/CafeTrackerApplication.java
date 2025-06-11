package com.skilldistillery.cafetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CafeTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CafeTrackerApplication.class, args);
	}
	
	@Bean
	protected PasswordEncoder configurePasswordEncoder() {
	   return new BCryptPasswordEncoder();
	}

}
