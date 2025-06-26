package com.video.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.video.model.Rental;
import com.video.model.User;
import com.video.model.Video;
import com.video.repository.RentalRepository;
import com.video.repository.UserRepository;
import com.video.repository.VideoRepository;
import com.video.service.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	private  RentalRepository rentalRepository;
	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private  VideoRepository videoRepository;

	private static final int MAX_ACTIVE_RENTALS = 2;

	public void rentVideo(String userEmail, Long videoId) throws Exception {
		User user = userRepository.findByEmail(userEmail)
				.orElseThrow(() -> new Exception("User not found"));

		List<Rental> activeRentals = rentalRepository.findByUserAndReturnedAtIsNull(user);
		if (activeRentals.size() >= MAX_ACTIVE_RENTALS) {
			throw new Exception("Cannot rent more than 2 videos at the same time");
		}

		Video video = videoRepository.findById(videoId)
				.orElseThrow(() -> new Exception("Video not found"));

		if (!video.isAvailable()) {
			throw new Exception("Video is not available for rent");
		}

		Rental rental = new Rental();
		rental.setUser(user);
		rental.setVideo(video);
		rental.setRentedAt(LocalDateTime.now());

		video.setAvailable(false);
		videoRepository.save(video);
		rentalRepository.save(rental);
	}

	public void returnVideo(String userEmail, Long videoId) throws Exception {
		User user = userRepository.findByEmail(userEmail)
				.orElseThrow(() -> new Exception("User not found"));

		Video video = videoRepository.findById(videoId)
				.orElseThrow(() -> new Exception("Video not found"));

		Rental rental = rentalRepository.findByUserAndVideoAndReturnedAtIsNull(user, video)
				.orElseThrow(() -> new Exception("No active rental found for this video by user"));

		rental.setRentedAt(LocalDateTime.now());
		rentalRepository.save(rental);

		video.setAvailable(true);
		videoRepository.save(video);
	}

	public List<Rental> getActiveRentals(String userEmail) throws Exception {
		User user = userRepository.findByEmail(userEmail)
				.orElseThrow(() -> new Exception("User not found"));

		return rentalRepository.findByUserAndReturnedAtIsNull(user);
	}

}
