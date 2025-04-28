package com.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.in.entity.Student;
import com.in.exception.StudentNotFoundException;
import com.in.repo.StudentRepo;
import com.in.utils.StudentUtils;

@Service
public class StudentService {

	@Value("${app.save}")
	private String save;

	@Value("${app.update}")
	private String update;

	@Value("${app.delete}")
	private String delete;

	@Autowired
	private StudentRepo studentRepo;

	public String saveStudent(Student student) {
		Student savedStudent = studentRepo.save(student);

		return save + " " + savedStudent.getId();
	}

	public String studentDeleteById(Long id) {
		if (studentRepo.existsById(id))
			studentRepo.deleteById(id);
		else
			throw new StudentNotFoundException("Given Id does not exists: " + id);

		return delete + " " + id;
	}

	public List<Student> findAllStudents() {
		List<Student> students = studentRepo.findAll();
		return students;
	}

	public Student findByStudentByUsingId(Long id) {
		Student student = studentRepo.findById(id).get();
		return student;
	}

	public String updateStudentByUsingId(Long id, Student student) {
		Student StudentRetrieved = studentRepo.findById(id).get();

		StudentRetrieved.setName(student.getName());//updating field value
		
		Student studentUpdated = studentRepo.save(StudentRetrieved);
		return update + " " + studentUpdated.getId();
	}

	public Boolean studentExistsById(Long id) {
		Boolean response = studentRepo.existsById(id);
		return response;
	}

	public String studentCount() {
		Integer count = (int) studentRepo.count();
		String result = StudentUtils.findCount(count);
		return result;
	}

}
