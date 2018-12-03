package com.f1data.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.f1data.domain.Result;
import com.f1data.service.ResultService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/result")
public class ResultController {

	@Autowired
	private ResultService service;

	@GetMapping
	@ApiOperation("Return the race results")
	public ResponseEntity<List<Result>> findResultsBySeasonAndRound(@RequestParam int season, @RequestParam int round) {
		List<Result> results = service.findResultsBySeasonAndRound(season, round);
		return ResponseEntity.ok().body(results);
	}
	
	@GetMapping("/drivers/{driverId}")
	@ApiOperation("Return a driver result")
	public ResponseEntity<Result> findResultBySeasonAndRoundAndDriver(@RequestParam int season, @RequestParam int round, @PathVariable String driverId) {
		Result driverResult = service.findResultBySeasonAndRoundAndDriver(season, round, driverId);
		return ResponseEntity.ok().body(driverResult);
	}

	@PostMapping
	@ApiOperation("Add a driver result to the race results")
	public ResponseEntity<Result> addDriverResult(@RequestBody Result result, @RequestParam int season, @RequestParam int round) throws Exception {
		Result driverResult = service.addDriverResult(season, round, result);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/drivers/{driverId}").buildAndExpand(driverResult.getDriver().getDriverId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/drivers/{driverId}")
	@ApiOperation("Modify a driver result to the race results")
	public ResponseEntity<Result> updateResult(@RequestBody Result result, @RequestParam int season, @RequestParam int round, @PathVariable String driverId) throws Exception {
		service.updateDriverResult(season, round, driverId, result);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/drivers/{driverId}")
	@ApiOperation("Exclude a driver result from the race results")
	public ResponseEntity<Void> removeRaceTable(@RequestParam int season, @RequestParam int round, @PathVariable String driverId) {
		service.removeDriverResult(season, round, driverId);
		return ResponseEntity.noContent().build();
	}

}
