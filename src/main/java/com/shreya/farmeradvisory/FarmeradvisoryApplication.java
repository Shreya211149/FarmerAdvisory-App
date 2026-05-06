package com.shreya.farmeradvisory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableScheduling
public class FarmeradvisoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmeradvisoryApplication.class, args);
	}


}
