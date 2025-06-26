package com.video.service;

import java.util.List;

import com.video.model.Rental;

public interface RentalService {

	void rentVideo(String name, Long videoId) throws Exception;

	void returnVideo(String name, Long videoId) throws Exception;

	List<Rental> getActiveRentals(String name) throws Exception;

}
