package com.sherold.studentroster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sherold.studentroster.models.Track;

@Repository // designates repo
public interface TrackRepository extends CrudRepository<Track, Long> {
	List<Track> findAll();
}
