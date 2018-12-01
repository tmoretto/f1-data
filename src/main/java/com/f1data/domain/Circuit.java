package com.f1data.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Circuit implements Serializable {

	private static final long serialVersionUID = 6000470982281223606L;
	
	private String circuitId;
	private String circuitName;
	
	public String getCircuitId() {
		return circuitId;
	}
	public void setCircuitId(String circuitId) {
		this.circuitId = circuitId;
	}
	public String getCircuitName() {
		return circuitName;
	}
	public void setCircuitName(String circuitName) {
		this.circuitName = circuitName;
	}
	
	
	
}
