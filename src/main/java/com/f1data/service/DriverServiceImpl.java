package com.f1data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.f1data.domain.Driver;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<Driver> findAll() {
		return mongoTemplate.findAll(Driver.class);
	}

	@Override
	public Driver findById(String driverId) {
		return mongoTemplate.findOne(new Query(Criteria
				.where("driverId").is(driverId)), Driver.class);
	}

}
