package com.example.exception;

public class UserNotVerifiedException extends Exception {
	private Boolean newEmailSent;

	public UserNotVerifiedException(Boolean newEmailSent) {
		super();
		this.newEmailSent = newEmailSent;
	}

	public Boolean isNewEmailSent() {
		return newEmailSent;
	}

	public void setNewEmailSent(Boolean newEmailSent) {
		this.newEmailSent = newEmailSent;
	}
	
}
