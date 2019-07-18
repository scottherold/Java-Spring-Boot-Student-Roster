package com.sherold.studentroster.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity // DB entity
@Table(name="tracks") // Table for data
public class Track {
	// <----- Attributes ----->
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(updatable=false) // Data in column is immutable once created
	private Date createdAt;
	private Date updatedAt;
	
	// <----- Relationships ----->
	
	@ManyToMany(fetch = FetchType.LAZY) // establishes relationship type
	 // Sets up middle table in n:m relationship
	@JoinTable(
			name = "students_tracks", // table name
			joinColumns = @JoinColumn(name = "track_id"), // where object is mapped on middle-table 
			inverseJoinColumns = @JoinColumn(name = "student_id") // where middle-table maps to other object
	)
	private List<Student> students;

	// <----- Constructors ----->
	public Track() {
	}

	public Track(String name) {
		this.name = name;
	}

	public Track(String name, List<Student> students) {
		this.name = name;
		this.students = students;
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
	// id
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
