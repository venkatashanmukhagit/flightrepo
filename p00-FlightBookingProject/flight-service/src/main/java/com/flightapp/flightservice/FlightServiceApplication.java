package com.flightapp.flightservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
public class FlightServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(FlightServiceApplication.class, args);
	}

}
