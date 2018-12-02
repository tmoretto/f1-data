package com.f1data.dto;

import java.io.Serializable;

import com.f1data.domain.Result;

public class ResultDTO implements Serializable {

	private static final long serialVersionUID = 8064559707850654270L;

	private Integer number;
	private Integer position;
	private Integer points;
	private Integer grid;
	private Integer laps;
	private String status;
	
	private String fastestLap;
	private String driver;
	private String constructor;

	private Integer season;
	private Integer round;

	public ResultDTO() {
		super();
	}

	public ResultDTO(Result result, Integer season, Integer round) {
		this.number = result.getNumber();
		this.position = result.getPosition();
		this.points = result.getPoints();
		this.grid = result.getGrid();
		this.laps = result.getLaps();
		this.status = result.getStatus();
		
		this.fastestLap = result.getFastestLap().getRank();
		this.driver = result.getDriver().getDriverId();
		this.constructor = result.getConstructor().getName();
		
		this.season = season;
		this.round = round;
	}
	
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

	public String getFastestLap() {
		return fastestLap;
	}

	public void setFastestLap(String fastestLap) {
		this.fastestLap = fastestLap;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getConstructor() {
		return constructor;
	}

	public void setConstructor(String constructor) {
		this.constructor = constructor;
	}

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
	
	

}
