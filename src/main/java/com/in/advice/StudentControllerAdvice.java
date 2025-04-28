package com.in.advice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.in.exception.StudentErrorResponse;
import com.in.exception.StudentKeyNotFoundException;
import com.in.exception.StudentNotFoundException;

@RestControllerAdvice
public class StudentControllerAdvice {   
	
	@ExceptionHandler(value = StudentNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<StudentErrorResponse> studentNotFound(StudentNotFoundException ex){
		
		StudentErrorResponse response=new StudentErrorResponse();
		response.setMessage(ex.getMessage());
		response.setErrorStatus(HttpStatus.NOT_FOUND.value());
		response.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<StudentErrorResponse> studentInternalServerError(Exception ex){
		
		StudentErrorResponse response=new StudentErrorResponse();
		response.setMessage(ex.getMessage());
		response.setErrorStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
		
		//return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		//Due to admin Server UI Issue added this below code.
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		           .contentType(MediaType.APPLICATION_JSON)
		           .body(response);
	}
	
	@ExceptionHandler(value = StudentKeyNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<StudentErrorResponse> studentKeyNotFound(StudentKeyNotFoundException ex){
		
		StudentErrorResponse response=new StudentErrorResponse();
		response.setMessage(ex.getMessage());
		response.setErrorStatus(HttpStatus.BAD_REQUEST.value());
		response.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}

}
