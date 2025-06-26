package com.video.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.video.dto.AuthRequest;
import com.video.dto.AuthResponse;
import com.video.dto.RegisterRequest;
import com.video.model.Role;
import com.video.model.User;
import com.video.repository.UserRepository;
import com.video.security.JwtTokenProvider;
import com.video.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
    private UserRepository userRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private JwtTokenProvider jwtTokenProvider;
	@Autowired
    private AuthenticationManager authenticationManager;

    public void register(RegisterRequest request) throws Exception {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new Exception("Email already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(request.getRole() == null ? Role.CUSTOMER : request.getRole());


        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) throws Exception {
        // Authenticate with email and password
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException ex) {
            throw new Exception("Invalid email/password supplied");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new Exception("User not found"));

        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(user, token);
    }
}

