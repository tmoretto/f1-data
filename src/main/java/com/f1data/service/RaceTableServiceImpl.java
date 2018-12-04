package com.f1data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.f1data.domain.RaceTable;
import com.f1data.exception.RaceTableNotFoundException;

@Service
public class RaceTableServiceImpl implements RaceTableService {

	private static final String ROUND = "round";
	private static final String SEASON = "season";
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<RaceTable> findAll() {
		return mongoTemplate.findAll(RaceTable.class);
	}
	
	@Override
	public List<RaceTable> findBySeason(int season) {
		List<RaceTable> races = mongoTemplate.find(new Query(Criteria
				.where(SEASON).is(season)), RaceTable.class);
		if (races == null || races.isEmpty()) {
			throw new RaceTableNotFoundException("Races table not found for season: " + season);
		}
		return races;
	}
	
	@Override
	public RaceTable findBySeasonAndRound(int season, int round) {
		RaceTable raceTable = mongoTemplate.findOne(new Query(Criteria
				.where(SEASON).is(season)
				.and(ROUND).is(round)), RaceTable.class);		
		if (raceTable == null) {
			throw new RaceTableNotFoundException("Race table not found: " + new RaceTable(season, round));
		}
		return raceTable;
	}
	
	@Override
	public RaceTable save(RaceTable race) {
		return mongoTemplate.save(race);
	}

	@Override
	public void addNewRoundIfApplicable(RaceTable race) {
		if (race == null) {
			return;
		}
		
		RaceTable raceTable = mongoTemplate.findOne(new Query(Criteria
				.where(SEASON).is(race.getSeason())
				.and(ROUND).is(race.getRound())), RaceTable.class);
		if (raceTable == null) {
			this.save(race);
		}
	}

	@Override
	public void removeSeasonRound(int season, int round) {
		mongoTemplate.remove(new Query(Criteria
				.where(SEASON).is(season)
				.and(ROUND).is(round)), RaceTable.class);
	}

}
