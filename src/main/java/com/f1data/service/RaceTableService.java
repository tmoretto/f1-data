package com.f1data.service;

import java.util.List;

import com.f1data.domain.RaceTable;

public interface RaceTableService {

	List<RaceTable> findAll();

	List<RaceTable> findBySeason(int season);
	
	RaceTable findBySeasonAndRound(int season, int round);

	RaceTable save(RaceTable race);

	void addNewRoundIfApplicable(RaceTable race);

	
}
