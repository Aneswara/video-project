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

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public List<Video> findAll() {
        return videoRepository.findByAvailableTrue();
    }

    public Video saveVideo(Video dto) {
        Video saved = videoRepository.save(dto);
        return saved;
    }

    public Video updateVideo(Long id, Video dto) {
        Video video =null;
		try {
			video = videoRepository.findById(id)
			        .orElseThrow(() -> new Exception("Video not found"));
			video.setTitle(dto.getTitle());
	        video.setDirector(dto.getDirector());
	        video.setGenre(dto.getGenre());
	        video.setAvailable(dto.isAvailable());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return videoRepository.save(video);
    }

    public void deleteVideo(Long id) throws Exception {
        if (!videoRepository.existsById(id)) {
            throw new Exception("Video not found");
        }
        videoRepository.deleteById(id);
    }

    public Video getVideoById(Long id) {
    	Video video = null;
    	try {
    		video = videoRepository.findById(id)
			        .orElseThrow(() -> new Exception("Video not found"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return video;
    }

}

