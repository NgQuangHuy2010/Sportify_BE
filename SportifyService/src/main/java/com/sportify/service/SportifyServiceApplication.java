package com.sportify.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.sportify.service"})
public class SportifyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportifyServiceApplication.class, args);
	}

}
