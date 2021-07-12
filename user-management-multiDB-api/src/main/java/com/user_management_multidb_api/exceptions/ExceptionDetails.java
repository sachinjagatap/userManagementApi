package com.user_management_multidb_api.exceptions;

import java.util.List;

public class ExceptionDetails {

	private String dateTime;
	private String message;
	private List<String> errorsDetails;

	public ExceptionDetails(String dateTime, String message, List<String> errors) {
		super();
		this.dateTime = dateTime;
		this.message = message;
		this.errorsDetails = errors;
	}

	public ExceptionDetails() {
		super();
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrorsDetails() {
		return errorsDetails;
	}

	public void setErrorsDetails(List<String> errorsDetails) {
		this.errorsDetails = errorsDetails;
	}
	
	

}

