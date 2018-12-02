package com.f1data.service;

import java.util.List;

import com.f1data.domain.ConstructorChampionship;
import com.f1data.domain.DriverChampionship;
import com.f1data.domain.Result;

public interface ResultService {
	
	List<Result> findResultsBySeasonAndRound(int season, int round);

	Result findResultBySeasonAndRoundAndDriver(int season, int round, String driverId);

	Result addDriverResult(int season, int round, Result result) throws Exception;

	Result updateDriverResult(int season, int round, String driverId, Result result) throws Exception;

	void removeDriverResult(int season, int round, String driverId);
	
	List<DriverChampionship> getDriveChampionship(int season);
	
	List<ConstructorChampionship> getConstructorChampionship(int season);
	
}
