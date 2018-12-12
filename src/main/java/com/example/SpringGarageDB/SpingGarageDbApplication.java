package com.example.SpringGarageDB;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class SpingGarageDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpingGarageDbApplication.class, args);
	}
}
