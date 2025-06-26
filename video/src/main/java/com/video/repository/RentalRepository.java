package com.video.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.video.model.Rental;
import com.video.model.User;
import com.video.model.Video;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByUserAndReturnedAtIsNull(User user);

    Optional<Rental> findByUserAndVideoAndReturnedAtIsNull(User user, Video video);
}

