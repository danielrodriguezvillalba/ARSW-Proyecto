package applicationAPI;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import application.controllers.RouletteAPIController;

@SpringBootApplication
@ComponentScan(basePackages = {"application"})
public class RouletteAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouletteAPIController.class, args);
	}
}
