package com.springboot.rest.entity;

public class CustomeException extends BaseExcepion{
	 

	private static final int code = 200;
	    private static final String appMessage = "Not Found";
	    private static final String message = "Not found";

	    public CustomeException(String message) {
			super(code, message);
			
		}
}
