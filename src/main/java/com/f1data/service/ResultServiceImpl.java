package com.f1data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.f1data.domain.RaceTable;
import com.f1data.domain.Result;

@Service
public class ResultServiceImpl implements ResultService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private RaceTableService raceTableService;
	
	@Override
	public List<Result> findResultsBySeasonAndRound(int season, int round) {
		RaceTable raceTable = mongoTemplate.findOne(new Query(Criteria
				.where("season").is(season)
				.and("round").is(round)), RaceTable.class);
		return raceTable.getRaces().get(0).getResults();
	}
	
	@Override
	public Result findResultBySeasonAndRoundAndDriver(int season, int round, String driverId) {
		RaceTable raceTable = mongoTemplate.findOne(new Query(Criteria
				.where("season").is(season)
				.and("round").is(round)), RaceTable.class);
		return raceTable.getRaces().get(0).getResults().stream()
			.filter(r -> driverId.equalsIgnoreCase(r.getDriver().getDriverId()))
			.findFirst()
			.orElse(null);
	}
	
	@Override
	public Result addDriverResult(int season, int round, Result result) {
		RaceTable raceTable = raceTableService.findBySeasonAndRound(season, round);
		if (raceTable != null) {
			raceTable.getRaces().get(0).getResults().add(result);
			raceTableService.save(raceTable);
		}
		return result;
	}
	
	@Override
	public Result updateDriverResult(int season, int round, String driverId, Result result) {
		RaceTable raceTable = raceTableService.findBySeasonAndRound(season, round);
		if (raceTable != null) {
			removeDriverResult(driverId, raceTable);
			raceTable.getRaces().get(0).getResults().add(result);
			raceTableService.save(raceTable);
		}
		return result;
	}
	
	@Override
	public void removeDriverResult(int season, int round, String driverId) {
		RaceTable raceTable = raceTableService.findBySeasonAndRound(season, round);
		if (raceTable != null) {
			removeDriverResult(driverId, raceTable);
			raceTableService.save(raceTable);
		}
	}
	
	private void removeDriverResult(String driverId, RaceTable raceTable) {
		raceTable.getRaces().get(0).getResults().removeIf(r -> r.getDriver().getDriverId().equalsIgnoreCase(driverId));
	}
}
