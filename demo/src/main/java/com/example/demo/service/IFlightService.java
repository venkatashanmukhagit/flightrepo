package com.example.demo.service;

import com.example.demo.entity.FlightDetails;

public interface IFlightService {
	public FlightDetails save(FlightDetails flightDetails);

	public void delete(Integer id);

	public Integer update(Integer id,FlightDetails flightDetails);

}
