package com.zyter.thermal.exception;

public class ThermalException extends Exception {
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public ThermalException() {
		super();
	}

	public ThermalException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}