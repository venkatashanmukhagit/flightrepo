package com.bookmanagement.utility;

public class ExceptionDetails {

	private String errorMessage;
	private String exceptionType;
	
	public ExceptionDetails() {}
	
	public ExceptionDetails(String errorMessage, String exceptionType) {
		super();
		this.errorMessage = errorMessage;
		this.exceptionType = exceptionType;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getExceptionType() {
		return exceptionType;
	}
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	
	
}
