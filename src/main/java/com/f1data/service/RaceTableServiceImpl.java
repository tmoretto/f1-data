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
public class RaceTableServiceImpl implements RaceTableService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<RaceTable> findAll() {
		return mongoTemplate.findAll(RaceTable.class);
	}

	public RaceTable findBySeasonAndRound(int season, int round) {
		return mongoTemplate.findOne(new Query(Criteria
				.where("season").is(season)
				.and("round").is(round)), RaceTable.class);
	}

	private RaceTable save(RaceTable race) {
		return mongoTemplate.save(race);
	}

	public void addResult(RaceTable race, Result result) {
		RaceTable raceTable = this.findBySeasonAndRound(race.getSeason(), race.getRound());
		if (raceTable != null) {
			raceTable.getRaces().get(0).getResults().add(result);
			this.save(raceTable);
		}
	
	}
	
	public void disqualifyResult(RaceTable race, String driverId) {
		RaceTable raceTable = this.findBySeasonAndRound(race.getSeason(), race.getRound());
		if (raceTable != null) {
			raceTable.getRaces().get(0).getResults().removeIf(r -> r.getDriver().getDriverId().equalsIgnoreCase(driverId));
			this.save(raceTable);
		}
	
	}
	
	public void saveNewRoundIfApplicable(RaceTable race) {
		if (race == null) {
			return;
		}
		
		RaceTable raceTable = this.findBySeasonAndRound(race.getSeason(), race.getRound());
		if (raceTable == null) {
			this.save(race);
		}

	}

}
