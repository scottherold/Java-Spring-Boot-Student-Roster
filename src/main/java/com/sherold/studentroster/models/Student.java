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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // DB entity 
@Table(name="students") // Table for data
public class Student {
	// <----- Attributes ----->
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message="First Name cannot be blank!")
	@Size(min=2, max=20, message="First Name must be a length between 2 and 20 characters!")
	private String firstName;
	@NotNull(message="Last Name cannot be blank!")
	@Size(min=2, max=20, message="Last Name must be a length between 2 and 20 characters!")
	private String lastName;
	@NotNull(message="Age cannot be blank!")
	@Min(18)
	@Max(150)
	private Integer age;
	@Column(updatable=false) // Data in column is immutable once created
	private Date createdAt;
	private Date updatedAt;
	
	// <----- Relationships ----->
	
	// One-to-One mapping w/ Contact Model: 
	// Maps Contact Model to attribute on Student Model, 
	// Ensures Model is referenced with Student object, 
	// Only establishes relationship with told
	@OneToOne(mappedBy="student", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Contact contact;
	@JsonIgnore // does not print to JSON
	// Established n:1 relationship
	// FetchType.LAZY = established relationship when assigned
	// JoinColumn links the PK to the relationship table ID
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dorm_id")
	private Dorm dorm;
	
	@JsonIgnore // does not print to JSON
	@ManyToMany(fetch = FetchType.LAZY) // establishes relationship type
	 // Sets up middle table in n:m relationship
	@JoinTable(
			name = "students_tracks", // table name
			joinColumns = @JoinColumn(name = "student_id"), // where object is mapped on middle-table 
			inverseJoinColumns = @JoinColumn(name = "track_id") // where middle-table maps to other object
	)
	private List<Track> tracks;
	
	// <----- Constructors ----->
	public Student() {
	}
	
	public Student(String firstName, String lastName, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public Student(String firstName, String lastName, Integer age, Contact contact) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.contact = contact;
	}
	
	// <----- Getters/Setters ----->
	// id - Getter only
	public Long getId() {
		return id;
	}

	// firstName
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// lastName
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// age
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	// createdAt - Getter only
	public Date getCreatedAt() {
		return createdAt;
	}

	// updatedAt - Getter only
	public Date getUpdatedAt() {
		return updatedAt;
	}

	// Contact
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	// Dorm
	public Dorm getDorm() {
		return dorm;
	}

	public void setDorm(Dorm dorm) {
		this.dorm = dorm;
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
