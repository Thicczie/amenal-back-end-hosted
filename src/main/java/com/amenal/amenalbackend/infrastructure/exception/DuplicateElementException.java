package com.amenal.amenalbackend.infrastructure.exception;

public class DuplicateElementException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DuplicateElementException(String message) {
		super(message);
	}

}
