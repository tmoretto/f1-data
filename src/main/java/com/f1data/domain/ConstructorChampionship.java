package com.f1data.domain;

import java.io.Serializable;
import java.util.List;

public class ConstructorChampionship implements Serializable {

	private static final long serialVersionUID = 6217830202171481620L;

	private String name;
	private Integer totalPoints;

	public ConstructorChampionship(String name) {
		super();
		this.name = name;
		this.totalPoints = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	public void addPoints(List<Result> results) {
		if (results != null && !results.isEmpty()) {
			for (Result r : results) {
				this.totalPoints += r.getPoints();				
			}
		}
	}

}
