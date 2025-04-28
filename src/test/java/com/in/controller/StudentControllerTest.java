package com.in.controller;

//import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in.constraints.StudentConstraints;
import com.in.entity.Student;
import com.in.service.StudentService;

@WebMvcTest(controllers = StudentController.class) 
//WebMvcTest will create bean for only controller class.
public class StudentControllerTest {
	/*
	@MockBean
	private StudentService studentService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private static final MediaType APPLICATION_JSON_UTF8=
			new MediaType(MediaType.APPLICATION_JSON.getType(),
					MediaType.APPLICATION_JSON.getSubtype(),
					Charset.forName("utf8"));
	
	private Student student;
	
	@BeforeEach //Calling before, every test method...
	public void setup() {
		
		//This method will load before each test case calling.....
		MockitoAnnotations.openMocks(this);//Initializing all Mocks...
		
		student=new Student();
		student.setId(9999L);
		student.setName("Mohan");
		student.setBranch("Civil");
		student.setIsMale(true);
	}
	
	@Test
	public void testSaveStudent() throws Exception {
		//passing controller and method path
		String path="/student/save";
		
		ObjectMapper objectMapper=new ObjectMapper();//Jackson API
		objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow=objectMapper.writer().withDefaultPrettyPrinter();
		//Here Converting Student Object into JSON String.
		String JSON=ow.writeValueAsString(student);
		
		
		MvcResult response=mockMvc
		   .perform(
				    MockMvcRequestBuilders
				       .post(path)
				       .contentType(APPLICATION_JSON_UTF8)
				       .content(JSON))
		   .andExpect(MockMvcResultMatchers.status().is(201)).andReturn();
		
		//Validating results
		Assertions.assertNotNull(response);
	}*/
	/*
	@MockBean
	private StudentService studentService;
	
	@Autowired
	private MockMvc mockMvc;
	
     private Student student;
	
	@BeforeEach //Calling before, every test method...
	public void setup() {
		
		//This method will load before each test case calling.....
		MockitoAnnotations.openMocks(this);//Initializing all Mocks...
		
		student=new Student();
		student.setId(9999L);
		student.setName("Mohan");
		student.setBranch("Civil");
		student.setIsMale(true);
	}
	
	@Test
	public void testSaveStudent() throws Exception {
		//passing controller and method path
		String path="/student/save";
		
		ObjectMapper objectMapper=new ObjectMapper();//Jackson API
		//Here Converting Student Object into JSON String.
		String JSON=objectMapper.writeValueAsString(student);
		
		
		MvcResult response=mockMvc
		   .perform(
				    MockMvcRequestBuilders
				       .post(path)
				       .contentType(MediaType.APPLICATION_JSON)
				       .content(JSON))
		   .andExpect(MockMvcResultMatchers.status().is(201)).andReturn();
		
		//Validating results
		Assertions.assertNotNull(response);
	}*/
	
	/*
	@MockBean
	private StudentService studentService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testSaveStudent() throws Exception {
		
		MvcResult response=mockMvc
		   .perform(
				    MockMvcRequestBuilders
				       .post("/student/save")
				       .contentType(MediaType.APPLICATION_JSON)
				       .content("{\"id\" : 1001,\"name\" : \"ravi\",\"branch\" : \"mech\",\"isMale\" : true}"))
		   .andExpect(MockMvcResultMatchers.status().is(201)).andReturn();
		
		//Validating results
		Assertions.assertNotNull(response);
	}*/
	/*
	@MockBean
	private StudentService studentService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testSaveStudent() throws Exception {
		
		MvcResult response=mockMvc
		   .perform(
				    MockMvcRequestBuilders
				       .post("/student/save").contentType(MediaType.APPLICATION_JSON)
				       .content("{}"))
		   .andExpect(MockMvcResultMatchers.status().is(201)).andReturn();
		
		//Validating results
		Assertions.assertNotNull(response);
	}*/
	
	@MockBean //Advanced of @Mock
	private StudentService studentService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Student student;
	
	private Student student2;
	
	@Autowired
	private ObjectMapper objectMapper; //Jackson API, Spring web starter
	
	@BeforeEach //Calling before, every test method.
	public void setup() {
		//This setup method will load before each test case/method calling.
		
		MockitoAnnotations.openMocks(this);//Initializing all mocks.
		//one student
		student=new Student();
		student.setId(9999L);
		student.setBranch("Civil");
		student.setName("Sankar");
		student.setIsMale(true);
		
		//second Student
		student2=new Student();
		student2.setId(8888L);
		student2.setBranch("Mech");
		student2.setName("Sai");
		student2.setIsMale(false);
	}

	@Test
	public void testSaveStudent() throws Exception {
		//Setting expectations or stubbing or Mocking the service behavior. 
		String expected="done";
		Mockito.when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(expected);
		
		//Actual method call or performing an HTTP request to create an Student
		//While passing path need to add both class level and method level path
		String path="/student/save";
		//While creating object is Java Student student=new Student();
		//To create object in JSON {}
		ResultActions response=mockMvc
		                           .perform(MockMvcRequestBuilders
		    		                  .post(path)
		    		                  .header(StudentConstraints.STUDENT_KEY, "092u2jt2tt2j2oj2t2to")
		    		                  .contentType(MediaType.APPLICATION_JSON)
		    		                  .content("{}"));
		
		//Assertions the response expectations.
		response.andExpect(MockMvcResultMatchers.status().isCreated())
		//While Validating String response in rest controller method.
		.andExpect(MockMvcResultMatchers.jsonPath("$").value("done"))
		.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo(expected)));
		Assertions.assertNotNull(response);
	}
	
	@Test
	public void testGetStudentById() throws Exception {
		//Setting expectations or Stubbing.
		Mockito.when(studentService.findByStudentByUsingId(Mockito.any(Long.class))).thenReturn(student);
		
		//class level path+method level path+path variable
		String path="/student/find/{id}";
		
		MvcResult mvcResult=mockMvc
		     .perform(MockMvcRequestBuilders
		    		 .get(path,"9999")
		    		 .header(StudentConstraints.STUDENT_KEY, "092u2jt2tt2j2oj2t2to")
		    		 .contentType(MediaType.APPLICATION_JSON))
		     //Wherever entity or model is returning those places jsonPath is applicable
		     .andExpect(MockMvcResultMatchers.status().isOk())//200
		     .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(9999))
		     .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Sankar"))
		     .andExpect(MockMvcResultMatchers.jsonPath("$.branch").value("Civil"))
		     .andExpect(MockMvcResultMatchers.jsonPath("$.isMale").value(true))
		     .andReturn();
		
		//Validating results
		Assertions.assertNotNull(mvcResult);
		Assertions.assertEquals(objectMapper.writeValueAsString(student), mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetAllStudents() throws Exception {
		//Setting expectations or Stubbing.
		Mockito.when(studentService.findAllStudents()).thenReturn(List.of(student,student2));
		
		//class level path+method level path
		String path="/student/find/all";
		
		MvcResult mvcResult=mockMvc
		     .perform(MockMvcRequestBuilders
		    		 .get(path)
		    		 .contentType(MediaType.APPLICATION_JSON))
		     //Wherever entity or model is returning those places jsonPath is applicable
		     .andExpect(MockMvcResultMatchers.status().isOk())//200
		     .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(9999))
		     .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Sankar"))
		     .andExpect(MockMvcResultMatchers.jsonPath("$[0].branch").value("Civil"))
		     .andExpect(MockMvcResultMatchers.jsonPath("$[0].isMale").value(true))
		     
		     .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(8888))
		     .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Sai"))
		     .andExpect(MockMvcResultMatchers.jsonPath("$[1].branch").value("Mech"))
		     .andExpect(MockMvcResultMatchers.jsonPath("$[1].isMale").value(false))
		     .andReturn();
		
		//Validating results
		Assertions.assertNotNull(mvcResult);
	}
	
	@Test
	public void testStudentDeleteById() throws Exception {
		//1. Expected
		String expected="deleted";
		
		//2. Setting expectations or Stubbing or Mock method
		Mockito.when(studentService.studentDeleteById(Mockito.any(Long.class))).thenReturn(expected);
		
		//3. class level path+method level path
		String path="/student/delete";
		
		//4. create Dummy or proxy or fake request
		MockHttpServletRequestBuilder request=MockMvcRequestBuilders.delete(path)
		
		//5. requestParam
		.param("id", "1001");
		
		//6. Execute request and get result
		MvcResult result=mockMvc.perform(request).andReturn();
		
		//7. Read Response
		MockHttpServletResponse response=result.getResponse();
		
		//8. Validating results 
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		Assertions.assertEquals(expected, response.getContentAsString());
	}
	
	@Test
	public void testUpdateStudentById() throws Exception {
		//1. Expected
		String expected="updated";
		
		//2. Setting Expectations or Stubbing or Mock method
		Mockito.when(studentService.updateStudentByUsingId(Mockito.any(Long.class), 
				                   Mockito.any(Student.class))).thenReturn(expected);
		
		//3. class level path+method level path+path variable 
		String path="/student/update/{id}";
		
		//4. create dummy or proxy or fake request
		MockHttpServletRequestBuilder request=MockMvcRequestBuilders.patch(path,"9999")
		
		//5. request body or payLoad
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(student));
		
		//6. Execute request and get result
		MvcResult result=mockMvc.perform(request).andReturn();
		
		//7. Read response
		MockHttpServletResponse response=result.getResponse();
		
		//8. Validating response
		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		Assertions.assertEquals(expected, response.getContentAsString());
	}
	
	@Test
	public void testStudentExistsById() throws Exception {
		//Setting expectations or stubbing or mock method
		Mockito.when(studentService.studentExistsById(Mockito.any(Long.class))).thenReturn(true);
		
		//Response-chain of methods
		ResultActions response= mockMvc
		                           .perform(get("/student/exists")
				                            .param("id", "9999")
				                            .contentType(MediaType.APPLICATION_JSON))
		                            .andExpect(status().isOk())
		                            .andExpect(jsonPath("$").value("true"));
		Assertions.assertNotNull(response);    
	}
	
	@Test
	public void testStudentCount() throws Exception {
		//Setting expectations or stubbing or mock method
		Mockito.when(studentService.studentCount()).thenReturn("TWO");
		
		//Response-chain of methods
		ResultActions response= mockMvc
		                           .perform(get("/student/count")
				                            .contentType(MediaType.APPLICATION_JSON))
		                            .andExpect(status().isOk())
		                            .andExpect(jsonPath("$").value("TWO"));
		Assertions.assertNotNull(response);    
	}
	
	@Test
	public void testStudentNotFound() throws Exception {
		
		//Response-chain of methods
		ResultActions response= mockMvc
		                           .perform(get("/student/exists")
		                        		    .param("id", "-100")
				                            .contentType(MediaType.APPLICATION_JSON))
		                            .andExpect(status().isNotFound()) //HttpStatus
		                            //After StudentControllerAdvice created commented this line.
		                            //.andExpect(content().string(equalTo("Student Not Found!")));
		                            .andExpect(jsonPath("$.message").value("Student Not Found! -100"));
		
		Assertions.assertNotNull(response);    
	}
	
	@Test
	public void testStudentInternalServerError() throws Exception {
		
		//Response-chain of methods
		ResultActions response= mockMvc
		                           .perform(get("/student/exists")
		                        		    .param("id", "Sankar")
				                            .contentType(MediaType.APPLICATION_JSON))
		                            .andExpect(status().isInternalServerError()) //HttpStatus
		                            .andExpect(jsonPath("$.errorStatus").value("500"));
		
		Assertions.assertNotNull(response);    
	}
}
