package com.video.service;

import com.video.dto.AuthRequest;
import com.video.dto.AuthResponse;
import com.video.dto.RegisterRequest;

public interface AuthService {

	 public void register(RegisterRequest request) throws Exception;
	 
	 public AuthResponse login(AuthRequest request) throws Exception;
}
