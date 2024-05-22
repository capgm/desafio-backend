package com.charles.desafiobackend.exception;

public class UsernameUniqueViolationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 698344513612433118L;

	public UsernameUniqueViolationException(String message) {
		super(message);
	}
}
