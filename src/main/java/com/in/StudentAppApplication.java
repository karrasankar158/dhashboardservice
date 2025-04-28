package com.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class StudentAppApplication {         

	public static void main(String[] args) {    
		//Disable Auto Restart
		//System.setProperty("spring.devtools.restart.enabled","false");
		
		SpringApplication.run(StudentAppApplication.class, args);
	}

}
