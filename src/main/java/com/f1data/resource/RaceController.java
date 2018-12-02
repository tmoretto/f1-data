package com.f1data.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.f1data.domain.Circuit;
import com.f1data.domain.Race;
import com.f1data.domain.Result;
import com.f1data.service.RaceService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/race")
@ApiOperation("Endpoints to find information about races")
public class RaceController {

	@Autowired
	private RaceService service;
	
	@GetMapping
	@ApiOperation("Return the race info from a season and round")
	public ResponseEntity<Race> findRace(@RequestParam int season, @RequestParam int round) {
		Race race = service.findRace(season, round);
		return ResponseEntity.ok().body(race);
	}
	
	@GetMapping("/circuit")
	@ApiOperation("Return the circuit info from a race")
	public ResponseEntity<Circuit> findRaceCircuit(@RequestParam int season, @RequestParam int round) {
		Circuit circuit = service.findRaceCircuit(season, round);
		return ResponseEntity.ok().body(circuit);
	}

	@GetMapping("/winner")
	@ApiOperation("Return the winner from a race")
	public ResponseEntity<Result> findRaceWinner(@RequestParam int season, @RequestParam int round) {
		Result winner = service.findRaceWinner(season, round);
		return ResponseEntity.ok().body(winner);
	}
	
	@GetMapping("/podium")
	@ApiOperation("Return the fisrt three from a race")
	public ResponseEntity<List<Result>> findRacePodium(@RequestParam int season, @RequestParam int round) {
		List<Result> racePodium = service.findRacePodium(season, round);
		return ResponseEntity.ok().body(racePodium);
	}

	@GetMapping("/best")
	@ApiOperation("Return the driver who had the best performance in terms of his final position compared with his grid position (Gain more positions)")
	public ResponseEntity<Result> findRaceBestDriver(@RequestParam int season, @RequestParam int round) {
		Result best = service.findRaceBestDriver(season, round);
		return ResponseEntity.ok().body(best);
	}

	@GetMapping("/worst")
	@ApiOperation("Return the driver who had the worst performance in terms of his final position compared with his grid position (Lost more positions)")
	public ResponseEntity<Result> findRaceWorstDriver(@RequestParam int season, @RequestParam int round) {
		Result worst = service.findRaceWorstDriver(season, round);
		return ResponseEntity.ok().body(worst);
	}
	
	@GetMapping("/problems")
	@ApiOperation("Return the drivers who didn't make it to the end (for any reasons)")
	public ResponseEntity<List<Result>> findRaceProblems(@RequestParam int season, @RequestParam int round) {
		List<Result> raceProblems = service.findRaceProblems(season, round);
		return ResponseEntity.ok().body(raceProblems);
	}

}
