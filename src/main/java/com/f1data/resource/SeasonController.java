package com.f1data.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.f1data.domain.RaceTable;
import com.f1data.domain.Result;
import com.f1data.service.RaceTableService;

@RestController
@RequestMapping("/season")
public class SeasonController {

	@Autowired
	private RaceTableService service;

	@GetMapping("/{season}/{round}/resuls")
	public ResponseEntity<RaceTable> findRaceTable(@PathVariable int season, @PathVariable int round) {
		RaceTable race = service.findBySeasonAndRound(season, round);
		return new ResponseEntity<RaceTable>(race, HttpStatus.OK);
	}
	
	@PostMapping("/{season}/{round}/resuls")
	public ResponseEntity<RaceTable> addRaceTable(@RequestBody Result result, @PathVariable int season, @PathVariable int round) {
		service.addResult(new RaceTable(season, round), result);
		return new ResponseEntity<RaceTable>(new RaceTable(season, round), HttpStatus.OK);
	}

	@DeleteMapping("/{season}/{round}/disqualify/{driverId}")
	public ResponseEntity<RaceTable> disqualifyResult(@PathVariable int season, @PathVariable int round, @PathVariable String driverId) {
		service.disqualifyResult(new RaceTable(season, round), driverId);
		return new ResponseEntity<RaceTable>(new RaceTable(season, round), HttpStatus.OK);
	}
}
