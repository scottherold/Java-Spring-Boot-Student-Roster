package com.sherold.studentroster.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // DB entity 
@Table(name="contacts") // Table for data
public class Contact {
	// <----- Attributes ----->
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@NotNull(message="Address cannot be blank!")
		@Size(min=5, max=50, message="Address must be a length between 5 and 50 characters!")
		private String address;
		@NotNull(message="City cannot be blank!")
		@Size(min=2, max=20, message="City must be a length between 2 and 20 characters!")
		private String city;
		@NotNull(message="State cannot be blank!")
		@Size(min=2, max=2, message="State must be a length of 2 characters!")
		private String state;
		@Column(updatable=false) // Data in column is immutable once created
		private Date createdAt;
		private Date updatedAt;
		// One-to-One mapping w/ Student Model: 
		// Only establishes relationship with told,
		// Maps Contact Model to attribute on Student Model on the Primary Key
		@JsonIgnore // Does not print to JSON
		@OneToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="student_id")
		private Student student;
		
		// <----- Constructors ----->
		
		public Contact() {
		}

		public Contact(String address, String city, String state, Student student) {
			this.address = address;
			this.city = city;
			this.state = state;
			this.student = student;
		}
		
		// <----- Getters/Setters ----->
		// id - Getter only
		public Long getId() {
			return id;
		}
		
		// address
		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		// city
		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		// state
		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		// createdAt - Getter only
		public Date getCreatedAt() {
			return createdAt;
		}

		// updatedAt - Getter only
		public Date getUpdatedAt() {
			return updatedAt;
		}
		
		// Student
		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
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
