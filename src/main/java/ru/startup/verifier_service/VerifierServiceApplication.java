package ru.startup.verifier_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class VerifierServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerifierServiceApplication.class, args);
	}

}
