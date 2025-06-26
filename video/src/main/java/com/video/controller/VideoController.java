package com.video.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.video.model.Video;
import com.video.service.VideoService;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addVideo(@RequestBody Video video) {
        return ResponseEntity.ok(videoService.saveVideo(video));
    }


    @GetMapping
    public ResponseEntity<List<Video>> findAll() {
        return ResponseEntity.ok(videoService.findAll());
    }

    @GetMapping("/available")
    public ResponseEntity<List<Video>> getAvailableVideos() {
        return ResponseEntity.ok(videoService.findAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Video> createVideo(@RequestBody Video Video) {
        return ResponseEntity.ok(videoService.saveVideo(Video));
    }

    @PutMapping("/{videoId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Video> updateVideo(@PathVariable Long videoId,
                                                @RequestBody Video Video) {
        return ResponseEntity.ok(videoService.updateVideo(videoId, Video));
    }

    @DeleteMapping("/{videoId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteVideo(@PathVariable Long videoId) throws Exception {
        videoService.deleteVideo(videoId);
        return ResponseEntity.ok("Video deleted successfully");
    }
}

