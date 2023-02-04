package com.poc.tutorials.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("starting services");
		SpringApplication.run(DemoApplication.class, args);
	}

}
