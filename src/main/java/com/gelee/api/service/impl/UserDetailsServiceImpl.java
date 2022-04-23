package com.gelee.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gelee.api.model.User;
import com.gelee.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Clase  service que maneja funcionalidad de autenticacion y registro de usuario 
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	
	UserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository =  userRepository;
	}
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
}
