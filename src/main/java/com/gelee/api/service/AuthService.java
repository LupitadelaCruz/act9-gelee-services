package com.gelee.api.service;

import org.springframework.http.ResponseEntity;

import com.gelee.api.dto.request.LoginRequest;
import com.gelee.api.dto.request.SignupRequest;

public interface AuthService {

	ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
	
	ResponseEntity<?> registerUser(SignupRequest signUpRequest);
}
