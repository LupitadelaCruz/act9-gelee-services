package com.gelee.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gelee.api.config.jwt.AuthEntryPointJwt;
import com.gelee.api.config.jwt.AuthTokenFilter;
import com.gelee.api.service.impl.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

/**
 * Clase que contiene adaptadores para manejo de recursos accesibles por spring security
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(		
		prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	UserDetailsServiceImpl userDetailsService;
	
	private AuthEntryPointJwt unauthorizedHandler;

	@Autowired
	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService,
			AuthEntryPointJwt unauthorizedHandler) {
		this.userDetailsService =  userDetailsService;
		this.unauthorizedHandler = unauthorizedHandler;
		
	}
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/v3/api-docs/**").permitAll()
				.antMatchers("/swagger-ui/**").permitAll()
				.antMatchers("/actuator/**").permitAll()
				.antMatchers("/api/public/v1/gelee/auth/signup/**").permitAll().antMatchers("/api/public/v1/gelee/**").permitAll().anyRequest()
				.authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
