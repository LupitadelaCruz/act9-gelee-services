package com.gelee.api.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Clase dto  LoginRequest
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

	@NotBlank
	private String username;

	@NotBlank
	private String password;

}
