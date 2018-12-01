package com.f1data.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results implements Serializable {

	private static final long serialVersionUID = 887163390876820958L;

	private Integer number;
	private Integer position;
	private Integer points;
	private Integer grid;
	private Integer laps;
	private String status;
	
	@JsonProperty("FastestLap")
	private FastestLap fastestLap;
	@JsonProperty("Driver")
	private Driver driver;
	@JsonProperty("Constructor")
	private Constructor constructor;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getGrid() {
		return grid;
	}

	public void setGrid(Integer grid) {
		this.grid = grid;
	}

	public Integer getLaps() {
		return laps;
	}

	public void setLaps(Integer laps) {
		this.laps = laps;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public FastestLap getFastestLap() {
		return fastestLap;
	}

	public void setFastestLap(FastestLap fastestLap) {
		this.fastestLap = fastestLap;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Constructor getConstructor() {
		return constructor;
	}

	public void setConstructor(Constructor constructor) {
		this.constructor = constructor;
	}

}
