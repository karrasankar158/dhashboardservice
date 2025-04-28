package com.in.advice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.in.exception.StudentErrorResponse;
import com.in.exception.StudentNotFoundException;

@SpringBootTest
public class StudentControllerAdviceTest {
	
	@InjectMocks
	private StudentControllerAdvice studentControllerAdvice;
	
	@Test
	public void testStudentNotFound() {
		String expected="student not found";
		//Actual method call
		ResponseEntity<StudentErrorResponse>  response=studentControllerAdvice.studentNotFound(
				new StudentNotFoundException(expected));
		
		//Validation results
		Assertions.assertNotNull(response);
		Assertions.assertEquals(expected,response.getBody().getMessage());
		Assertions.assertEquals(404,response.getBody().getErrorStatus());
		Assertions.assertNotNull(response.getBody().getTimestamp());
	}
	
	@Test
	public void testStudentInternalServerError() {
		//Actual method call
		ResponseEntity<StudentErrorResponse>  response=studentControllerAdvice.studentInternalServerError(
				new Exception());
		
		//Validation results
		Assertions.assertNotNull(response);
		Assertions.assertEquals(500,response.getBody().getErrorStatus());
		Assertions.assertNotNull(response.getBody().getTimestamp());
	}

}
