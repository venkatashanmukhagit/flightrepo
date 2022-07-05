package com.example.priceManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceManagementController {
	
	@GetMapping(path ="/getPrice")
	public Double getPrice() {
		return 100.00;
	}

}
