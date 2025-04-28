package com.in.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.in.entity.Student;
import com.in.exception.StudentNotFoundException;
import com.in.repo.StudentRepo;
import com.in.utils.StudentUtils;

@SpringBootTest
public class StudentServiceTest {
	
	private static final String save="Student Saved Successfully:";
	private static final String update="Student Updated Successfully:";
	private static final String delete="Student Successfully Deleted:";

	@InjectMocks // @Autowired
	private StudentService studentService;

	@Mock
	private StudentRepo studentRepo;
	
	@BeforeAll //Approach-2
	public static void setupAll() {
		//When test class is loaded this method will load and this method is static method.
		//Before executing any test method.... this method will load.
		Mockito.mockStatic(StudentUtils.class);
	}

	@BeforeEach // calling before, every test method...
	public void setUp() {
		//This method will load before each test case calling.
		MockitoAnnotations.openMocks(this); // Initializing all Mocks...
	}

	@Test
	public void testSaveStudent() {
		//Setting expectations for private fields in Mockito. 
		String expected = save+" 9999";
		ReflectionTestUtils.setField(studentService, "save",save);

		// Setting expectations or stubbing
		Mockito.when(studentRepo.save(Mockito.any(Student.class))).thenReturn(this.getStudent());

		// Actual test method call..
		String actual = studentService.saveStudent(this.getStudent());
		
		// validating results...
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual);

		// verify mock methods calling or not.
		Mockito.verify(studentRepo).save(this.getStudent());
	}

	@Test
	public void testStudentDeleteByIdSuccess() {
		//Setting expectations for private fields in Mockito. 
		String expected = delete+" 9999";
		ReflectionTestUtils.setField(studentService, "delete",delete);
		
		Boolean response = true;

		// Setting expectations.... How to Mock void method...
		Mockito.when(studentRepo.existsById(Mockito.any(Long.class))).thenReturn(response);
		Mockito.doNothing().when(studentRepo).deleteById(this.getStudent().getId());

		// Actual test method call..
		String actual = studentService.studentDeleteById(this.getStudent().getId());

		// validating results...
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual);

		// verify mock methods calling or not.
		Mockito.verify(studentRepo).deleteById(this.getStudent().getId());
		Mockito.verify(studentRepo).existsById(this.getStudent().getId());
	}

	@Test
	public void testStudentDeleteByIdException() {
		Boolean response = false;

		// Setting expectations.... How to Mock void method...
		//Mockito.when(studentRepo.existsById(Mockito.any(Long.class))).thenReturn(response);
		//or
		Mockito.doThrow(StudentNotFoundException.class).when(studentRepo).existsById(0L);
 
		// Actual test method call..
		Assertions.assertThrows(StudentNotFoundException.class, () -> 
			studentService.studentDeleteById(this.getStudent().getId()));

		// verify mock methods calling or not.
		Mockito.verify(studentRepo).existsById(this.getStudent().getId());
	}

	@Test
	public void testFindAllStudents() {
		// Setting expectations....
		Mockito.when(studentRepo.findAll()).thenReturn(this.getAllStudents());

		// Actual Method call..
		List<Student> actual = studentService.findAllStudents();

		// Validating results...
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(2L, actual.stream().count());
		Assertions.assertFalse(actual.stream().iterator().next().getIsMale());

		// Verify mock methods calling or not...
		Mockito.verify(studentRepo).findAll();
	}

	@Test
	public void testFindByStudentByUsingId() {

		// Setting expectations....
		Mockito.when(studentRepo.findById(Mockito.any(Long.class))).thenReturn(Optional.of(this.getStudent()));

		// Actual Method call..
		Student actual = studentService.findByStudentByUsingId(this.getStudent().getId());

		// Validating results...
		Assertions.assertNotNull(actual);
		Assertions.assertTrue(actual.getIsMale());
		Assertions.assertEquals(9999L, actual.getId());
		Assertions.assertEquals("ECE", actual.getBranch());
		Assertions.assertEquals("sankar", actual.getName());

		// Verify mock methods calling or not...
		Mockito.verify(studentRepo).findById(this.getStudent().getId());
	}

	@Test
	public void testupdateStudentByUsingId() {
		//Setting expectations for private fields in Mockito. 
		String expected = update+" 9999";
		ReflectionTestUtils.setField(studentService, "update",update);

		// Setting expectations...
		Mockito.when(studentRepo.findById(Mockito.any(Long.class))).thenReturn(Optional.of(this.getStudent()));
		Mockito.when(studentRepo.save(Mockito.any(Student.class))).thenReturn(this.getStudent());

		// Actual Method call..
		String actual = studentService.updateStudentByUsingId(this.getStudent().getId(), this.getStudent());

		// Validating results...
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual);

		// Verify mock methods calling or not...
		Mockito.verify(studentRepo).findById(this.getStudent().getId());
		Mockito.verify(studentRepo).save(this.getStudent());
	}

	@Test
	public void testStudentExistsById() {
		Boolean response = true;

		// Setting expectations...
		Mockito.when(studentRepo.existsById(Mockito.any(Long.class))).thenReturn(true);

		// Actual Method call..
		Boolean actual = studentService.studentExistsById(this.getStudent().getId());

		// Validating results...
		Assertions.assertNotNull(actual);
		Assertions.assertTrue(actual);

		// Verify mock methods calling or not...
		Mockito.verify(studentRepo).existsById(this.getStudent().getId());

	}
	
	@Test
	public void testStudentCountPositive() {
		String expected="TWO";
		
		// Setting expectations...
		Mockito.when(studentRepo.count()).thenReturn(this.getAllStudents().stream().count());
		Mockito.when(StudentUtils.findCount(2)).thenReturn(expected);
		
		// Actual Method call..
		String actual=studentService.studentCount();
		
		// Validating results...
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual);
		
		// Verify mock methods calling or not...
		Mockito.verify(studentRepo).count();

	}
	
	@Test
	public void testStudentCountNone() {
		String expected="NONE";
		
		// Setting expectations...
		Mockito.when(studentRepo.count()).thenReturn(0L);
		Mockito.when(StudentUtils.findCount(0)).thenReturn(expected);
		
		// Actual Method call..
		String actual=studentService.studentCount();
		
		// Validating results...
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual);
		
		// Verify mock methods calling or not...
		Mockito.verify(studentRepo).count();

	}
	
	@Test
	public void testStudentCountEight() {
		String expected="NONE";
		
		// Setting expectations...
		Mockito.when(studentRepo.count()).thenReturn(8L);
		Mockito.when(StudentUtils.findCount(8)).thenReturn(expected);
		
		// Actual Method call..
		String actual=studentService.studentCount();
		
		// Validating results...
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual);
		
		// Verify mock methods calling or not...
		Mockito.verify(studentRepo).count();

	}
	
	@Test //Approach-1
	@Disabled
	public void testStudentCountValueFour() {
		try(MockedStatic<StudentUtils> mockedUtils=Mockito.mockStatic(StudentUtils.class)) {//try with resources
		String expected="FOUR";
		
		// Setting expectations...
		Mockito.when(studentRepo.count()).thenReturn(4L);
		mockedUtils.when(()->StudentUtils.findCount(4)).thenReturn(expected);//static method mocking.
		
		// Actual Method call..
		String actual=studentService.studentCount();
		
		// Validating results...
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(expected, actual);
		
		// Verify mock methods calling or not...
		Mockito.verify(studentRepo).count();
		mockedUtils.verify(()->StudentUtils.findCount(4));
		}
	}

	private Student getStudent() {
		Student student = new Student();
		student.setId(9999L);
		student.setBranch("ECE");
		student.setName("sankar");
		student.setIsMale(true);
		return student;
	}

	private List<Student> getAllStudents() {
		Student student = new Student();
		student.setId(9999L);
		student.setBranch("ECE");
		student.setName("sankar");
		student.setIsMale(false);

		Student student1 = new Student();
		student1.setId(9991L);
		student1.setBranch("CSE");
		student1.setName("mohan");
		student1.setIsMale(true);
		return List.of(student, student1);
	}
	
	@AfterEach
	public void tearDown() {
		studentService=null;
		studentRepo=null;
	}

}
