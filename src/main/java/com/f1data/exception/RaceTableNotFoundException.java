package com.f1data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RaceTableNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -961944592501357432L;

	public RaceTableNotFoundException(String exception) {
		super(exception);
	}

}
