package com.f1data.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MRData implements Serializable {
	
	private static final long serialVersionUID = -2138640587273369781L;
	
	@JsonProperty("series")
	private String series;
	
	@JsonProperty("RaceTable")
	private RaceTable raceTable;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public RaceTable getRaceTable() {
		return raceTable;
	}

	public void setRaceTable(RaceTable raceTable) {
		this.raceTable = raceTable;
	}
	

}
