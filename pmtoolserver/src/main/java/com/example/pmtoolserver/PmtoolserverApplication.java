package com.example.pmtoolserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class PmtoolserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmtoolserverApplication.class, args);
	}

}
