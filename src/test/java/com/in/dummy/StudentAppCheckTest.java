package com.in.dummy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
public class StudentAppCheckTest {
	
	@Spy //spy will call real object.
	private List<String> spyList=new ArrayList<>();
	
	@Mock //mock will call mock object. 
	private List<String> mockList=new ArrayList<>();
	
	@Test //spy will call real object.
	public void testSpy() {
		//Setting expectations or stubbing
		Mockito.when(spyList.add(Mockito.any(String.class))).thenReturn(true);
		
		// validating results...
		Assertions.assertEquals(1, spyList.size());
		Assertions.assertSame(1, spyList.size());
		
	}
	
	@Test
	public void testSpyCheck() {
		List<String> list=new LinkedList<>();
		List<String> spy=Mockito.spy(list);
		//Setting expectations or stubbing
		//Mockito.when(spy.get(0)).thenReturn("Sankar");
		Mockito.doReturn("Sankar").when(spy).get(0);
	}
	
	@Test //mock will call mock object. 
	public void testMock() {
		//Setting expectations or stubbing
		Mockito.when(mockList.add(Mockito.any(String.class))).thenReturn(true);
		Mockito.when(mockList.size()).thenReturn(1);
		
		// validating results...
		Assertions.assertEquals(1, mockList.size());
		Assertions.assertSame(1, mockList.size());
		
	}

	@Test
	public void testToAccessPrivateFieldsMethodsAndConstrctor() throws Exception {
		Constructor<StudentAppCheck> constructor=
				StudentAppCheck.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		StudentAppCheck studentAppCheck=constructor.newInstance();
		Assertions.assertNotNull(studentAppCheck);
		
		Field field=StudentAppCheck.class.getDeclaredField("name");//Accessing field.
		field.setAccessible(true);
		field.set(studentAppCheck, "Sankar");
		String name=(String)field.get(studentAppCheck);
		Assertions.assertNotNull(name);
		
		Method method=StudentAppCheck.class.getDeclaredMethod("getNumber", Integer.class);
		method.setAccessible(true);
		Integer number=(Integer)method.invoke(studentAppCheck,100);
		Assertions.assertNotNull(number);
		
		ReflectionTestUtils.setField(studentAppCheck, "name", "Hello");
		String name1=(String)ReflectionTestUtils.getField(studentAppCheck, "name");
		System.out.println(name1);
		
		Integer response=(Integer)ReflectionTestUtils.invokeMethod(studentAppCheck, "getNumber", 10);
		System.out.println(response);
	}
}
