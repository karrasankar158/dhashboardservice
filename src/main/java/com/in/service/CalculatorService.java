package com.in.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

	public Integer add(Integer num1,Integer num2) {
		return num1+num2;
	}
}
