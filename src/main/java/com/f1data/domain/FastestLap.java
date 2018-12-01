package com.f1data.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FastestLap implements Serializable {

	private static final long serialVersionUID = -2516175119198245227L;

	private String rank;
	private String lap;

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getLap() {
		return lap;
	}

	public void setLap(String lap) {
		this.lap = lap;
	}

}
