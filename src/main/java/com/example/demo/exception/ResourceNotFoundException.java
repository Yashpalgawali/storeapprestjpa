package com.example.demo.exception;



public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11213L;

	public ResourceNotFoundException(String resourceName,String fieldName,String fieldValue) {
		super(String.format("%s not found with the given input data %s : '%s'",resourceName,fieldName,fieldValue));
	}

	
}
