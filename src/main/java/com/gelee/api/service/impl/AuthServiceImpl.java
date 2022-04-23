package com.gelee.api.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gelee.api.config.jwt.JwtUtils;
import com.gelee.api.dto.request.LoginRequest;
import com.gelee.api.dto.request.SignupRequest;
import com.gelee.api.dto.response.JwtResponse;
import com.gelee.api.dto.response.MessageResponse;
import com.gelee.api.exceptions.NoDataFoundException;
import com.gelee.api.model.ERole;
import com.gelee.api.model.Role;
import com.gelee.api.model.User;
import com.gelee.api.repository.RoleRepository;
import com.gelee.api.repository.UserRepository;
import com.gelee.api.service.AuthService;

import lombok.RequiredArgsConstructor;

/**
 * Clase  service que maneja funcionalidad de autenticacion y registro de usuario 
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Override
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));

	}

	@Override
	public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Este nombre de usuario ya existe!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email ya existe!"));
		}

		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new NoDataFoundException("Error: Rol no encontrado."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new NoDataFoundException("Error: Rol no encontrado."));
					roles.add(adminRole);

					break;
				case "ope":
					Role modRole = roleRepository.findByName(ERole.ROLE_OPERATION)
							.orElseThrow(() -> new NoDataFoundException("Error: Rol no encontrado."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new NoDataFoundException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Usuario registrad@ exitosamente!"));
	}

}
