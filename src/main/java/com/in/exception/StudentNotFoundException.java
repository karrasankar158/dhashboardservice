package com.in.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class StudentNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;
}
