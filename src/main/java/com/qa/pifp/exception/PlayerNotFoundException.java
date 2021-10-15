package com.qa.pifp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No player found with that id")
public class PlayerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4233798419170783353L;

	public PlayerNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlayerNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PlayerNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PlayerNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PlayerNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
