package com.in.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentErrorResponse {
	private String message;
	private Integer errorStatus;
	private String timestamp;
}
