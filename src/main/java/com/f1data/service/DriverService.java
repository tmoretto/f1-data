package com.f1data.service;

import java.util.List;

import com.f1data.domain.Driver;

public interface DriverService {

	List<Driver> findAll();
	
	Driver findById(String driverId);
	
}
