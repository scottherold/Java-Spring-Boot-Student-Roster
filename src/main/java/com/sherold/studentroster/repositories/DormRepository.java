package com.sherold.studentroster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sherold.studentroster.models.Dorm;

@Repository // Designates interface as repo
public interface DormRepository extends CrudRepository<Dorm, Long> {
	List<Dorm> findAll();
}
