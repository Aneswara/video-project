package com.video.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.video.model.Rental;
import com.video.service.RentalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {

	@Autowired
    private RentalService rentalService;

    @PostMapping("/videos/{videoId}/rent")
    public ResponseEntity<String> rentVideo(@PathVariable Long videoId,
                                            Authentication authentication) throws Exception {
        rentalService.rentVideo(authentication.getName(), videoId);
        return ResponseEntity.ok("Video rented successfully");
    }

    @PostMapping("/videos/{videoId}/return")
    public ResponseEntity<String> returnVideo(@PathVariable Long videoId,
                                              Authentication authentication) throws Exception {
        rentalService.returnVideo(authentication.getName(), videoId);
        return ResponseEntity.ok("Video returned successfully");
    }

    @GetMapping("/my")
    public ResponseEntity<List<Rental>> getMyActiveRentals(Authentication authentication) throws Exception {
        return ResponseEntity.ok(rentalService.getActiveRentals(authentication.getName()));
    }
}
