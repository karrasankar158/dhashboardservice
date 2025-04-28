package com.in.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in.entity.Student;
import com.in.service.StudentService;

//ctrl+shift+o for imports..
@Component
public class StudentRunner implements CommandLineRunner {
	
	@Autowired
	private StudentService studentService;

	@Override
	public void run(String... args) throws Exception {
		Student student=new Student();
		student.setId(1004L);
		student.setBranch("EEE");
		student.setName("kiran");
		student.setIsMale(true);
		
		//String response=studentService.saveStudent(student);
		//System.out.println(response);
		//String response=studentService.studentDeleteById(1001L);
		//System.out.println(response);
		//List<Student> response=studentService.findAllStudents();
		//System.out.println(response);
		//Student response=studentService.findByStudentByUsingId(student.getId());
		//System.out.println(response);
		//String response=studentService.updateStudentByUsingId(student.getId(), student);
		//System.out.println(response);
		//Boolean response=studentService.studentExistsById(student.getId());
		//System.out.println(response);
		//String response=studentService.studentCount();
		//System.out.println(response);
	}

}
