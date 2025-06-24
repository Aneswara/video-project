package com.video.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.video.model.Video;
import com.video.repository.VideoRepository;
import com.video.service.VideoService;

/**
 * 
 * @author Aneswara reddy
 *
 */
@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoRepository videoRepository;

	public List<Video> findAll() {
		return videoRepository.findByAvailable(true);
	}

	public Video saveVideo(Video video) {
		return videoRepository.save(video);
	}

	public Video updateVideo(Long id, Video updatedVideo) {
		Video video = videoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Video not found"));
		video.setTitle(updatedVideo.getTitle());
		video.setDirector(updatedVideo.getDirector());
		video.setGenre(updatedVideo.getGenre());
		video.setAvailable(updatedVideo.isAvailable());
		return videoRepository.save(video);
	}

	public void deleteVideo(Long id) {
		if (!videoRepository.existsById(id)) throw new RuntimeException("Video not found");
		videoRepository.deleteById(id);
	}

}

