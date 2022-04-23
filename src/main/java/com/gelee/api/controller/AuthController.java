package com.gelee.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gelee.api.dto.request.LoginRequest;
import com.gelee.api.dto.request.SignupRequest;
import com.gelee.api.service.AuthService;
import com.gelee.api.util.Constants;

/**
 * Clase Controller para el manejo de seguridad en api: Registra usuario y logea un usuario
 * Permite generar un bearer token.
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(Constants.BASE_PATH)
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/auth/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		return authService.authenticateUser(loginRequest);
	}

	@PostMapping("/auth/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return authService.registerUser(signUpRequest);
	}
}
