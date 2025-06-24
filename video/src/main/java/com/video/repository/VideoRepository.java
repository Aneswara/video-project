package com.video.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.video.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
	List<Video> findByAvailable(boolean available);
}


