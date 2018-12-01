package com.f1data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f1data.domain.RaceTable;
import com.f1data.repository.RaceTableRepository;

@Service
public class RaceTableService {

	@Autowired
	private RaceTableRepository repository;

	public List<RaceTable> findAll() {
		return repository.findAll();
	}

}
