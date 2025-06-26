package com.video.service;

import java.util.List;

import com.video.model.Video;

public interface VideoService {

	Video saveVideo(Video video);
	
	Video updateVideo(Long id, Video video);
	
	void deleteVideo(Long id) throws Exception;
	
	List<Video> findAll();
}
