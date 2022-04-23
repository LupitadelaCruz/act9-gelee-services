package com.gelee.api.exceptions;

import java.util.Collections;
import java.util.List;

import com.gelee.api.util.SpecialCharacterConstants;

import lombok.Setter;

/**
 * Clase  custom ErrorResponse dto
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Setter
public class ErrorResponse {
	
	  /**
	   * Dato generico de la respuesta.
	   */
	  private String status;

	  /**
	   * Dato generico de la respuesta.
	   */  
	  private List<String> errors;
	  

	  public String getStatus() {
	    return status != null ? status : SpecialCharacterConstants.EMPTY_STRING;
	    
	  }

	  public List<String> getErrors() {
	    return errors != null ? errors : Collections.emptyList();
	  }

}
