package com.f1data.service;

import java.util.List;

import com.f1data.domain.Constructor;

public interface ConstructorService {

	List<Constructor> findAll();
	
	Constructor findByName(String name);
	
}
