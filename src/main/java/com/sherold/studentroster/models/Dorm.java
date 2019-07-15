package com.sherold.studentroster.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity // DB entity 
@Table(name="dorms") // Table for data
public class Dorm {
	// <----- Attributes ----->
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message="First Name cannot be blank!")
	@Size(min=2, max=20, message="First Name must be a length between 2 and 20 characters!")
	private String name;
	@Column(updatable=false) // Data in column is immutable once created
	private Date createdAt;
	private Date updatedAt;
	// Established 1:n relationship
	// mappedBy = table to map PK
	// FetchType.LAZY = established relationship when assigned
	// "List" due to "One" object being tied to "Many" objects
	@OneToMany(mappedBy="dorm", fetch = FetchType.LAZY)
	private List<Student> students;
	
	// <----- Constructors ----->
	public Dorm() {
	}

	public Dorm(String name) {
		this.name = name;
	}
	
	public Dorm(String name, List<Student> students) {
		this.name = name;
	}
	
	// <----- Getters/Setters ----->
	// name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// students
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	// Getters only
	//id
	public Long getId() {
		return id;
	}

	// createdAt
	public Date getCreatedAt() {
		return createdAt;
	}

	// updatedAt
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	// <----- Methods ----->
	@PrePersist // Generated 'created at' at time of instantiation
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate // Set at time of save()
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}
