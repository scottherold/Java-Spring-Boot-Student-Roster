package com.sherold.studentroster.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sherold.studentroster.models.Contact;
import com.sherold.studentroster.models.JSONStudent;
import com.sherold.studentroster.models.Student;
import com.sherold.studentroster.services.ApiService;

@RestController // Designates class as an API controller
public class ApiController {
	// <----- Attributes ----->
	// Dependency injection
	private final ApiService apiService;
	
	// <----- Constructors ----->
	public ApiController(ApiService apiService) {
		this.apiService = apiService;
	}
	
	// REST Route for displaying all students
	@RequestMapping(value="students", method=RequestMethod.GET, produces="application/json")
	public ArrayList<JSONStudent> students() {
		List<Student> sList = apiService.allStudents(); // Queries DB for all students
		ArrayList<JSONStudent> students = new ArrayList<JSONStudent>(); // ArrayList to return object to JSON
		for (Student student : sList) {
			students.add(new JSONStudent(
						student.getId(), 
						student.getFirstName(), 
						student.getLastName(),
						student.getAge(),
						student.getContact().getAddress(),
						student.getContact().getCity(),
						student.getContact().getState(),
						student.getCreatedAt(),
						student.getUpdatedAt()
						)
					);
		}
		return students;
	}
	
	// REST Route for creating a new student
	@RequestMapping("students/create")
	// Uses query parameters for Student object instantiation
	public void createStudent(@RequestParam(value="firstName") String firstName, 
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="age") int age) {		
		Student student = new Student(firstName, lastName, age); // Instantiates Student object
		apiService.createStudent(student);
	}
	
	// REST Route for creating a new contact
	@RequestMapping("contacts/create")
	// Uses query parameters for Contact object instantiation
	public void createContact(@RequestParam(value="student") Long id, 
			@RequestParam(value="address") String address,
			@RequestParam(value="city") String city,
			@RequestParam(value="state") String state) {
		Student student = apiService.findStudent(id); // Instantiates Student object from query id		
		if (!student.equals(null)) {
			Contact contact = new Contact(address, city, state, student); // Instantiates Contact object
			apiService.createContact(contact);
		}		
	}
}
