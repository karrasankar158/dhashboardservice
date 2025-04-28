package com.in.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.in.entity.Student;

//@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

}
