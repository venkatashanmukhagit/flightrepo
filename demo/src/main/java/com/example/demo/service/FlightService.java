package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.FlightDetails;

@Service
public class FlightService implements IFlightService {

	@Autowired
	IFlightRepository repo;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public FlightDetails save(FlightDetails flightDetails) {
		return repo.save(flightDetails);

	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Integer update(Integer id, FlightDetails flightDetails) {
		FlightDetails fetchedFlightDetails = repo.getById(id);
		fetchedFlightDetails.setAirlineName(flightDetails.getAirlineName());
		fetchedFlightDetails.setFlightName(flightDetails.getFlightName());
		return repo.save(fetchedFlightDetails).getId();
	}

}
