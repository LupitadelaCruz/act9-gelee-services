package com.gelee.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gelee.api.util.Constants;

/**
 * Clase Controller solo permite probar spring security y roles.
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(Constants.BASE_PATH)
public class TestController {
	
	@GetMapping("/all")
	  public String allAccess() {
	    return "Public Content.";
	  }

	  @GetMapping("/user")
	  @PreAuthorize("hasRole('USER') or hasRole('OPERATION') or hasRole('ADMIN')")
	  public String userAccess() {
	    return "User Content.";
	  }

	  @GetMapping("/ope")
	  @PreAuthorize("hasRole('OPERATION')")
	  public String moderatorAccess() {
	    return "Moderator Board.";
	  }

	  @GetMapping("/admin")
	  @PreAuthorize("hasRole('ADMIN')")
	  public String adminAccess() {
	    return "Admin Board.";
	  }

}
