package com.f1data.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.f1data.domain.RaceTable;
import com.f1data.domain.Result;
import com.f1data.service.RaceTableService;

@RestController
@RequestMapping("/seasons")
public class SeasonController {

	@Autowired
	private RaceTableService service;

//	@GetMapping("/{season}/{round}/results")
//	public ResponseEntity<RaceTable> findRaceTable(@PathVariable int season, @PathVariable int round) {
//		RaceTable race = service.findBySeasonAndRound(season, round);
//		return new ResponseEntity<RaceTable>(race, HttpStatus.OK);
//	}
//	
//	@PostMapping("/{season}/{round}/results")
//	public ResponseEntity<RaceTable> addRaceTable(@RequestBody Result result, @PathVariable int season, @PathVariable int round) {
//		service.addResult(new RaceTable(season, round), result);
//		return new ResponseEntity<RaceTable>(new RaceTable(season, round), HttpStatus.OK);
//	}
//
//	@PutMapping("/{season}/{round}/resuls/{driverId}")
//	public ResponseEntity<RaceTable> updateResult(@RequestBody Result result, @PathVariable int season, @PathVariable int round, @PathVariable String driverId) {
//		service.updateResult(new RaceTable(season, round), driverId, result);
//		return new ResponseEntity<RaceTable>(new RaceTable(season, round), HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/{season}/{round}/disqualify/{driverId}")
//	public ResponseEntity<Void> disqualifyResult(@PathVariable int season, @PathVariable int round, @PathVariable String driverId) {
//		service.disqualifyResult(new RaceTable(season, round), driverId);
//		return ResponseEntity.noContent().build();
//	}

	
	
	@GetMapping("/{season}/rounds/{round}/championship/drivers")
	public ResponseEntity<RaceTable> getDriversChampionshipTable(@PathVariable int season, @PathVariable int round) {
		//RaceTable race = service.findBySeasonAndRound(season, round);
		return new ResponseEntity<RaceTable>(new RaceTable(season, round), HttpStatus.OK);
	}

	@GetMapping("/{season}/rounds/{round}/championship/constructors")
	public ResponseEntity<RaceTable> getConstructorChampionshipTable(@PathVariable int season, @PathVariable int round) {
		//RaceTable race = service.findBySeasonAndRound(season, round);
		return new ResponseEntity<RaceTable>(new RaceTable(season, round), HttpStatus.OK);
	}
	
}
