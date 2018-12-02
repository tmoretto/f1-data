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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.f1data.domain.RaceTable;
import com.f1data.domain.Result;
import com.f1data.service.RaceTableService;

@RestController
@RequestMapping("/race")
public class RaceController {

	@Autowired
	private RaceTableService service;

	@GetMapping
	public ResponseEntity<RaceTable> findRaceTable(@RequestParam int season, @RequestParam int round) {
		//RaceTable race = service.findBySeasonAndRound(season, round);
		return new ResponseEntity<RaceTable>(new RaceTable(season, round), HttpStatus.OK);
	}
	
	@GetMapping("/circuit")
	public ResponseEntity<RaceTable> findRaceCircuit(@RequestBody Result result, @RequestParam int season, @RequestParam int round) {
		//service.addResult(new RaceTable(season, round), result);
		return new ResponseEntity<RaceTable>(new RaceTable(season, round), HttpStatus.OK);
	}

	@GetMapping("/winner")
	public ResponseEntity<RaceTable> findRaceWinner(@RequestBody Result result, @RequestParam int season, @RequestParam int round) {
		//service.addResult(new RaceTable(season, round), result);
		return new ResponseEntity<RaceTable>(new RaceTable(season, round), HttpStatus.OK);
	}

	@GetMapping("/fastest")
	public ResponseEntity<RaceTable> findRaceFastestLap(@RequestBody Result result, @RequestParam int season, @RequestParam int round) {
		//service.addResult(new RaceTable(season, round), result);
		return new ResponseEntity<RaceTable>(new RaceTable(season, round), HttpStatus.OK);
	}

	@GetMapping("/best")
	public ResponseEntity<RaceTable> findRaceBestDriver(@RequestBody Result result, @RequestParam int season, @RequestParam int round) {
		//service.addResult(new RaceTable(season, round), result);
		return new ResponseEntity<RaceTable>(new RaceTable(season, round), HttpStatus.OK);
	}

	@GetMapping("/worst")
	public ResponseEntity<RaceTable> findRaceWorstDriver(@RequestBody Result result, @RequestParam int season, @RequestParam int round) {
		//service.addResult(new RaceTable(season, round), result);
		return new ResponseEntity<RaceTable>(new RaceTable(season, round), HttpStatus.OK);
	}
	
	@GetMapping("/problems")
	public ResponseEntity<RaceTable> findRaceCrashs(@RequestParam int season, @RequestParam int round) {
		//RaceTable race = service.findBySeasonAndRound(season, round);
		return new ResponseEntity<RaceTable>(new RaceTable(season, round), HttpStatus.OK);
	}

	
}
