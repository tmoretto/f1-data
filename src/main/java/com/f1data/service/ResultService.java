package com.f1data.service;

import java.util.List;

import com.f1data.domain.Result;

public interface ResultService {
	
	List<Result> findResultsBySeasonAndRound(int season, int round);

	Result findResultBySeasonAndRoundAndDriver(int season, int round, String driverId);

	Result addDriverResult(int season, int round, Result result);

	Result updateDriverResult(int season, int round, String driverId, Result result);

	void removeDriverResult(int season, int round, String driverId);
	
}
