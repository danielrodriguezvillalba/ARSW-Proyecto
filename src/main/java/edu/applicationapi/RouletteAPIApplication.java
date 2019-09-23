package edu.applicationapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import edu.application.controllers.RouletteAPIController;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.application"})
public class RouletteAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouletteAPIApplication.class, args);
	}
}
