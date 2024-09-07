package com.mindhub.user_service;

import com.mindhub.user_service.models.UserEntity;
import com.mindhub.user_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(UserRepository userRepository){
		return args -> {
			UserEntity user = new UserEntity("Gabriel","gabrielturizo13@gmail.com", passwordEncoder.encode("1234"));
			userRepository.save(user).subscribe();
		};
	}
}
