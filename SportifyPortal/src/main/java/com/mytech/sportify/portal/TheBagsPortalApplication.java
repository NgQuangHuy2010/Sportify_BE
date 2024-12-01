package com.mytech.sportify.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan({"com.mytech.thebags.portal", "com.mytech.thebags.service"})
public class TheBagsPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheBagsPortalApplication.class, args);
	}
}
