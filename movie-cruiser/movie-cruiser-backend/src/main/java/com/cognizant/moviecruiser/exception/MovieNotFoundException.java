package com.cognizant.moviecruiser.exception;
/**
 * @author Navya Surendran
 *
 */
public class MovieNotFoundException extends Exception {

	private String message;

	public MovieNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MovieNotFoundException [message=" + message + "]";
	}
}
