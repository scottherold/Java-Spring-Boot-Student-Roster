package com.sherold.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sherold.studentroster.models.Contact;
import com.sherold.studentroster.models.Dorm;
import com.sherold.studentroster.models.Student;
import com.sherold.studentroster.repositories.ContactRepository;
import com.sherold.studentroster.repositories.DormRepository;
import com.sherold.studentroster.repositories.StudentRepository;

@Service // Designates class as a service
public class ApiService {
	// <----- Attributes ----->
	// Dependency injection
	private final StudentRepository studentRepo;
	private final ContactRepository contactRepo;
	private final DormRepository dormRepo;
	
	// <----- Constructors ----->
	public ApiService(StudentRepository studentRepo, ContactRepository contactRepo, DormRepository dormRepo) {
		this.studentRepo = studentRepo;
		this.contactRepo = contactRepo;
		this.dormRepo = dormRepo;
	}
	
	// <----- Methods ----->
	// SELECT * FROM students
	public List<Student> allStudents() {
		return studentRepo.findAll();
	}
	
	// SELECT * FROM dorms
	public List<Dorm> allDorms() {
		return dormRepo.findAll();
	}
	
	// SELECT * FROM students WHERE id =
	public Student findStudent(Long id) {
		// Optional allows for null
		Optional<Student> optionalStudent = studentRepo.findById(id);
		if(optionalStudent.isPresent()) {
			return optionalStudent.get();
		} else {
			return null;
		}
	}
	
	// SELECT * FROM dorms WHERE id =
	public Dorm findDorm(Long id) {
		// Optional allows for null
		Optional<Dorm> optionalDorm = dormRepo.findById(id);
		if(optionalDorm.isPresent()) {
			return optionalDorm.get();
		} else {
			return null;
		}
	}
	
	// INSERT INTO students
	public Student createStudent(Student s) {
		return studentRepo.save(s);
	}
	
	// INSERT INTO contacts
	public Contact createContact(Contact c) {
		return contactRepo.save(c);
	}
	
	// INSERT INTO dorms
	public Dorm createDorm(Dorm d) {
		return dormRepo.save(d);
	}
}
