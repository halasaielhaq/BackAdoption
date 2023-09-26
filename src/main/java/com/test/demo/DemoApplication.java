package com.test.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
@ComponentScan("com.test.demo")

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}



	/*@Bean
	CommandLineRunner initDatabaseMySQL(UserRepository userRepository) {
		return args -> {
			userRepository.save(new user("John", "Doe", "john.doe@example.com", "password123"));
			userRepository.save(new user("Hala", "Saielhaq", "hala@123.com", "hahaha"));
			userRepository.save(new user("marwa", "ouaqar", "hala@123.com", "hahaha"));
		};
	}*/
}