package com.example.backend;

import com.example.backend.service.MemoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	 @Bean
    CommandLineRunner init(MemoService memoService) {
        return args -> memoService.seed();
    }
}
