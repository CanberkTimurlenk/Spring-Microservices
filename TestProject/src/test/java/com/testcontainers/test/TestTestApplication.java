package com.testcontainers.test;

import org.springframework.boot.SpringApplication;

public class TestTestApplication {

	public static void main(String[] args) {
		SpringApplication.from(TestApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
