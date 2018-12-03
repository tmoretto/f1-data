package com.f1data.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f1data.domain.Constructor;
import com.f1data.domain.ConstructorChampionship;
import com.f1data.domain.Driver;
import com.f1data.domain.DriverChampionship;
import com.f1data.domain.RaceTable;
import com.f1data.domain.Result;
import com.f1data.exception.F1DataException;
import com.f1data.exception.ResultNotFoundException;

@Service
public class ResultServiceImpl implements ResultService {
	
	@Autowired
	private RaceTableService raceTableService;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private ConstructorService constructorService;
	
	@Override
	public List<Result> findResultsBySeasonAndRound(int season, int round) {
		return raceTableService.findBySeasonAndRound(season, round).getRaces().get(0).getResults();
	}
	
	@Override
	public Result findResultBySeasonAndRoundAndDriver(int season, int round, String driverId) {
		Result result = raceTableService.findBySeasonAndRound(season, round)
		.getRaces().get(0).getResults().stream()
		.filter(r -> driverId.equalsIgnoreCase(r.getDriver().getDriverId()))
		.findFirst()
		.orElse(null);
		if (result == null) {
			throw new ResultNotFoundException("Result not found for driver: " + driverId);
		}
		return result;
	}
	
	@Override
	public Result addDriverResult(int season, int round, Result result) throws Exception {
		if (findResultBySeasonAndRoundAndDriver(season, round, result.getDriver().getDriverId()) != null) {
			throw new F1DataException("Driver already in the race result: " + new RaceTable(season, round) + " " + result.getDriver());
		}
		RaceTable raceTable = raceTableService.findBySeasonAndRound(season, round);
		raceTable.getRaces().get(0).getResults().add(result);
		raceTableService.save(raceTable);
		return result;
	}
	
	@Override
	public Result updateDriverResult(int season, int round, String driverId, Result result) throws Exception {
		if (findResultBySeasonAndRoundAndDriver(season, round, driverId) == null) {
			throw new F1DataException("Driver not found in the race result: " + new RaceTable(season, round));
		}
		RaceTable raceTable = raceTableService.findBySeasonAndRound(season, round);
		removeDriverResult(driverId, raceTable);
		raceTable.getRaces().get(0).getResults().add(result);
		raceTableService.save(raceTable);
		return result;
	}
	
	@Override
	public void removeDriverResult(int season, int round, String driverId) {
		RaceTable raceTable = raceTableService.findBySeasonAndRound(season, round);
		removeDriverResult(driverId, raceTable);
		raceTableService.save(raceTable);
	}
	
	public List<DriverChampionship> getDriveChampionship(int season) {
		List<DriverChampionship> driveChampionship = new ArrayList<>();
		List<RaceTable> raceTables = raceTableService.findBySeason(season);
		List<Driver> drivers = driverService.findAll();

		for (Driver driver : drivers) {
			DriverChampionship dv = new DriverChampionship(driver.getDriverId());
			for (RaceTable raceTable : raceTables) {
				dv.addPoints(raceTable.getRaces().get(0).getResults().stream()
						.filter(r -> driver.getDriverId().equalsIgnoreCase(r.getDriver().getDriverId())).findFirst()
						.orElse(null));
			}
			driveChampionship.add(dv);
		}
		driveChampionship.sort(Comparator.comparing(DriverChampionship::getTotalPoints).reversed());
		return driveChampionship;
	}
	
	public List<ConstructorChampionship> getConstructorChampionship(int season) {
		List<ConstructorChampionship> constructorChampionship = new ArrayList<>();
		List<RaceTable> raceTables = raceTableService.findBySeason(season);
		List<Constructor> constructors = constructorService.findAll();

		for (Constructor constructor : constructors) {
			ConstructorChampionship cc = new ConstructorChampionship(constructor.getName());
			for (RaceTable raceTable : raceTables) {
				cc.addPoints(raceTable.getRaces().get(0).getResults().stream()
						.filter(r -> constructor.getName().equalsIgnoreCase(r.getConstructor().getName())).collect(Collectors.toList()));
			}
			constructorChampionship.add(cc);
		}
		constructorChampionship.sort(Comparator.comparing(ConstructorChampionship::getTotalPoints).reversed());
		return constructorChampionship;
	}
	
	private void removeDriverResult(String driverId, RaceTable raceTable) {
		raceTable.getRaces().get(0).getResults().removeIf(r -> r.getDriver().getDriverId().equalsIgnoreCase(driverId));
	}
	
}
