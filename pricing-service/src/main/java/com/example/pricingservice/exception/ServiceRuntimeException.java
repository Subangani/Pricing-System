package com.example.pricingservice.exception;

import lombok.Getter;

@SuppressWarnings("serial")
public class ServiceRuntimeException extends RuntimeException {

	@Getter
	private String code;


	public ServiceRuntimeException(String code, String message) {
		super(message);
		this.code = code;
	}
}
