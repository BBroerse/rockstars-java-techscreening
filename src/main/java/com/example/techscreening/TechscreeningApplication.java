package com.example.techscreening;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TechscreeningApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechscreeningApplication.class, args);
	}


	// TODO: implement: https://javahowtos.com/guides/107-spring/376-how-to-seed-the-database-in-spring-boot-project.html

	// import json file
	// read file name and store in migrations table
	// loop over records
	// insert all data
}
