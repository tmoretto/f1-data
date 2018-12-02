package com.f1data.domain;

import java.io.Serializable;

public class DriverChampionship implements Serializable {

	private static final long serialVersionUID = -3930464930972054363L;

	private String driverId;
	private Integer totalPoints;

	public DriverChampionship(String driverId) {
		super();
		this.driverId = driverId;
		this.totalPoints = 0;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	public void addPoints(Result result) {
		if (result != null) {
			this.totalPoints += result.getPoints();
		}
	}

}
