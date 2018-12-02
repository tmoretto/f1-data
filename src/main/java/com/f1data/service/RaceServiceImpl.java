package com.f1data.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f1data.domain.Circuit;
import com.f1data.domain.Race;
import com.f1data.domain.RaceTable;
import com.f1data.domain.Result;

@Service
public class RaceServiceImpl implements RaceService {
	
	@Autowired
	private RaceTableService raceTableService;
		
	@Override
	public Race findRace(int season, int round) {
		RaceTable raceTable = raceTableService.findBySeasonAndRound(season, round);
		return raceTable.getRaces().get(0);
	}

	@Override
	public Circuit findRaceCircuit(int season, int round) {
		RaceTable raceTable = raceTableService.findBySeasonAndRound(season, round);
		return raceTable.getRaces().get(0).getCircuit();
	}

	@Override
	public Result findRaceWinner(int season, int round) {
		RaceTable raceTable = raceTableService.findBySeasonAndRound(season, round);
		return raceTable.getRaces().get(0).getResults().stream()
			.min(Comparator.comparing(Result::getPosition))
			.get();
	}

	@Override
	public List<Result> findRacePodium(int season, int round) {
		RaceTable raceTable = raceTableService.findBySeasonAndRound(season, round);
		return raceTable.getRaces().get(0).getResults().stream()
			.sorted(Comparator.comparing(Result::getPosition))
			.limit(3).collect(Collectors.toList());
	}

	@Override
	public Result findRaceBestDriver(int season, int round) {
		RaceTable raceTable = raceTableService.findBySeasonAndRound(season, round);
		return raceTable.getRaces().get(0).getResults().stream()
			.max(Comparator.comparing(Result::getPerformance))
			.get();
	}

	@Override
	public Result findRaceWorstDriver(int season, int round) {
		RaceTable raceTable = raceTableService.findBySeasonAndRound(season, round);
		return raceTable.getRaces().get(0).getResults().stream()
			.min(Comparator.comparing(Result::getPerformance))
			.get();
	}

	@Override
	public List<Result> findRaceProblems(int season, int round) {
		RaceTable raceTable = raceTableService.findBySeasonAndRound(season, round);
		return raceTable.getRaces().get(0).getResults().stream()
				.filter(r -> !"Finished".equalsIgnoreCase(r.getStatus()))
				.collect(Collectors.toList());
	}

}
