package com.f1data.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RaceTable implements Serializable {

	private static final long serialVersionUID = -6293576080882356541L;

	private Integer season;
	private Integer round;

	@JsonProperty("Races")
	private List<Race> races;

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public List<Race> getRaces() {
		return races;
	}

	public void setRaces(List<Race> races) {
		this.races = races;
	}

	@Override
	public String toString() {
		return "RaceTable [season=" + season + ", round=" + round + "]";
	}

}
