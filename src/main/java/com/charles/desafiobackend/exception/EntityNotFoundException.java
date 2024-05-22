package com.charles.desafiobackend.exception;

public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7544672603773259594L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
