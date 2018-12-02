package com.f1data.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.f1data.domain.ConstructorChampionship;
import com.f1data.domain.DriverChampionship;
import com.f1data.service.RaceTableService;
import com.f1data.service.ResultService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/seasons")
public class SeasonController {

	@Autowired
	private ResultService resultService;
	
	@Autowired
	private RaceTableService raceTableService;

	@GetMapping("/{season}/championship/drivers")
	@ApiOperation("Return the drivers championship table (until this moment)")
	public ResponseEntity<List<DriverChampionship>> getDriversChampionshipTable(@PathVariable int season) {
		List<DriverChampionship> championship = resultService.getDriveChampionship(season);
		return ResponseEntity.ok().body(championship);
	}

	@GetMapping("/{season}/championship/constructors")
	@ApiOperation("Return the constructors championship table (until this moment)")
	public ResponseEntity<List<ConstructorChampionship>> getConstructorChampionshipTable(@PathVariable int season) {
		List<ConstructorChampionship> championship = resultService.getConstructorChampionship(season);
		return ResponseEntity.ok().body(championship);
	}
	
	@DeleteMapping("/{season}/rounds/{round}")
	@ApiOperation("Remove round from season")
	public ResponseEntity<Void> removeSeasonRound(@PathVariable int season, @PathVariable int round) {
		raceTableService.removeSeasonRound(season, round);
		return ResponseEntity.noContent().build();
	}
	
}
