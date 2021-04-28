package com.springboot.rest.entity;

public class BaseExcepion extends Exception{
	 private Integer code;
	    private String message;
	    private String appMessage;

	    public BaseExcepion(Integer code, String message) {
	        this.code = code;
	        this.message = message;
	    }

	    public BaseExcepion(Integer code, String message, String appMessage) {
	        this.code = code;
	        this.message = message;
	        this.appMessage = appMessage;
	    }

	    public Integer getCode() {
	        return this.code;
	    }

	    public String getMessage() {
	        return this.message;
	    }

	    public String getAppMessage() {
	        return this.appMessage;
	    }
}
