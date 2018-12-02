package com.f1data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.f1data.domain.Constructor;

@Service
public class ConstructorServiceImpl implements ConstructorService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<Constructor> findAll() {
		return mongoTemplate.findAll(Constructor.class);
	}

	@Override
	public Constructor findByName(String name) {
		return mongoTemplate.findOne(new Query(Criteria
				.where("name").is(name)), Constructor.class);
	}

}
