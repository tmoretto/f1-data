package com.f1data.service;

import java.util.List;

import com.f1data.domain.RaceTable;
import com.f1data.domain.Result;

public interface RaceTableService {

	List<RaceTable> findAll();

	RaceTable findBySeasonAndRound(int season, int round);

	void saveNewRoundIfApplicable(RaceTable race);

	void addResult(RaceTable race, Result result);

	void disqualifyResult(RaceTable raceTable, String driverId);

}
