package com.sherold.studentroster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sherold.studentroster.models.Student;

@Repository // Designates interface as a repo
public interface StudentRepository extends CrudRepository<Student, Long> {
	// Sets findAll() to return Student objects
	List<Student> findAll();
}
