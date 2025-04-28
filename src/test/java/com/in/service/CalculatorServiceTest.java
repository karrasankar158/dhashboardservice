package com.in.service;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorServiceTest {

	@InjectMocks
	private CalculatorService calculatorService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	public static List<Integer[]> data() {
		return Arrays.asList(new Integer[][] { { -1, 2, 1 }, { 1, 2, 3 }, { 6, 7, 13 } });
	}

	@ParameterizedTest
	@MethodSource("data")
	public void testAdd_Return_Result(Integer num1, Integer num2, Integer expectedResult) {
		
		//Calling Actual method...
		Integer actual = calculatorService.add(num1, num2);
		
		//Validating results.....
		Assertions.assertEquals(expectedResult, actual);
	}

}
