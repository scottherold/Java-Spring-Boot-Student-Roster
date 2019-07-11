package com.sherold.studentroster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sherold.studentroster.models.Contact;

@Repository // Designates interface as a repo
public interface ContactRepository extends CrudRepository<Contact, Long> {
	// Sets findAll() to return Contact objects
	List<Contact> findAll();
}
