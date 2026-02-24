package com.nip.public_issue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;



@SpringBootApplication
@EnableScheduling
public class NipApplication {

	public static void main(String[] args) {
		SpringApplication.run(NipApplication.class, args);
	}

}
