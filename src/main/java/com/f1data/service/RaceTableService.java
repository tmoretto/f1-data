package com.f1data.service;

import java.util.List;

import com.f1data.domain.RaceTable;

public interface RaceTableService {

	List<RaceTable> findAll();

	RaceTable findBySeasonAndRound(int season, int round);

	void saveNewRoundIfApplicable(RaceTable race);

}
