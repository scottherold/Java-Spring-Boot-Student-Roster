package com.sherold.studentroster.models;

import java.util.ArrayList;
import java.util.Date;

public class JSONDorm {
	// <----- Attributes ----->
	private Long id;
	private String name;
	private Date createdAt;
	private Date updatedAt;
	private ArrayList<JSONStudent> students;
	
	// <----- Constructors ----->
	public JSONDorm() {
	}	

	public JSONDorm(Long id, String name, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public JSONDorm(Long id, String name, Date createdAt, Date updatedAt, ArrayList<JSONStudent> students) {
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.students = students;
	}
	
	// <----- Getters/Setters -----
	// id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// createdAt
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	// updatedAt
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	// students
	public ArrayList<JSONStudent> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<JSONStudent> students) {
		this.students = students;
	}
	
	// <----- Methods ----->
	// add student to students
	public void addJSONStudent(JSONStudent student) {
		this.students.add(student);
	}
	
	// remove student from students
	public void removeJSONStudent(JSONStudent student) {
		this.students.remove(student);
	}
}
