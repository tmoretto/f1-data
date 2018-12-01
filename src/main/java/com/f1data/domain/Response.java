package com.f1data.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response implements Serializable {

	private static final long serialVersionUID = 2837674652581091328L;

	@JsonProperty("MRData")
	private MRData mrData;

	public MRData getMrData() {
		return mrData;
	}

	public void setMrData(MRData mrData) {
		this.mrData = mrData;
	}

}
