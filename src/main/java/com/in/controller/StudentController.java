package com.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.in.constraints.StudentConstraints;
import com.in.entity.Student;
import com.in.exception.StudentKeyNotFoundException;
import com.in.exception.StudentNotFoundException;
import com.in.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/student")
public class StudentControllerHandlingAllStudentOperationsSuchAsSaveUpdateDeleteRetrieveFindByIdCountExistenceCheckAndPartialUpdateOperationsWithHeaderValidationAndExceptionHandlingToEnsureSecureAndRobustStudentManagementInTheApplicationLayer {

    @Autowired
    private StudentService studentService; //Has-A

    @PostMapping("/save") //save operation //Handler method
    public ResponseEntity<String> saveStudent(@RequestHeader(value=StudentConstraints.STUDENT_KEY,required = true) String studentKey,
            @RequestBody Student student){//payLoad
        
        if(ObjectUtils.isEmpty(studentKey)) //null or empty
            throw new StudentKeyNotFoundException("request header missing");
        
        String response=studentService.saveStudent(student);
        
        HttpHeaders headers=new HttpHeaders();
        headers.set(StudentConstraints.STUDENT_KEY, studentKey);
        
        return new ResponseEntity<>(response,headers,HttpStatus.CREATED);
    }
    
    @GetMapping("/find/{id}")//retrieve Single row operation //Handler method
    public ResponseEntity<Student> getStudentById(HttpServletRequest httpServletRequest,
            @PathVariable("id") Long id){
        
        String studentKey=httpServletRequest.getHeader(StudentConstraints.STUDENT_KEY);
        
        if(ObjectUtils.isEmpty(studentKey)) //null or empty
        throw new StudentKeyNotFoundException("request header missing");
        
        Student response=studentService.findByStudentByUsingId(id);
        
        HttpHeaders headers=new HttpHeaders();
        headers.set(StudentConstraints.STUDENT_KEY, studentKey);
        
        return new ResponseEntity<>(response,headers,HttpStatus.OK);
    }
    
    @GetMapping("/find/all")//retrieve all rows operation //Handler method
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> response=studentService.findAllStudents();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> studentDeleteById(@RequestParam Long id){
        String response=studentService.studentDeleteById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateStudentById(@PathVariable("id") long id,@RequestBody Student student){
        String response=studentService.updateStudentByUsingId(id, student);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @GetMapping("/exists")
    public ResponseEntity<Object> studentExistsById(@RequestParam Long id){
        if(id<0)
            throw new StudentNotFoundException("Student Not Found! "+id);
        
        Boolean response=studentService.studentExistsById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @GetMapping("/count")
    public ResponseEntity<String> studentCount(){
        String response=studentService.studentCount();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
