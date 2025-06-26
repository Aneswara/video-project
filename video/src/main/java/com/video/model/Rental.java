package com.video.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;

/**
 * 
 * @author Aneswara reddy
 *
 */
@Entity
@Table(name = "rentals")
@Builder
public class Rental {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Video video;

    private LocalDateTime rentedAt;

    private LocalDateTime returnedAt;  // null if not returned yet

    public boolean isActive() {
        return returnedAt == null;
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public LocalDateTime getRentedAt() {
		return rentedAt;
	}
	public void setRentedAt(LocalDateTime rentedAt) {
		this.rentedAt = rentedAt;
	}
	
}


