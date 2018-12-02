package com.f1data.service;

import java.util.List;

import com.f1data.domain.Circuit;
import com.f1data.domain.Race;
import com.f1data.domain.Result;

public interface RaceService {

	Race findRace(int season, int round);

	Circuit findRaceCircuit(int season, int round);

	Result findRaceWinner(int season, int round);

	List<Result> findRacePodium(int season, int round);

	Result findRaceBestDriver(int season, int round);

	Result findRaceWorstDriver(int season, int round);

	List<Result> findRaceProblems(int season, int round);

}
