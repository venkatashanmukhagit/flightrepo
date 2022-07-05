package com.example.demo.controller;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.FlightDetails;
import com.example.demo.service.IFlightService;

@RestController
public class FlightController {

	@Autowired
	IFlightService flightService;
	
	@Autowired
	RestTemplate restTemplate;

	@GetMapping(path = "/hello")
	public String helloWorld() {
		return "Hello World";
	}

	@PostMapping(path = "/addflight")
	public FlightDetails addFlight(@RequestBody FlightDetails flightDetails) {
		return flightService.save(flightDetails);
	}

	@DeleteMapping(path = "/removeflight/{id}")
	public String deleteFlight(@PathVariable Integer id) {
		try {
			flightService.delete(id);
			System.out.println("Deleted Id is :" + id);
			return "Deleted Row";
		} catch (Exception e) {
			System.out.println("Exception is :" + e.getMessage());
			return e.getMessage();

		}
	}

	@PutMapping(path = "/updateflight/{id}")
	public Integer updateData(@PathVariable Integer id, @Valid @RequestBody FlightDetails flightDetails){
		return flightService.update(id, flightDetails);
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleException(MethodArgumentNotValidException exception) {
		Map<String, String> messages = new HashMap<>();
		exception.getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = ((FieldError) error).getDefaultMessage();
			messages.put(fieldName, errorMessage);

		});
		return messages;

	}

	/**
	 * To test microservice architechture.
	 * @return
	 */
	@GetMapping(path = "/getpricing")
	public Double getPrice() {
//		HttpHeaders headers = new HttpHeaders();
//		HttpEntity<String> entity = new HttpEntity<String>(headers);
//		return restTemplate.exchange("http://localhost:8082/getPrice", HttpMethod.GET, entity, Double.class).getBody();
		
		return restTemplate.getForObject("http://localhost:8082/getPrice", Double.class);
		
		
	}
}
