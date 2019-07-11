package com.sherold.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sherold.studentroster.models.Contact;
import com.sherold.studentroster.models.Student;
import com.sherold.studentroster.repositories.ContactRepository;
import com.sherold.studentroster.repositories.StudentRepository;

@Service // Designates class as a service
public class ApiService {
	// <----- Attributes ----->
	// Dependency injection
	private final StudentRepository sRepo;
	private final ContactRepository cRepo;
	
	// <----- Constructors ----->
	public ApiService(StudentRepository sRepo, ContactRepository cRepo) {
		this.sRepo = sRepo;
		this.cRepo = cRepo;
	}
	
	// <----- Methods ----->
	// SELECT * FROM students
	public List<Student> allStudents() {
		return sRepo.findAll();
	}
	
	// SELECT * FROM students WHERE id =
	public Student findStudent(Long id) {
		// Optional allows for null
		Optional<Student> optionalStudent = sRepo.findById(id);
		if(optionalStudent.isPresent()) {
			return optionalStudent.get();
		} else {
			return null;
		}
	}
	
	// INSERT INTO students
	public Student createStudent(Student s) {
		return sRepo.save(s);
	}
	
	// INSERT INTO contacts
	public Contact createContact(Contact c) {
		return cRepo.save(c);
	}
}
