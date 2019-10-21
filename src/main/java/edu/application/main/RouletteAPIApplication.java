package edu.application.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.application"})
public class RouletteAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouletteAPIApplication.class,args);
	}


}
