package com.video.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.video.model.Role;
import com.video.model.User;
import com.video.repository.UserRepository;
import com.video.security.SecurityConfig;

@Service
public class UserServiceImpl {


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecurityConfig securityConfig;
	
	public void register(User dto) {
		if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists.");
		}

		User user = new User();
		user.setEmail(dto.getEmail());
		user.setPassword(securityConfig.passwordEncoder().encode(dto.getPassword()));
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setRole(dto.getRole() != null ? dto.getRole() : Role.CUSTOMER);

		userRepository.save(user);
	}
	
}

